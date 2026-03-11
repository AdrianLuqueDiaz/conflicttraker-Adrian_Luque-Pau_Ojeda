package com.example.conflicttracker.util;

import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.model.Conflict;

import java.util.stream.Collectors;

public class MapperUtils {

    // Convertir entidad Conflict a DTO
    public static ConflictDTO convertirAConflictDTO(Conflict conflicto) {
        ConflictDTO dto = new ConflictDTO();
        dto.setId(conflicto.getId());
        dto.setNombre(conflicto.getNombre());
        dto.setFechaInicio(conflicto.getFechaInicio());
        dto.setEstado(conflicto.getEstado().name());
        dto.setDescripcion(conflicto.getDescripcion());
        dto.setPaises(conflicto.getPaises().stream().map(p -> p.getNombre()).collect(Collectors.toList()));
        return dto;
    }
}
