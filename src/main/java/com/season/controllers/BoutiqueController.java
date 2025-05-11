package com.season.controllers;

import com.season.entities.Boutique;
import com.season.entities.User;
import com.season.services.BoutiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boutiques")
@RequiredArgsConstructor
public class BoutiqueController {

    @Autowired
    private BoutiqueService boutiqueService;

    // Create
    @PostMapping("/{id}")
    public ResponseEntity<Boutique> createBoutique(@PathVariable Long id,@RequestBody Boutique boutique) {
        Boutique createdBoutique = boutiqueService.createBoutique(id,boutique);
        return ResponseEntity.ok(createdBoutique);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Boutique> getBoutiqueById(@PathVariable Long id) {
        return boutiqueService.getBoutiqueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Boutique>> getAllBoutiques() {
        List<Boutique> boutiques = boutiqueService.getAllBoutiques();
        return ResponseEntity.ok(boutiques);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Boutique> updateBoutique(@PathVariable Long id, @RequestBody Boutique boutique) {
        Boutique updatedBoutique = boutiqueService.updateBoutique(id, boutique);
        return ResponseEntity.ok(updatedBoutique);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoutique(@PathVariable Long id) {
        boutiqueService.deleteBoutique(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{idUser}")
    public List<Boutique> getBoutiqueByUser(@PathVariable Long idUser){
        return boutiqueService.getBoutiquesByUser(idUser);
    }
}