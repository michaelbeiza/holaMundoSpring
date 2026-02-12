package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Aficion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "gustos")
    @JsonIgnore
    private Collection<Persona> aficionados;

    @ManyToMany(mappedBy = "odios")
    @JsonIgnore
    private Collection<Persona> haters;

    // Constructores
    public Aficion(String nombre) {
        this.nombre = nombre;
        this.aficionados = new ArrayList<>();
        this.haters = new ArrayList<>();
    }

    public Aficion() {
        this.aficionados = new ArrayList<>();
        this.haters = new ArrayList<>();
    }

    // Getters y Setters que TE FALTABAN:
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

    // Resto de Getters y Setters de las colecciones
    public Collection<Persona> getAficionados() {
        return aficionados;
    }

    public void setAficionados(Collection<Persona> aficionados) {
        this.aficionados = aficionados;
    }

    public Collection<Persona> getHaters() {
        return haters;
    }

    public void setHaters(Collection<Persona> haters) {
        this.haters = haters;
    }
}
