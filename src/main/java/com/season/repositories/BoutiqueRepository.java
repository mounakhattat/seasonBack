package com.season.repositories;


import com.season.entities.Boutique;
import com.season.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
    @Query("SELECT b FROM Boutique b WHERE b.user.id = :userId")
    List<Boutique> findByUserId(Long userId);
    @Query("SELECT b FROM Boutique b WHERE b.active = true")
    List<Boutique> findByIsPublishedTrue();

}