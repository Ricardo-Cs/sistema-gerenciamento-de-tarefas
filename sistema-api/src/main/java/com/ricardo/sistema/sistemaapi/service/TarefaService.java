package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Tarefa;
import com.ricardo.sistema.sistemaapi.repository.TarefaRepository;

@Service
public class TarefaService implements IService<Tarefa> {
    
    private TarefaRepository repo;

    public TarefaService (TarefaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Tarefa> get() {
        return repo.findAll();
    }

    @Override
    public Tarefa get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Tarefa save(Tarefa objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
