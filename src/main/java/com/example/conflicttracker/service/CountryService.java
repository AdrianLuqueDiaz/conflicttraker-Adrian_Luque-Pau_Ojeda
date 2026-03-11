package com.example.conflicttracker.service;

import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository repositorioPais;

    public CountryService(CountryRepository repositorioPais) {
        this.repositorioPais = repositorioPais;
    }

    // Obtener todos los países
    public List<Country> obtenerTodos() {
        return repositorioPais.findAll();
    }

    // Obtener país por id
    public Optional<Country> obtenerPorId(Long id) {
        return repositorioPais.findById(id);
    }

    // Obtener país por código
    public Optional<Country> obtenerPorCodigo(String codigo) {
        return repositorioPais.findByCodigo(codigo);
    }

    // Guardar o actualizar país
    public Country guardar(Country pais) {
        return repositorioPais.save(pais);
    }

    // Eliminar país por id
    public void eliminarPorId(Long id) {
        repositorioPais.deleteById(id);
    }
}
