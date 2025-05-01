package com.season.controllers;

import com.season.entities.Site;
import com.season.entities.User;
import com.season.repositories.SiteRepository;
import com.season.jwt.JwtService;
import com.season.services.SiteService;
import com.season.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    @Autowired
    SiteService siteService;



    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Site createdSite = siteService.createSite(site, user);
        return ResponseEntity.ok(createdSite);
    }

    @GetMapping
    public ResponseEntity<List<Site>> getUserSites(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Site> sites = siteService.getUserSites(user);
        return ResponseEntity.ok(sites);
    }

    @PutMapping("/{siteId}")
    public ResponseEntity<Site> updateSite(@PathVariable Long siteId, @RequestBody Site site, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Site updatedSite = siteService.updateSite(siteId, site, user);
        return ResponseEntity.ok(updatedSite);
    }

    @DeleteMapping("/{siteId}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long siteId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        siteService.deleteSite(siteId, user);
        return ResponseEntity.noContent().build();
    }

}