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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Profissional save(Profissional objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
