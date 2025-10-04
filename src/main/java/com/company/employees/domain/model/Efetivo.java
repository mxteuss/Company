package com.company.employees.domain.model;

import com.company.employees.domain.model.Enum.Tipo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
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


