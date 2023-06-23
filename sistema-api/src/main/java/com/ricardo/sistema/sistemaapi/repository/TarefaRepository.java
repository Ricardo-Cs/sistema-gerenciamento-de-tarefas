package com.ricardo.sistema.sistemaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
    @Query("SELECT t FROM Tarefa t" +
        " LEFT JOIN Profissional p ON p.id = t.profissional" + 
        " WHERE t.title LIKE %?1%" +
        " OR t.description LIKE %?1%" +
        " OR t.due_date LIKE %?1%" +
        " OR t.status LIKE %?1%" +
        " OR t.priority LIKE %?1%" + 
        " OR p.nome LIKE %?1%")
    List<Tarefa> findByAll(String termoBusca);

    List<Tarefa> findByProfissional(Profissional profissional);
}
