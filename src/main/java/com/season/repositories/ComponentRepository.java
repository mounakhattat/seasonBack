package com.season.repositories;

import com.season.entities.Component;
import com.season.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {
    List<Component> findByPage(Page page);
}
