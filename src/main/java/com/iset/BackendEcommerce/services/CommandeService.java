package com.iset.BackendEcommerce.services;

import com.iset.BackendEcommerce.dto.response.CommandeResponse;
import com.iset.BackendEcommerce.entities.Commande;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommandeService {
    CommandeResponse passerCommande(Authentication authentication);
    List<CommandeResponse> getMesCommandes(Authentication auth);
    List<CommandeResponse> getAllCommandes();
}
