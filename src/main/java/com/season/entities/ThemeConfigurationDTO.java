package com.season.entities;

import lombok.Data;

import java.util.List;

@Data
public class ThemeConfigurationDTO {
    private String themeName;
    private boolean isCustom;
    private List<ConfigurationBlockDTO> blocks;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public List<ConfigurationBlockDTO> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<ConfigurationBlockDTO> blocks) {
        this.blocks = blocks;
    }
}