package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAllCategories();
    Categorie createCategorie(String nom);
    void deleteCategorie(Long id);
}