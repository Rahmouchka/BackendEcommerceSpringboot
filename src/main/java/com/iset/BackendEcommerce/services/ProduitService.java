package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dto.request.CreateProduitRequest;
import com.iset.BackendEcommerce.dto.response.ProduitResponse;

import java.util.List;

public interface ProduitService {
    List<ProduitResponse> getAllProduits();
    ProduitResponse getProduitById(Long id);
    ProduitResponse createProduit(CreateProduitRequest request);
    ProduitResponse updateProduit(Long id, CreateProduitRequest request);
    void deleteProduit(Long id);
}