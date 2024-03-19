package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.employee.request.EmployeeInsertRequestDto;
import com.fiap.burger.api.dto.employee.response.EmployeeResponseDto;
import com.fiap.burger.api.dto.employee.response.LoginResponseDto;
import com.fiap.burger.controller.adapter.api.EmployeeController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "funcionário", description = "API responsável pelo controle de funcionários do sistema.")
public class EmployeeApi {


    @Autowired
    EmployeeController employeeController;

    @Operation(summary = "Consultar funcionário", description = "Consultar um funcionário", tags = {"funcionário"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{id}")
    public EmployeeResponseDto findById(@PathVariable String id) {
        return EmployeeResponseDto.toResponseDto(employeeController.findById(id));
    }


    @Operation(summary = "Cadastrar funcionário", description = "Cadastrar um funcionário", tags = {"funcionário"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Funcionário inválido")
    })
    @PostMapping()
    public EmployeeResponseDto insert(@RequestBody EmployeeInsertRequestDto employeeDto) {
        return EmployeeResponseDto.toResponseDto(employeeController.insert(employeeDto.toEntity()));
    }

    @Operation(summary = "Logar no sistema", description = "Gerar token de login", tags = {"funcionário"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/login")
    public LoginResponseDto login(@RequestParam @Nullable String id,
                                 @RequestParam @Nullable String password) {
        return LoginResponseDto.toResponseDto(employeeController.login(id, password));
    }
}
