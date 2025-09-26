package com.company.employees.controller;

import com.company.employees.model.Employee;
import com.company.employees.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class EmployeeController {

    public EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> findAll(){
        Page<Employee> employees = employeeService.findAll(0, 10);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee){
        return employeeService.salvar(employee);
    }

    @PostMapping("/employees/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
 try {
     employeeService.deleteById(id);
     return ResponseEntity.noContent().build();
 }
 catch (Exception e){
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar id:" + e.getMessage());
 }

    }
}
