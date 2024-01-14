package com.fiap.burger.gateway.customer.gateway;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.gateway.customer.dao.CustomerDAO;
import com.fiap.burger.gateway.customer.model.CustomerJPA;
import com.fiap.burger.usecase.adapter.gateway.CustomerCpfGateway;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultCustomerGateway implements CustomerGateway {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerCpfGateway customerCpfGateway;

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id).map(CustomerJPA::toEntity).orElse(null);
    }

    @Override
    public Customer findByCpf(String cpf) {
        Optional<Customer> customer = customerDAO.findByCpf(cpf).map(CustomerJPA::toEntity);
        return customer.orElse(null);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        Customer persisted = customerDAO.save(CustomerJPA.toJPA(customer)).toEntity();
        customerCpfGateway.save(persisted.getCpf(), persisted.getId());
        return persisted;
    }

}
