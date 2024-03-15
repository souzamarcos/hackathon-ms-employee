package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.customer.Customer;

public interface CustomerController {
    Customer findById(String customerId);

    Customer findByCpf(String cpf);

    Customer insert(Customer customer);

    String deleteByCpf(String cpf);
}
