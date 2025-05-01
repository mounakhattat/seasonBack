package com.season.iservices;

import com.season.entities.Theme;

import java.util.List;

public interface IThemeService {
    Theme createTheme(Theme theme);
    List<Theme> getAllThemes();
    Theme getDefaultTheme();
}
