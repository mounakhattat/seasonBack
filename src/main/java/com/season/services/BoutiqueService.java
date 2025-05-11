package com.season.services;

import com.season.entities.Boutique;
import com.season.entities.Integrations;
import com.season.entities.Theme;
import com.season.entities.User;
import com.season.repositories.BoutiqueRepository;
import com.season.repositories.ThemeRepository;
import com.season.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoutiqueService {

    @Autowired
    private BoutiqueRepository boutiqueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;


    @Transactional
    public Boutique createBoutique(Long id, Boutique boutique) {
        User user=userRepository.findById(id).get();


        if (boutique.getUser() == null) {
            throw new IllegalArgumentException("User is required for boutique creation");
        }

        boutique.setUser(user);
        Theme defaultTheme = themeRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Thème par défaut avec ID 1 introuvable"));
        boutique.setTheme(defaultTheme);
        boutique.setActive(true);
        boutique.setCreatedAt(LocalDateTime.now());
        boutique.setUpdatedAt(LocalDateTime.now());
        return boutiqueRepository.save(boutique);
    }


    public Optional<Boutique> getBoutiqueById(Long id) {
        return boutiqueRepository.findById(id);
    }

    public List<Boutique> getAllBoutiques() {
        return boutiqueRepository.findAll();
    }

    public List<Boutique> getBoutiquesByUser(Long idUser) {
        return boutiqueRepository.findByUserId(idUser);
    }

    // Update
    @Transactional
    public Boutique updateBoutique(Long id, Boutique updatedBoutique) {
        Optional<Boutique> existingBoutique = boutiqueRepository.findById(id);
        if (existingBoutique.isPresent()) {
            Boutique boutique = existingBoutique.get();

            if (updatedBoutique.getNom() != null) {
                boutique.setNom(updatedBoutique.getNom());
            }
            if (updatedBoutique.getActive() != null) {
                boutique.setActive(updatedBoutique.getActive());
            }
            if (updatedBoutique.getTheme() != null) {
                boutique.setTheme(updatedBoutique.getTheme());
            }
            if (updatedBoutique.getIntegrations() != null) {
                boutique.setIntegrations(updatedBoutique.getIntegrations());
            }
            if (updatedBoutique.getConfiguration() != null) {
                boutique.setConfiguration(updatedBoutique.getConfiguration());
            }

            boutique.setUpdatedAt(LocalDateTime.now());
            return boutiqueRepository.save(boutique);
        }
        throw new RuntimeException("Boutique not found with id: " + id);
    }

    // Delete
    @Transactional
    public void deleteBoutique(Long id) {
        if (!boutiqueRepository.existsById(id)) {
            throw new RuntimeException("Boutique not found with id: " + id);
        }
        boutiqueRepository.deleteById(id);
    }
}