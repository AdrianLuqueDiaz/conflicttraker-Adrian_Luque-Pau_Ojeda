package com.example.conflicttracker.dto;

import java.time.LocalDate;

public class EventDTO {

    private Long id;
    private LocalDate fecha;
    private String ubicacion;
    private String descripcion;

    private Long conflictoId;

    // ----- GETTERS Y SETTERS -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getConflictoId() {
        return conflictoId;
    }

    public void setConflictoId(Long conflictoId) {
        this.conflictoId = conflictoId;
    }
}
