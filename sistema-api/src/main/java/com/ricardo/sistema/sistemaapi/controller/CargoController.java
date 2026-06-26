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

import com.ricardo.sistema.sistemaapi.dto.request.CargoRequestDTO;
import com.ricardo.sistema.sistemaapi.dto.response.CargoResponseDTO;
import com.ricardo.sistema.sistemaapi.model.Cargo;
import com.ricardo.sistema.sistemaapi.service.CargoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cargo")
@Tag(name = "Cargo", description = "Gerenciamento de cargos dos profissionais")
public class CargoController {

    private CargoService service;

    public CargoController(CargoService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todos os cargos", description = "Retorna a lista completa de cargos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<CargoResponseDTO>> get() {
        List<CargoResponseDTO> result = service.get().stream()
                .map(CargoResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/busca/{termoBusca}")
    @Operation(summary = "Buscar cargos", description = "Busca cargos pelo nome")
    @ApiResponse(responseCode = "200", description = "Resultados da busca retornados com sucesso")
    public ResponseEntity<List<CargoResponseDTO>> get(
            @Parameter(description = "Termo de busca") @PathVariable("termoBusca") String termoBusca) {
        List<CargoResponseDTO> result = service.get(termoBusca).stream()
                .map(CargoResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cargo por ID", description = "Retorna um cargo específico pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cargo encontrado"),
        @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    public ResponseEntity<CargoResponseDTO> get(
            @Parameter(description = "ID do cargo") @PathVariable("id") Long id) {
        return new ResponseEntity<>(CargoResponseDTO.fromEntity(service.get(id)), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Criar cargo", description = "Cadastra um novo cargo no sistema")
    @ApiResponse(responseCode = "201", description = "Cargo criado com sucesso")
    public ResponseEntity<CargoResponseDTO> insert(@RequestBody CargoRequestDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());
        return new ResponseEntity<>(CargoResponseDTO.fromEntity(service.save(cargo)), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar cargo", description = "Atualiza os dados de um cargo existente")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cargo atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    public ResponseEntity<CargoResponseDTO> update(@RequestBody CargoRequestDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setId(dto.getId());
        cargo.setNome(dto.getNome());
        return new ResponseEntity<>(CargoResponseDTO.fromEntity(service.save(cargo)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cargo", description = "Remove um cargo do sistema pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cargo excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "ID do cargo") @PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
