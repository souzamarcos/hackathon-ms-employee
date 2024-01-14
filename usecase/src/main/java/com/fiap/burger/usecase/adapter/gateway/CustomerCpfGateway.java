package com.fiap.burger.usecase.adapter.gateway;


public interface CustomerCpfGateway {

    void save(String cpf, Long customerId);

    Long findByCpf(String cpf);

}
