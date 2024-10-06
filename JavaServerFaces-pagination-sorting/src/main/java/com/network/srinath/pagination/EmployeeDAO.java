package com.network.srinath.pagination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public List<Employee> getEmployees(int offset, int pageSize, String sortField, String sortOrder) throws SQLException {
		//String query = "SELECT * FROM employee LIMIT ? OFFSET ?";
		String query = "SELECT * FROM employee ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";


		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, pageSize);
			stmt.setInt(2, offset);
			ResultSet rs = stmt.executeQuery();

			List<Employee> employees = new ArrayList<>();
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("position")));
			}
			return employees;
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "admin";
		Connection connection = null;
		try {
			// Load the PostgreSQL JDBC driver
			Class.forName("org.postgresql.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {

		}
		return connection;
	}

	public int getTotalEmployees() throws SQLException {
		String query = "SELECT COUNT(*) FROM employee";

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {
			rs.next();
			return rs.getInt(1);
		}
	}
}
