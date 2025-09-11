package UI;

import java.util.List;
import java.util.Scanner;

import service.employeeService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import model.Employee;
import service.*;

import util.Singleton;

@Data
@Log4j2
public class Menu {
    public static void main(String[] args) {
        Employee newEmp;
        log.info("========================");
        log.info("||         MENU        ||");
        log.info("========================");

        log.info("Escolhe a opção desejada: ");


        log.info("1 - Adicionar novo funcionário");
        log.info("2 - Visualizar funcionários");
        log.info("3 - Buscar funcionário");
        log.info("4 - Relatório da empresa");
        log.info("5 - Editar dados de funcionário");
        log.info("6 - Excluir dados de funcionário");
        Scanner sc = Singleton.getInstance();
        int opcao = sc.nextInt();
        sc.nextLine();
        log.info("Opção escolhida: " + opcao);

        //
        switch (opcao) {
            case 1:
                newEmp = AddEmployee.add();
                employeeService.salvar(newEmp);
                break;

            case 2:
                StringBuilder all = employeeService.findAll();
                log.info(all);
                break;
            case 3:
                String search = employeeService.search();
                log.info(search);
                break;
            case 4:
               employeeService.report();
                break;
            case 5:
                employeeService.uptade();
                break;
            case 6:
                employeeService.delete();
                break;
            default:
                log.error("Erro");
        }
    }
}
