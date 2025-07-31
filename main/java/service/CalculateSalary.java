package service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import model.Efetivo;
import model.Enum.Tipo;
import model.Employee;
import model.Terceirizado;

public class CalculateSalary {
    public static Employee calculate(){
        Path caminho = Paths.get("src/data/funcionarios.txt");

        try {
            List<String> linhas = (Files.readAllLines(caminho));

            for (String line : linhas) {
                String[] partes = line.split(";");
                if (partes[0].equalsIgnoreCase("Efetivo")) {
                    Efetivo efetivado = new Efetivo(Tipo.EFETIVO, partes[1], Integer.parseInt(partes[2]));
                    int sF = efetivado.calcularSalarioFinal();
                    System.out.println("Salário de " + partes[1] + ": R$ " + sF);
                }
                if (partes[0].equalsIgnoreCase("Terceirizado")) {
                    Terceirizado terceirizado = new Terceirizado(Tipo.TERCEIRIZADO, partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
                    int sF = terceirizado.calcularSalarioFinal();
                    System.out.println("Salário de " + partes[1] + ": R$" + sF);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
       }

}
