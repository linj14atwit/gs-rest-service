package com.example.restservice;



public class EmployeeManager {
    private Employees employees;

    public EmployeeManager() {
        this.employees = Employees.getInstance();
        this.employees.addEmployee(new Employee(1, "John", "Doe", "john.doe@example.com", "Software Engineer"));
        this.employees.addEmployee(new Employee(2, "Jane", "Smith", "jane.smith@example.com", "Product Manager"));
        this.employees.addEmployee(new Employee(3, "Alice", "Johnson", "alice.johnson@example.com", "Designer"));
    }

    public Employees getEmployees() {
        return employees;
    }
}