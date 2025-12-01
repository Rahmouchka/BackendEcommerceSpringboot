package com.iset.BackendEcommerce.dto.response;

public record LigneCommandeResponse(
        Long produitId,
        String nomProduit,
        double prixUnitaire,
        int quantite,
        double sousTotal
) {}
