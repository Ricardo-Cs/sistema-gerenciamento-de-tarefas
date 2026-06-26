package com.ricardo.sistema.sistemaapi.dto.response;

import java.time.LocalDate;

import com.ricardo.sistema.sistemaapi.model.ESolicitacaoStatus;
import com.ricardo.sistema.sistemaapi.model.Solicitacao;

public class SolicitacaoResponseDTO {

    private Long id;
    private LocalDate data;
    private ESolicitacaoStatus status;
    private TarefaResponseDTO tarefa;
    private ProfissionalResponseDTO profissionalSolicitado;
    private ProfissionalResponseDTO profissionalSolicitante;

    public static SolicitacaoResponseDTO fromEntity(Solicitacao solicitacao) {
        if (solicitacao == null) return null;
        SolicitacaoResponseDTO dto = new SolicitacaoResponseDTO();
        dto.id = solicitacao.getId();
        dto.data = solicitacao.getData();
        dto.status = solicitacao.getStatus();
        dto.tarefa = TarefaResponseDTO.fromEntity(solicitacao.getTarefa());
        dto.profissionalSolicitado = ProfissionalResponseDTO.fromEntity(solicitacao.getProfissionalSolicitado());
        dto.profissionalSolicitante = ProfissionalResponseDTO.fromEntity(solicitacao.getProfissionalSolicitante());
        return dto;
    }

    public Long getId() { return id; }
    public LocalDate getData() { return data; }
    public ESolicitacaoStatus getStatus() { return status; }
    public TarefaResponseDTO getTarefa() { return tarefa; }
    public ProfissionalResponseDTO getProfissionalSolicitado() { return profissionalSolicitado; }
    public ProfissionalResponseDTO getProfissionalSolicitante() { return profissionalSolicitante; }
}
