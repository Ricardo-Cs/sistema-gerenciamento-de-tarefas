package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.repository.ProfissionalRepository;

@Service
public class ProfissionalService implements IService<Profissional> {

    private ProfissionalRepository repo;

    public ProfissionalService (ProfissionalRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Profissional> get() {
        return repo.findAll();
    }

    @Override
    public Profissional get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Profissional save(Profissional objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}