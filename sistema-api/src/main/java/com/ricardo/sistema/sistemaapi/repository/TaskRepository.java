package com.ricardo.sistema.sistemaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.sistema.sistemaapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
