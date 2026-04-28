package com.example.conflicttracker.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.conflicttracker.dto.FactionCreateDTO;
import com.example.conflicttracker.dto.FactionDTO;
import com.example.conflicttracker.model.Faction;
import com.example.conflicttracker.service.ConflictService;
import com.example.conflicttracker.service.CountryService;
import com.example.conflicttracker.service.FactionService;

@RestController
@RequestMapping("/api/v1/factions")
@CrossOrigin(origins = "https://conflicttraker-frontend-adrian-luqu.vercel.app")
public class FactionController {

    private final FactionService servicioFaction;
    private final ConflictService servicioConflicto;
    private final CountryService servicioPais;

    public FactionController(FactionService servicioFaction, ConflictService servicioConflicto, CountryService servicioPais) {
        this.servicioFaction = servicioFaction;
        this.servicioConflicto = servicioConflicto;
        this.servicioPais = servicioPais;
    }

    // GET /api/v1/factions
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(servicioFaction.obtenerTodos().stream().map(this::convertirADTO).collect(Collectors.toList()));
    }

    // GET /api/v1/factions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> obtenerPorId(@PathVariable Long id) {
        return servicioFaction.obtenerPorId(id)
                .map(faction -> ResponseEntity.ok(convertirADTO(faction)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/factions
    @PostMapping
    public ResponseEntity<FactionDTO> crear(@RequestBody FactionCreateDTO dto) {
        Faction faction = new Faction();
        faction.setNombre(dto.getNombre());

        servicioConflicto.obtenerPorId(dto.getConflictoId()).ifPresent(faction::setConflicto);

        if (dto.getCodigosPaises() != null) {
            faction.setPaisesApoyados(dto.getCodigosPaises().stream()
                    .map(c -> servicioPais.obtenerPorCodigo(c).orElse(null))
                    .filter(p -> p != null)
                    .collect(Collectors.toList()));
        }

        Faction guardado = servicioFaction.guardar(faction);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(guardado));
    }

    // PUT /api/v1/factions/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> actualizar(@PathVariable Long id, @RequestBody FactionCreateDTO dto) {
        return servicioFaction.obtenerPorId(id)
                .map(faction -> {
                    faction.setNombre(dto.getNombre());
                    servicioConflicto.obtenerPorId(dto.getConflictoId()).ifPresent(faction::setConflicto);

                    if (dto.getCodigosPaises() != null) {
                        faction.setPaisesApoyados(dto.getCodigosPaises().stream()
                                .map(c -> servicioPais.obtenerPorCodigo(c).orElse(null))
                                .filter(p -> p != null)
                                .collect(Collectors.toList()));
                    }

                    Faction actualizado = servicioFaction.guardar(faction);
                    return ResponseEntity.ok(convertirADTO(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/factions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioFaction.obtenerPorId(id).isPresent()) {
            servicioFaction.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Convertir entidad a DTO
    private FactionDTO convertirADTO(Faction faction) {
        FactionDTO dto = new FactionDTO();
        dto.setId(faction.getId());
        dto.setNombre(faction.getNombre());
        dto.setConflictoId(faction.getConflicto() != null ? faction.getConflicto().getId() : null);
        dto.setPaisesApoyados(faction.getPaisesApoyados().stream().map(p -> p.getNombre()).collect(Collectors.toList()));
        return dto;
    }
}
