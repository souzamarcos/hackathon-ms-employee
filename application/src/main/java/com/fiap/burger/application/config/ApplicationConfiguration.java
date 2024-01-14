package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.adapter.usecase.CustomerUseCase;
import com.fiap.burger.usecase.usecase.DefaultCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CustomerUseCase customerUseCase(CustomerGateway repository) {
        return new DefaultCustomerUseCase(repository);
    }
}
