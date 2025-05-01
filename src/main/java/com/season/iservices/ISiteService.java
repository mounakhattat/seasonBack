package com.season.iservices;

import com.season.entities.Site;
import com.season.entities.User;

import java.util.List;

public interface ISiteService {
        Site createSite(Site site, User user);
        List<Site> getUserSites(User user);
        Site updateSite(Long siteId, Site site, User user);
        void deleteSite(Long siteId, User user);
     Site publishSite(Long siteId, User user);
    List<Site> getPublishedSites();
}