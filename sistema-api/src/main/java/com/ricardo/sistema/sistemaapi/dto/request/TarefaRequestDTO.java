package com.ricardo.sistema.sistemaapi.dto.request;

import java.time.LocalDate;

import com.ricardo.sistema.sistemaapi.model.EPriority;
import com.ricardo.sistema.sistemaapi.model.EStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar ou atualizar uma tarefa")
public class TarefaRequestDTO {

    @Schema(description = "ID da tarefa (obrigatório apenas para atualização)", example = "1")
    private Long id;

    @Schema(description = "Título da tarefa", example = "Implementar tela de login", required = true)
    private String title;

    @Schema(description = "Descrição detalhada da tarefa", example = "Criar o formulário de login com validação")
    private String description;

    @Schema(description = "Data de vencimento da tarefa", example = "2025-12-31", required = true)
    private LocalDate due_date;

    @Schema(description = "Prioridade da tarefa", example = "ALTA", allowableValues = {"BAIXA", "MÉDIA", "ALTA"}, required = true)
    private EPriority priority;

    @Schema(description = "Status da tarefa (padrão: PENDENTE)", example = "PENDENTE", allowableValues = {"PENDENTE", "FAZENDO", "COMPLETADO"})
    private EStatus status;

    @Schema(description = "ID do profissional responsável pela tarefa", example = "1", required = true)
    private Long profissionalId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDue_date() { return due_date; }
    public void setDue_date(LocalDate due_date) { this.due_date = due_date; }

    public EPriority getPriority() { return priority; }
    public void setPriority(EPriority priority) { this.priority = priority; }

    public EStatus getStatus() { return status; }
    public void setStatus(EStatus status) { this.status = status; }

    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
}
