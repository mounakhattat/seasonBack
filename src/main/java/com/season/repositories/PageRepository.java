package com.season.repositories;

import com.season.entities.Page;
import com.season.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findBySite(Site site);
}

