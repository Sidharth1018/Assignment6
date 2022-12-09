package com.assignment5.assignment5.controller;

import com.assignment5.assignment5.Repository.EmployeeRepository;
import com.assignment5.assignment5.model.Employee;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllTutorials(@RequestParam(required = false) String department) {
        List<Employee> employees = new ArrayList<Employee>();

        if (department == null)
            employeeRepository.findAll().forEach(employees::add);
        else
            employeeRepository.findByDepartmentContaining(department).forEach(employees::add);

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getTutorialById(@PathVariable("id") long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createTutorial(@RequestBody Employee employee) {
        Employee _employee = employeeRepository.save(new Employee(employee.getDepartment(), employee.getName()));
        return new ResponseEntity<>(_employee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateTutorial(@PathVariable("id") long id, @RequestBody Employee employee) {
        Employee _employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _employee.setDepartment(employee.getDepartment());
        _employee.setName(employee.getName());


        return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        employeeRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
