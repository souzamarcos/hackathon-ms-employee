package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.customer.request.CustomerInsertRequestDto;
import com.fiap.burger.api.dto.customer.response.CustomerResponseDto;
import com.fiap.burger.controller.adapter.api.CustomerController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Tag(name = "cliente", description = "API responsável pelo controle de clientes.")
public class CustomerApi {

    @Autowired
    CustomerController customerController;

    @Operation(summary = "Consultar cliente", description = "Consultar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{customerId}")
    public CustomerResponseDto findById(@PathVariable Long customerId) {
        return CustomerResponseDto.toResponseDto(customerController.findById(customerId));
    }

    @Operation(summary = "Buscar cliente por cpf", description = "Buscar cliente por cpf", tags = {"cliente"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/by-cpf/{customerCpf}")
    public CustomerResponseDto findByCpf(@PathVariable String customerCpf) {
        return CustomerResponseDto.toResponseDto(customerController.findByCpf(customerCpf));
    }

    @Operation(summary = "Cadastrar cliente", description = "Cadastrar um cliente", tags = {"cliente"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Cliente inválido")
    })
    @PostMapping()
    public CustomerResponseDto insert(@RequestBody CustomerInsertRequestDto customerDto) {
        return CustomerResponseDto.toResponseDto(customerController.insert(customerDto.toEntity()));
    }

}
