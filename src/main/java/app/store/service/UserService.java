package app.store.service;


import app.store.persistence.domain.Authority;
import app.store.persistence.domain.User;
import app.store.persistence.repository.AuthorityRepository;
import app.store.persistence.repository.UserRepository;
import app.store.secority.AuthoritiesConstants;
import app.store.secority.Constants;
import app.store.secority.SecurityUtils;
import app.store.service.dto.AddressDto;
import app.store.service.dto.UserDto;
import app.store.service.mapper.UserMapper;
import app.store.service.util.RandomUtil;
import app.store.web.rest.error.InvalidPasswordException;
import app.store.web.rest.error.UserNotFoundException;
import org.bson.types.ObjectId;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserMapper userMapper, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isExists(String userId) {
        return userRepository.existsById(new ObjectId(userId));
    }

    public Optional<User> isMobileExists(String mobile) {
        return userRepository.findOneByMobile(mobile);
    }

    public Optional<User> isEmailExists(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    public Optional<UserDto> createUser(UserDto userDto) {
        return Optional.of(userMapper.toEntity(userDto))
                .map(user -> {
                    User result = userRepository.save(user);
                    log.debug("Save Information for User: {}", result);
                    return result;
                }).map(userMapper::toDto);
    }

    public Optional<UserDto> getUserDto(String id) {
        return Optional.of(userRepository
                .findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(userMapper::toDto);
    }

    public Optional<User> getUser(String id) {
        return Optional.of(userRepository
                .findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }


    public Optional<UserDto> updateUser(UserDto userDto, String id) {
        return Optional.of(userRepository.findById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    user.setMobile(userDto.getMobile());
                    user.setFirstName(userDto.getFirstName());
                    user.setLastName(userDto.getLastName());
                    user.setEmail(userDto.getEmail());
                    user.setImageUrl(userDto.getImageUrl());
                    user.setActivated(userDto.isActivated());
                    user.setGender(userDto.getGender());
                    user.setCardNumber(userDto.getCardNumber());

                    if (userDto.getAddresses() != null) {
                        List<AddressDto> addresses = userDto.getAddresses();
                        addresses.clear();
                        addresses.addAll(userDto.getAddresses());
                    }
                    if (userDto.getLangKey() == null) {
                        user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
                    } else {
                        user.setLangKey(userDto.getLangKey());
                    }
                    if (userDto.getAuthorities() != null) {
                        Set<Authority> authorities = userDto.getAuthorities().stream()
                                .map(authorityRepository::findByName)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toSet());
                        user.setAuthorities(authorities);
                    }

                    userRepository.save(user);
                    log.debug("Changed Information for User: {}", user);
                    return user;
                })
                .map(UserDto::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public Optional<User> activateRegistration(String login, String key) {
        log.debug("Activating user for activation key: {} and mobile: {}", key, login);
        return userRepository.findByMobileAndActivationKey(login, key).map(user -> {
            // activate given user for the registration key.
            user.setActivated(true);
            user.setActivationKey(null);
            userRepository.save(user);
            log.debug("Activated user by mobile: {}", user);
            return user;
        });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    User save = userRepository.save(user);
                    log.debug("The password reset(by login): {}", save);
                    return save;
                });
    }

//    public Optional<UserDto> requestPasswordReset(String mobile) {
//        return userRepository.findOneByMobile(mobile)
//                .filter(User::isActivated)
//                .map(user -> {
//                    user.setResetKey(RandomUtil.generateResetKey());
//                    user.setResetDate(Instant.now());
//                    User save = userRepository.save(user);
//                    log.debug("The password reset (by mobile): {}", user);
//                    return save;
//                }).map(userMapper::toDto);
//    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    userRepository.save(user);
                    log.debug("Changed password for User: {}", user);
                });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setPassword(newPassword);
                    user.setResetKey(null);
                    user.setResetDate(null);
                    userRepository.save(user);
                    return user;
                });
    }

    @Transactional
    public User loadUserByMobile(String mobile) {
        User user = userRepository.findOneByMobile(mobile)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> mobile or email : " + mobile)
                );
        return user;
    }

    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream()
                .map(Authority::getName).collect(Collectors.toList());
    }


    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(7, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
        }
    }

    public User registerUser(UserDto userDto, String password) {
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDto.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setImageUrl(userDto.getImageUrl());
        newUser.setMobile(userDto.getMobile());
        newUser.setLangKey(userDto.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findByName(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public Page<UserDto> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, AuthoritiesConstants.ADMIN)
                .map(UserDto::new);
    }

    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByMobile);
    }


    public Optional<UserDto> updateUserByEmail(String firstName, String lastName, String email, String langKey, String imageUrl) {
        return Optional.of(userRepository.findOneByEmailIgnoreCase(email))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    if (firstName != null && !firstName.equals(""))
                        user.setFirstName(firstName);
                    if (lastName != null && !lastName.equals(""))
                        user.setLastName(lastName);
                    if (langKey != null && !langKey.equals(""))
                        user.setLangKey(langKey);
                    if (imageUrl != null && !imageUrl.equals(""))
                        user.setImageUrl(imageUrl);
                    User result = userRepository.save(user);
                    log.debug("Changed Information for User: {}", user);
                    return result;
                }).map(userMapper::toDto);
    }

    public Optional<UserDto> updateUserByMobile(String firstName, String lastName, String mobile, String langKey, String imageUrl) {
        return Optional.of(userRepository.findOneByMobileAndActivatedTrue(mobile))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    if (firstName != null && !firstName.equals(""))
                        user.setFirstName(firstName);
                    if (lastName != null && !lastName.equals(""))
                        user.setLastName(lastName);
                    if (langKey != null && !langKey.equals(""))
                        user.setLangKey(langKey);
                    if (imageUrl != null && !imageUrl.equals(""))
                        user.setImageUrl(imageUrl);
                    User result = userRepository.save(user);
                    log.debug("Changed Information for User: {}", user);
                    return result;
                }).map(userMapper::toDto);
    }

    private static Boolean isUsernameMobileOrEmail(String principle) {
        if (principle.matches("(^(9)[0-9]{9})")) {
            return true;
        } else if (new EmailValidator().isValid(principle, null)) {
            return false;
        } else
            return null;
    }


    public Optional<User> getUsername(String principle) {
        if (principle.matches("(^(9)[0-9]{9})")) {
            return userRepository.findOneByMobileAndActivatedTrue(principle);
        } else if (new EmailValidator().isValid(principle, null)) {
            return userRepository.findOneByEmailIgnoreCase(principle);
        } else
            throw new UserNotFoundException();
    }

    public Optional<User> getUserIdByPrinciple(String username) {
        return null;
    }
}