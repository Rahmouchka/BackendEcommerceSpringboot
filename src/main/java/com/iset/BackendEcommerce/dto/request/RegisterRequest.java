package com.iset.BackendEcommerce.dto.request;

public record RegisterRequest(
        String nom,
        String prenom,
        String email,
        String password,
        String telephone,
        String adresse
) {}