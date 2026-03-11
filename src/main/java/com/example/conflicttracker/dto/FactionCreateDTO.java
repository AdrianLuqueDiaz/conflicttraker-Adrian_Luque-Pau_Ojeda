package com.example.conflicttracker.dto;

import java.util.List;

public class FactionCreateDTO {

    private String nombre;
    private Long conflictoId;

    // Lista de códigos de países
    private List<String> codigosPaises;

    // ----- GETTERS Y SETTERS -----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getConflictoId() {
        return conflictoId;
    }

    public void setConflictoId(Long conflictoId) {
        this.conflictoId = conflictoId;
    }

    public List<String> getCodigosPaises() {
        return codigosPaises;
    }

    public void setCodigosPaises(List<String> codigosPaises) {
        this.codigosPaises = codigosPaises;
    }
}
