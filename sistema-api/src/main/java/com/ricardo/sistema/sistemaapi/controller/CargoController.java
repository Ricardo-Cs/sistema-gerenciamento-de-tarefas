package com.ricardo.sistema.sistemaapi.controller;

import java.util.List;

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
    @GetMapping("/")
    public ResponseEntity<List<Cargo>> get() {
        List<Cargo> registros = service.get();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/busca/{termoBusca}")
    public ResponseEntity<List<Cargo>> get(@PathVariable("termoBusca") String termoBusca) {
        List<Cargo> registros = service.get(termoBusca);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cargo> get(@PathVariable("id") Long id) {
        Cargo registro = service.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Cargo> insert(@RequestBody Cargo objeto) {
        Cargo registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Cargo> update(@RequestBody Cargo objeto) {
        Cargo registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
