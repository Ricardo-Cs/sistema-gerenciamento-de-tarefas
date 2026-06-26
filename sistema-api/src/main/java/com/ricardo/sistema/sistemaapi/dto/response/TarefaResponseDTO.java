package com.ricardo.sistema.sistemaapi.dto.response;

import java.time.LocalDate;

import com.ricardo.sistema.sistemaapi.model.EPriority;
import com.ricardo.sistema.sistemaapi.model.EStatus;
import com.ricardo.sistema.sistemaapi.model.Tarefa;

public class TarefaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate due_date;
    private EPriority priority;
    private EStatus status;
    private ProfissionalResponseDTO profissional;

    public static TarefaResponseDTO fromEntity(Tarefa tarefa) {
        if (tarefa == null) return null;
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.id = tarefa.getId();
        dto.title = tarefa.getTitle();
        dto.description = tarefa.getDescription();
        dto.due_date = tarefa.getDue_date();
        dto.priority = tarefa.getPriority();
        dto.status = tarefa.getStatus();
        dto.profissional = ProfissionalResponseDTO.fromEntity(tarefa.getProfissional());
        return dto;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDue_date() { return due_date; }
    public EPriority getPriority() { return priority; }
    public EStatus getStatus() { return status; }
    public ProfissionalResponseDTO getProfissional() { return profissional; }
}
