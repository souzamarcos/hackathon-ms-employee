package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.customer.request.CustomerInsertRequestDto;
import com.fiap.burger.api.dto.customer.response.CustomerResponseDto;
import com.fiap.burger.controller.adapter.api.CustomerController;
import com.fiap.burger.entity.customer.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerApiTest {

    @InjectMocks
    CustomerApi api;

    @Mock
    CustomerController controller;

    @Test
    void shouldFindById() {
        var id = "1L";
        var customer = new Customer(id, "12345678901", "email@email.com", "Nome");
        var expected = new CustomerResponseDto(id, "12345678901", "email@email.com", "Nome");

        when(controller.findById(id)).thenReturn(customer);

        CustomerResponseDto actual = api.findById(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).findById(id);
    }

    @Test
    void shouldFindByCpf() {
        var id = "1L";
        var cpf = "12345678901";
        var customer = new Customer(id, cpf, "email@email.com", "Nome");
        var expected = new CustomerResponseDto(id, cpf, "email@email.com", "Nome");

        when(controller.findByCpf(cpf)).thenReturn(customer);

        CustomerResponseDto actual = api.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(controller, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldInsert() {
        var id = "1L";
        var cpf = "12345678901";
        var request = new CustomerInsertRequestDto(cpf, "email@email.com", "Nome");
        var customer = new Customer(id, cpf, "email@email.com", "Nome");
        var expected = new CustomerResponseDto(id, cpf, "email@email.com", "Nome");

        when(controller.insert(request.toEntity())).thenReturn(customer);

        CustomerResponseDto actual = api.insert(request);

        assertEquals(expected, actual);

        verify(controller, times(1)).insert(request.toEntity());
    }

    @Test
    void shouldDelete() {
        var cpf = "12345678901";
        var expected = "Customer has been successfully deleted.";
        when(controller.deleteByCpf(cpf)).thenReturn("Customer has been successfully deleted.");

        String actual = api.deleteByCpf(cpf);

        assertEquals(expected, actual);

        verify(controller, times(1)).deleteByCpf(cpf);
    }
}
