package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import model.Enum.Tipo;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
    public class Efetivo extends Employee {

        public Efetivo(Integer id, Tipo tipo, String nome, Integer salarioBase) {
            super(id, tipo, nome, salarioBase);
        }

        public double bonusFixo = 500.00;
        @Override
        protected Integer salarioFinal(){
            return (int) (salarioBase + bonusFixo);

        }
        public Integer calcularSalarioFinal(){
            return salarioFinal();
            // Uso futuro

        }

        @Override
        public String toString() {
            return tipo.getDesc() +
                    ";" + nome +
                    ";" + salarioBase +
                    ';';
        }
    }


