package com.ricardo.sistema.sistemaapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Solicitacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    private Tarefa tarefa;

    @ManyToOne
    private Profissional profissionalSolicitado;

    @ManyToOne
    private Profissional profissionalSolicitante;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ESolicitacaoStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Profissional getProfissionalSolicitado() {
        return profissionalSolicitado;
    }

    public void setProfissionalSolicitado(Profissional profissionalSolicitado) {
        this.profissionalSolicitado = profissionalSolicitado;
    }

    public Profissional getProfissionalSolicitante() {
        return profissionalSolicitante;
    }

    public void setProfissionalSolicitante(Profissional profissionalSolicitante) {
        this.profissionalSolicitante = profissionalSolicitante;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ESolicitacaoStatus getStatus() {
        return status;
    }

    public void setStatus(ESolicitacaoStatus status) {
        this.status = status;
    }
}
