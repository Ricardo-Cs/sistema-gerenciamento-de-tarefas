package com.ricardo.sistema.sistemaapi.dto.request;

import java.time.LocalDate;

import com.ricardo.sistema.sistemaapi.model.ESolicitacaoStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar ou atualizar uma solicitação de reatribuição de tarefa")
public class SolicitacaoRequestDTO {

    @Schema(description = "ID da solicitação (obrigatório apenas para atualização)", example = "1")
    private Long id;

    @Schema(description = "Data da solicitação (padrão: data atual)", example = "2025-06-26")
    private LocalDate data;

    @Schema(description = "Status da solicitação (padrão: SOLICITADA)", example = "SOLICITADA", allowableValues = {"SOLICITADA", "ACEITA"})
    private ESolicitacaoStatus status;

    @Schema(description = "ID da tarefa que está sendo solicitada", example = "1", required = true)
    private Long tarefaId;

    @Schema(description = "ID do profissional que recebe a solicitação", example = "2", required = true)
    private Long profissionalSolicitadoId;

    @Schema(description = "ID do profissional que fez a solicitação", example = "1", required = true)
    private Long profissionalSolicitanteId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public ESolicitacaoStatus getStatus() { return status; }
    public void setStatus(ESolicitacaoStatus status) { this.status = status; }

    public Long getTarefaId() { return tarefaId; }
    public void setTarefaId(Long tarefaId) { this.tarefaId = tarefaId; }

    public Long getProfissionalSolicitadoId() { return profissionalSolicitadoId; }
    public void setProfissionalSolicitadoId(Long profissionalSolicitadoId) { this.profissionalSolicitadoId = profissionalSolicitadoId; }

    public Long getProfissionalSolicitanteId() { return profissionalSolicitanteId; }
    public void setProfissionalSolicitanteId(Long profissionalSolicitanteId) { this.profissionalSolicitanteId = profissionalSolicitanteId; }
}
