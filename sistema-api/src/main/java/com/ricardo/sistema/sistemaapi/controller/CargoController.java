package com.ricardo.sistema.sistemaapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.sistema.sistemaapi.model.Cargo;
import com.ricardo.sistema.sistemaapi.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController implements IController<Cargo> {

    private CargoService service;

    public CargoController(CargoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Cargo>> get() {
        List<Cargo> registros = service.get();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cargo> get(Long id) {
        Cargo registro = service.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cargo> insert(Cargo objeto) {
        Cargo registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cargo> update(Cargo objeto) {
        Cargo registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
