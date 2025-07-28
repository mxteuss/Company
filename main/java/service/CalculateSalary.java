package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Efetivo;
import model.Enum.Tipo;
import model.Employee;
import model.Terceirizado;

public class CalculateSalary {
    public static Employee calculate(){
        List<String> info = new ArrayList<>();
        Path caminho = Paths.get("src/data/funcionarios.txt");
        String linha;
        try (BufferedReader br = Files.newBufferedReader(caminho)) {
            while ((linha = br.readLine()) != null){
                info.add(linha);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

       for (String line : info){
           String [] partes = line.split(";");
           if (partes[0].equalsIgnoreCase("Efetivo")){
               Efetivo efetivados = new Efetivo(Tipo.EFETIVO, partes[1], Integer.parseInt(partes[2]));
            double sF = efetivados.calcularSalarioFinal();
               System.out.println("Salário de " + partes[1] + ": R$ " + sF);
           }
           if(partes[0].equalsIgnoreCase("Terceirizado")){
               Terceirizado terceirizado = new Terceirizado(Tipo.TERCEIRIZADO, partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
                double sF = terceirizado.calcularSalarioFinal();
               System.out.println("Salário de " + partes[1] + ": R$" + sF);
           }

       }
    return null;
    }
}
