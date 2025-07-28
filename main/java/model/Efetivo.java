package model;

import model.Enum.Tipo;


    public class Efetivo extends Employee {

        public Efetivo(Tipo tipo, String nome, Integer salarioBase) {
            super(tipo, nome, salarioBase);
        }
        public Efetivo() {}
        public double bonusFixo = 500.00;
        @Override
        protected Integer salarioFinal(){
            final int sFinal = (int) (salarioBase + bonusFixo);
            return sFinal;

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


