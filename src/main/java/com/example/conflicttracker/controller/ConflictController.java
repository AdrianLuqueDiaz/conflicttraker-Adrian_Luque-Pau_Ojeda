package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.dto.ConflictCreateDTO;
import com.example.conflicttracker.model.Conflict;
import com.example.conflicttracker.model.Country;
import com.example.conflicttracker.model.EstadoConflicto;
import com.example.conflicttracker.service.ConflictService;
import com.example.conflicttracker.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/conflicts")
@CrossOrigin("http://localhost:5173")
public class ConflictController {

    private final ConflictService servicioConflicto;
    private final CountryService servicioPais;

    public ConflictController(ConflictService servicioConflicto, CountryService servicioPais) {
        this.servicioConflicto = servicioConflicto;
        this.servicioPais = servicioPais;
    }

    // GET /api/v1/conflicts
    @GetMapping
    public List<ConflictDTO> obtenerTodos(@RequestParam(required = false) String estado) {
        List<Conflict> conflictos;

        if (estado != null) {
            EstadoConflicto estadoEnum = EstadoConflicto.valueOf(estado.toUpperCase());
            conflictos = servicioConflicto.obtenerPorEstado(estadoEnum);
        } else {
            conflictos = servicioConflicto.obtenerTodos();
        }

        return conflictos.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    // GET /api/v1/conflicts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ConflictDTO> obtenerPorId(@PathVariable Long id) {
        return servicioConflicto.obtenerPorId(id)
                .map(conflict -> ResponseEntity.ok(convertirADTO(conflict)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/conflicts
    @PostMapping
    public ResponseEntity<ConflictDTO> crear(@RequestBody ConflictCreateDTO dto) {
        Conflict conflicto = new Conflict();
        conflicto.setNombre(dto.getNombre());
        conflicto.setFechaInicio(dto.getFechaInicio());
        conflicto.setEstado(EstadoConflicto.valueOf(dto.getEstado().toUpperCase()));
        conflicto.setDescripcion(dto.getDescripcion());

        // Agregar países por código
        if (dto.getCodigosPaises() != null) {
            conflicto.setPaises(
                    dto.getCodigosPaises().stream()
                            .map(codigo -> servicioPais.obtenerPorCodigo(codigo).orElse(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())
            );
        }

        Conflict guardado = servicioConflicto.guardar(conflicto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(guardado));
    }

    // PUT /api/v1/conflicts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ConflictDTO> actualizar(@PathVariable Long id, @RequestBody ConflictCreateDTO dto) {
        return servicioConflicto.obtenerPorId(id)
                .map(conflicto -> {
                    conflicto.setNombre(dto.getNombre());
                    conflicto.setFechaInicio(dto.getFechaInicio());
                    conflicto.setEstado(EstadoConflicto.valueOf(dto.getEstado().toUpperCase()));
                    conflicto.setDescripcion(dto.getDescripcion());

                    // Actualizar países
                    if (dto.getCodigosPaises() != null) {
                        conflicto.setPaises(
                                dto.getCodigosPaises().stream()
                                        .map(codigo -> servicioPais.obtenerPorCodigo(codigo).orElse(null))
                                        .filter(p -> p != null)
                                        .collect(Collectors.toList())
                        );
                    }

                    Conflict actualizado = servicioConflicto.guardar(conflicto);
                    return ResponseEntity.ok(convertirADTO(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/conflicts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioConflicto.obtenerPorId(id).isPresent()) {
            servicioConflicto.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Método interno para convertir entidad a DTO
    private ConflictDTO convertirADTO(Conflict conflicto) {
        ConflictDTO dto = new ConflictDTO();
        dto.setId(conflicto.getId());
        dto.setNombre(conflicto.getNombre());
        dto.setFechaInicio(conflicto.getFechaInicio());
        dto.setEstado(conflicto.getEstado().name());
        dto.setDescripcion(conflicto.getDescripcion());
        dto.setPaises(conflicto.getPaises().stream().map(Country::getNombre).collect(Collectors.toList()));
        return dto;
    }
}
