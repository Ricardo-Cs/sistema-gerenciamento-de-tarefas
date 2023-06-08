package com.ricardo.sistema.sistemaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.sistema.sistemaapi.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
    
}
