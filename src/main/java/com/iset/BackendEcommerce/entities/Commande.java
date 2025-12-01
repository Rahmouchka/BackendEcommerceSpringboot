package com.iset.BackendEcommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "commande")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero = "CMD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    @Column
    private LocalDateTime dateCommande = LocalDateTime.now();

    @Column
    private double montantTotal;

    @Enumerated(EnumType.STRING)
    @Column
    private StatutCommande statut = StatutCommande.EN_COURS;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement modePaiement = ModePaiement.CARTE;

    @Column
    private String stripePaymentIntentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignes = new ArrayList<>();
}
