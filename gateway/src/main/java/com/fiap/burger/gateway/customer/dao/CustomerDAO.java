package com.fiap.burger.gateway.customer.dao;

import com.fiap.burger.gateway.customer.model.CustomerJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDAO extends JpaRepository<CustomerJPA, Long> {
    Optional<CustomerJPA> findByCpf(String cpf);
}

