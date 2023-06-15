package com.ricardo.sistema.sistemaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.sistema.sistemaapi.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
    Profissional findByUsername(String username);
}
