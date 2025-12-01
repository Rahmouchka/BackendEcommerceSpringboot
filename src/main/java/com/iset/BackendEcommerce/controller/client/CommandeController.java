package com.iset.BackendEcommerce.controller.client;

import com.iset.BackendEcommerce.dto.response.CommandeResponse;
import com.iset.BackendEcommerce.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class CommandeController {

    private final CommandeService commandeService;

    @PostMapping("/passer")
    public ResponseEntity<CommandeResponse> passerCommande(Authentication auth) {
        return ResponseEntity.ok(commandeService.passerCommande(auth));
    }

    @GetMapping("/mes-commandes")
    public ResponseEntity<List<CommandeResponse>> mesCommandes(Authentication auth) {
        return ResponseEntity.ok(commandeService.getMesCommandes(auth));
    }
}
