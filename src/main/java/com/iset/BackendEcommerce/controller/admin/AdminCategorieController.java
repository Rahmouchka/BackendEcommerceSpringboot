package com.iset.BackendEcommerce.controller.admin;

import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminCategorieController {

    private final CategorieService categorieService;

    @PostMapping
    public ResponseEntity<Categorie> create(@RequestParam String nom) {
        return ResponseEntity.ok(categorieService.createCategorie(nom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.ok("Catégorie supprimée");
    }
}
