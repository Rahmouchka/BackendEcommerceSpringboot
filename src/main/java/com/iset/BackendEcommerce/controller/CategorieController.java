package com.iset.BackendEcommerce.controller;

import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.services.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    private ICategorieService categorieService;

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        try {
            if (categorieService.existsByDesignant(categorie.getDesignant())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Categorie savedCategorie = categorieService.saveCategorie(categorie);
            return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
