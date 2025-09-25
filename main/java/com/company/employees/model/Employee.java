
package com.company.employees.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import com.company.employees.model.Enum.Tipo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
     protected Tipo tipo;
     protected String nome;
     protected Integer salario;
     protected Integer adicional;


    public Employee(Long id, Tipo tipo, String nome, Integer salario) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.salario = salario;
    }


    Integer salarioFinal() {
        return null;
    }

}
