package com.iset.BackendEcommerce.dao;

import com.iset.BackendEcommerce.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUserId(Long userId);

    @Query("""
    SELECT c.user, COUNT(c), SUM(c.montantTotal)
    FROM Commande c
    GROUP BY c.user
    ORDER BY SUM(c.montantTotal) DESC
    """)
    List<Object[]> findTopClients(@Param("limit") int limit);
}
