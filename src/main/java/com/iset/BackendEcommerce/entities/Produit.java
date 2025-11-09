package com.iset.BackendEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduit;
    @Column
    private String nom;
    @Column
    private Date dateAchat;
    @Column
    private String description;
    @Column
    private double prixUnitaire;
    @Column
    private float qteStock;
    @ManyToOne
    @JoinColumn(name = "idCategorie",nullable = false)
    @JsonBackReference
    private Categorie categorie;

    public long getIdProduit() {
        return idProduit;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public String getDescription() {
        return description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public float getQteStock() {
        return qteStock;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQteStock(float qteStock) {
        this.qteStock = qteStock;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
