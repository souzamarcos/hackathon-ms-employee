package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.employee.request.EmployeeInsertRequestDto;
import com.fiap.burger.api.dto.employee.response.EmployeeResponseDto;
import com.fiap.burger.api.dto.employee.response.LoginResponseDto;
import com.fiap.burger.controller.adapter.api.EmployeeController;
import com.fiap.burger.entity.employee.Employee;
import com.fiap.burger.entity.employee.EmployeeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeApiTest {

    @InjectMocks
    EmployeeApi api;

    @Mock
    EmployeeController controller;

    @Test
    void shouldFindById() {
        var id = "ABC001";
        var employee = new Employee(id, "Nome", "email@email.com", EmployeeType.USER, "senha");
        var expected = new EmployeeResponseDto(id, "Nome", "email@email.com", EmployeeType.USER);

        when(controller.findById(id)).thenReturn(employee);

        EmployeeResponseDto actual = api.findById(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).findById(id);
    }

    @Test
    void shouldInsert() {
        var id = "ABC001";
        var request = new EmployeeInsertRequestDto(id, "Nome", "email@email.com", EmployeeType.USER, "senha");
        var employee = new Employee(id, "Nome", "email@email.com", EmployeeType.USER, "senha");
        var expected = new EmployeeResponseDto(id, "Nome", "email@email.com", EmployeeType.USER);

        when(controller.insert(employee)).thenReturn(employee);

        EmployeeResponseDto actual = api.insert(request);

        assertEquals(expected, actual);

        verify(controller, times(1)).insert(employee);
    }

    @Test
    void shouldLogin() {
        var id = "ABC001";
        var password = "senha";
        var token = "token-ABC001-senha";
        var expected = new LoginResponseDto(token);

        when(controller.login(id, password)).thenReturn(token);

        LoginResponseDto actual = api.login(id, password);

        assertEquals(expected, actual);

        verify(controller, times(1)).login(id, password);
    }
}
