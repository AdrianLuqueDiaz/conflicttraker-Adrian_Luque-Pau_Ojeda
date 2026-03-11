package com.example.conflicttracker.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la facción
    private String nombre;

    // El conflicto al que pertenece esta facción
    @ManyToOne
    @JoinColumn(name = "conflict_id")
    private Conflict conflicto;

    // Países que apoyan a esta facción
    @ManyToMany
    @JoinTable(
            name = "faction_country",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> paisesApoyados = new ArrayList<>();

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

    public Conflict getConflicto() {
        return conflicto;
    }

    public void setConflicto(Conflict conflicto) {
        this.conflicto = conflicto;
    }

    public List<Country> getPaisesApoyados() {
        return paisesApoyados;
    }

    public void setPaisesApoyados(List<Country> paisesApoyados) {
        this.paisesApoyados = paisesApoyados;
    }
}
