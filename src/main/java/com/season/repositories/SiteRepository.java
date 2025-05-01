package com.season.repositories;


import com.season.entities.Site;
import com.season.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findByUser(User user);
    boolean existsByDomain(String domain);
    List<Site> findByIsPublishedTrue();

}