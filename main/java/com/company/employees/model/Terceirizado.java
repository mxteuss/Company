package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
    public class Terceirizado extends Employee {

        @Override
        protected Integer salarioFinal(){
            return salarioBase + adicionalVar;
        }
        public Integer calcularSalarioFinal(){
            return salarioFinal();
        }

        @Override
        public String toString() {
            return tipo.getDesc() +
                    ";" + nome +
                    ";" + salarioBase +
                    ";" + adicionalVar;
        }
    }

