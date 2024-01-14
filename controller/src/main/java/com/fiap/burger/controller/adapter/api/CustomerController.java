package com.fiap.burger.controller.adapter.api;

import com.fiap.burger.entity.customer.Customer;

public interface CustomerController {
    Customer findById(Long customerId);

    Customer findByCpf(String cpf);

    Customer insert(Customer customer);
}
