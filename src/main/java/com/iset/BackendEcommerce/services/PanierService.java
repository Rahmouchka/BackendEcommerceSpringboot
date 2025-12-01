package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dto.response.PanierResponse;

public interface PanierService {
    void ajouterProduit(Long produitId, int quantite);
    void supprimerProduit(Long produitId);
    void viderPanier();
    PanierResponse getPanier();
}
