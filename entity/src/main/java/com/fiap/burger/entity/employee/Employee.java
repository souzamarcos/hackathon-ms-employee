package com.fiap.burger.entity.employee;

import com.fiap.burger.entity.common.AuditableEntity;

import java.util.Objects;

public class Employee extends AuditableEntity {

    private String id;
    private String name;
    private String email;
    private String password;
    private EmployeeType type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && type == employee.type && Objects.equals(password, employee.password) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, type, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", type=" + type +
            ", password='" + password + '\'' +
            '}';
    }

    public Employee(String id, String name, String email, EmployeeType type, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public Employee(String id, String name, String email, EmployeeType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }
}
