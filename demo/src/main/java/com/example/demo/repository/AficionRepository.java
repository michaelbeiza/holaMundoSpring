package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Aficion;

@Repository
public interface AficionRepository extends JpaRepository<Aficion,Long> {
}
