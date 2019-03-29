package app.store.web.rest;

import app.store.persistence.domain.User;
import app.store.persistence.repository.UserRepository;
import app.store.secority.JwtResponse;
import app.store.secority.SecurityUtils;
import app.store.secority.jwt.JWTConfigurer;
import app.store.secority.jwt.TokenProvider;
import app.store.service.MailService;
import app.store.service.SmsService;
import app.store.service.UserService;
import app.store.service.dto.PasswordChangeDto;
import app.store.service.dto.UserDto;
import app.store.web.rest.error.*;
import app.store.web.rest.vm.AuthenticateVM;
import app.store.web.rest.vm.KeyAndPasswordVM;
import app.store.web.rest.vm.LoginVM;
import app.store.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class AccountResource {
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;
    private final UserService userService;
    private final MailService mailService;
    private final SmsService smsService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    public AccountResource(UserRepository userRepository, UserService userService, MailService mailService, SmsService smsService, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.smsService = smsService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
//        if (managedUserVM.getLogin() != null)
//            userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).ifPresent(u -> {
//                throw new LoginAlreadyUsedException();
//            });
        if (managedUserVM.getEmail() != null && (new EmailValidator().isValid(managedUserVM.getEmail(), null)))
            userRepository.findOneByEmailIgnoreCase(managedUserVM.getEmail()).ifPresent(u -> {
                throw new EmailAlreadyUsedException();
            });
        if (managedUserVM.getMobile() != null && managedUserVM.getMobile().matches("(^(9)[0-9]{9})"))
            userRepository.findOneByMobile(managedUserVM.getMobile()).ifPresent(u -> {
                throw new MobileAlreadyUsedException();
            });

        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
        if (managedUserVM.getEmail() != null)
            mailService.sendActivationEmail(user);
        if (managedUserVM.getMobile() != null)
            smsService.sendActivationSms(user);
    }


    @PostMapping("/activate")
    @ResponseStatus(HttpStatus.CREATED)
    public void activateAccount(@Valid @RequestBody AuthenticateVM authenticateVM) {
        Optional<User> user = userService.activateRegistration(authenticateVM.getLogin(), authenticateVM.getCode());
        if (!user.isPresent()) {
            throw new InternalServerErrorException("No user was found for this activation key");
        }
    }


    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @GetMapping("/account")
    public UserDto getAccount() {
        return userService.getUserWithAuthorities()
                .map(UserDto::new)
                .orElseThrow(() -> new InternalServerErrorException("User could not be found"));
    }


    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody UserDto userDto) {
        final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));

        if (new EmailValidator().isValid(userLogin, null)) {
            userRepository.findOneByEmailIgnoreCase(userDto.getEmail()).ifPresent(u -> { u.getEmail().equalsIgnoreCase(userLogin);
                throw new EmailAlreadyUsedException();
            });

            Optional<User> userByEmail = userRepository.findOneByEmailIgnoreCase(userLogin);
            if (!userByEmail.isPresent())
                throw new InternalServerErrorException("User could not be found");

            userService.updateUserByEmail(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
                    userDto.getLangKey(), userDto.getImageUrl());
        }
        if (userLogin.matches("(^(9)[0-9]{9})")) {
            userRepository.findOneByMobileAndActivatedTrue(userDto.getMobile()).ifPresent(m -> { m.getMobile().equals(userLogin);
                throw new MobileAlreadyUsedException();
            });
            Optional<User> userByMobile = userRepository.findOneByMobileAndActivatedTrue(userLogin);
            if (!userByMobile.isPresent())
                throw new InternalServerErrorException("User could not be found");

            userService.updateUserByMobile(userDto.getFirstName(), userDto.getLastName(), userDto.getMobile(),
                    userDto.getLangKey(), userDto.getImageUrl());
        }
    }


    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        mailService.sendPasswordResetMail(
                userService.requestPasswordReset(mail)
                        .orElseThrow(EmailNotFoundException::new)
        );
    }

    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<User> user =
                userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new InternalServerErrorException("No user was found for this reset key");
        }
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }


    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> authorize(@Valid @RequestBody LoginVM loginVM) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtResponse(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logoutDo(HttpServletRequest request) {
        String jwt = tokenProvider.resolveToken(request);
        tokenProvider.invalideJwtToken(jwt);
        return "logout";
    }


}
