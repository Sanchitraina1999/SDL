package clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import connection.ConnectionClass;

public class CollegePage {
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	
	
	public static int getcId()
	{
		int id = 0;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select cId from staticIds";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			
			query = "update staticIds set cId = " + (id + 1) + " where cid = " + id;
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
		CollegeInfo college = new CollegeInfo();
		System.out.print("Name : ");
		college.name = ins.nextLine();
		System.out.print("Email : ");
		college.email = ins.nextLine();
		System.out.print("City : ");
		college.city = ins.nextLine();
		college.collegeId  = getcId();
		
		System.out.println("\nCongrats You Have Successfully added Details\n");
		
		while(true)
		{
			System.out.print("Generate Password : ");
			college.password = ins.nextLine();
			System.out.print("Re Enter Password : ");
			String tpassword = ins.nextLine();
			if(tpassword.equals(college.password))
				break;
		}
		
		System.out.println("\nCongrats You have Succesfully Registered\n");
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			String query = "insert into colleges(cId, name, city, email) values (?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, college.collegeId);
			pst.setString(2, college.name);
			pst.setString(3, college.city);
			pst.setString(4, college.email);
			pst.executeUpdate();
			
			query = "Insert into collegeLogin(cId, password) values(?, ?)";
			pst = con.prepareStatement(query);
			pst.setInt(1, college.collegeId);
			pst.setString(2, college.password);
			pst.executeUpdate();
			
			con.close();
			System.out.println("Succesfully Added to Database");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Could Not add to Database");
		}
		
		System.out.println(" ----------------  Now Add Branches To College ----------- ");
		CollegeLogin.addBranches(college.collegeId);
		
		System.out.println(" ");
		System.out.println("Your College - Id is : " + college.collegeId);
		System.out.println("-------- Congrats for Submitting Succesfully ----------");
		
		
	}
	
	
	public static int login()
	{
		int collegeId = 0;
		System.out.print("College - Id : ");
		int cId = ino.nextInt();
		System.out.print("Password : ");
		String sPassword = ins.nextLine();
		String rPassword = null;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select password from Collegelogin where cid = " + cId;
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
		
		collegeId = cId;
		return collegeId;
	}
	

}
