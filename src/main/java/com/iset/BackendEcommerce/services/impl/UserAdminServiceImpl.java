package com.iset.BackendEcommerce.services.impl;

import com.iset.BackendEcommerce.dao.CommandeRepository;
import com.iset.BackendEcommerce.dao.UserRepository;
import com.iset.BackendEcommerce.dto.response.UserResponse;
import com.iset.BackendEcommerce.entities.User;
import com.iset.BackendEcommerce.services.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;
    private final CommandeRepository commandeRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public List<UserResponse> getTopClients(int limit) {
        return commandeRepository.findTopClients(limit).stream()
                .map(row -> {
                    User user = (User) row[0];
                    Long totalCommandes = (Long) row[1];
                    Double totalDepense = row[2] != null ? (Double) row[2] : 0.0;
                    UserResponse dto = toDto(user);
                    // tu peux ajouter totalCommandes et totalDepense si tu veux
                    return dto;
                })
                .toList();
    }

    private UserResponse toDto(User u) {
        return new UserResponse(
                u.getId(),
                u.getEmail(),
                u.getNom(),
                u.getPrenom(),
                u.getTelephone(),
                u.getRole(),
                u.isEnabled()
        );
    }
}