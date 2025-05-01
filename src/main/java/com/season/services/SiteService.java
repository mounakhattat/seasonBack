package com.season.services;


import com.season.entities.Site;
import com.season.entities.Theme;
import com.season.entities.User;
import com.season.iservices.ISiteService;
import com.season.repositories.SiteRepository;
import com.season.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService implements ISiteService {
    @Autowired
     SiteRepository siteRepository;
    @Autowired
    ThemeRepository themeRepository;

    @Override
    public Site createSite(Site site, User user) {
        if (siteRepository.existsByDomain(site.getDomain())) {
            throw new IllegalArgumentException("Domain already exists");
        }
        site.setUser(user);
        if (site.getTheme() == null) {
            Theme defaultTheme = themeRepository.findByIsDefaultTrue()
                    .orElseThrow(() -> new IllegalArgumentException("No default theme found"));
            site.setTheme(defaultTheme);

    } else {
        Theme theme = themeRepository.findById(site.getTheme().getId())
                .orElseThrow(() -> new IllegalArgumentException("Theme not found"));
        site.setTheme(theme);
    }
        return siteRepository.save(site);
    }

    @Override
    public List<Site> getUserSites(User user) {
        List<Site> sites = siteRepository.findByUser(user);
        // Forcer l'initialisation de Theme pour chaque Site
        sites.forEach(site -> Hibernate.initialize(site.getTheme()));
        return sites;
    }


    @Override
    public Site updateSite(Long siteId, Site site, User user) {
        Site existingSite = siteRepository.findById(siteId)
                .orElseThrow(() -> new IllegalArgumentException("Site not found"));
        if (!existingSite.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        existingSite.setName(site.getName());
        if (!existingSite.getDomain().equals(site.getDomain()) && siteRepository.existsByDomain(site.getDomain())) {
            throw new IllegalArgumentException("Domain already exists");
        }
        existingSite.setDomain(site.getDomain());
        if (site.getTheme() != null) {
            Theme theme = themeRepository.findById(site.getTheme().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Theme not found"));
            existingSite.setTheme(theme);
        }
        Site updatedSite = siteRepository.save(existingSite);
        // Forcer l'initialisation de l'entité Theme
        Hibernate.initialize(updatedSite.getTheme());
        return updatedSite;
    }

    @Override
    public void deleteSite(Long siteId, User user) {
        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new IllegalArgumentException("Site not found"));
        if (!site.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        siteRepository.delete(site);
    }
    @Override
    public Site publishSite(Long siteId, User user) {
        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new IllegalArgumentException("Site not found"));
        if (!site.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        site.setIsPublished(true);
        Site publishedSite = siteRepository.save(site);
        // Forcer l'initialisation de l'entité Theme
        Hibernate.initialize(publishedSite.getTheme());
        return publishedSite;
    }

    @Override
    public List<Site> getPublishedSites() {
        List<Site> sites = siteRepository.findByIsPublishedTrue();
        // Forcer l'initialisation de Theme pour chaque Site
        sites.forEach(site -> Hibernate.initialize(site.getTheme()));
        return sites;
    }
}

