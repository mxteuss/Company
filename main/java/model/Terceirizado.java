package model;

import model.Enum.Tipo;


    public class Terceirizado extends Employee {
        public Terceirizado(Tipo tipo, String nome, Integer salarioBase, Integer adicionalVar) {
            super(tipo, nome, salarioBase, adicionalVar);
        }
        public Terceirizado(){}
        @Override
        protected Integer salarioFinal(){
            final Integer sFinal = salarioBase + adicionalVar;
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
                    ";" + adicionalVar;
        }
    }

