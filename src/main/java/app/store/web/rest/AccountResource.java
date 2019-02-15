package app.store.web.rest;

import app.store.persistence.domain.User;
import app.store.persistence.repository.UserRepository;
import app.store.secority.SecurityUtils;
import app.store.service.MailService;
import app.store.service.SmsService;
import app.store.service.UserService;
import app.store.service.dto.PasswordChangeDto;
import app.store.service.dto.UserDto;
import app.store.web.rest.error.*;
import app.store.web.rest.vm.KeyAndPasswordVM;
import app.store.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    public AccountResource(UserRepository userRepository, UserService userService, MailService mailService, SmsService smsService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.smsService = smsService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).ifPresent(u -> {
            throw new LoginAlreadyUsedException();
        });
        userRepository.findOneByEmailIgnoreCase(managedUserVM.getEmail()).ifPresent(u -> {
            throw new EmailAlreadyUsedException();
        });
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
    }


    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<User> user = userService.activateRegistration(key);
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
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDto.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> user = userRepository.findOneByLogin(userLogin);
        if (!user.isPresent()) {
            throw new InternalServerErrorException("User could not be found");
        }
        userService.updateUser(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
                userDto.getLangKey(), userDto.getImageUrl());
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
}
