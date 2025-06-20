package controller;

import model.Employee;
import service.AddEmployee;

public class MenuCadastro {
    public static void main(String[] args) {
        Employee f = AddEmployee.add();
        if (f != null) {
            save.salvar(f);
        } else {
            System.out.println("⚠ Cadastro cancelado.");
        }
    }
}
