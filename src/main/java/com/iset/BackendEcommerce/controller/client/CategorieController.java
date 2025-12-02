package com.iset.BackendEcommerce.controller.client;

import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategorieController {

    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Categorie>> getAll() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }
}
