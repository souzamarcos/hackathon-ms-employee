package com.fiap.burger.gateway.customercpf.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCpfModelTest {

    @Test
    void shouldUseSettersCorrectly() {
        var newCpf = "123456";
        var newId = 123L;

        var actual = new CustomerCpfModel("oldCpf", 1L);
        actual.setCpf(newCpf);
        actual.setId(newId);

        assertEquals(actual.getCpf(), newCpf);
        assertEquals(actual.getId(), newId);
    }

    @Test
    void shouldCreateInstanceCorrectly() {
        var cpf = "123456";
        var id = 1L;

        var actual = new CustomerCpfModel(cpf, id);

        assertEquals(actual.getCpf(), cpf);
        assertEquals(actual.getId(), id);
    }

    @Test
    void shouldMatchToString() {
        var expected = "Customer [cpf=123456, id=1]";
        var customerCpf = new CustomerCpfModel("123456", 1L);

        var actual = customerCpf.toString();

        assertEquals(expected, actual);
    }
}