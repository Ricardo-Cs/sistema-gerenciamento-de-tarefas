package com.ricardo.sistema.sistemaapi.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;

@RestController
@RequestMapping("/profissional_info")
public class LoginController {
    
    private ProfissionalService service;

    public LoginController(ProfissionalService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Profissional> getProfissional() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Profissional profissional = service.getByUsername(principal.getName());
        return new ResponseEntity<>(profissional, HttpStatus.OK);
    }
}
