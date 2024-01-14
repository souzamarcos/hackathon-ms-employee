package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.adapter.usecase.CustomerUseCase;
import com.fiap.burger.usecase.misc.exception.CustomerCpfAlreadyExistsException;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;

import static com.fiap.burger.usecase.misc.validation.ValidationCPF.validateCPF;
import static com.fiap.burger.usecase.misc.validation.ValidationUtils.*;

public class DefaultCustomerUseCase implements CustomerUseCase {

    private final CustomerGateway repository;

    public DefaultCustomerUseCase(CustomerGateway repository) {
        this.repository = repository;
    }

    public Customer findById(Long id) {
        return repository.findById(id);
    }

    public Customer findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Customer insert(Customer customer) {
        Customer persistedCustomer = findByCpf(customer.getCpf());
        if (persistedCustomer != null) {
            throw new CustomerCpfAlreadyExistsException();
        }
        validateCustomerToInsert(customer);
        return repository.save(customer);
    }

    private void validateCustomerToInsert(Customer customer) {
        if (customer.getId() != null) throw new InvalidAttributeException("Customer should not have defined id", "id");
        validateCustomer(customer);
    }

    private void validateCustomer(Customer customer) {
        validateNotNull(customer.getCpf(), "cpf");
        validateNotBlank(customer.getCpf(), "cpf");
        validateCPF(customer.getCpf());
        validateNotNull(customer.getEmail(), "email");
        validateNotBlank(customer.getEmail(), "email");
        validateEmailFormat(customer.getEmail());
        validateNotNull(customer.getName(), "name");
        validateNotBlank(customer.getName(), "name");
    }
}