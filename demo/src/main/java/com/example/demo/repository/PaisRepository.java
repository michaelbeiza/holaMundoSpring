package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Pais;
import com.example.demo.domain.Persona;

public interface PaisRepository extends JpaRepository <Pais,Long>{

}
