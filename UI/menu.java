package UI;

import java.util.Scanner;

import Main.ProgramaInicial;
import model.Funcionarios;
import service.adicionarFuncionario;

public class menu {
    public static void main(String[] args) {

        System.out.println("==================");
        System.out.println("       MENU       ");
        System.out.println("==================");

        System.out.println("Escolhe a opção desejada: ");

        System.out.println("1 - Calcular salário de funcionário da empresa");
        System.out.println("2 - Adicionar novo funcionário");
        Scanner sc = new Scanner(System.in);
        String opcao = sc.nextLine();

        if (opcao.equals("1")){
        ProgramaInicial.main(new String[] {});
        }

        else if (opcao.equals("2")){
            Funcionarios f = adicionarFuncionario.addFunc();
        }

        // O projeto tera novas features à medida que eu for evoluindo
    }
}
