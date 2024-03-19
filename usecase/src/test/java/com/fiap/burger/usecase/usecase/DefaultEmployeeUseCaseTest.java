package com.fiap.burger.usecase.usecase;

import com.fiap.burger.usecase.adapter.gateway.EmployeeGateway;
import com.fiap.burger.usecase.misc.EmployeeBuilder;
import com.fiap.burger.usecase.misc.exception.EmployeeIdAlreadyExistsException;
import com.fiap.burger.usecase.misc.exception.EmployeeNotFoundException;
import com.fiap.burger.usecase.misc.token.TokenJwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DefaultEmployeeUseCaseTest {
    @Mock
    EmployeeGateway gateway;

    @Mock
    TokenJwtUtils tokenJwtUtils;

    @InjectMocks
    DefaultEmployeeUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class insert {
        @Test
        void shouldInsert() {
            var id = "ABC001";
            var employee = new EmployeeBuilder().withId(id).build();
            var expected = new EmployeeBuilder().withId(id).build();

            when(gateway.findById(id)).thenReturn(null);
            when(gateway.save(employee)).thenReturn(employee);

            var actual = useCase.insert(employee);

            assertEquals(expected, actual);

            verify(gateway, times(1)).findById(id);
            verify(gateway, times(1)).save(employee);
        }

        @Test
        void shouldThrowEmployeeIdAlreadyExistsExceptionWhenInsertEmployee() {
            var id = "ABC001";
            var employee = new EmployeeBuilder().withId(id).build();

            when(gateway.findById(id)).thenReturn(employee);

            assertThrows(EmployeeIdAlreadyExistsException.class, () -> useCase.insert(employee));

            verify(gateway, times(1)).findById(id);
            verify(gateway, never()).save(employee);
        }
    }

    @Nested
    class findBy{
        @Test
        void shouldFindById() {
            var id = "ABC001";
            var expected = new EmployeeBuilder().withId(id).build();

            when(gateway.findById(id)).thenReturn(expected);

            var actual = useCase.findById(id);

            assertEquals(expected, actual);

            verify(gateway, times(1)).findById(id);
        }
    }

    @Nested
    class login{
        @Test
        void shouldLogin() {
            var id = "ABC001";
            var password = "password";
            var employee = new EmployeeBuilder().withId(id).withPassword(password).build();
            var expected = "gerar-token";

            when(gateway.findById(id)).thenReturn(employee);
            when(tokenJwtUtils.generateEmployeeToken(employee)).thenReturn("gerar-token");

            var actual = useCase.login(id, password);

            assertEquals(expected, actual);

            verify(gateway, times(1)).findById(id);
            verify(tokenJwtUtils, times(1)).generateEmployeeToken(employee);
        }

        @Test
        void shouldThrowEmployeeNotFoundExceptionWhenEmployeeNotFoundById() {
            var id = "ABC001";
            var password = "password";

            when(gateway.findById(id)).thenReturn(null);

            assertThrows(EmployeeNotFoundException.class, () -> useCase.login(id, password));

            verify(gateway, times(1)).findById(id);
            verify(tokenJwtUtils, never()).generateEmployeeToken(any());
        }

        @Test
        void shouldThrowEmployeeNotFoundExceptionWhenEmployeeSearchPasswordDontMatchStoredPassword() {
            var id = "ABC001";
            var searchPassword = "password";
            var storedPassword = "other-password";
            var employee = new EmployeeBuilder().withId(id).withPassword(storedPassword).build();

            when(gateway.findById(id)).thenReturn(employee);

            assertThrows(EmployeeNotFoundException.class, () -> useCase.login(id, searchPassword));

            verify(gateway, times(1)).findById(id);
            verify(tokenJwtUtils, never()).generateEmployeeToken(any());
        }
    }
}
