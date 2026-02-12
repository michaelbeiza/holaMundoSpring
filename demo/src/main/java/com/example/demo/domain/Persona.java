package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nombre;
    private String loginName;
    private String pwd;
    @ManyToOne
    private Pais paisNacimiento;
    @ManyToOne
    private Pais paisResidencia;
    @ManyToMany
    @Builder.Default
    private Collection<Aficion> gustos = new ArrayList<>();
    @ManyToMany
    @Builder.Default
    private Collection<Aficion> odios = new ArrayList<>();


    public Persona(String nombre){
        this.nombre = nombre;
        this.gustos= new ArrayList<>();
        this.odios = new ArrayList<>();
    }

    @GetMapping("login")
    public String loginGet() {
    return "/login";
    }

    @PostMapping("login")
    public String loginPost(){
        return "";
    }
}
