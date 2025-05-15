package com.season.services;

import com.season.entities.Store;
import com.season.entities.Theme;
import com.season.exceptions.ResourceNotFoundException;
import com.season.repositories.IStoreRepository;
import com.season.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private IStoreRepository storeRepository;
    @Autowired
    private ThemeRepository themeRepository;

    public Theme getThemeForStore(Long storeId) {
        return storeRepository.findById(storeId)
                .map(Store::getTheme)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    public List<Theme> getAvailableThemes(Long storeId) {
        return themeRepository.findAll();
    }

    public Optional<Theme> getDefaultTheme() {
        return themeRepository.findById(1L);
    }

    public Store updateStoreTheme(Long storeId, Long themeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new ResourceNotFoundException("Theme not found"));

        // Remove these lines:
        // if (store.getTheme() != null) {
        //     Theme currentTheme = store.getTheme();
        //     currentTheme.setActive(false);
        //     themeRepository.save(currentTheme);
        // }

        // theme.setActive(true);
        // themeRepository.save(theme);

        store.setTheme(theme);
        return storeRepository.save(store);
    }




    public Store updateThemeCustomization(Long storeId, Theme customization) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        // Update store-specific customizations instead of the theme
        store.setCustomizedPrimaryColor(customization.getPrimaryColor());
        store.setCustomizedFontFamily(customization.getFontFamily());
        return storeRepository.save(store);
    }
}