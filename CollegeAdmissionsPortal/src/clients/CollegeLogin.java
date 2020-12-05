package clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import connection.ConnectionClass;

public class CollegeLogin {
	
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	
	public static TreeMap<Integer, Integer> getBranchesFromDb(int cId)
	{
		TreeMap<Integer, Integer> selectedBranches = new TreeMap<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select bId, capacity from branchCapacity where cid = " + cId ;
			PreparedStatement pst = con.prepareStatement(query);
			
			
			ResultSet rs =pst.executeQuery();
			
			while(rs.next())
			{
				selectedBranches.put(rs.getInt(1), rs.getInt(2));
			}
			
		
		} catch (Exception e) {
			System.out.println("Could not get data from db");
		}
		return selectedBranches;
	}
	
	public static void addbranchtoDb(String query)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
		
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();		
		
		} catch (Exception e) {
			System.out.println("Could not get data from db");
		}
	}
	
	public static void addBranches(int cId)
	{
		TreeSet<Integer> remainingBranches = new TreeSet<>(CollegeInfo.branches.keySet());
		HashMap<Integer, String> branchNames = getBranchNames();
		while(true)
		{
			if(remainingBranches.size() == 0)
			{
				System.out.println("All the Branches of this college Have been Selected");
				break;
			}
			System.out.println("\nAvailable Branches are : \n");
			Iterator<Integer> itr= remainingBranches.iterator();
			while(itr.hasNext())
			{
				int bId = itr.next();
				System.out.println(bId + " " + branchNames.get(bId));				
			}
			
			System.out.println("Enter Choice or Exit(0) : ");
			int bChoice = ino.nextInt();
			
			if(bChoice == 0)
				break;
			
			if(!remainingBranches.contains(bChoice))
			{
				System.out.println("Enter a valid Choice.");
				continue;
			}
			System.out.println("Enter Capacity : ");
			int capacity = ino.nextInt();
			String query = "Insert into branchCapacity values (" + cId + ", " + bChoice + ", " + capacity + ")";
			addbranchtoDb(query);
			remainingBranches.remove(bChoice);
		}
		
		System.out.println("\nYou have Successfully added branches \n");
	}
	
	
	
	public static HashMap<Integer, String> getBranchNames()
	{
		HashMap<Integer, String> branches = new HashMap<Integer, String>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from branches";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				branches.put(rs.getInt(1), rs.getString(2));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch Branch Names");
		}
		return branches;
	}
	
	
	public static void DisplayAppliedStudents(int cId, int bId)
	{
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
//			select students.sId, students.name, students.marks
//		    -> from students inner join studentSelections on
//		    -> studentSelections.sId = students.sId where cId = 100101 and bId = 6;
//			
			
			String query = "select students.sId, students.name, students.marks " + 
					" from students inner join studentSelections ON " + 
					" studentSelections.sId = students.sId where cId = " + cId + " and bId = " + bId + 
					" and students.submit = " + true + " order by students.marks desc"; 
			
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			System.out.println("The Applied Students are : \n");
			int cnt = 0;
			while(rs.next())
			{
				cnt++;
				System.out.println(rs.getInt(1) + ".  " + rs.getString(2) + " :  " + rs.getInt(3));
			}
			
			if(cnt == 0)
			{
				System.out.println("\nNo Student Choose this Branch");
			}
			System.out.println("");
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Display Applied Students");
		}
	}
	
	
	public static void displayStudents(int cId)
	{
		TreeMap<Integer, Integer> availableBranches = getBranchesFromDb(cId);
		HashMap<Integer, String> branchNames = getBranchNames();
		System.out.println("The Available Branches are :\n");
		Iterator<Integer> itr = availableBranches.keySet().iterator();
		
		while(true)
		{
			while(itr.hasNext())
			{
				int bId = itr.next();
				System.out.println(bId + ". " + branchNames.get(bId) );
			}
			
			System.out.println("\nSelect Branch Id  or Exit(0) :" );
			int bChoice = ino.nextInt();
			
			if(bChoice == 0)
				break;
			
			if(!availableBranches.containsKey(bChoice))
			{
				System.out.println("Enter a valid Branch Id");
				continue;
			}
			
			
			DisplayAppliedStudents(cId, bChoice);
			
		}
	}
	
	public static void showStudentResult(int cId, int bId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select students.sId, students.name, students.marks from students inner join finalResult on students.sId = finalResult.sId " +
							" where finalResult.cId = " + cId + " and finalResult.bId = " + bId ;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			System.out.println("The selected Students are ");
			
			while(rs.next())
			{
				System.out.println( rs.getInt(1) + "  :  " + rs.getString(2) + " : " + rs.getInt(3) + "  marks" );
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch Data from result");
		}

	}
	
	public static void displayResult(int cId)
	{
		TreeMap<Integer, Integer> availableBranches = getBranchesFromDb(cId);
		HashMap<Integer, String> branchNames = getBranchNames();
		System.out.println("The Available Branches are :\n");
		Iterator<Integer> itr = availableBranches.keySet().iterator();
		
		while(true)
		{
			while(itr.hasNext())
			{
				int bId = itr.next();
				System.out.println(bId + ". " + branchNames.get(bId) );
			}
			
			System.out.println("\nSelect Branch Id  or Exit(0) :" );
			int bChoice = ino.nextInt();
			
			if(bChoice == 0)
				break;
			
			if(!availableBranches.containsKey(bChoice))
			{
				System.out.println("Enter a valid Branch Id");
				continue;
			}
			showStudentResult(cId, bChoice);
		}
	}
	

}
