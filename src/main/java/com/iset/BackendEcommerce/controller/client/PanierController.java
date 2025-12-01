package com.iset.BackendEcommerce.controller.client;

import com.iset.BackendEcommerce.dto.response.PanierResponse;
import com.iset.BackendEcommerce.services.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class PanierController {

    private final PanierService panierService;

    @GetMapping
    public ResponseEntity<PanierResponse> getPanier() {
        return ResponseEntity.ok(panierService.getPanier());
    }

    @PostMapping("/add/{produitId}")
    public ResponseEntity<String> ajouterAuPanier(
            @PathVariable Long produitId,
            @RequestParam(defaultValue = "1") int quantite) {

        panierService.ajouterProduit(produitId, quantite);
        return ResponseEntity.ok("Produit ajouté au panier");
    }

    @DeleteMapping("/remove/{produitId}")
    public ResponseEntity<String> supprimerDuPanier(@PathVariable Long produitId) {
        panierService.supprimerProduit(produitId);
        return ResponseEntity.ok("Produit retiré du panier");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> viderPanier() {
        panierService.viderPanier();
        return ResponseEntity.ok("Panier vidé");
    }
}