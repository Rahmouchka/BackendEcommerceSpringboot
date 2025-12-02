package com.iset.BackendEcommerce.dto.request;

public record CreateProduitRequest(
        String nom,
        String description,
        double prixUnitaire,
        int stock,
        String imageUrl,
        Long categorieId
) {}
