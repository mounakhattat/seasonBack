package com.season.iservices;

import com.season.entities.Store;
import com.season.entities.User;

import java.util.List;

public interface IStoreService {
        Store createSite(Store boutique, User user);
        List<Store> getUserSites(User user);
    Store updateSite(Long siteId, Store boutique, User user);
        void deleteSite(Long siteId, User user);
    Store publishSite(Long siteId, User user);
    List<Store> getPublishedSites();
}