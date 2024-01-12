package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.adapter.usecase.ClientUseCase;
import com.fiap.burger.usecase.usecase.DefaultClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ClientUseCase clientUseCase(ClientGateway repository) {
        return new DefaultClientUseCase(repository);
    }
}
