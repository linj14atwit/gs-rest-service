package com.example.restservice;

public class Employee {
    private long employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String title;

    // Default constructor for JSON deserialization
    public Employee() {}

    // Constructor
    public Employee(long employee_id, String first_name, String last_name, String email, String title) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.title = title;
    }

    // Getter functions
    public long getEmployee_id() { return employee_id; }
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getEmail() { return email; }
    public String getTitle() { return title; }

    // Setter functions
    public void setEmployee_id(long employee_id) { this.employee_id = employee_id; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setEmail(String email) { this.email = email; }
    public void setTitle(String title) { this.title = title; }
}