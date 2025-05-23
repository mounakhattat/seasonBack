package com.season.repositories;

import com.season.entities.Store;
import com.season.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ThemeRepository extends JpaRepository<Theme, Long> {
        Optional<Theme> findByIsDefaultTrue();


}
