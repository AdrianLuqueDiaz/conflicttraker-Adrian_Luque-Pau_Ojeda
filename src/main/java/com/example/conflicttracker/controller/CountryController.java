package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.CountryDTO;
import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService servicioPais;

    public CountryController(CountryService servicioPais) {
        this.servicioPais = servicioPais;
    }

    // GET /api/v1/countries
    @GetMapping
    public List<CountryDTO> obtenerTodos() {
        return servicioPais.obtenerTodos().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    // GET /api/v1/countries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> obtenerPorId(@PathVariable Long id) {
        return servicioPais.obtenerPorId(id)
                .map(pais -> ResponseEntity.ok(convertirADTO(pais)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/countries
    @PostMapping
    public ResponseEntity<CountryDTO> crear(@RequestBody CountryDTO dto) {
        Country pais = new Country();
        pais.setNombre(dto.getNombre());
        pais.setCodigo(dto.getCodigo());
        Country guardado = servicioPais.guardar(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(guardado));
    }

    // PUT /api/v1/countries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> actualizar(@PathVariable Long id, @RequestBody CountryDTO dto) {
        return servicioPais.obtenerPorId(id)
                .map(pais -> {
                    pais.setNombre(dto.getNombre());
                    pais.setCodigo(dto.getCodigo());
                    Country actualizado = servicioPais.guardar(pais);
                    return ResponseEntity.ok(convertirADTO(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/countries/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioPais.obtenerPorId(id).isPresent()) {
            servicioPais.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Convertir entidad a DTO
    private CountryDTO convertirADTO(Country pais) {
        CountryDTO dto = new CountryDTO();
        dto.setId(pais.getId());
        dto.setNombre(pais.getNombre());
        dto.setCodigo(pais.getCodigo());
        return dto;
    }
}
