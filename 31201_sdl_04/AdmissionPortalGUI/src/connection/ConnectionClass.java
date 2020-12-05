package connection;

import java.sql.*;

public class ConnectionClass {
	public Connection con;

	public ConnectionClass() {
		try {
			String url = "jdbc:mysql://localhost:3306/collegeAdmissionsPortal";
			String user = "root";
			String password = "Abhi.20july";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			// System.out.println("Connected to Database");
		} catch (Exception e) {
			System.out.println("Could Not Connect to Database");
		}
	}

	public Connection returnConnection() {
		return con;
	}
}
