package com.example.conflicttracker.service;

import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.EstadoConflicto;
import com.example.conflicttracker.repository.ConflictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConflictService {

    private final ConflictRepository repositorioConflicto;

    public ConflictService(ConflictRepository repositorioConflicto) {
        this.repositorioConflicto = repositorioConflicto;
    }

    // Obtener todos los conflictos
    public List<Conflict> obtenerTodos() {
        return repositorioConflicto.findAll();
    }

    // Obtener conflicto por id
    public Optional<Conflict> obtenerPorId(Long id) {
        return repositorioConflicto.findById(id);
    }

    // Guardar o actualizar un conflicto
    public Conflict guardar(Conflict conflicto) {
        return repositorioConflicto.save(conflicto);
    }

    // Eliminar un conflicto por id
    public void eliminarPorId(Long id) {
        repositorioConflicto.deleteById(id);
    }

    // Buscar conflictos por estado
    public List<Conflict> obtenerPorEstado(EstadoConflicto estado) {
        return repositorioConflicto.findByEstado(estado);
    }
}
