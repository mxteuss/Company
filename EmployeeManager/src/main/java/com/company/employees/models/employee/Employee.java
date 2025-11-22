
package com.company.employees.models.employee;

import jakarta.persistence.*;
import lombok.*;
import com.company.employees.models.employee.Enum.Tipo;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Enumerated(EnumType.STRING)
     protected Tipo tipo;
     protected String nome;
     protected Integer salario;
     protected Integer adicional;
     private String email;

    public Employee(Long id, Tipo tipo, String nome, Integer integer, Integer salario) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.salario = salario;
    }

    public Employee(EmployeeDTO data){
        this.tipo = data.tipo();
        this.nome = data.name();
        this.salario = data.salario();
        this.adicional = data.adicional();
    }
}
