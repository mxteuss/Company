package service;

import controller.save;
import model.Enum.Tipo;
import model.Funcionarios;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class adicionarFuncionario {
    public static Funcionarios addFunc(){
        Scanner sc = new Scanner(System.in);
        List<String> funcionariosList = new ArrayList<>();
        System.out.println("O funcionário é Efetivo? ");
        String res = sc.nextLine();
        if (res.equalsIgnoreCase("Sim")){
            Tipo tipoStr = Tipo.EFETIVO;
            System.out.println("Nome do funcionário: ");
            String nomeStr = sc.nextLine();

            System.out.println("Salário base do funcionário(Efetivo): ");
            double sBase = sc.nextDouble();

            return new Funcionarios.Efetivo(tipoStr, nomeStr, sBase);
        } else if (res.equalsIgnoreCase("Não")) {
            Tipo tipoStr = Tipo.TERCEIRIZADO;

            System.out.println("Nome do funcionário: ");
            String nomeStr = sc.nextLine();

            System.out.println("Salário base do funcionário (Terceirizado): ");
            double sBase = sc.nextDouble();

            System.out.println("Insira a adicional variável: ");
            double addVar = sc.nextDouble();
            return new Funcionarios.Terceirizado(tipoStr, nomeStr, sBase, addVar);
        }
        else {
            System.out.println("Resposta inválida! ");
            return null;
        }

    }
}
