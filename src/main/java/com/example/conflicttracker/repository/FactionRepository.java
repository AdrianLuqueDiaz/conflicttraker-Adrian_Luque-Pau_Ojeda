package com.example.conflicttracker.repository;

import com.example.conflicttracker.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {

    // Repositorio básico para Faction
}
