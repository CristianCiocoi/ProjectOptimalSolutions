package OptimalSolutions.md;

import java.sql.*;
import java.util.Scanner;

public class DBConnection {

	Connection co;

	public boolean open() {
		try {
			Class.forName("org.sqlite.JDBC");
			co = DriverManager.getConnection("jdbc:sqlite:customers.db");
			System.out.println("Connected");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public void insert() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		String surname = scanner.nextLine();

		try {

			String query = "INSERT INTO customers (name, surname) " + "VALUES ('" + name + "', '" + surname + "')";

			Statement statement = co.createStatement(); // statement is made based on query
			statement.executeUpdate(query);

			System.out.println("Rows added");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			co.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
