package service;

import model.Efetivo;
import model.Employee;
import model.Terceirizado;
import util.Singleton;


import java.util.Scanner;

import static model.Enum.Tipo.EFETIVO;
import static model.Enum.Tipo.TERCEIRIZADO;

public class AddEmployee {
    public static Employee add() {
        Employee employee;
        Scanner sc = Singleton.getInstance();

        System.out.println("Insira o  tipo  de contrato: ");
        String tipo = sc.nextLine();
        switch (tipo.toUpperCase()){
            case "EFETIVO":
                employee = new Efetivo();
                employee.setTipo(EFETIVO);
            break;
            case "TERCEIRIZADO":
                employee = new Terceirizado();
                employee.setTipo(TERCEIRIZADO);
            break;
            default: throw new IllegalArgumentException("Tipo inválido");

        }

        System.out.println("Insira o nome: ");
         employee.setNome(sc.nextLine());

        System.out.println("Insira o salário base: ");
        employee.setSalarioBase(sc.nextInt());

        if (employee.getTipo().equals(TERCEIRIZADO)){
            System.out.println("Adicione a adicional variável: ");
            employee.setAdicionalVar(sc.nextInt());
        }
    return employee;
    }
}
