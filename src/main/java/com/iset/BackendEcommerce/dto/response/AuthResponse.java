package com.iset.BackendEcommerce.dto.response;

public record AuthResponse(String token, String role, String email) {}