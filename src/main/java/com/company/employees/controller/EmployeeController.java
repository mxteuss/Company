package com.company.employees.controller;

import com.company.employees.domain.model.Employee;
import com.company.employees.representation.EmployeeRepresentation;
import com.company.employees.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    public EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<Page<Employee>> findAll(){
        Page<Employee> employees = employeeService.findAll(0, 10);
        return ResponseEntity.ok(employees);


    }

    @GetMapping("/search/{id}")
    public ResponseEntity<EmployeeRepresentation> findById(@PathVariable Long id){
        Employee employee = (employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        EmployeeRepresentation employeeRepresentation = new EmployeeRepresentation();

        employeeRepresentation.setId(employee.getId());
        employeeRepresentation.setTipo(employee.getTipo());
        employeeRepresentation.setNome(employee.getNome());
        employeeRepresentation.setSalario(employee.getSalario());
        employeeRepresentation.setAdicional(employee.getAdicional());

        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel());
        employeeRepresentation.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("all-products"));

        return ResponseEntity.ok(employeeRepresentation);
    }

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee){
        return employeeService.salvar(employee);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
    try {
     employeeService.deleteById(id);
     return ResponseEntity.noContent().build();
    }
    catch (Exception e){
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar id:" + e.getMessage());
    }
    }

    @GetMapping("/report")
    public ResponseEntity<String> report(){
        return ResponseEntity.ok(employeeService.report());
    }

}
