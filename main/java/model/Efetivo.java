package model;

import model.Enum.Tipo;


    public class Efetivo extends Employee {

        public Efetivo(Tipo tipo, String nome, Integer salarioBase) {
            super(tipo, salarioBase, nome);

        }

        public Efetivo() {}
        public double bonusFixo = 500.00;
        @Override
        protected Integer salarioFinal(){
            return (int) (salarioBase + bonusFixo);

        }
        public Integer calcularSalarioFinal(){
            return salarioFinal();

        }

        @Override
        public String toString() {
            return tipo.getDesc() +
                    ";" + nome +
                    ";" + salarioBase +
                    ';';
        }
    }


