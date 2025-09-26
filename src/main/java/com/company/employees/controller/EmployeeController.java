package com.company.employees.controller;

import com.company.employees.model.Employee;
import com.company.employees.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
public class EmployeeController {

    public EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> findAll(){
        Page<Employee> employees = employeeService.findAll(0, 10);
        return ResponseEntity.ok(employees);
    }
}
