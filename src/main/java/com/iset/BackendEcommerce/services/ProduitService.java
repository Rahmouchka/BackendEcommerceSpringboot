package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dao.ProduitRepository;
import com.iset.BackendEcommerce.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitService implements IProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
    @Override
    public Produit getProduit(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElse(null);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

}
