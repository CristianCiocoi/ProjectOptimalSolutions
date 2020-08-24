package OptimalSolutions.md;
import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:sqlite:customers.db";
	private Connection connection;
	
	//Don't create the PreparedStatement in the loop. Create it once and reuse it.
	private String insertInCustomersTalbe = "INSERT INTO customers (name, surname, email, gender, selfImg) VALUES (?, ?, ?, ?, ?)"; 
	
	
	
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
		

		try (PreparedStatement pstmt = connection.prepareStatement(insertInCustomersTalbe)){
			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, email);
			pstmt.setString(4, gender);
			pstmt.setString(5, selfImg);//selfImg dont forget that it is of type BLOB
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void clearAll() {

		String clearSequence = "DELETE FROM SQLITE_SEQUENCE";
		String clearCustomers = "DELETE FROM customers";
		try (Statement stmt  = connection.createStatement()){
			stmt.executeUpdate(clearSequence);
			stmt.executeUpdate(clearCustomers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	CREATE TABLE suppliers (
			supplier_id integer PRIMARY KEY,
			supplier_name text NOT NULL,
			group_id integer NOT NULL
		);

		CREATE TABLE supplier_groups (
			group_id integer PRIMARY KEY,
			group_name text NOT NULL
		);

	CREATE TABLE suppliers (
	    supplier_id   INTEGER PRIMARY KEY,
	    supplier_name TEXT    NOT NULL,
	    group_id      INTEGER NOT NULL,
	    FOREIGN KEY (group_id)
	       REFERENCES supplier_groups (group_id) 
	);
	
	INSERT INTO supplier_groups (group_name)
	VALUES
	   ('Domestic'),
	   ('Global'),
	   ('One-Time');
	
	INSERT INTO suppliers (supplier_name, group_id)
	VALUES ('HP', 2);
	*/

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
