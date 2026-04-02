//Example provide by Hewlett Packard Enterprise Development Job Simulation

package com.example.restservice;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.Employees;
import com.example.restservice.EmployeeManager;

// Creating the REST controller
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees()
    {
        return employeeManager.getEmployees();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        employeeManager.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

}