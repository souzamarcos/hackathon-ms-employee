package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.burger.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.burger.usecase.misc.token.TokenJwtUtils;
import com.fiap.burger.usecase.usecase.DefaultEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EmployeeUseCase employeeUseCase(EmployeeGateway repository, TokenJwtUtils tokenJwtUtils) {
        return new DefaultEmployeeUseCase(repository, tokenJwtUtils);
    }
}
