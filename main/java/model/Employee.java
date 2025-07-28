package model;

import model.Enum.Tipo;

public abstract class Employee {
    protected Tipo tipo;
    protected String nome;
    protected Integer salarioBase;
    protected Integer adicionalVar;

    public Employee(Tipo tipo, String nome, Integer salarioBase, Integer adicionalVar) {
    }

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

    public Integer getAdicionalVar() {
        return adicionalVar;
    }

    public void setAdicionalVar(Integer adicionalVar) {
        this.adicionalVar = adicionalVar;
    }

    public Employee(Tipo tipo, String nome, Integer salarioBase) {
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public Employee() {
    }

    protected abstract Integer salarioFinal();
}
