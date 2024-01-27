package com.fiap.burger.entity.customer;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void shouldCreateInstanceWithOnlyIdConstructor() {
        var id = "1L";

        Customer actual = new Customer(id);

        assertEquals(id, actual.getId());
    }

    @Test
    void shouldCreateInstanceWithSimpleConstructor() {
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";


        Customer actual = new Customer(cpf, email, name);

        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
    }

    @Test
    void shouldCreateInstanceWithSimpleConstructorWithId() {
        var id = "1L";
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";


        Customer actual = new Customer(id, cpf, email, name);

        assertEquals(id, actual.getId());
        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
    }

    @Test
    void shouldCreateInstanceWithFullConstructor() {
        var id = "1L";
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Customer actual = new Customer(id, cpf, email, name, createdAt, modifiedAt, null);

        assertEquals(id, actual.getId());
        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

}
