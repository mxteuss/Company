package service;

import dao.connectionFactory;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import model.Employee;
import model.Enum.Tipo;
import util.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.Types.NULL;

@Log4j2
public class employeeService {
    private final Connection conn;

    public employeeService(Connection conn) {
        this.conn = conn;
    }

    public static void salvar(Employee emp) {
        String sql = "INSERT INTO Employees (name, type, salary, variable) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, emp.getNome());
            stmt.setString(2, String.valueOf(emp.getTipo()));
            stmt.setInt(3, emp.getSalarioBase());
            if (String.valueOf(emp.getTipo()).equalsIgnoreCase("Efetivo")) {
                stmt.setInt(4, NULL); //Inserindo NULL na tabela
            } else {
                stmt.setDouble(4, emp.getAdicionalVar());
            }
            int rows = stmt.executeUpdate();
            stmt.close();
            log.info("Linhas afetadas: " + rows);
        } catch (SQLException e) {
            log.error("Erro: " + e.getMessage());
        }
    }

    public static void delete() {
        String sql = "DELETE FROM `Company`.`Employees` WHERE (`id` = ?)";
        try (Connection conn = connectionFactory.getConnection()) {
            log.info("Insira o ID do funcionário a ser deletado: ");
            Scanner sc = Singleton.getInstance();
            int id = sc.nextInt();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                log.info("Funcionário com ID '{}' foi deletado. Linhas afetadas: {}", id, rows);
            } else {
                log.warn("Nenhum funcionário com o ID '{}' foi encontrado no banco de dados.", id);
            }
        } catch (SQLException e) {
            log.error("Erro ao deletar dados: " + e.getMessage());
        }
    }

    public static void uptade() {
        String sql = "UPDATE `Company`.`Employees` SET `type` = ?, `salary` = ?, `variable` = ? WHERE `id` = ?";
        Scanner sc = Singleton.getInstance();
        try (Connection conn = connectionFactory.getConnection()) {
            log.info("Insira o id do funcionário à ter os dados atualizados: ");
            PreparedStatement stmt = conn.prepareStatement(sql);
            int id = sc.nextInt();
            stmt.setInt(4, id);

            log.info("Insira o tipo contratual do funcionário: ");
            String type = sc.nextLine();
            if (type.equalsIgnoreCase("Efetivo")) {
                stmt.setInt(3, NULL);
                stmt.setString(1, type.toUpperCase());
                sc.nextLine();

                // calculando salário final do efetivado
                log.info("Insira o salário do funcionário: ");
                int salary = sc.nextInt();
                salary += 500;
                stmt.setInt(2, salary);
            } else {
                stmt.setString(1, type.toUpperCase());
                sc.nextLine();
                log.info("Insira o salário do funcionário: ");
                int salary = sc.nextInt();
                stmt.setInt(2, salary);

                log.info("Insira a variável adicional do funcionário: ");
                int var = sc.nextInt();
                stmt.setInt(3, var);
            }
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                log.info("Funcionário com ID '{}' foi atualizado. Linhas afetadas: {}", id, rows);
            } else {
                log.warn("Nenhum funcionário com o ID '{}' foi encontrado no banco de dados.", id);
            }
            stmt.close();

        } catch (SQLException e) {
            log.error("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    public static List<Employee> findAll() {
        log.info("Procurando todos  os funcionários");
        String sql = "SELECT * FROM Company.Employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = connectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                employees.add(
                        Employee.builder()
                                .id(rs.getInt("id"))
                                .tipo(Tipo.valueOf(rs.getString("type")))
                                .nome(rs.getString("name"))
                                .salarioBase(rs.getInt("variable"))
                                .build());
            }
        } catch (SQLException e) {
            log.error("Erro na pesquisa dos funcionários: " + e.getMessage());
        }
        return employees;
    }

    @SneakyThrows
    public static List<Employee> search() {
        log.info("Procurando funcionário...");
        Scanner sc = Singleton.getInstance();
        String sql = "SELECT * FROM Company.Employees WHERE name = ?";
        List<Employee> employees = new ArrayList<>();

        log.info("Insira o  nome do funcionário: ");
        String s = sc.nextLine();

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String tipoStr = rs.getString("type");
                    Tipo tipo = Tipo.valueOf(tipoStr);
                    employees.add(
                            Employee.builder()
                                    .id(rs.getInt("id"))
                                    .nome(rs.getString("name"))
                                    .tipo(tipo)
                                    .salarioBase(rs.getInt("salary"))
                                    .adicionalVar(rs.getInt("variable"))
                                    .build());
                }
            } catch (SQLException e) {
                log.error("Erro na pesquisa dos funcionários: " + e.getMessage());
            }

            return employees;
        }
    }

    public static List<Integer> report(){
        List<Integer> avg = new ArrayList<>();
        String sql = "SELECT salary, variable FROM `Company`.`Employees`";
        int sum = 0;
        int i = 0;
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                avg.add(rs.getInt("salary"));
                avg.add(rs.getInt("variable"));

                for (int total : avg){
                    sum += total;
                }
                i = sum / avg.size();
            }
            }
        }
        catch (SQLException e){
            log.error("Erro ao gerar relatórios: " + e.getMessage());
        }
        log.info("Total: " + sum);
        log.info("Média: " + i);

        return avg;
    }
}



