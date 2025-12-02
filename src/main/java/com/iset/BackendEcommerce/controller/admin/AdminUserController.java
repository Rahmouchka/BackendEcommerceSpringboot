package com.iset.BackendEcommerce.controller.admin;

import com.iset.BackendEcommerce.dto.response.UserResponse;
import com.iset.BackendEcommerce.services.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserAdminService userAdminService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userAdminService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userAdminService.getUserById(id));
    }

    @GetMapping("/top-clients")
    public ResponseEntity<List<UserResponse>> getTopClients() {
        return ResponseEntity.ok(userAdminService.getTopClients(5));
    }
}