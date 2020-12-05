package jdbcTest;

import java.sql.*;

public class connection {
	
	Connection con;
	Statement st;
	
	public connection() {
		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = "Abhi.20july";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connected to Database");
			st = con.createStatement();
		
			String query = " select * from sname";
			 
			String qUpdate = "insert into sname values (\"Anmol\"), (\"Rajesh\") ";
			st.executeUpdate(qUpdate);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("Could Not Connect to Database");
		}
	}
	
	public static void main(String[] args) {
		new connection();
	}
}
