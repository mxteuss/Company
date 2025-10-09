package com.company.employees.domain.model;

import com.company.employees.domain.model.Enum.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
    public class Terceirizado extends Employee {

    public Terceirizado(Long id, Tipo tipo, String nome, Integer salario, Integer adicional) {
        super(id, tipo, nome, salario, adicional);
    }
}

