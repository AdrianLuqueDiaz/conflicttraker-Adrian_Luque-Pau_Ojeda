package com.example.conflicttracker.repository;

import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.EstadoConflicto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    // Buscar todos los conflictos por estado (ACTIVO, CONGELADO, FINALIZADO)
    List<Conflict> findByEstado(EstadoConflicto estado);
}
