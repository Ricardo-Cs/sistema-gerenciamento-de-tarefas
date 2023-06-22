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

import com.ricardo.sistema.sistemaapi.model.Solicitacao;
import com.ricardo.sistema.sistemaapi.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController implements IController<Solicitacao>{

    private SolicitacaoService service;

    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Solicitacao>> get() {
        List<Solicitacao> registros = service.get();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> get(Long id) {
        Solicitacao registro = service.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }
   
    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Solicitacao>> getByProfissionalId(@PathVariable("profissionalId") Long profissionalId) {
        List<Solicitacao> registros = service.getByProfissionalId(profissionalId);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Solicitacao> insert(@RequestBody Solicitacao objeto) {
        Solicitacao registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Solicitacao> update(@RequestBody Solicitacao objeto) {
        Solicitacao registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
