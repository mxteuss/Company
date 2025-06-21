package service;


import model.Employee;
import model.Enum.Tipo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SearchEmployee {
    public static Employee search(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do funcionário para busca: ");
        String nomeStr = sc.nextLine();
        String linha;
        Employee employeeFounded = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/funcionarios.txt"))){
            while ((linha = br.readLine()) != null){
                String [] partes = linha.split(";");
                if (partes[1].equalsIgnoreCase(nomeStr)){
                    if (partes[0].equals(Tipo.EFETIVO.getDesc())){
                        employeeFounded = new Employee.Efetivo(Tipo.EFETIVO, partes[1], Integer.parseInt(partes[2]));
                        System.out.println(employeeFounded);

                    } else if (partes[0].equals(Tipo.TERCEIRIZADO.getDesc())) {
                        employeeFounded = new Employee.Terceirizado(Tipo.TERCEIRIZADO, partes[1], Integer.parseInt(partes[2]), Double.parseDouble(partes[3]));
                        System.out.println(employeeFounded);
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Busca encerrada.");
        }
        return employeeFounded;
        }
    }
