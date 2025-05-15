package com.season.controllers;

import com.season.entities.Store;
import com.season.entities.Theme;
import com.season.entities.ThemeConfigurationDTO;
import com.season.exceptions.ResourceNotFoundException;
import com.season.repositories.IStoreRepository;
import com.season.repositories.ThemeRepository;
import com.season.services.StoreService;
import com.season.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private IStoreRepository storeRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ThemeService themeService;

    @GetMapping("/user")
    public List<Store> getUserStores() {
        return storeService.getUserStores();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long id) {
        Optional<Store> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }
    @PutMapping("/{storeId}/themes/{themeId}/activate")
    public ResponseEntity<Store> activateTheme(
            @PathVariable("storeId") Long storeId,  // Add explicit name
            @PathVariable("themeId") Long themeId   // Add explicit name
    ) {
        Store updatedStore = themeService.updateStoreTheme(storeId, themeId);
        return ResponseEntity.ok(updatedStore);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store store) {
        Optional<Store> updatedStore = storeService.updateStore(id, store);
        return updatedStore.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{storeId}/themes/available")
    public ResponseEntity<List<Theme>> getAvailableThemes(@PathVariable("storeId") Long storeId) {
        List<Theme> themes = themeService.getAvailableThemes(storeId);
        return ResponseEntity.ok(themes);
    }

    @GetMapping("/{storeId}/theme")
    public ResponseEntity<Theme> getActiveTheme(@PathVariable("storeId") Long storeId) {
        Theme theme = themeService.getThemeForStore(storeId);
        return ResponseEntity.ok(theme);
    }

    @PutMapping("/{storeId}/theme/customize")
    public ResponseEntity<Store> customizeTheme(
            @PathVariable("storeId") Long storeId,  // Add explicit name
            @RequestBody Theme customization
    ) {
        Store updatedStore = themeService.updateThemeCustomization(storeId, customization);
        return ResponseEntity.ok(updatedStore);
    }


    @RestController
    @RequestMapping("/api/stores")
    public class ThemeConfigurationController {

        @PostMapping("/{storeId}/themes/{themeId}/configuration")
        public ResponseEntity<Store> saveThemeConfiguration(@PathVariable Long storeId,
                                                            @PathVariable Long themeId,
                                                            @RequestBody ThemeConfigurationDTO configuration) {
            Store updated = storeService.saveThemeConfiguration(storeId, themeId, configuration);
            return ResponseEntity.ok(updated);
        }
    }
}