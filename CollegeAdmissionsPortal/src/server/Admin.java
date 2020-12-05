package server;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.*;
import java.net.*;


import clients.Pair;
import clients.StudentLogin;
import connection.ConnectionClass;

public class Admin implements Serializable {
	
	static Socket client = null;
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	public static boolean resultDeclared = false;
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	public static PrintStream out;
	public static HashMap<Pair<Integer, Integer>, Integer> currentBranchSize = new HashMap<Pair<Integer,Integer>, Integer>();  // <cid, bId>, size
	public static HashMap<Pair<Integer, Integer>, Integer> finalBranchSize = new HashMap<Pair<Integer,Integer>, Integer>();
	
	public static TreeSet<Integer> getBranches(int cId)
	{
		
		TreeSet<Integer> branchList = new TreeSet<Integer>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select bId from branchCapacity where cId = " + cId;
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				branchList.add(rs.getInt(1));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Branches");
		}
		
		return branchList;
	}
	
	public static void getBranchSizes()
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from branchCapacity";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				int cId = rs.getInt(1);
				int bId = rs.getInt(2);
				int cap = rs.getInt(3);
				currentBranchSize.put(new Pair<Integer, Integer>(cId, bId), 0);
				finalBranchSize.put(new Pair<Integer, Integer>(cId, bId), cap);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Sizes from db");
		}
	}
	
	public static ArrayList<Integer> getStudentsMarkWise()
	{
		ArrayList<Integer> students = new ArrayList<Integer>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select sId from students where submit = true order by marks desc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				students.add(rs.getInt(1));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Sizes from db");
		}
		
		return students;
	}
	
	public static void addToResult(int sId, int cId, int bId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Insert into finalResult values(?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, sId);
			pst.setInt(2, cId);
			pst.setInt(3, bId);
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Add result to db");
		}
		
	}
	
	public static ArrayList<Pair<Integer, Integer>> getPriorities(int sId)
	{
		ArrayList<Pair<Integer, Integer> > priorities = new ArrayList<Pair<Integer,Integer>>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, bId from studentSelections where sId = " + sId + " order by preference asc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Pair<Integer, Integer> pair = new Pair<Integer, Integer>(rs.getInt(1), rs.getInt(2));
				priorities.add(pair);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Priorities from db");
		}
		
		return priorities;
	}
	
	
	public static void displayListofColleges()
	{
		HashMap<Integer, String> branchNames = StudentLogin.getBranchNames();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				System.out.println(" --------------------------------------------------------------------------------------\n");
				System.out.println("College Id : " + rs.getInt(1) + "           " + rs.getString(2));
				
				System.out.println("Avilable Branches ::  \n");
				TreeSet<Integer> branches = getBranches(rs.getInt(1));
				
				Iterator<Integer> itr = branches.iterator();
				
				while(itr.hasNext())
				{
					int bId = itr.next();
					System.out.println("         " + bId + ".   " + branchNames.get(bId));
				}
				System.out.println(" --------------------------------------------------------------------------------------\n");
			}
			
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Branches");
		}
	}
	
	public static void displayMeritList()
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select sId, name, marks from students order by marks desc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1) +  " .   " + rs.getString(2) + "  :   " + rs.getInt(3) + " marks");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Merit List");
		}
	}
	
	public static void declareResult()
	{
		System.out.println("*************** Warning *****************");
		System.out.println("You will not receive more responses");
		System.out.println("Do You Want to Declare Result : ");
		System.out.print(" Continue or Exit(0) : ");
		int choice = ino.nextInt();
		
		if(choice == 0)
			return;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "update result set declared = true where declared = false";
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			
			compileResult();
			resultDeclared = true;
			System.out.println("\nCongrats, Result has been Declared ");
		
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Merit List");
		}
	}
	
	
	public static void compileResult()
	{
		getBranchSizes();
		ArrayList<Integer> students = getStudentsMarkWise();
		
		Iterator<Integer> itr = students.iterator();
		while(itr.hasNext())
		{
			int sId = itr.next();
			ArrayList<Pair<Integer, Integer>> branches = getPriorities(sId);
			Iterator<Pair<Integer, Integer>> itr1 = branches.iterator();
			
			while(itr1.hasNext())
			{
				Pair<Integer, Integer> pair = itr1.next();
				int cId = pair.a;
				int bId = pair.b;
				
				if(currentBranchSize.get(pair) < finalBranchSize.get(pair))
				{
					int size = currentBranchSize.get(pair);
					currentBranchSize.replace(pair, (size + 1) );
					addToResult(sId, cId, bId);
					break;
				}
			}
		}
	}
	
	public static void viewResult()
	{
		HashMap<Integer, String> branchNames = StudentLogin.getBranchNames();
		HashMap<Integer, String> collegeNames = StudentLogin.getCollegeNames();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from finalResult";
			PreparedStatement pst = con.prepareStatement(query);
			
			System.out.println("**********************************************  Final Result  **************************************************\n");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1) +  " .   " + collegeNames.get( rs.getInt(2) ) + "  :   " + branchNames.get( rs.getInt(3)));
			}
			System.out.println("*****************************************************************************************************************\n");
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Merit List");
		}
	}
	
	
	

	public static void main(String[] args) {
		
		try {
			client = new Socket("127.0.0.1", 8000);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
			System.out.println("Connected To Server");
			
		} catch (Exception e) {
			System.out.println("Could not connect to server");
		}
		
		int choice = 0;
		while(choice != 5)
		{
			System.out.println("************** Administrative Module ****************\n");
			System.out.println("1. Display List of Colleges");
			System.out.println("2. Display Merit List of Students");
			System.out.println("3. Display Result");
			System.out.println("4. Declare Result");
			System.out.println("5. Exit");
			
			System.out.print("\nEnter Choice : ");
			choice = ino.nextInt();
			
			switch(choice)
			{
				case 1:
					displayListofColleges();
					break;
					
				case 2:
					displayMeritList();
					break;
					
				case 3:
					if(resultDeclared == false)
					{
						System.out.println("Result has not been declared yet");
						break;
					}
					viewResult();
					break;
					
				case 4:
					declareResult();
					break;
					
				case 5:
					out.print("Exit");
					break;
					
					
				default:
					System.out.println("You Have Entered Wrong Choice.");
						
			}
		}
			
	}
}
