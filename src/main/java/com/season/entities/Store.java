package com.season.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    private String description;
    private boolean active;
    private String customizedPrimaryColor;
    private String customizedFontFamily;
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "configuration_id")
    private Configuration configuration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    @OneToOne
    private Integrations integrations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="store")
    private List<Commandes> commandes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="store")
    private List<Produits> produits;
    @JsonProperty("themeId")
    public Long getThemeId() {
        return theme != null ? theme.getId() : null;
    }


    public String getCustomizedPrimaryColor() { return customizedPrimaryColor; }
    public void setCustomizedPrimaryColor(String customizedPrimaryColor) { this.customizedPrimaryColor = customizedPrimaryColor; }
    public String getCustomizedFontFamily() { return customizedFontFamily; }
    public void setCustomizedFontFamily(String customizedFontFamily) { this.customizedFontFamily = customizedFontFamily; }

<<<<<<< HEAD
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
=======
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
    }

    public Configuration getConfiguration() {
        return configuration;
    }
<<<<<<< HEAD

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
=======
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
}