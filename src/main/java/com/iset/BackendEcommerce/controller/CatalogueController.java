package com.iset.BackendEcommerce.controller;

import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.entities.Produit;
import com.iset.BackendEcommerce.services.ICategorieService;
import com.iset.BackendEcommerce.services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
// au lieu de * on met http//localhost et le num de port qu'on va accepter (coté sécurité)
public class CatalogueController {

    @Autowired
    private IProduitService produitService;
    @Autowired
    private ICategorieService categorieService;

    @GetMapping("/produits")
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/produits/{id}")
    public Produit getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @PostMapping("/produits")
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/produits/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    @DeleteMapping("/produits/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Categorie getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }

    @PostMapping("/categories")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.saveCategorie(categorie);
    }

    @PutMapping("/categories/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        return categorieService.updateCategorie(id, categorie);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
