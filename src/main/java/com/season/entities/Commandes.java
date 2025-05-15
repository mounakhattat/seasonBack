package com.season.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Commandes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
=======

>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b

    @ManyToMany
    @JoinTable(
            name = "produit_commande",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produits> produits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

=======
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
    public List<Produits> getProduits() {
        return produits;
    }

    public void setProduits(List<Produits> produits) {
        this.produits = produits;
    }

}