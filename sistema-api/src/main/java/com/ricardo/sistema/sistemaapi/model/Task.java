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

@Entity
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private LocalDate due_date;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EPriority priority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDENTE;

    @Column(nullable = false)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public EPriority getPriority() {
        return priority;
    }

    public void setPriority(EPriority priority) {
        this.priority = priority;
    }
}