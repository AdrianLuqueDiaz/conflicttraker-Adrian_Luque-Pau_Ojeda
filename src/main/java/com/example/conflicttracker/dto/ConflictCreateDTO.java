package com.example.conflicttracker.dto;

import java.time.LocalDate;
import java.util.List;

public class ConflictCreateDTO {

    private String nombre;
    private LocalDate fechaInicio;
    private String estado;
    private String descripcion;

    // Lista de códigos de país: ["ESP", "UKR"]
    private List<String> codigosPaises;

    // ----- GETTERS Y SETTERS -----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getCodigosPaises() {
        return codigosPaises;
    }

    public void setCodigosPaises(List<String> codigosPaises) {
        this.codigosPaises = codigosPaises;
    }
}
