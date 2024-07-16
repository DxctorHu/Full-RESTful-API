package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String name;

    @Column
    public String job;

    @Column
    public int salary;

    // Constructors

    public Employee(int id, String name, String job, int salary) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;

    }

    public Employee() {

    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String firstname) {
        this.name = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}