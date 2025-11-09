package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.entities.Categorie;

import java.util.List;

public interface ICategorieService {
    Categorie saveCategorie(Categorie categorie);
    Categorie updateCategorie(Long id, Categorie categorie);
    void deleteCategorie(Long id);
    Categorie getCategorieById(Long id);
    List<Categorie> getAllCategories();
    Categorie findByDesignant(String designant);
    boolean existsByDesignant(String designant);
}
