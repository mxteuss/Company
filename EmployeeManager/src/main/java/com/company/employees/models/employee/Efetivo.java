package com.company.employees.models.employee;

import com.company.employees.models.employee.Enum.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")

    public class Efetivo extends Employee {

        public Efetivo(Long id, Tipo tipo, String nome, Integer salario) {
            super(id, tipo, nome, salario);
        }


    public double bonusFixo = 500.00;

        protected Integer salarioFinal(){
            return (int) (salario + bonusFixo);

        }
        public Integer calcularSalarioFinal(){
            return salarioFinal();
            // Uso futuro

        }

    }


