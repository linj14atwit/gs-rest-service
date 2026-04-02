package com.example.restservice;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeManager employeeManager;

    private Employees mockEmployees;

    @BeforeEach
    public void setUp() {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "John", "Doe", "john.doe@example.com", "Software Engineer"));
        list.add(new Employee(2, "Jane", "Smith", "jane.smith@example.com", "Product Manager"));
        mockEmployees = new Employees(list);
    }

    @Test
    public void getEmployeesShouldReturnAllEmployees() throws Exception {
        when(employeeManager.getEmployees()).thenReturn(mockEmployees);

        mockMvc.perform(get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees").isArray())
                .andExpect(jsonPath("$.employees.length()").value(2))
                .andExpect(jsonPath("$.employees[0].employee_id").value(1))
                .andExpect(jsonPath("$.employees[0].first_name").value("John"))
                .andExpect(jsonPath("$.employees[0].last_name").value("Doe"))
                .andExpect(jsonPath("$.employees[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.employees[0].title").value("Software Engineer"));
    }

    @Test
    public void getEmployeesShouldReturnEmptyListWhenNoEmployees() throws Exception {
        when(employeeManager.getEmployees()).thenReturn(new Employees(new ArrayList<>()));

        mockMvc.perform(get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees").isArray())
                .andExpect(jsonPath("$.employees.length()").value(0));
    }

    @Test
    public void createEmployeeShouldReturn201WithCreatedEmployee() throws Exception {
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        String requestBody = """
                {
                    "employee_id": "3",
                    "first_name": "Alice",
                    "last_name": "Johnson",
                    "email": "alice.johnson@example.com",
                    "title": "Designer"
                }
                """;

        mockMvc.perform(post("/employees/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employee_id").value(3))
                .andExpect(jsonPath("$.first_name").value("Alice"))
                .andExpect(jsonPath("$.last_name").value("Johnson"))
                .andExpect(jsonPath("$.email").value("alice.johnson@example.com"))
                .andExpect(jsonPath("$.title").value("Designer"));
    }

    @Test
    public void createEmployeeShouldCallAddEmployee() throws Exception {
        doNothing().when(employeeManager).addEmployee(any(Employee.class));

        String requestBody = """
                {
                    "employee_id": "4",
                    "first_name": "Bob",
                    "last_name": "Brown",
                    "email": "bob.brown@example.com",
                    "title": "Engineer"
                }
                """;

        mockMvc.perform(post("/employees/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        verify(employeeManager, times(1)).addEmployee(any(Employee.class));
    }
}
