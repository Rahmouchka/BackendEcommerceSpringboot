package com.iset.BackendEcommerce.services.impl;

import com.iset.BackendEcommerce.dao.*;
import com.iset.BackendEcommerce.dto.response.*;
import com.iset.BackendEcommerce.entities.*;
import com.iset.BackendEcommerce.services.CommandeService;
import com.iset.BackendEcommerce.services.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ProduitRepository produitRepository;
    private final UserRepository userRepository;
    private final PanierService panierService;

    @Override
    @Transactional
    public CommandeResponse passerCommande(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        var panier = panierService.getPanier();
        if (panier.items().isEmpty()) {
            throw new RuntimeException("Le panier est vide");
        }

        Commande commande = new Commande();
        commande.setUser(user);
        commande.setMontantTotal(panier.total().doubleValue());
        commande.setStatut(StatutCommande.PAYEE);
        commande.setModePaiement(ModePaiement.CARTE);
        final Commande finalCommande = commandeRepository.save(commande);

        var lignes = panier.items().stream().map(item -> {
            LigneCommande ligne = new LigneCommande();
            ligne.setCommande(finalCommande);
            ligne.setProduit(produitRepository.findById(item.produitId()).get());
            ligne.setQuantite(item.quantite());
            ligne.setPrixUnitaire(item.prixUnitaire());
            return ligneCommandeRepository.save(ligne);
        }).toList();

        panierService.viderPanier();

        return toDto(commande, lignes);
    }

    @Override
    public List<CommandeResponse> getMesCommandes(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName()).orElseThrow();
        return commandeRepository.findByUserId(user.getId()).stream()
                .map(c -> toDto(c, ligneCommandeRepository.findByCommandeId(c.getId())))
                .toList();
    }

    @Override
    public List<CommandeResponse> getAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(c -> toDto(c, ligneCommandeRepository.findByCommandeId(c.getId())))
                .toList();
    }

    private CommandeResponse toDto(Commande commande, List<LigneCommande> lignes) {
        var lignesDto = lignes.stream()
                .map(l -> new LigneCommandeResponse(
                        l.getProduit().getId(),
                        l.getProduit().getNom(),
                        l.getPrixUnitaire(),
                        l.getQuantite(),
                        l.getPrixUnitaire() * l.getQuantite()
                ))
                .toList();

        return new CommandeResponse(
                commande.getId(),
                commande.getNumero(),
                commande.getDateCommande(),
                commande.getMontantTotal(),
                commande.getStatut(),
                commande.getModePaiement(),
                lignesDto
        );
    }
}