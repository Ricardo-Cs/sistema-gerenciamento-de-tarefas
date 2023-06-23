package com.ricardo.sistema.sistemaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ricardo.sistema.sistemaapi.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

    @Query("SELECT c FROM Cargo c WHERE c.nome LIKE %?1%")
    List<Cargo> findByAll(String termoBusca);
}
