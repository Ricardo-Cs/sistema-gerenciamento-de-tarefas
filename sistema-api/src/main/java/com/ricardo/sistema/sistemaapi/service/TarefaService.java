package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Tarefa;
import com.ricardo.sistema.sistemaapi.repository.TarefaRepository;

@Service
public class TarefaService implements IService<Tarefa> {
    
    private TarefaRepository repo;
    private ProfissionalService serviceProfissional;

    public TarefaService (TarefaRepository repo, ProfissionalService serviceProfissional) {
        this.repo = repo;
        this.serviceProfissional = serviceProfissional;
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

    public List<Tarefa> getByProfissionalId(Long profissionalId) {
        Profissional profissional = serviceProfissional.get(profissionalId);
        List<Tarefa> tarefas = repo.findByProfissional(profissional);
        return tarefas;
    }

    public List<Tarefa> get(String termoBusca) {
        return repo.findByAll(termoBusca);
    }

    public Tarefa updateStatus(Long id) {
        Tarefa registro = repo.findById(id).orElse(null);
        registro.setStatus(registro.getStatus().proximo());
        repo.save(registro);
        return registro;
    } 

}
