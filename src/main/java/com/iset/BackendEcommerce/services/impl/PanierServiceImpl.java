package com.iset.BackendEcommerce.services.impl;

import com.iset.BackendEcommerce.dao.ProduitRepository;
import com.iset.BackendEcommerce.dto.response.*;
import com.iset.BackendEcommerce.services.PanierService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private static final String PANIER_KEY = "PANIER_SESSION";

    private final HttpSession session;
    private final ProduitRepository produitRepository;

    private List<PanierItemSession> getItems() {
        List<PanierItemSession> items = (List<PanierItemSession>) session.getAttribute(PANIER_KEY);
        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute(PANIER_KEY, items);
        }
        return items;
    }

    @Override
    public void ajouterProduit(Long produitId, int quantite) {
        var produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        var items = getItems();
        items.removeIf(i -> i.produitId().equals(produitId));
        items.add(new PanierItemSession(
                produitId,
                produit.getNom(),
                BigDecimal.valueOf(produit.getPrixUnitaire()),  // ← conversion double → BigDecimal
                quantite
        ));
    }

    @Override
    public PanierResponse getPanier() {
        var items = getItems();
        var itemDtos = items.stream()
                .map(i -> new PanierItemResponse(
                        i.produitId(),
                        i.nom(),
                        i.prix().doubleValue(),                    // ← BigDecimal → double
                        i.quantite(),
                        i.prix().doubleValue() * i.quantite()      // ← sous-total en double
                ))
                .toList();

        double total = items.stream()
                .mapToDouble(i -> i.prix().doubleValue() * i.quantite())
                .sum();

        return new PanierResponse(itemDtos, BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP),
                items.stream().mapToInt(PanierItemSession::quantite).sum());
    }

    @Override
    public void supprimerProduit(Long produitId) {
        getItems().removeIf(i -> i.produitId().equals(produitId));
    }

    @Override
    public void viderPanier() {
        session.removeAttribute(PANIER_KEY);
    }

    // Classe interne privée → pas exposée
    private record PanierItemSession(Long produitId, String nom, BigDecimal prix, int quantite) {}
}