package OptimalSolutions.md;

import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:sqlite:customers.db";
	private Connection connection;

	private String insertInCustomersTable = "INSERT INTO customers (name, surname, email, gender, selfImg, paymentService, transactionFee, columnH, columnI, transactionCity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public boolean openConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
			System.out.println("Connected");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public void insertCustomer(Customer metadata) {

		String name = metadata.getColumnA();
		String surname = metadata.getColumnB();
		String email = metadata.getColumnC();
		String gender = metadata.getColumnD();
		String selfImg = metadata.getColumnE();
		String paymentService = metadata.getColumnF();
		String transactionFee = metadata.getColumnG();
		String columnH = metadata.getColumnH();
		String columnI = metadata.getColumnI();
		String transactionCity = metadata.getColumnJ();

		try (PreparedStatement pstmt = connection.prepareStatement(insertInCustomersTable)) {
			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, email);
			pstmt.setString(4, gender);
			pstmt.setString(5, selfImg);
			pstmt.setString(6, paymentService);
			pstmt.setString(7, transactionFee);
			pstmt.setString(8, columnH);
			pstmt.setString(9, columnI);
			pstmt.setString(10, transactionCity);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void clearAll() {
		String clearSequence = "DELETE FROM SQLITE_SEQUENCE";
		String clearCustomers = "DELETE FROM customers";
		try (Statement stmt = connection.createStatement()) {
			stmt.executeUpdate(clearSequence);
			stmt.executeUpdate(clearCustomers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
