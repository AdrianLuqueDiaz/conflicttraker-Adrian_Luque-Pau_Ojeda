package com.example.conflicttracker.dto;

import java.util.List;

public class FactionDTO {

    private Long id;
    private String nombre;

    private Long conflictoId; // Id del conflicto al que pertenece

    private List<String> paisesApoyados; // Nombres de países

    // ----- GETTERS Y SETTERS -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getPaisesApoyados() {
        return paisesApoyados;
    }

    public void setPaisesApoyados(List<String> paisesApoyados) {
        this.paisesApoyados = paisesApoyados;
    }
}
