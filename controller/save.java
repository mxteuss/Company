package controller;

import model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class save {
    public static void salvar  (Employee f){
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
