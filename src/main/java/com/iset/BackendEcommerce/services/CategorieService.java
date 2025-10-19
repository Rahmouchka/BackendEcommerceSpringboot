package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dao.CategorieRepository;
import com.iset.BackendEcommerce.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieService implements ICategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie getCategorie(Long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.orElse(null);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie findByDesignant(String designant) {
        return categorieRepository.findByDesignant(designant);
    }

    @Override
    public boolean existsByDesignant(String designant) {
        return categorieRepository.existsByDesignant(designant);
    }

}
