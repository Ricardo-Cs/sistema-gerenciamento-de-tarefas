package com.ricardo.sistema.sistemaapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar ou atualizar um cargo")
public class CargoRequestDTO {

    @Schema(description = "ID do cargo (obrigatório apenas para atualização)", example = "1")
    private Long id;

    @Schema(description = "Nome do cargo", example = "Desenvolvedor", required = true)
    private String nome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
