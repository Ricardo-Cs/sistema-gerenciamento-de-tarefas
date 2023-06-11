package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Solicitacao;
import com.ricardo.sistema.sistemaapi.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService implements IService<Solicitacao> {

    private SolicitacaoRepository repo;

    public SolicitacaoService(SolicitacaoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Solicitacao> get() {
        return repo.findAll();
    }

    @Override
    public Solicitacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Solicitacao save(Solicitacao objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
