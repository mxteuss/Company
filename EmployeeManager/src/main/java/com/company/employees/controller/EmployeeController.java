package com.company.employees.controller;

import com.company.employees.infra.SecurityConfiguration;
import com.company.employees.models.employee.Employee;
import com.company.employees.models.employee.EmployeeDTO;
import com.company.employees.producer.EmployeeProducer;
import com.company.employees.representation.EmployeeRepresentation;
import com.company.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@AllArgsConstructor
@RequestMapping("/employees")
@Tag(name = "employee-manager")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class EmployeeController {

    public EmployeeService employeeService;
    private EmployeeProducer employeeProducer;

    @Operation(summary = "Listar funcionários", description = "Lista todos os funcionários cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Funcionários listados.")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @GetMapping("/list")
    public ResponseEntity <@NonNull Page<@NonNull Employee>> findAll(){
        Page<@NonNull Employee> employees = employeeService.findAll(0, 10);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar por id", description = "Busca um funcionário cadastrado no sistema pelo id.")
    @ApiResponse(responseCode = "200", description = "Funcionário encontrado.")
    @ApiResponse(responseCode = "400", description = "Id informado não existe no sistema.")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<@NonNull EmployeeRepresentation> findById(@PathVariable Long id){
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
    @Operation(summary = "Cadastrar funcionários", description = "Cadastra um novo funcionário no sistema.")
    @ApiResponse(responseCode = "200", description = "Funcionário cadastrado ao sistema com sucesso.")
    @ApiResponse(responseCode = "400", description = "Informações pendentes.")
    @ApiResponse(responseCode = "500", description = "Erro no servidor.")
    public ResponseEntity<@NonNull Employee> save(@RequestBody EmployeeDTO  employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        if (employee.getTipo().getDesc().equalsIgnoreCase("Efetivo")){
            employee.setAdicional(null);
        }
        employeeService.save(employee);
        return ResponseEntity.ok().body(employee);

    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Deletar por id")
    @ApiResponse(responseCode = "200", description = "Funcionário deletado do sistema com sucesso.")
    @ApiResponse(responseCode = "400", description = "Id informado não existe no sistema.")
    @ApiResponse(responseCode = "500", description = "Erro no servidor.")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
    try {
     employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    catch (Exception e){
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar id:" + e.getMessage());
    }
    }

    @Operation(summary = "Relatório", description = "Retorna um relatório em email dos usuários cadastrados e seus salários.")
    @ApiResponse(responseCode = "200", description = "Relatório enviado com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro no servidor.")
    @GetMapping("/report")
    public ResponseEntity<@NonNull String> report(){
        System.out.println("Enviando relatório por email...");
        byte[] pdf = employeeProducer.sendReportEmail();

        if (pdf == null){
            System.out.println("Erro ao enviar o relatório.");
            return ResponseEntity.noContent().build();
        }
        else {
            System.out.println("Relatório enviado com sucesso.");
            return ResponseEntity.ok("Relatório enviado com sucesso.");
        }
    }

}
