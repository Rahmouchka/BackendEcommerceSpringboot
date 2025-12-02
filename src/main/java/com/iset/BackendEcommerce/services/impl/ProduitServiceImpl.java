package com.iset.BackendEcommerce.services.impl;

import com.iset.BackendEcommerce.dao.CategorieRepository;
import com.iset.BackendEcommerce.dao.ProduitRepository;
import com.iset.BackendEcommerce.dto.request.CreateProduitRequest;
import com.iset.BackendEcommerce.dto.response.ProduitResponse;
import com.iset.BackendEcommerce.entities.Produit;

import com.iset.BackendEcommerce.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    @Override
    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ProduitResponse getProduitById(Long id) {
        return produitRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    @Override
    @Transactional
    public ProduitResponse createProduit(CreateProduitRequest request) {
        var categorie = categorieRepository.findById(request.categorieId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        Produit p = new Produit();
        p.setNom(request.nom());
        p.setDescription(request.description());
        p.setPrixUnitaire(request.prixUnitaire());
        p.setStock(request.stock());
        p.setImageUrl(request.imageUrl());
        p.setCategorie(categorie);

        return toDto(produitRepository.save(p));
    }

    @Override
    @Transactional
    public ProduitResponse updateProduit(Long id, CreateProduitRequest request) {
        Produit p = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        var categorie = categorieRepository.findById(request.categorieId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        p.setNom(request.nom());
        p.setDescription(request.description());
        p.setPrixUnitaire(request.prixUnitaire());
        p.setStock(request.stock());
        p.setImageUrl(request.imageUrl());
        p.setCategorie(categorie);

        return toDto(produitRepository.save(p));
    }

    @Override
    @Transactional
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    private ProduitResponse toDto(Produit p) {
        return new ProduitResponse(
                p.getId(),
                p.getNom(),
                p.getDescription(),
                p.getPrixUnitaire(),
                p.getStock(),
                p.getImageUrl(),
                p.getCategorie().getId(),
                p.getCategorie().getNom()
        );
    }
}