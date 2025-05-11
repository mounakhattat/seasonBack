package com.season.entities;
import jakarta.persistence.*;

@Entity
public class Integrations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pixel_fb;
    private String pixel_tiktok;
    private String nomDomaine;

    @OneToOne(mappedBy = "integrations")
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

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

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public String getPixel_tiktok() {
        return pixel_tiktok;
    }

    public void setPixel_tiktok(String pixel_tiktok) {
        this.pixel_tiktok = pixel_tiktok;
    }
}
