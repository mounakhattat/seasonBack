package com.season.repositories;

import com.season.entities.Store;
import com.season.entities.Theme;
import com.season.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IStoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByUserId(Long userId);

    @Query("SELECT s.theme FROM Store s WHERE s.id = :storeId AND s.theme.active = true")
    Theme findActiveThemeByStoreId(@Param("storeId") Long storeId);

    @Modifying
    @Query("UPDATE Store s SET s.theme = :theme WHERE s.id = :storeId")
    void updateActiveTheme(@Param("storeId") Long storeId, @Param("theme") Theme theme);

}

