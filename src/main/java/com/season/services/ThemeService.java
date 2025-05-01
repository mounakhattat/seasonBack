package com.season.services;

import com.season.entities.Theme;
import com.season.iservices.IThemeService;
import com.season.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService implements IThemeService {
    @Autowired
    ThemeRepository themeRepository;

    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme getDefaultTheme() {
        return themeRepository.findByIsDefaultTrue()
                .orElseThrow(() -> new IllegalArgumentException("No default theme found"));
    }
}
