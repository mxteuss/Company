package service;

import model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ViewEmployee {
    public static Employee view(){
        Path caminho = Paths.get("src/data/funcionarios.txt");

        try {
            List<String> linhas = Files.readAllLines(caminho);
            for (String linha : linhas) {
                System.out.println(linha);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
        }
    }

