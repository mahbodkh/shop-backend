package app.store.service;

import app.store.persistence.domain.Configuration;
import app.store.persistence.repository.ConfigurationRepository;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {


    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Constructor
    public Configuration getConfiguration() {
        return configurationRepository.findAll().get(0);
    }

//    @PreDestroy
//    public void destroyConfiguration() {
//        configurationRepository.deleteAll();
//    }

    public Optional<List<Configuration>> getAllConfiguration() {
        return Optional.of(configurationRepository.findAll());
    }


}
