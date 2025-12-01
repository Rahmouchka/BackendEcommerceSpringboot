package com.iset.BackendEcommerce.dto.response;

public record PanierItemResponse(
        Long produitId,
        String nom,
        double prixUnitaire,
        int quantite,
        double sousTotal
) {}
