package com.ricardo.sistema.sistemaapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ricardo.sistema.sistemaapi.model.Profissional;
import com.ricardo.sistema.sistemaapi.service.ProfissionalService;

public class PerfilProfissionalService implements UserDetailsService{

    @Autowired
    private ProfissionalService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profissional profissional = service.getByNomeProfissional(username);
        return new PerfilProfissional(profissional);
    }
    
}
