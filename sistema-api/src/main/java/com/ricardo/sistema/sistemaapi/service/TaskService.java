package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Task;
import com.ricardo.sistema.sistemaapi.repository.TaskRepository;

@Service
public class TaskService implements IService<Task> {
    
    private TaskRepository repo;

    public TaskService (TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Task> get() {
        return repo.findAll();
    }

    @Override
    public Task get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Task save(Task objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
