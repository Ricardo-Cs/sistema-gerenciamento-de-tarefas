package com.ricardo.sistema.sistemaapi.dto.response;

import com.ricardo.sistema.sistemaapi.model.Cargo;

public class CargoResponseDTO {

    private Long id;
    private String nome;

    public static CargoResponseDTO fromEntity(Cargo cargo) {
        if (cargo == null) return null;
        CargoResponseDTO dto = new CargoResponseDTO();
        dto.id = cargo.getId();
        dto.nome = cargo.getNome();
        return dto;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}
