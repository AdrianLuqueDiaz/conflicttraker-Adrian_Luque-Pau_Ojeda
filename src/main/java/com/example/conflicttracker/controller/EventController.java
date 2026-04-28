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

import com.example.conflicttracker.dto.EventCreateDTO;
import com.example.conflicttracker.dto.EventDTO;
import com.example.conflicttracker.model.Event;
import com.example.conflicttracker.service.ConflictService;
import com.example.conflicttracker.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "https://conflicttraker-frontend-adrian-luqu.vercel.app")
public class EventController {

    private final EventService servicioEvento;
    private final ConflictService servicioConflicto;

    public EventController(EventService servicioEvento, ConflictService servicioConflicto) {
        this.servicioEvento = servicioEvento;
        this.servicioConflicto = servicioConflicto;
    }

    // GET /api/v1/events
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(servicioEvento.obtenerTodos().stream().map(this::convertirADTO).collect(Collectors.toList()));
    }

    // GET /api/v1/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> obtenerPorId(@PathVariable Long id) {
        return servicioEvento.obtenerPorId(id)
                .map(evento -> ResponseEntity.ok(convertirADTO(evento)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/events
    @PostMapping
    public ResponseEntity<EventDTO> crear(@RequestBody EventCreateDTO dto) {
        Event evento = new Event();
        evento.setFecha(dto.getFecha());
        evento.setUbicacion(dto.getUbicacion());
        evento.setDescripcion(dto.getDescripcion());

        servicioConflicto.obtenerPorId(dto.getConflictoId()).ifPresent(evento::setConflicto);

        Event guardado = servicioEvento.guardar(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(guardado));
    }

    // PUT /api/v1/events/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> actualizar(@PathVariable Long id, @RequestBody EventCreateDTO dto) {
        return servicioEvento.obtenerPorId(id)
                .map(evento -> {
                    evento.setFecha(dto.getFecha());
                    evento.setUbicacion(dto.getUbicacion());
                    evento.setDescripcion(dto.getDescripcion());

                    servicioConflicto.obtenerPorId(dto.getConflictoId()).ifPresent(evento::setConflicto);

                    Event actualizado = servicioEvento.guardar(evento);
                    return ResponseEntity.ok(convertirADTO(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/events/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioEvento.obtenerPorId(id).isPresent()) {
            servicioEvento.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Convertir entidad a DTO
    private EventDTO convertirADTO(Event evento) {
        EventDTO dto = new EventDTO();
        dto.setId(evento.getId());
        dto.setFecha(evento.getFecha());
        dto.setUbicacion(evento.getUbicacion());
        dto.setDescripcion(evento.getDescripcion());
        dto.setConflictoId(evento.getConflicto() != null ? evento.getConflicto().getId() : null);
        return dto;
    }
}
