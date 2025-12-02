package com.iset.BackendEcommerce.controller.client;

import com.iset.BackendEcommerce.dto.response.ProduitResponse;

import com.iset.BackendEcommerce.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public ResponseEntity<List<ProduitResponse>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponse> getProduit(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }
}
