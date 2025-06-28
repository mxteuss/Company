package model;

import model.Enum.Tipo;

public abstract class Employee {
    protected Tipo tipo;
    protected String nome;
    protected Integer salarioBase;


    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Integer salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Employee(Tipo tipo, String nome, Integer salarioBase) {
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public Employee() {
    }

    protected abstract Double salarioFinal();

    public static class Efetivo extends Employee {
        public double bonusFixo = 500.00;
        public Efetivo(Tipo tipo,String nome, Integer salarioBase) {
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
                    ";" + nome +
                    ";" + salarioBase +
                    ';';
        }
    }




    public static class Terceirizado extends Employee {
        public Double adicionalVar;

        public Terceirizado(Tipo tipo, String nome, Integer salarioBase, double adicionalVar) {
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
                    ";" + nome +
                    ";" + salarioBase +
                    ";" + adicionalVar;
        }
    }


}
