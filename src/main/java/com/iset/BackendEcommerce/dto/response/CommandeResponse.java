package com.iset.BackendEcommerce.dto.response;

import com.iset.BackendEcommerce.entities.ModePaiement;
import com.iset.BackendEcommerce.entities.StatutCommande;

import java.time.LocalDateTime;
import java.util.List;

public record CommandeResponse(
        Long id,
        String numeroCommande,
        LocalDateTime dateCommande,
        double montantTotal,
        StatutCommande statut,
        ModePaiement modePaiement,
        List<LigneCommandeResponse> lignes
) {}
