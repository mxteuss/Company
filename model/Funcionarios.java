package model;

public abstract class Funcionarios {
    protected String nome;
    protected double salarioBase;

    public Funcionarios(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    protected abstract Double salarioFinal();

    public static class Efetivo extends Funcionarios{
        public double bonusFixo = 500.00;
        public Efetivo(String nome, double salarioBase) {
            super(nome, salarioBase);
        }
        @Override
        protected Double salarioFinal(){
            final Double sFinal = salarioBase + bonusFixo;
            return sFinal;

        }
        public Double calcularSalarioFinal(){
            return salarioFinal();

        }
    }




    public static class Terceirizado extends Funcionarios{
        public Double adicionalVar;

        public Terceirizado(String nome, double salarioBase, double adicionalVar) {
            super(nome, salarioBase);
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
    }
}
