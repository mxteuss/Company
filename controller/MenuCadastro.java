package controller;

import controller.save;
import model.Funcionarios;
import service.adicionarFuncionario;

public class MenuCadastro {
    public static void main(String[] args) {
        Funcionarios f = adicionarFuncionario.addFunc();
        if (f != null) {
            save.salvar(f);
        } else {
            System.out.println("⚠ Cadastro cancelado.");
        }
    }
}
