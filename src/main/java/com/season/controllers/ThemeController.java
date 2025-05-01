package com.season.controllers;

import com.season.entities.Theme;
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
     ThemeService themeService;

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme) {
        Theme createdTheme = themeService.createTheme(theme);
        return ResponseEntity.ok(createdTheme);
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes() {
        List<Theme> themes = themeService.getAllThemes();
        return ResponseEntity.ok(themes);
    }

    @GetMapping("/default")
    public ResponseEntity<Theme> getDefaultTheme() {
        Theme defaultTheme = themeService.getDefaultTheme();
        return ResponseEntity.ok(defaultTheme);
    }
}