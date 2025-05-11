package com.season.entities;

import jakarta.persistence.*;

@Entity
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logo;
    private String banner;
    private String contact;
    private String a_propos;

    @OneToOne(mappedBy = "configuration")
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getA_propos() {
        return a_propos;
    }

    public void setA_propos(String a_propos) {
        this.a_propos = a_propos;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
}