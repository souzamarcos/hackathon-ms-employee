package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.customer.Customer;

public interface CustomerUseCase {
    Customer findById(Long id);

    Customer findByCpf(String cpf);

    Customer insert(Customer customer);
}
