package com.season.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Integrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pixel_fb;
    private String pixel_tiktok;
    private String nomDomaine;

<<<<<<< HEAD
    @OneToOne(mappedBy = "integrations")
    @JsonIgnore
    @JoinColumn(name = "store_id")
    private Store store;
=======
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPixel_fb() {
        return pixel_fb;
    }

    public void setPixel_fb(String pixel_fb) {
        this.pixel_fb = pixel_fb;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

<<<<<<< HEAD
    public Store getStore() {
        return store;
    }

    public void setStore(Store boutique) {
        this.store= boutique;
    }
=======
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b

    public String getPixel_tiktok() {
        return pixel_tiktok;
    }

    public void setPixel_tiktok(String pixel_tiktok) {
        this.pixel_tiktok = pixel_tiktok;
    }

}
