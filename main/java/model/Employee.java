package model;

import model.Enum.Tipo;

public abstract class Employee {
    protected Tipo tipo;
    protected String nome;
    protected Integer salarioBase;
    protected Integer adicionalVar;

    public Employee() {
    }

    // construtor pra terceirizado
    public Employee(Tipo tipo,  String nome, Integer salarioBase, Integer adicionalVar) {
        this.tipo = tipo;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.adicionalVar = adicionalVar;
    }

    // construtor pra efetivo
    public Employee(Tipo tipo, Integer salarioBase, String nome) {
        this.tipo = tipo;
        this.salarioBase = salarioBase;
        this.nome = nome;
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

    protected abstract Integer salarioFinal();
}
