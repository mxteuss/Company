package model;

import model.Enum.Tipo;

public abstract class Funcionarios {
    protected Tipo tipo;
    protected String nome;
    protected double salarioBase;


    public Funcionarios(Tipo tipo, String nome, double salarioBase) {
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public Funcionarios() {
    }

    protected abstract Double salarioFinal();

    public static class Efetivo extends Funcionarios{
        public double bonusFixo = 500.00;
        public Efetivo(Tipo tipo,String nome, double salarioBase) {
            super(tipo, nome, salarioBase);
        }
        @Override
        protected Double salarioFinal(){
            final Double sFinal = salarioBase + bonusFixo;
            return sFinal;

        }
        public Double calcularSalarioFinal(){
            return salarioFinal();

        }

        @Override
        public String toString() {
            return tipo.getDesc() +
                    ";" + nome + '\'' +
                    ";" + salarioBase +
                    ';';
        }
    }




    public static class Terceirizado extends Funcionarios{
        public Double adicionalVar;

        public Terceirizado(Tipo tipo, String nome, double salarioBase, double adicionalVar) {
            super(tipo, nome, salarioBase);
            this.adicionalVar = adicionalVar;
        }

        @Override
        protected Double salarioFinal(){
            final Double sFinal = salarioBase + adicionalVar;
            return sFinal;
        }

        public Double calcularSalarioFinal(){
            return salarioFinal();
        }

        @Override
        public String toString() {
            return tipo.getDesc() +
                    ";" + nome + '\'' +
                    ";" + salarioBase +
                    ";" + adicionalVar;
        }
    }


}
