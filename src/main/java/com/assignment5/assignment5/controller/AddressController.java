package com.assignment5.assignment5.controller;

import com.assignment5.assignment5.Repository.AddressRepository;
import com.assignment5.assignment5.Repository.EmployeeRepository;
import com.assignment5.assignment5.model.Address;
import com.assignment5.assignment5.model.Employee;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController

public class AddressController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/employees/{employeeId}/addresses")
    public ResponseEntity<List<Address>> getAllAddressesByEmployeeId(@PathVariable(value = "employeeId") Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + employeeId);
        }

        List<Address> addresses = addressRepository.findByEmployeeId(employeeId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

   

    @PostMapping("/employees/{employeeId}/addresses")
    public ResponseEntity<Address> createComment(@PathVariable(value = "employeeId") Long employeeId,
                                                 @RequestBody Address addressRequest) {
        Address address = employeeRepository.findById(employeeId).map(employee -> {
            addressRequest.setEmployee(employee);
            return addressRepository.save(addressRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + employeeId));

        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateComment(@PathVariable("id") long id, @RequestBody Address addressRequest) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        address.setContent(addressRequest.getContent());

        return new ResponseEntity<>(addressRepository.save(address), HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        addressRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{employeeId}/addresses")
    public ResponseEntity<List<Employee>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!employeeRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        addressRepository.deleteByEmployeeId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
