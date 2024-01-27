package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.misc.CustomerBuilder;
import com.fiap.burger.usecase.misc.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultCustomerUseCaseTest {

    @Mock
    CustomerGateway gateway;

    @InjectMocks
    DefaultCustomerUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = "1L";
        var expected = new CustomerBuilder().withId(id).build();

        when(gateway.findById(id)).thenReturn(expected);

        var actual = useCase.findById(id);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findById(id);
    }

    @Test
    void shouldFindByCpf() {
        var cpf = "1234567901";
        var expected = new CustomerBuilder().withCpf(cpf).build();

        when(gateway.findByCpf(cpf)).thenReturn(expected);

        var actual = useCase.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldSaveCustomer() {
        Customer customer = new CustomerBuilder().withId(null).build();

        when(gateway.save(customer)).thenReturn(customer);

        Customer actual = useCase.insert(customer);

        assertEquals(customer, actual);

        verify(gateway, times(1)).save(customer);
    }


    @Test
    void shouldThrowCustomerCpfAlreadyExistsExceptionWhenCustomerAlreadyExistsToInsert() {
        var cpf = "68203895077";
        Customer customer = new CustomerBuilder().withId(null).withCpf(cpf).build();

        when(gateway.findByCpf(cpf)).thenReturn(customer);

        assertThrows(CustomerCpfAlreadyExistsException.class, () -> useCase.insert(customer));

        verify(gateway, times(1)).findByCpf(cpf);
        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowInvalidAttributeExceptionWhenCustomerIdIsNotNullToInsert() {
        Customer customer = new CustomerBuilder().withId("1L").build();

        assertThrows(InvalidAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenCustomerCpfIsNullToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withCpf(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenCustomerCpfIsBlankToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withCpf("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowInvalidCPFExceptionExceptionWhenCustomerCpfHasNonNumericCharactersToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withCpf("12345A2345").build();

        assertThrows(InvalidCPFException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowInvalidCPFExceptionExceptionWhenCustomerCpfIsInvalidToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withCpf("000000000000").build();

        assertThrows(InvalidCPFException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenCustomerEmailIsNullToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withEmail(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenCustomerEmailIsBlankToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withEmail("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowInvalidEmailFormatExceptionWhenCustomerEmailHasInvalidFormatToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withEmail("emailemailemail").build();

        assertThrows(InvalidEmailFormatException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenCustomerNameIsNullToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withName(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenCustomerNameIsBlankToInsert() {
        Customer customer = new CustomerBuilder().withId(null).withName("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(customer));

        verify(gateway, times(0)).save(customer);
    }
}
