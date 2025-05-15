package com.season.entities;

import jakarta.persistence.*;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String previewImage;
    private boolean active = false;
    private String primaryColor;
    private String fontFamily;
    private String type;
    private boolean isDefault = false;
    private String previewBannerImage;
    private String previewLogoImage;
    @Column(name = "is_custom")
    private boolean  isCustom;

    // Getters et Setters

    public boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(boolean custom) {
        isCustom = custom;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPreviewImage() { return previewImage; }
    public void setPreviewImage(String previewImage) { this.previewImage = previewImage; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean isDefault) { this.isDefault = isDefault; }
    public String getPreviewBannerImage() { return previewBannerImage; }
    public void setPreviewBannerImage(String previewBannerImage) { this.previewBannerImage = previewBannerImage; }
    public String getPreviewLogoImage() { return previewLogoImage; }
    public void setPreviewLogoImage(String previewLogoImage) { this.previewLogoImage = previewLogoImage; }

}