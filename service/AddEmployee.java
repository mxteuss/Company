package service;

import controller.save;
import model.Enum.Tipo;
import model.Employee;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Enum.Tipo.EFETIVO;
import static model.Enum.Tipo.TERCEIRIZADO;

public class AddEmployee {
    public static Employee add() {
        Employee employee =  null;
        Scanner sc = new Scanner(System.in);

        System.out.println("O funcionário é Efetivo? ");
        String res = sc.nextLine();
        if (res.equalsIgnoreCase("Sim")) {
            System.out.println("Nome do funcionário: ");
            String nomeStr = sc.nextLine();

            System.out.println("Salário base do funcionário(Efetivo): ");
            int sBase = sc.nextInt();

            employee = new Employee.Efetivo(EFETIVO, nomeStr, sBase);
            save.salvar(employee);
        } else if (res.equalsIgnoreCase("Não")) {

            System.out.println("Nome do funcionário: ");
            String nomeStr = sc.nextLine();

            System.out.println("Salário base do funcionário (Terceirizado): ");
            int sBase = sc.nextInt();

            System.out.println("Insira a adicional variável: ");
            double addVar = sc.nextDouble();
            employee = new Employee.Terceirizado(TERCEIRIZADO, nomeStr, sBase, addVar);
            save.salvar(employee);

        } else {
            System.out.println("Resposta inválida! ");

        }
        sc.close();
        return employee;
    }
}
