package jdbcTest;

import java.sql.*;

public class testingClass {

	public static void main(String[] args){
		
		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = "Abhi.20july";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement st = con.createStatement();
		
			String query = " select * from sname";
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

	}

}
