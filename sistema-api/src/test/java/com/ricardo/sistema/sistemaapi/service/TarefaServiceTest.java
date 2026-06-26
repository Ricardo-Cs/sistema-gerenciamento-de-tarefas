package com.ricardo.sistema.sistemaapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ricardo.sistema.sistemaapi.model.EPriority;
import com.ricardo.sistema.sistemaapi.model.EStatus;
import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.model.Tarefa;
import com.ricardo.sistema.sistemaapi.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private ProfissionalService profissionalService;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;

    @BeforeEach
    void setUp() {
        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitle("Tarefa Teste");
        tarefa.setDescription("Descrição da tarefa de teste");
        tarefa.setDue_date(LocalDate.of(2025, 12, 31));
        tarefa.setPriority(EPriority.ALTA);
        tarefa.setStatus(EStatus.PENDENTE);
    }

    @Test
    void get_deveRetornarListaDeTarefas() {
        Tarefa outraTarefa = new Tarefa();
        outraTarefa.setId(2L);
        outraTarefa.setTitle("Outra Tarefa");
        when(tarefaRepository.findAll()).thenReturn(List.of(tarefa, outraTarefa));

        List<Tarefa> resultado = tarefaService.get();

        assertEquals(2, resultado.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    void getPorId_deveRetornarTarefaQuandoEncontrada() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa resultado = tarefaService.get(1L);

        assertNotNull(resultado);
        assertEquals("Tarefa Teste", resultado.getTitle());
        assertEquals(EStatus.PENDENTE, resultado.getStatus());
    }

    @Test
    void getPorId_deveRetornarNullQuandoNaoEncontrada() {
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        Tarefa resultado = tarefaService.get(99L);

        assertNull(resultado);
    }

    @Test
    void save_deveSalvarERetornarTarefa() {
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa resultado = tarefaService.save(tarefa);

        assertNotNull(resultado);
        assertEquals("Tarefa Teste", resultado.getTitle());
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    void delete_deveChamarDeleteById() {
        doNothing().when(tarefaRepository).deleteById(1L);

        tarefaService.delete(1L);

        verify(tarefaRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateStatus_deveAvancarDePendenteParaFazendo() {
        tarefa.setStatus(EStatus.PENDENTE);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa resultado = tarefaService.updateStatus(1L);

        assertEquals(EStatus.FAZENDO, resultado.getStatus());
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    void updateStatus_deveVoltarParaPendenteAposCompletado() {
        tarefa.setStatus(EStatus.COMPLETADO);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);

        Tarefa resultado = tarefaService.updateStatus(1L);

        assertEquals(EStatus.PENDENTE, resultado.getStatus());
    }

    @Test
    void getByProfissionalId_deveRetornarTarefasDoProfissional() {
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Ricardo");

        tarefa.setProfissional(profissional);

        when(profissionalService.get(1L)).thenReturn(profissional);
        when(tarefaRepository.findByProfissional(profissional)).thenReturn(List.of(tarefa));

        List<Tarefa> resultado = tarefaService.getByProfissionalId(1L);

        assertEquals(1, resultado.size());
        assertEquals("Tarefa Teste", resultado.get(0).getTitle());
        verify(profissionalService, times(1)).get(1L);
        verify(tarefaRepository, times(1)).findByProfissional(profissional);
    }
}
