package com.iset.BackendEcommerce.controller.admin;

import com.iset.BackendEcommerce.dto.request.CreateProduitRequest;
import com.iset.BackendEcommerce.dto.response.ProduitResponse;
import com.iset.BackendEcommerce.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/produits")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminProduitController {

    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitResponse> create(@RequestBody CreateProduitRequest request) {
        return ResponseEntity.ok(produitService.createProduit(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponse> update(
            @PathVariable Long id,
            @RequestBody CreateProduitRequest request) {
        return ResponseEntity.ok(produitService.updateProduit(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok("Produit supprimé avec succès");
    }
}
