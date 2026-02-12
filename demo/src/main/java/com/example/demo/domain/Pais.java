package com.example.demo.domain;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(unique = true)
    private String nombre;

    public Collection<Persona> getNacidos() {
        return nacidos;
    }

    public void setNacidos(Collection<Persona> nacidos) {
        this.nacidos = nacidos;
    }

    public Collection<Persona> getResidentes() {
        return residentes;
    }

    public void setResidentes(Collection<Persona> residentes) {
        this.residentes = residentes;
    }
    @OneToMany(mappedBy = "paisNacimiento")
    private Collection<Persona>  nacidos;
    @OneToMany(mappedBy = "paisResidencia")
    private Collection<Persona>residentes;


    public Pais(){
        
    }

    public Pais(String nombre){
        this.nombre = nombre;
    }
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
    
}
