package com.ricardo.sistema.sistemaapi.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.sistema.sistemaapi.dto.response.ProfissionalResponseDTO;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/profissional_info")
@Tag(name = "Autenticação", description = "Informações do profissional autenticado")
public class LoginController {

    private ProfissionalService service;

    public LoginController(ProfissionalService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Obter profissional autenticado", description = "Retorna os dados do profissional autenticado na sessão atual via HTTP Basic Auth")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dados do profissional retornados com sucesso"),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
    public ResponseEntity<ProfissionalResponseDTO> getProfissional() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(
                ProfissionalResponseDTO.fromEntity(service.getByUsername(principal.getName())),
                HttpStatus.OK);
    }
}
