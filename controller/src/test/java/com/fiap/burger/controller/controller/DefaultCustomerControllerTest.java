package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.misc.exception.CustomerNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultCustomerControllerTest {

    @Mock
    DefaultCustomerUseCase useCase;

    @InjectMocks
    DefaultCustomerController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var expected = new Customer(id);

        when(useCase.findById(id)).thenReturn(expected);

        Customer actual = controller.findById(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findById(id);
    }

    @Test
    void shouldThrownCustomerNotFoundExceptionWhenCustomerNotFoundById() {
        var id = 1L;

        when(useCase.findById(id)).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> controller.findById(id));

        verify(useCase, times(1)).findById(id);
    }

    @Test
    void shouldFindByCpf() {
        var cpf = "12345678909";
        var expected = new Customer(1L);

        when(useCase.findByCpf(cpf)).thenReturn(expected);

        Customer actual = controller.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldThrownCustomerNotFoundExceptionWhenCustomerNotFoundByCpf() {
        var cpf = "12345678901";

        when(useCase.findByCpf(cpf)).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> controller.findByCpf(cpf));

        verify(useCase, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldInsertCustomer() {
        var customer = new Customer(1L);

        when(useCase.insert(customer)).thenReturn(customer);

        Customer actual = controller.insert(customer);

        assertEquals(customer, actual);

        verify(useCase, times(1)).insert(customer);
    }
}
