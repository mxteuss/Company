package service;

import model.Employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportService {
    public static Employee loadReport(){
        List<String> partTime = new ArrayList<>();
        List<String> fullTime = new ArrayList<>();
        double mediaS = 0;
        double total = 0;
        double temp = 0;
        double temp2 = 0;
        String linha;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/funcionarios.txt"))){
            while ((linha = br.readLine()) != null){
                String [] partes = linha.split(";");
                if (partes[0].equalsIgnoreCase("Terceirizado")){
                    partTime.add(linha);
                } else if (partes[0].equalsIgnoreCase("Efetivo")) {
                    fullTime.add(linha);
                }
                if (partes[0].equalsIgnoreCase("Efetivo")){
                    temp = Double.parseDouble(partes[2]);
                } else {
                    temp2 = Double.parseDouble(partes[2]) + Double.parseDouble(partes[3]);
                }
                total = temp + temp2;
                mediaS = total / (fullTime.size() + partTime.size());
            }
            System.out.println("Quantidade de funcionários terceirizados: " + partTime.size());
            System.out.println("Quantidade de funcionários efetivados: " + fullTime.size());
            System.out.println("Total de salário: " + total);
            System.out.println("Média de salário: " +mediaS);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
