package service;

import model.Employee;
import util.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditEmployee {
    public static Employee edit(){
        Scanner sc = Singleton.getInstance();
        Path caminho = Paths.get("src/data/funcionarios.txt");

        try {
            List<String> linhas = (Files.readAllLines(caminho));

            for (int i = 0; i < linhas.size(); i++ ){
                System.out.println((i + 1) + " - " + linhas.get(i));
            }
            System.out.println("Insira o número correspondente do funcionário desejado: ");
            int opcao = sc.nextInt();
            sc.nextLine();
            if (opcao > linhas.size()){
                throw new IllegalArgumentException("Funcionário não encontrado");
            }

            int index = opcao - 1;
            String linha = linhas.get(index);
            String [] partes = linha.split(";");

            if (partes[0].equals("Efetivo")){
                System.out.println("O funcionário terá um novo modelo contratual? ");
                String tipoStr = null;
                if (sc.nextLine().equalsIgnoreCase("Sim")){
                     tipoStr = "Terceirizado";
                    System.out.println("Insira o novo salário do funcionário: ");
                    String salario = sc.nextLine();
                    System.out.println("Insira o novo bônus fixo: ");
                    String bFixo = sc.nextLine();
                    String novaLinha = String.format("%s;%s;%s;%s", tipoStr, partes[1], salario, bFixo);
                    linhas.set(index, novaLinha);

                }
                System.out.println("Insira o novo salário do funcionário: ");
                String salario = sc.nextLine();
                String novaLinha = String.format("%s;%s;%s;", tipoStr, partes[1], salario);
                linhas.set(index, novaLinha);


            } else if (partes[0].equals("Terceirizado")) {
                System.out.println("O funcionário terá um novo modelo contratual? ");
                String tipoStr = null;
                if (sc.nextLine().equalsIgnoreCase("Sim")){
                    tipoStr = "Efetivo";
                    System.out.println("Insira o novo salário do funcionário: ");
                    String salario = sc.nextLine();
                    String novaLinha = String.format("%s;%s;%s;", tipoStr, partes[1], salario);
                    linhas.set(index, novaLinha);


                } else if (sc.nextLine().equalsIgnoreCase("Não")) {
                    System.out.println("Insira o novo salário do funcionário: ");
                    String salario = sc.nextLine();
                    System.out.println("Insira o novo bônus fixo: ");
                    String bFixo = sc.nextLine();
                    String novaLinha = String.format("%s;%s;%s;%s", tipoStr, partes[1], salario, bFixo);
                    linhas.set(index, novaLinha);

                }


            }
            Files.write(caminho, linhas, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Dados do funcionário editados com sucesso! ");

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
