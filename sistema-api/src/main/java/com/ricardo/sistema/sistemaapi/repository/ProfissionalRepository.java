package com.ricardo.sistema.sistemaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ricardo.sistema.sistemaapi.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    @Query("SELECT p FROM Profissional p" +
        " LEFT JOIN Cargo c ON c.id = p.cargo" +
        " WHERE p.nome LIKE %?1%" + 
        " OR p.username LIKE %?1%" +
        " OR c.nome LIKE %?1%")
    List<Profissional> findByAll(String termoBusca);
    
    Profissional findByUsername(String username);
}
