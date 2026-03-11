package com.example.conflicttracker.repository;

import com.example.conflicttracker.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    // Buscar un país por su código (ej: "ESP", "UKR")
    Optional<Country> findByCodigo(String codigo);
}
