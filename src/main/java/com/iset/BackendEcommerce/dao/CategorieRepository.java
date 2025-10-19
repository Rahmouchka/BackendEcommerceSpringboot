package com.iset.BackendEcommerce.dao;

import com.iset.BackendEcommerce.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByDesignant(String designant);

    boolean existsByDesignant(String designant);
}
