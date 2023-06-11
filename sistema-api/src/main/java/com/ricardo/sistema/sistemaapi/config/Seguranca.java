package com.ricardo.sistema.sistemaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Seguranca {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.cors();
        http.authorizeHttpRequests().anyRequest().permitAll();
        // http.authenticationProvider(authProvider());

        // http.authorizeHttpRequests().antMatchers("/config/**").hasRole("ADMIN");
        // http.authorizeHttpRequests().anyRequest().authenticated();

        // http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        // http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));

        // http.csrf().disable();

        return http.build();

    }
    
}