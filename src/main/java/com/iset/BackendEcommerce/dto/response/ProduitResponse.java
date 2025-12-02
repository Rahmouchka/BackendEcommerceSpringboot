package com.iset.BackendEcommerce.dto.response;

public record ProduitResponse(
        Long id,
        String nom,
        String description,
        double prixUnitaire,
        int stock,
        String imageUrl,
        Long categorieId,
        String categorieNom
) {}
