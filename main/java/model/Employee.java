package model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.Enum.Tipo;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
     protected Tipo tipo;
     protected String nome;
     protected Integer salarioBase;
     protected Integer adicionalVar;
     protected Integer id;

    public Employee(Integer id, Tipo tipo, String nome, Integer salarioBase) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    Integer salarioFinal() {
        return null;
    }
}
