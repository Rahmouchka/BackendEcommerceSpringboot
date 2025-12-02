package com.iset.BackendEcommerce.services.impl;

import com.iset.BackendEcommerce.dao.CategorieRepository;
import com.iset.BackendEcommerce.entities.Categorie;
import com.iset.BackendEcommerce.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    @Transactional
    public Categorie createCategorie(String nom) {
        Categorie cat = new Categorie();
        cat.setNom(nom);
        return categorieRepository.save(cat);
    }

    @Override
    @Transactional
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}