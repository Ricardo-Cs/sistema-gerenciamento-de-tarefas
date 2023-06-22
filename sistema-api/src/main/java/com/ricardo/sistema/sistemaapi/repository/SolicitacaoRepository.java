package com.ricardo.sistema.sistemaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    
    List<Solicitacao> findByProfissionalSolicitado(Profissional profissionalSolicitado);

}
