package com.season.controllers;

import com.season.entities.Theme;
import com.season.repositories.ThemeRepository;
import com.season.services.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor

public class ThemeController {

    @Autowired
    private ThemeRepository themeRepository;
    @GetMapping
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

}