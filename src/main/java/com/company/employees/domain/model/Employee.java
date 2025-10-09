
package com.company.employees.domain.model;

import jakarta.persistence.*;
import lombok.*;
import com.company.employees.domain.model.Enum.Tipo;

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
