package com.example.demo.dto;

public class EmployeeDTO {
    private int id;
    private String name;

    // Constructor
//    public EmployeeDTO(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
}