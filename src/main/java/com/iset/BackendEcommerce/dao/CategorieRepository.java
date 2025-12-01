package com.iset.BackendEcommerce.dao;

import com.iset.BackendEcommerce.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {


    Optional<Categorie> findByNom(String nom);

    boolean existsByNom(String nom);

}
