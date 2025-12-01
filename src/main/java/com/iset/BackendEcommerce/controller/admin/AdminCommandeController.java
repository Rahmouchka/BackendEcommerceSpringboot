package com.iset.BackendEcommerce.controller.admin;

import com.iset.BackendEcommerce.dto.response.CommandeResponse;
import com.iset.BackendEcommerce.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/commandes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminCommandeController {

    private final CommandeService commandeService;

    @GetMapping
    public ResponseEntity<List<CommandeResponse>> toutesLesCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }
}
