package com.ricardo.sistema.sistemaapi.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ricardo.sistema.sistemaapi.model.Profissional;

public class PerfilProfissional implements UserDetails{

    public Profissional profissional;

    public PerfilProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(profissional.getCargo().getNome());
        return Arrays.asList(auth);
    }

    @Override
    public String getPassword() {
        return profissional.getPassword();
    }

    @Override
    public String getUsername() {
        return profissional.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
