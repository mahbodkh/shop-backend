package app.store.secority;

import app.store.persistence.domain.User;
import app.store.persistence.repository.UserRepository;
import app.store.secority.error.UserNotActivatedException;
import app.store.service.util.TextHelper;
import app.store.web.rest.error.MobileNotFoundException;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            Optional<User> userByEmailFromDatabase = userRepository.findOneByEmailIgnoreCase(login);
            return userByEmailFromDatabase.map(user -> createSpringSecurityUser(login, user))
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        if (login.matches("(^(9)[0-9]{9})")) {
            Optional<User> userByMobileFromDatabase = userRepository.findOneByMobile(Long.valueOf(TextHelper.changeDigitsToEnglish(login)));
            return userByMobileFromDatabase.map(user -> createSpringSecurityUser(login, user))
                    .orElseThrow(() -> new MobileNotFoundException("User with mobile " + login + "was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userByLoginFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
        return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        if (user.getMobile() != null)
            return new org.springframework.security.core.userdetails.User(user.getMobile().toString(),
                    user.getPassword(),
                    grantedAuthorities);
        if (user.getEmail() != null)
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities);
        if (user.getLogin() != null)
            return new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(),
                    grantedAuthorities);
        return null;
    }


}
