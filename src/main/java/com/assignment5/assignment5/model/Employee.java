package com.assignment5.assignment5.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    private long id;

    @Column(name = "name")
    private String name;



    @Column(name = "department")
    private String  department;

    public Employee() {
    }

    public Employee(String department, String name) {
    }

    public Employee(long id, String name, String department) {
        this.id = id;
        this.name = name;

        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
