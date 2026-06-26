package com.ricardo.sistema.sistemaapi.dto.response;

import com.ricardo.sistema.sistemaapi.model.Profissional;

public class ProfissionalResponseDTO {

    private Long id;
    private String nome;
    private String username;
    private CargoResponseDTO cargo;

    public static ProfissionalResponseDTO fromEntity(Profissional profissional) {
        if (profissional == null) return null;
        ProfissionalResponseDTO dto = new ProfissionalResponseDTO();
        dto.id = profissional.getId();
        dto.nome = profissional.getNome();
        dto.username = profissional.getUsername();
        dto.cargo = CargoResponseDTO.fromEntity(profissional.getCargo());
        return dto;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getUsername() { return username; }
    public CargoResponseDTO getCargo() { return cargo; }
}
