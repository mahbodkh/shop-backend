package app.store.service;


import app.store.persistence.domain.Authority;
import app.store.persistence.domain.User;
import app.store.persistence.repository.AuthorityRepository;
import app.store.persistence.repository.UserRepository;
import app.store.service.dto.AddressDto;
import app.store.service.dto.UserDto;
import app.store.service.mapper.UserMapper;
import app.store.service.util.RandomUtil;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;


    public UserService(UserRepository userRepository, UserMapper userMapper, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
    }

    public boolean isExists(String userId) {
        return userRepository.existsById(new ObjectId(userId));
    }

    public Optional<User> isMobileExists(Long mobile) {
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
                    user.setLangKey(userDto.getLangKey());
                    user.setGender(userDto.getGender());
                    user.setCardNumber(userDto.getCardNumber());

                    List<AddressDto> addresses = userDto.getAddresses();
                    addresses.clear();
                    addresses.addAll(userDto.getAddresses());

                    Set<Authority> managedAuthorities = user.getAuthorities();
                    managedAuthorities.clear();
                    userDto.getAuthorities().stream()
                            .map(authorityRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);

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

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(user -> {
                    // activate given user for the registration key.
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    userRepository.save(user);
                    return user;
                });
    }

    public Optional<UserDto> requestPasswordReset(Long mobile) {
        return userRepository.findOneByMobile(mobile)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    User save = userRepository.save(user);
                    return userMapper.toDto(save);
                });
    }

//    @Transactional
//    public User loadUserByMobile(String mobile) {
//        User user = userRepository.findOneByMobile(Long.valueOf(mobile))
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User Not Found with -> mobile or email : " + mobile)
//                );
//        return user;
//    }

//    List<GrantedAuthority> authorities = user.getAuthorities().stream().map(role ->
//            new SimpleGrantedAuthority(role.getName())
//    ).collect(Collectors.toList());


    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream()
                .map(Authority::getName).collect(Collectors.toList());
    }


    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }
}
