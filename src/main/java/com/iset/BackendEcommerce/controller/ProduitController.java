package com.iset.BackendEcommerce.controller;


import com.iset.BackendEcommerce.entities.Produit;
import com.iset.BackendEcommerce.services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "*")
// au lieu de * on met http//localhost et le num de port qu'on va accepter (coté sécurité)

public class ProduitController {
    @Autowired
    private IProduitService produitService;

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        try {
            if (produit.getCategorie() == null || produit.getCategorie().getIdCategorie() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Produit savedProduit = produitService.saveProduit(produit);
            return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        try {
            List<Produit> produits = produitService.getAllProduits();

            if (produits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(produits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") long id) {
        Produit produit = produitService.getProduit(id);

        if (produit != null) {
            return new ResponseEntity<>(produit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") long id, @RequestBody Produit produit) {
        Produit existingProduit = produitService.getProduit(id);

        if (existingProduit != null) {
            produit.setIdProduit(id);
            Produit updatedProduit = produitService.updateProduit(produit);
            return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
