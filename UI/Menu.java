package UI;

import java.util.Scanner;

import service.*;
import model.Employee;

public class Menu {
    public static void main(String[] args) {

        System.out.println("========================");
        System.out.println("||         MENU        ||  ");
        System.out.println("========================");

        System.out.println("Escolhe a opção desejada: ");

        System.out.println("1 - Calcular salário dos funcionários da empresa");
        System.out.println("2 - Adicionar novo funcionário");
        System.out.println("3 - Visualizar funcionários");
        System.out.println("4 - Buscar funcionário");
        System.out.println("5 - Relatório da empresa");
        System.out.println("6 - Editar dados de funcionário");
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        System.out.println("Opção escolhida: " + opcao);
        //
        switch (opcao) {
            case 1:
                CalculateSalary.calculate();
                break;

            case 2:
                AddEmployee.add();
                break;

            case 3:
                ViewEmployee.view();
                break;
            case 4:
                SearchEmployee.search();
                break;
            case 5:
                ReportService.loadReport();
                break;
            case 6:
                EditEmployee.edit();
        }
        // O projeto tera novas features à medida que eu for evoluindo
    }
}
