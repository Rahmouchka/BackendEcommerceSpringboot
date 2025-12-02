package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dto.response.UserResponse;
import java.util.List;

public interface UserAdminService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    List<UserResponse> getTopClients(int limit);  // LES MEILLEURS CLIENTS
}