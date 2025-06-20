package controller;

import model.Funcionarios;
import service.adicionarFuncionario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class save {
    public static void salvar  (Funcionarios f){
        if (f != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/funcionarios.txt", true))) {
                bw.write(f.toString());
                bw.newLine();
                System.out.println(" ✅ Dados salvos.");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
