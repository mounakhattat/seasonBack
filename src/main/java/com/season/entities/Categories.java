package com.season.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produits produits;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<SousCategories> sousCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
    }

    public List<SousCategories> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<SousCategories> sousCategories) {
        this.sousCategories = sousCategories;
    }
}