package com.fiap.hackathon.gateway.misc;

import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;

public class EmployeeBuilder {

    private String id = "ABC001";
    private String name = "Nome";
    private String email = "email@email.com";
    private EmployeeType type =EmployeeType.USER;
    private String password = "password";

    public EmployeeBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder withType(EmployeeType type) {
        this.type = type;
        return this;
    }

    public EmployeeBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    public Employee build() {
        return new Employee(id, name, email, type, password);
    }

}
