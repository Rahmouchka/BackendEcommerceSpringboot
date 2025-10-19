package com.iset.BackendEcommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategorie;
    @Column
    private String designant;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public long getIdCategorie() {
        return idCategorie;
    }

    public String getDesignant() {
        return designant;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setIdCategorie(long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setDesignant(String designant) {
        this.designant = designant;
    }
}
