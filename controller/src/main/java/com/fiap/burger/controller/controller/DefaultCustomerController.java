package com.fiap.burger.controller.controller;

import com.fiap.burger.controller.adapter.api.CustomerController;
import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.usecase.CustomerUseCase;
import com.fiap.burger.usecase.misc.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class DefaultCustomerController implements CustomerController {
    @Autowired
    private CustomerUseCase useCase;

    public Customer findById(@PathVariable String customerId) {
        var persistedCustomer = useCase.findById(customerId);
        if (persistedCustomer == null) throw new CustomerNotFoundException(customerId);
        return persistedCustomer;
    }

    public Customer findByCpf(@PathVariable String customerCpf) {
        var persistedCustomer = useCase.findByCpf(customerCpf);
        if (persistedCustomer == null) throw new CustomerNotFoundException();
        return persistedCustomer;
    }

    public Customer insert(Customer customer) {
        return useCase.insert(customer);
    }

    public String deleteByCpf(String cpf) {
        useCase.deleteByCpf(cpf);
        return "Customer has been successfully deleted.";
    }
}