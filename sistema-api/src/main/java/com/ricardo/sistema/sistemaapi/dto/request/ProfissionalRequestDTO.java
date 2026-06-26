package com.ricardo.sistema.sistemaapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar ou atualizar um profissional")
public class ProfissionalRequestDTO {

    @Schema(description = "ID do profissional (obrigatório apenas para atualização)", example = "1")
    private Long id;

    @Schema(description = "Nome completo do profissional", example = "Ricardo Silva", required = true)
    private String nome;

    @Schema(description = "Nome de usuário para login", example = "ricardo.silva", required = true)
    private String username;

    @Schema(description = "Senha do profissional (se omitida na atualização, a senha atual é mantida)", example = "senha123")
    private String password;

    @Schema(description = "ID do cargo do profissional", example = "2", required = true)
    private Long cargoId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getCargoId() { return cargoId; }
    public void setCargoId(Long cargoId) { this.cargoId = cargoId; }
}
