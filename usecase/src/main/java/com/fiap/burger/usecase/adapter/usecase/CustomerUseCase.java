package com.fiap.burger.usecase.adapter.usecase;

import com.fiap.burger.entity.customer.Customer;

public interface CustomerUseCase {
    Customer findById(String id);

    Customer findByCpf(String cpf);

    Customer insert(Customer customer);
}
