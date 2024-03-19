package com.fiap.hackathon.controller.controller;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import com.fiap.hackathon.usecase.adapter.usecase.EmployeeUseCase;
import com.fiap.hackathon.usecase.misc.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DefaultEmployeeControllerTest {
    @Mock
    EmployeeUseCase useCase;
    @InjectMocks
    DefaultEmployeeController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class insert {
        @Test
        void shouldInsertEmployee() {
            var employee = new Employee("ABC001", "Nome", "email@email.com", EmployeeType.USER, "senha");

            when(useCase.insert(employee)).thenReturn(employee);

            Employee actual = controller.insert(employee);

            assertEquals(employee, actual);

            verify(useCase, times(1)).insert(employee);
        }
    }

    @Nested
    class findById {
        @Test
        void shouldFindById() {
            var id = "ABC001";
            var expected = new Employee("ABC001", "Nome", "email@email.com", EmployeeType.USER);

            when(useCase.findById(id)).thenReturn(expected);

            Employee actual = controller.findById(id);

            assertEquals(expected, actual);

            verify(useCase, times(1)).findById(id);
        }

        @Test
        void shouldThrownEmployeeNotFoundExceptionWhenEmployeeNotFoundById() {
            var id = "ABC001";

            when(useCase.findById(id)).thenReturn(null);

            assertThrows(EmployeeNotFoundException.class, () -> controller.findById(id));

            verify(useCase, times(1)).findById(id);
        }
    }

    @Nested
    class login {
        @Test
        void shouldLogin() {
            var id = "ABC001";
            var password = "senha";
            var expected = "token-ABC001-senha";

            when(useCase.login(id, password)).thenReturn(expected);

            String actual = controller.login(id, password);

            assertEquals(expected, actual);

            verify(useCase, times(1)).login(id, password);
        }

        @Test
        void shouldThrownEmployeeNotFoundExceptionWhenLoginFail() {
            var id = "ABC001";
            var password = "senha";

            when(useCase.login(id, password)).thenReturn(null);

            assertThrows(EmployeeNotFoundException.class, () -> controller.login(id, password));

            verify(useCase, times(1)).login(id, password);
        }
    }
}
