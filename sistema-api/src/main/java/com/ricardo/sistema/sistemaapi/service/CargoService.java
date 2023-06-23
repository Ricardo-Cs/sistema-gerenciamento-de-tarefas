package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Cargo;
import com.ricardo.sistema.sistemaapi.repository.CargoRepository;

@Service
public class CargoService implements IService<Cargo>{

    private CargoRepository repo;

    public CargoService(CargoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cargo> get() {
        return repo.findAll();
    }

    public List<Cargo> get(String termoBusca) {
        return repo.findByAll(termoBusca);
    }

    @Override
    public Cargo get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Cargo save(Cargo objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
