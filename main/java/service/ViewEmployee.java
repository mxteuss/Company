package service;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewEmployee {
    public static Employee view(){
        Path caminho = Paths.get("src/data/funcionarios.txt");
        String linha;
        try (BufferedReader br = Files.newBufferedReader(caminho)){

            while ((linha = br.readLine())!= null){
                System.out.println(linha);
            }
        }
        catch (IOException e)
        {e.printStackTrace();}

        return null;
        }
    }

