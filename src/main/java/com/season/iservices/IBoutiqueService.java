package com.season.iservices;

import com.season.entities.Boutique;
import com.season.entities.User;

import java.util.List;

public interface IBoutiqueService {
        Boutique createSite(Boutique boutique, User user);
        List<Boutique> getUserSites(User user);
        Boutique updateSite(Long siteId, Boutique boutique, User user);
        void deleteSite(Long siteId, User user);
     Boutique publishSite(Long siteId, User user);
    List<Boutique> getPublishedSites();
}