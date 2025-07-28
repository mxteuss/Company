package dao;

import model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {
    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Employee emp) throws SQLException {
        String sql = "INSERT INTO Employees (name, type, salary, variable) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, emp.getNome());
        stmt.setString(2, String.valueOf(emp.getTipo()));
        stmt.setInt(3, emp.getSalarioBase());
        stmt.setDouble(4, emp.getAdicionalVar());
        stmt.executeUpdate();
        stmt.close();
    }
}
