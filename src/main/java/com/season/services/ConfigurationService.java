package com.season.services;

import com.season.entities.Configuration;
import com.season.entities.Store;
import com.season.repositories.ConfigurationRepository;
import com.season.repositories.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private IStoreRepository storeRepository;

    public Configuration getConfigurationByStoreId(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            return null;
        }
        return configurationRepository.findByStore(store).orElse(null);
    }

    public Configuration saveConfiguration(Configuration config) {
        return configurationRepository.save(config);
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Boutique not found"));
    }
}