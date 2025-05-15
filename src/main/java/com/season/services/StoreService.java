package com.season.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.season.entities.*;
import com.season.repositories.ConfigurationRepository;
import com.season.repositories.IStoreRepository;
import com.season.repositories.ThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private IStoreRepository storeRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ThemeService themeService;
    @Autowired
    ConfigurationRepository configurationRepository;

    public Store createStore(Store store) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        store.setUser(user);

        // Attribuer le thème par défaut si aucun n'est spécifié
        if (store.getTheme() == null) {
            Theme defaultTheme = themeService.getDefaultTheme()
                    .orElseThrow(() -> new RuntimeException("Thème par défaut non trouvé"));
            store.setTheme(defaultTheme);
        }

        return storeRepository.save(store);
    }

    public Store updateStoreTheme(Long storeId, Long themeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store non trouvé"));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Thème non trouvé"));

        store.setTheme(theme);
        return storeRepository.save(store);
    }
    public List<Store> getUserStores() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return storeRepository.findByUserId(user.getId());
    }

    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }
    public Optional<Store> updateStore(Long id, Store updatedStore) {
        return storeRepository.findById(id).map(store -> {
            store.setName(updatedStore.getName());
            store.setUrl(updatedStore.getUrl());
            store.setDescription(updatedStore.getDescription());
            store.setActive(updatedStore.isActive());
            return storeRepository.save(store);
        });
    }
    @Transactional
    public Store saveThemeConfiguration(Long storeId, Long themeId, ThemeConfigurationDTO configurationDTO) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
        theme.setIsCustom(true); // Set isCustom to true
        // Convertir ThemeConfigurationDTO en JSON brut
        ObjectMapper objectMapper = new ObjectMapper();
        String customizationsJson;
        try {
            customizationsJson = objectMapper.writeValueAsString(configurationDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion de la configuration en JSON", e);
        }

        Configuration configuration = store.getConfiguration();
        if (configuration == null) {
            configuration = new Configuration();
        }

        configuration.setCustomizations(customizationsJson);
        configuration.setStore(store); // Associer la configuration au store

    Configuration savedConfig = configurationRepository.save(configuration);
    store.setConfiguration(savedConfig);
    storeRepository.save(store);
        store.setTheme(theme);

        return storeRepository.save(store);
    }

}