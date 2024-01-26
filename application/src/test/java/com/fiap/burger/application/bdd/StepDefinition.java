package com.fiap.burger.application.bdd;

import com.fiap.burger.api.dto.customer.response.CustomerResponseDto;
import com.fiap.burger.application.config.DynamoDbConfigTest;
import com.fiap.burger.application.utils.CustomerHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class StepDefinition extends CucumberIntegrationTest {

    private Response response;

    private CustomerResponseDto customerResponse;

    private String getEndpoint() { return "http://localhost:" + port + "/customers"; }

    @Quando("submeter um novo cliente")
    public CustomerResponseDto submeterUmNovoCliente() {
        DynamoDbConfigTest.cleanTable();
        var customerRequest = CustomerHelper.createCustomerRequest();
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(customerRequest)
            .when().post(getEndpoint());
        return response.then().extract().as(CustomerResponseDto.class);
    }
    @Entao("a mensagem é registrada com sucesso")
    public void mensagemRegistradaComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

    @Dado("que um cliente já foi registrado")
    public void clienteFoiRegistrado() {
        customerResponse = submeterUmNovoCliente();
    }
    @Quando("requisitar a busca de um pedido por id")
    public void requisitarBuscaDePedidoPorId() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/{id}", customerResponse.id().toString());
    }
    @Entao("cliente é exibido com sucesso")
    public void clienteExibidoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }
    @Quando("requisitar a busca de um pedido por cpf")
    public void requisitarBuscaDePedidoPorCpf() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/by-cpf/{cpf}", customerResponse.cpf().toString());
    }
}
