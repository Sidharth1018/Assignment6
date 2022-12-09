package com.assignment5.assignment5.Repository;

import com.assignment5.assignment5.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentContaining(String department);

}
