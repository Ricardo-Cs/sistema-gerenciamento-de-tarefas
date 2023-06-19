package com.ricardo.sistema.sistemaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
    List<Tarefa> findByProfissional(Profissional profissional);
}
