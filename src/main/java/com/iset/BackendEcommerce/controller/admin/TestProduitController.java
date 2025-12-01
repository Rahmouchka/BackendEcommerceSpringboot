package com.iset.BackendEcommerce.controller.admin;

import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.entities.Produit;
import com.iset.BackendEcommerce.dao.CategorieRepository;
import com.iset.BackendEcommerce.dao.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/test")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TestProduitController {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    @PostMapping("/creer-produits")
    public String creerProduits() {
        // On crée une catégorie si elle n'existe pas
        Categorie cat = categorieRepository.findByNom("Vêtements")
                .orElseGet(() -> {
                    Categorie c = new Categorie();
                    c.setNom("Vêtements");
                    return categorieRepository.save(c);
                });

        // 5 produits rapides
        produitRepository.save(new Produit(0, "T-shirt Bleu", "Coton doux", 29.99, 50, "https://via.placeholder.com/300", cat));
        produitRepository.save(new Produit(0, "Jean Slim", "Denim stretch", 69.99, 30, "https://via.placeholder.com/300", cat));
        produitRepository.save(new Produit(0, "Basket Blanche", "Taille 40-44", 89.99, 20, "https://via.placeholder.com/300", cat));
        produitRepository.save(new Produit(0, "Pull Gris", "Laine mérinos", 49.99, 25, "https://via.placeholder.com/300", cat));
        produitRepository.save(new Produit(0, "Chemise Blanche", "Coton égyptien", 59.99, 40, "https://via.placeholder.com/300", cat));

        return "5 produits créés avec succès !";
    }
}