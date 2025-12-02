package com.iset.BackendEcommerce.dto.response;

import com.iset.BackendEcommerce.entities.Role;

public record UserResponse(
        Long id,
        String email,
        String nom,
        String prenom,
        String telephone,
        Role role,
        boolean enabled
) {}