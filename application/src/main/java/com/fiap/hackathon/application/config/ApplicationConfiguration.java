package com.fiap.hackathon.application.config;

import com.fiap.hackathon.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.hackathon.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.hackathon.usecase.misc.token.TokenJwtUtils;
import com.fiap.hackathon.usecase.usecase.DefaultEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EmployeeUseCase employeeUseCase(EmployeeGateway repository, TokenJwtUtils tokenJwtUtils) {
        return new DefaultEmployeeUseCase(repository, tokenJwtUtils);
    }
}
