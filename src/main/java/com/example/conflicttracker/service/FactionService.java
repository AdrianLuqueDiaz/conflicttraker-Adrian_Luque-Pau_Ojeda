package com.example.conflicttracker.service;

import com.example.conflicttracker.model.Faction;
import com.example.conflicttracker.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactionService {

    private final FactionRepository repositorioFaction;

    public FactionService(FactionRepository repositorioFaction) {
        this.repositorioFaction = repositorioFaction;
    }

    // Obtener todas las facciones
    public List<Faction> obtenerTodos() {
        return repositorioFaction.findAll();
    }

    // Obtener facción por id
    public Optional<Faction> obtenerPorId(Long id) {
        return repositorioFaction.findById(id);
    }

    // Guardar o actualizar facción
    public Faction guardar(Faction faction) {
        return repositorioFaction.save(faction);
    }

    // Eliminar facción por id
    public void eliminarPorId(Long id) {
        repositorioFaction.deleteById(id);
    }
}