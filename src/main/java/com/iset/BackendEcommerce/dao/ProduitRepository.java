package com.iset.BackendEcommerce.dao;

import com.iset.BackendEcommerce.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByCategorieIdCategorie(Long idCategorie);
}
