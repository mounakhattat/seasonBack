package com.season.controllers;


import com.season.entities.Configuration;
import com.season.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores/{storeId}/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @GetMapping
    public ResponseEntity<Configuration> getConfiguration(@PathVariable Long storeId) {
        Configuration config = configurationService.getConfigurationByStoreId(storeId);
        if (config == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(config);
    }

    @PutMapping
    public ResponseEntity<Configuration> saveConfiguration(@PathVariable Long storeId, @RequestBody Configuration config) {
        config.setStore(configurationService.getStoreById(storeId));
        Configuration updatedConfig = configurationService.saveConfiguration(config);
        return ResponseEntity.ok(updatedConfig);
    }
}
