package controller;

import dao.EmployeeDAO;
import model.Employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class save {
    public static void salvar  (Employee f){
        if (f != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/data/funcionarios.txt", true))) {
                bw.write(f.toString());
                bw.newLine();
                System.out.println("Dados salvos.");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
    public static void salvarDB (Connection conn, Employee f) {
        if (f!= null){
            EmployeeDAO dao = new EmployeeDAO(conn);
            try {
            dao.salvar(f);
            System.out.println("Dados salvos no banco");
            }
            catch (SQLException e){
                System.err.println("Erro ao salvar no banco de dados" + e.getMessage());
            }

        }
    }
}
