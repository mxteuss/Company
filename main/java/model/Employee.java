package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.Enum.Tipo;
import lombok.AllArgsConstructor;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
     Tipo tipo;
     String nome;
     Integer salarioBase;
     Integer adicionalVar;
     Integer id;

    public Employee(Integer id, Tipo tipo, String nome, Integer salarioBase) {
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    Integer salarioFinal() {
        return null;
    }
}
