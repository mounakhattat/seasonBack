package com.season.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.security.core.Transient;

@Entity

public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(columnDefinition = "TEXT")
    @JsonRawValue
    private String customizations; // JSON pour stocker les personnalisations (Navbar, Header, Footer)

    @OneToOne(mappedBy = "configuration")
<<<<<<< HEAD
    @JsonIgnore
=======
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
    @JoinColumn(name = "store_id")
    private Store store;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

<<<<<<< HEAD
    public String getCustomizations() { return customizations; }
    public void setCustomizations(String customizations) { this.customizations = customizations; }
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
=======
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
}