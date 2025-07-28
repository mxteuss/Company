package UI;

import java.sql.Connection;
import java.util.Scanner;

import controller.save;
import dao.ConnectionFactory;
import dao.EmployeeDAO;
import model.Employee;
import service.*;

import model.*;
import util.Singleton;

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
        Scanner sc = Singleton.getInstance();
        int opcao = sc.nextInt();
        sc.nextLine();
        System.out.println("Opção escolhida: " + opcao);
        //
        switch (opcao) {
            case 1:
                CalculateSalary.calculate();
                break;

            case 2:
                Employee newEmp = AddEmployee.add();
                save.salvar(newEmp);
                try (Connection conn = ConnectionFactory.getConnection()){
                    save.salvarDB(conn, newEmp);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
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
