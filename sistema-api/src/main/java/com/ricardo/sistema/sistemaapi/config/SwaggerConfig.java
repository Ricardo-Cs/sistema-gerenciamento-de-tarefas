package com.ricardo.sistema.sistemaapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gerenciamento de Tarefas")
                        .description("API REST para gerenciamento de tarefas, profissionais, cargos e solicitações")
                        .version("1.0.0"));
    }
}
