package service;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewEmployee {
    public static Employee view(){
        String linha;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/funcionarios.txt"))){

            while ((linha = br.readLine())!= null){
                System.out.println(linha);
            }
        }
        catch (IOException e)
        {e.printStackTrace();}
        return null;
        }
    }

