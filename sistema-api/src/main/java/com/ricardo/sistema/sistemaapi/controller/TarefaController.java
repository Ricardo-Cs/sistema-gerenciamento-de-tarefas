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

import com.ricardo.sistema.sistemaapi.dto.request.TarefaRequestDTO;
import com.ricardo.sistema.sistemaapi.dto.response.TarefaResponseDTO;
import com.ricardo.sistema.sistemaapi.model.EStatus;
import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Tarefa;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;
import com.ricardo.sistema.sistemaapi.service.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Tarefa", description = "Gerenciamento de tarefas")
public class TarefaController {

    private TarefaService service;
    private ProfissionalService profissionalService;

    public TarefaController(TarefaService service, ProfissionalService profissionalService) {
        this.service = service;
        this.profissionalService = profissionalService;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todas as tarefas", description = "Retorna a lista completa de tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<TarefaResponseDTO>> get() {
        List<TarefaResponseDTO> result = service.get().stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/profissional/{profissionalId}")
    @Operation(summary = "Listar tarefas por profissional", description = "Retorna todas as tarefas atribuídas a um profissional específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<List<TarefaResponseDTO>> getByProfissionalId(
            @Parameter(description = "ID do profissional") @PathVariable("profissionalId") Long profissionalId) {
        List<TarefaResponseDTO> result = service.getByProfissionalId(profissionalId).stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/busca/{termoBusca}")
    @Operation(summary = "Buscar tarefas", description = "Busca tarefas por título, descrição, data, status, prioridade ou nome do profissional")
    @ApiResponse(responseCode = "200", description = "Resultados da busca retornados com sucesso")
    public ResponseEntity<List<TarefaResponseDTO>> get(
            @Parameter(description = "Termo de busca") @PathVariable("termoBusca") String termoBusca) {
        List<TarefaResponseDTO> result = service.get(termoBusca).stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TarefaResponseDTO> get(
            @Parameter(description = "ID da tarefa") @PathVariable("id") Long id) {
        return new ResponseEntity<>(TarefaResponseDTO.fromEntity(service.get(id)), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Criar tarefa", description = "Cadastra uma nova tarefa no sistema")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    public ResponseEntity<TarefaResponseDTO> insert(@RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return new ResponseEntity<>(TarefaResponseDTO.fromEntity(service.save(tarefa)), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TarefaResponseDTO> update(@RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return new ResponseEntity<>(TarefaResponseDTO.fromEntity(service.save(tarefa)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa do sistema pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "ID da tarefa") @PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    @Operation(summary = "Avançar status da tarefa", description = "Avança o status da tarefa: PENDENTE → FAZENDO → COMPLETADO → PENDENTE")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TarefaResponseDTO> updateStatus(
            @Parameter(description = "ID da tarefa") @PathVariable("id") Long id) {
        return new ResponseEntity<>(TarefaResponseDTO.fromEntity(service.updateStatus(id)), HttpStatus.OK);
    }

    private Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(dto.getId());
        tarefa.setTitle(dto.getTitle());
        tarefa.setDescription(dto.getDescription());
        tarefa.setDue_date(dto.getDue_date());
        tarefa.setPriority(dto.getPriority());
        tarefa.setStatus(dto.getStatus() != null ? dto.getStatus() : EStatus.PENDENTE);
        if (dto.getProfissionalId() != null) {
            Profissional profissional = profissionalService.get(dto.getProfissionalId());
            tarefa.setProfissional(profissional);
        }
        return tarefa;
    }
}
