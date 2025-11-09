package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.entities.Produit;

import java.util.List;

public interface IProduitService {
    Produit saveProduit(Produit produit);
    Produit updateProduit(Long id, Produit produit);
    void deleteProduit(Long id);
    Produit getProduitById(Long id);
    List<Produit> getAllProduits();

}
