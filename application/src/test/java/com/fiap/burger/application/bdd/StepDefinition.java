package com.fiap.burger.application.bdd;

import com.fiap.burger.api.dto.employee.response.EmployeeResponseDto;
import com.fiap.burger.application.config.DynamoDbConfigTest;
import com.fiap.burger.application.utils.EmployeeHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class StepDefinition extends CucumberIntegrationTest {

    private Response response;

    private EmployeeResponseDto employeeResponseDto;

    private String getEndpoint() { return "http://localhost:" + port + "/employees"; }

    @Quando("submeter um novo funcionário")
    public EmployeeResponseDto submeterUmNovoFuncionario() {
        DynamoDbConfigTest.cleanTableEmployee();
        var employeeRequest = EmployeeHelper.createEmployeeRequest();
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(employeeRequest)
            .when().post(getEndpoint());
        return response.then().extract().as(EmployeeResponseDto.class);
    }
    @Entao("o funcionário é registrado com sucesso")
    public void funcionarioRegistradoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

    @Dado("que um funcionário já foi registrado")
    public void funcionarioFoiRegistrado() {
        employeeResponseDto = submeterUmNovoFuncionario();
    }
    @Quando("requisitar a busca de um funcionário por id")
    public void requisitarBuscaDeFuncionarioPorId() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/{id}", employeeResponseDto.id().toString());
    }
    @Entao("funcionário é exibido com sucesso")
    public void funcionarioExibidoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar login")
    public void requisitarLogin() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/login?id={id}&password={password}", employeeResponseDto.id().toString(), "password");
    }

    @Entao("token é exibido com sucesso")
    public void tokenExibidoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value());
    }

}
