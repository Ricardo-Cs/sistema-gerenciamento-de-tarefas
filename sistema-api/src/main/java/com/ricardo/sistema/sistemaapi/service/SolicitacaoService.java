package com.ricardo.sistema.sistemaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Solicitacao;
import com.ricardo.sistema.sistemaapi.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService implements IService<Solicitacao> {

    private SolicitacaoRepository repo;
    private ProfissionalService serviceProfissional;

    public SolicitacaoService(SolicitacaoRepository repo, ProfissionalService serviceProfissional) {
        this.repo = repo;
        this.serviceProfissional = serviceProfissional;
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

    public List<Solicitacao> getByProfissionalId(Long profissionalId) {
        Profissional profissional = serviceProfissional.get(profissionalId);
        List<Solicitacao> solicitacoes = repo.findByProfissionalSolicitado(profissional);
        return solicitacoes;
    }
    
}
