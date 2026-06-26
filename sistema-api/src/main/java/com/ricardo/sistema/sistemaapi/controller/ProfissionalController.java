package com.ricardo.sistema.sistemaapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.sistema.sistemaapi.dto.request.ProfissionalRequestDTO;
import com.ricardo.sistema.sistemaapi.dto.response.ProfissionalResponseDTO;
import com.ricardo.sistema.sistemaapi.model.Cargo;
import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.service.CargoService;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/profissional")
@Tag(name = "Profissional", description = "Gerenciamento de profissionais")
public class ProfissionalController {

    private ProfissionalService service;
    private CargoService cargoService;

    public ProfissionalController(ProfissionalService service, CargoService cargoService) {
        this.service = service;
        this.cargoService = cargoService;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todos os profissionais", description = "Retorna a lista completa de profissionais cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<ProfissionalResponseDTO>> get() {
        List<ProfissionalResponseDTO> result = service.get().stream()
                .map(ProfissionalResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID", description = "Retorna um profissional específico pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional encontrado"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<ProfissionalResponseDTO> get(
            @Parameter(description = "ID do profissional") @PathVariable("id") Long id) {
        return new ResponseEntity<>(ProfissionalResponseDTO.fromEntity(service.get(id)), HttpStatus.OK);
    }

    @GetMapping("/busca/{termoBusca}")
    @Operation(summary = "Buscar profissionais", description = "Busca profissionais pelo nome ou username")
    @ApiResponse(responseCode = "200", description = "Resultados da busca retornados com sucesso")
    public ResponseEntity<List<ProfissionalResponseDTO>> get(
            @Parameter(description = "Termo de busca") @PathVariable("termoBusca") String termoBusca) {
        List<ProfissionalResponseDTO> result = service.get(termoBusca).stream()
                .map(ProfissionalResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Criar profissional", description = "Cadastra um novo profissional. A senha é armazenada com criptografia BCrypt")
    @ApiResponse(responseCode = "201", description = "Profissional criado com sucesso")
    public ResponseEntity<ProfissionalResponseDTO> insert(@RequestBody ProfissionalRequestDTO dto) {
        Profissional profissional = toEntity(dto);
        return new ResponseEntity<>(ProfissionalResponseDTO.fromEntity(service.save(profissional)), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar profissional", description = "Atualiza os dados de um profissional. Se a senha não for enviada, a atual é mantida")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Profissional atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<ProfissionalResponseDTO> update(@RequestBody ProfissionalRequestDTO dto) {
        Profissional profissional = toEntity(dto);
        return new ResponseEntity<>(ProfissionalResponseDTO.fromEntity(service.save(profissional)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir profissional", description = "Remove um profissional do sistema pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "ID do profissional") @PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Profissional toEntity(ProfissionalRequestDTO dto) {
        Profissional profissional = new Profissional();
        profissional.setId(dto.getId());
        profissional.setNome(dto.getNome());
        profissional.setUsername(dto.getUsername());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            profissional.setPassword(dto.getPassword());
        }
        if (dto.getCargoId() != null) {
            Cargo cargo = cargoService.get(dto.getCargoId());
            profissional.setCargo(cargo);
        }
        return profissional;
    }
}
