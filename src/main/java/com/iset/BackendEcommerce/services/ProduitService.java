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
    public Produit updateProduit(Long id, Produit produit) {
        Produit existing = produitRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNom(produit.getNom());
            existing.setDateAchat(produit.getDateAchat());
            existing.setDescription(produit.getDescription());
            existing.setPrixUnitaire(produit.getPrixUnitaire());
            existing.setQteStock(produit.getQteStock());
            existing.setCategorie(produit.getCategorie());
            return produitRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
    @Override
    public Produit getProduitById(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElse(null);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

}
