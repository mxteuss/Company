package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Enum.Tipo;
import model.Funcionarios;

public class ProgramaInicial {
    public static void main(String[] args)  {
        List<String> info = new ArrayList<>();
        String linha = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/funcionarios.txt"))) {
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
               Funcionarios.Efetivo efetivados = new Funcionarios.Efetivo(Tipo.EFETIVO, partes[1], Double.parseDouble(partes[2]));
            double sF = efetivados.calcularSalarioFinal();
               System.out.println("Salário de: " + partes[1] + "R$: " + sF);
           }
           if(partes[0].equalsIgnoreCase("Terceirizado")){
               Funcionarios.Terceirizado terceirizado = new Funcionarios.Terceirizado(Tipo.TERCEIRIZADO, partes[1], Double.parseDouble(partes[2]), Double.parseDouble(partes[3]));
                double sF = terceirizado.calcularSalarioFinal();
               System.out.println("Salário de: " + partes[1] + ": R$" + sF);
           }

       }

    }
}
