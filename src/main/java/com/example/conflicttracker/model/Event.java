package com.example.conflicttracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha del evento
    private LocalDate fecha;

    // Ciudad o región donde ocurrió el evento
    private String ubicacion;

    // Descripción del evento
    private String descripcion;

    // Conflicto al que pertenece el evento
    @ManyToOne
    @JoinColumn(name = "conflict_id")
    private Conflict conflicto;

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

    public Conflict getConflicto() {
        return conflicto;
    }

    public void setConflicto(Conflict conflicto) {
        this.conflicto = conflicto;
    }
}
