package com.example.restservice;

import java.util.ArrayList;

public class Employees {
    private ArrayList<Employee> employees;
    private static Employees instance;

    // Constructor
    public Employees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    // Singleton pattern to ensure only one instance of Employees exists
    public static Employees getInstance() {
        if (instance == null) {
            instance = new Employees(new ArrayList<>());
        }
        return instance;
    }

    // Getter function
    public ArrayList<Employee> getEmployees() { return employees; }

    // Method to add an employee to the list
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

}