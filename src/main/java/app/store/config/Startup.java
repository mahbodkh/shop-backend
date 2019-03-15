package app.store.config;

import app.store.persistence.domain.Authority;
import app.store.persistence.domain.Configuration;
import app.store.persistence.domain.User;
import app.store.persistence.repository.ConfigurationRepository;
import app.store.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.*;

@Component
public class Startup implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger log = LoggerFactory.getLogger(Startup.class);

    private final UserRepository userRepository;
    private final ConfigurationRepository configurationRepository;
    private List<User> users = new ArrayList<>();
    private Configuration configuration = null;

    public Startup(UserRepository userRepository, ConfigurationRepository configurationRepository) {
        this.userRepository = userRepository;
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("This is startup class");

        getDefaultConfig();
        getDefaultUser();
    }

    private void getDefaultUser() {
        Authority anonymousAuthority = new Authority();
        Set<Authority> anonymousAuthoritySet = new HashSet<>();

        User anonymous = new User();
        anonymous.setMobile(0000000000L);
        anonymous.setActivated(true);
        //
        anonymousAuthority.setName("ROLE_ANONYMOUS");
        anonymousAuthoritySet.add(anonymousAuthority);
        anonymous.setAuthorities(anonymousAuthoritySet);
        //
        anonymous.setLogin("000");
        anonymous.setLangKey("FA");
        anonymous.setEmail("anonymous@");


        Authority systemAuthority = new Authority();
        Set<Authority> systemAuthoritySet = new HashSet<>();
        User system = new User();
        system.setMobile(1111111111L);
        system.setLogin("001");
        system.setLangKey("EN");
        system.setActivated(true);
        systemAuthority.setName("system");
        systemAuthoritySet.add(systemAuthority);
        system.setAuthorities(systemAuthoritySet);
        system.setEmail("system@");

        Authority adminAuthority = new Authority();
        Set<Authority> adminAuthoritySet = new HashSet<>();

        User admin = new User();
        admin.setLogin("admin");
        admin.setMobile(9121234567L);
        admin.setLangKey("EN");
        adminAuthority.setName("ROLE_ADMIN");
        admin.setActivated(true);
        adminAuthoritySet.add(adminAuthority);
        admin.setAuthorities(adminAuthoritySet);
        admin.setEmail("admin@shop.com");


        log.debug("The Application First System User");
        users = userRepository.saveAll(Arrays.asList(anonymous, system, admin));
    }


    private void getDefaultConfig() {
        Configuration conf = new Configuration();
        conf.setTheme("default");
        conf.setLicense("default");
        conf.setImageSize("2MB");


        log.debug("The Application First Configuration");
        configuration = configurationRepository.save(conf);
    }


    @PreDestroy
    public void destroyUsers() {
        userRepository.deleteAll(users);
        configurationRepository.delete(configuration);
    }


}
