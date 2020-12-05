package clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import connection.ConnectionClass;

public class StudentPage {
	
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	
	public static void showMenu()
	{
		System.out.println("************  Select Option ************");
		System.out.println("1. Add Branches");
		System.out.println("2. Delete Branches");
		System.out.println("3. Display Selected Branches");
		System.out.println("4. Display Priority");
		System.out.println("5. Change Priority");
		System.out.println("6. Submit Form");
		System.out.println("7. Display Result");
		System.out.println("8. Exit");
		
	}
	
	public static int getsId()
	{
		int id = 0;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select sId from staticIds";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			
			query = "update staticIds set sId = " + (id + 1) + " where sid = " + id;
			pst.executeUpdate(query);
			con.close();
		
		} catch (Exception e) {
			System.out.println("Student Id could not be accesed");
			return 0;
		}
		return id;
		
	}
	
	public static void sigup()
	{
		StudentInfo st = new StudentInfo();
		System.out.print("Name : ");
		st.name = ins.nextLine();
		System.out.print("Email : ");
		st.email = ins.nextLine();
		System.out.print("Address : ");
		st.Address = ins.nextLine();
		System.out.print("Jee Marks : ");
		st.jeemarks = ino.nextInt();
		st.StudentId = getsId();
		
		System.out.println("\nCongrats You Have Successfully added Details\n");
		
		while(true)
		{
			System.out.print("Generate Password : ");
			st.password = ins.nextLine();
			System.out.print("Re Enter Password : ");
			String tpassword = ins.nextLine();
			if(tpassword.equals(st.password))
				break;
		}
		
		
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			String query = "insert into students(sId, name, marks, address, email) values (?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, st.StudentId);
			pst.setString(2, st.name);
			pst.setInt(3, st.jeemarks);
			pst.setString(4, st.Address);
			pst.setString(5, st.email);
			pst.executeUpdate();
			
			query = "Insert into studentPasswords values(?, ?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, st.StudentId);
			pst.setString(2, st.password);
			pst.executeUpdate();
			
			con.close();
//			System.out.println("Succesfully Added to Database");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Could Not add to Database");
			e.printStackTrace();
		}
		
		
		System.out.println("\nYour Login Id is : " + st.StudentId);
		System.out.println("\n---- Congrats You have Succesfully Registered ----\n");
		
	}
	
	public static int login()
	{
		
		System.out.print("User - Id : ");
		int stId = ino.nextInt();
		System.out.print("Password : ");
		String sPassword = ins.nextLine();
		String rPassword = null;
		int stuId = 0;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select password from studentPasswords where sid = " + stId;
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			rs.next();
			rPassword = rs.getString(1);
			con.close();
			if( !rPassword.equals(sPassword))
				throw new Exception("Wrong Details");
		
		} catch (Exception e) {
			System.out.println("Wrong Login Details");
			return 0;
		}
		
		stuId = stId;
		return stuId;
		
	}
	
	

}
