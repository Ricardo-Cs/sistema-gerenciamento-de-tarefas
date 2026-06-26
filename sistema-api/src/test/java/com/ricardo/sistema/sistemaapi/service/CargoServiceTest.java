package com.ricardo.sistema.sistemaapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ricardo.sistema.sistemaapi.model.Cargo;
import com.ricardo.sistema.sistemaapi.repository.CargoRepository;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    private Cargo cargo;

    @BeforeEach
    void setUp() {
        cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome("Desenvolvedor");
    }

    @Test
    void get_deveRetornarListaDeCargos() {
        Cargo outroCargo = new Cargo();
        outroCargo.setId(2L);
        outroCargo.setNome("Gerente");
        when(cargoRepository.findAll()).thenReturn(List.of(cargo, outroCargo));

        List<Cargo> resultado = cargoService.get();

        assertEquals(2, resultado.size());
        assertEquals("Desenvolvedor", resultado.get(0).getNome());
        verify(cargoRepository, times(1)).findAll();
    }

    @Test
    void getPorId_deveRetornarCargoQuandoEncontrado() {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        Cargo resultado = cargoService.get(1L);

        assertNotNull(resultado);
        assertEquals("Desenvolvedor", resultado.getNome());
    }

    @Test
    void getPorId_deveRetornarNullQuandoNaoEncontrado() {
        when(cargoRepository.findById(99L)).thenReturn(Optional.empty());

        Cargo resultado = cargoService.get(99L);

        assertNull(resultado);
    }

    @Test
    void save_deveSalvarERetornarCargo() {
        when(cargoRepository.save(cargo)).thenReturn(cargo);

        Cargo resultado = cargoService.save(cargo);

        assertNotNull(resultado);
        assertEquals("Desenvolvedor", resultado.getNome());
        verify(cargoRepository, times(1)).save(cargo);
    }

    @Test
    void delete_deveChamarDeleteById() {
        doNothing().when(cargoRepository).deleteById(1L);

        cargoService.delete(1L);

        verify(cargoRepository, times(1)).deleteById(1L);
    }
}
