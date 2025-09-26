package com.company.employees.model;

import com.company.employees.model.Enum.Tipo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
    public class Terceirizado extends Employee {

    public Terceirizado(Long id, Tipo tipo, String nome, Integer salario, Integer adicional) {
        super(id, tipo, nome, salario, adicional);
    }
}

