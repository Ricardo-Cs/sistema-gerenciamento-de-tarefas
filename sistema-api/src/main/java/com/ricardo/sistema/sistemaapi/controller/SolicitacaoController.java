package com.ricardo.sistema.sistemaapi.controller;

import java.time.LocalDate;
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

import com.ricardo.sistema.sistemaapi.dto.request.SolicitacaoRequestDTO;
import com.ricardo.sistema.sistemaapi.dto.response.SolicitacaoResponseDTO;
import com.ricardo.sistema.sistemaapi.model.ESolicitacaoStatus;
import com.ricardo.sistema.sistemaapi.model.Solicitacao;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;
import com.ricardo.sistema.sistemaapi.service.SolicitacaoService;
import com.ricardo.sistema.sistemaapi.service.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/solicitacao")
@Tag(name = "Solicitação", description = "Gerenciamento de solicitações de reatribuição de tarefas")
public class SolicitacaoController {

    private SolicitacaoService service;
    private TarefaService tarefaService;
    private ProfissionalService profissionalService;

    public SolicitacaoController(SolicitacaoService service, TarefaService tarefaService, ProfissionalService profissionalService) {
        this.service = service;
        this.tarefaService = tarefaService;
        this.profissionalService = profissionalService;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todas as solicitações", description = "Retorna a lista completa de solicitações cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<SolicitacaoResponseDTO>> get() {
        List<SolicitacaoResponseDTO> result = service.get().stream()
                .map(SolicitacaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar solicitação por ID", description = "Retorna uma solicitação específica pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Solicitação encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitação não encontrada")
    })
    public ResponseEntity<SolicitacaoResponseDTO> get(
            @Parameter(description = "ID da solicitação") @PathVariable("id") Long id) {
        return new ResponseEntity<>(SolicitacaoResponseDTO.fromEntity(service.get(id)), HttpStatus.OK);
    }

    @GetMapping("/profissional/{profissionalId}")
    @Operation(summary = "Listar solicitações por profissional", description = "Retorna todas as solicitações direcionadas a um profissional específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<List<SolicitacaoResponseDTO>> getByProfissionalId(
            @Parameter(description = "ID do profissional solicitado") @PathVariable("profissionalId") Long profissionalId) {
        List<SolicitacaoResponseDTO> result = service.getByProfissionalId(profissionalId).stream()
                .map(SolicitacaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Criar solicitação", description = "Registra uma nova solicitação de reatribuição de tarefa entre profissionais")
    @ApiResponse(responseCode = "201", description = "Solicitação criada com sucesso")
    public ResponseEntity<SolicitacaoResponseDTO> insert(@RequestBody SolicitacaoRequestDTO dto) {
        Solicitacao solicitacao = toEntity(dto);
        return new ResponseEntity<>(SolicitacaoResponseDTO.fromEntity(service.save(solicitacao)), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Atualizar solicitação", description = "Atualiza os dados de uma solicitação existente (ex: aceitar solicitação)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Solicitação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Solicitação não encontrada")
    })
    public ResponseEntity<SolicitacaoResponseDTO> update(@RequestBody SolicitacaoRequestDTO dto) {
        Solicitacao solicitacao = toEntity(dto);
        return new ResponseEntity<>(SolicitacaoResponseDTO.fromEntity(service.save(solicitacao)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir solicitação", description = "Remove uma solicitação do sistema pelo seu ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Solicitação excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Solicitação não encontrada")
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "ID da solicitação") @PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Solicitacao toEntity(SolicitacaoRequestDTO dto) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(dto.getId());
        solicitacao.setData(dto.getData() != null ? dto.getData() : LocalDate.now());
        solicitacao.setStatus(dto.getStatus() != null ? dto.getStatus() : ESolicitacaoStatus.SOLICITADA);
        if (dto.getTarefaId() != null) {
            solicitacao.setTarefa(tarefaService.get(dto.getTarefaId()));
        }
        if (dto.getProfissionalSolicitadoId() != null) {
            solicitacao.setProfissionalSolicitado(profissionalService.get(dto.getProfissionalSolicitadoId()));
        }
        if (dto.getProfissionalSolicitanteId() != null) {
            solicitacao.setProfissionalSolicitante(profissionalService.get(dto.getProfissionalSolicitanteId()));
        }
        return solicitacao;
    }
}
