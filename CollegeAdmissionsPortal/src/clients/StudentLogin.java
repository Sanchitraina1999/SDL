package clients;

import java.sql.*;
import java.util.*;


import connection.ConnectionClass;

public class StudentLogin {
	
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);

	public static TreeMap<Integer, String> getCollegesFomDb() // Returns All Colleges in Db
	{
		TreeMap<Integer, String> availableColleges = new TreeMap<Integer, String>();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			while(rs.next())
			{
				availableColleges.put(rs.getInt(1), rs.getString(2));
			}
		
		} catch (Exception e) {
			System.out.println("Could Not Get Colleges from Db");
		}
		
		return availableColleges;
	}
	
	public static TreeSet<Integer> getBranchesFromDb(int sId, int cId)
	{
		TreeSet<Integer> availableBranches = new TreeSet<Integer>();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			
			String query = "Select bid from branchCapacity where cid = " + cId;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs =pst.executeQuery();
			
			while(rs.next())
			{
				availableBranches.add(rs.getInt(1));
			}
			
//			query = "select bId from studentSelections where sId = 1001 and cId = 100101";
			// Select bId from studentSelections where sId = " + sId + "and cId = " + cId
			query = "select bId from studentSelections where sId = " + sId + " and cId = " + cId;
			pst = con.prepareStatement(query);
			
			
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				availableBranches.remove(rs.getInt(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Get Branches From Db");
		}
		
		return availableBranches;
	}
	
	public static void addStudentToDb(String query)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Could Add Data to Student");
		}
	}
	
	public static void addCollegesAndBranches(int sId)
	{
		TreeMap<Integer, String> availableColleges = getCollegesFomDb();
		
		while(true)
		{
			if(availableColleges.size() == 0)
			{
				System.out.println("No Remaining College Remained to be selected");
				break;
			}
			System.out.println("Available Colleges are : ");
			Iterator<Integer> itr = availableColleges.keySet().iterator();
			while(itr.hasNext())
			{
				int cId = itr.next();
				System.out.println(cId +  " : " + availableColleges.get(cId));
			}
			System.out.println("\nChoose College or Exit(0) : ");
			int cChoice = ino.nextInt();
			
			if( cChoice == 0)
				break;
			
			if(!availableColleges.containsKey(cChoice))
			{
				System.out.println("Please Enter a valid college Id.");
				continue;
			}
			
			
			while(true)
			{
				TreeSet<Integer> availableBranches = getBranchesFromDb(sId, cChoice);
				HashMap<Integer, String> branchNames = getBranchNames();
				if(availableBranches.size() == 0)
				{
					System.out.println("You have already Already Selected all the branches of this college");
					break;
				}
				
				System.out.println("Available Branches are : \n");
				Iterator<Integer> itr1 = availableBranches.iterator();
				while(itr1.hasNext())
				{
					int bId = itr1.next();
					System.out.println(bId +  "  :  " + branchNames.get(bId));
				}
				System.out.print("\nEnter Branchid or Exit(0) : ");
				int bChoice = ino.nextInt();
				
				if(bChoice == 0)
					break;
				if(!availableBranches.contains(bChoice))
				{
					System.out.println("Enter a valid branch code");
					continue;
				}
				
				System.out.print("Enter Preference : ");
				int pref = ino.nextInt();
				
				String query = "insert into studentSelections(sId, cId, bId, preference) values( " + sId + " , " + cChoice + "," + bChoice + ", " + pref + " )" ;
				addStudentToDb(query);
			}
		}
		updatePreferences(sId);
	}
	
	public static TreeMap<Integer, Pair<Integer,Integer> > getChoices(int sId)
	{
		TreeMap<Integer,Pair<Integer, Integer>> choices = new TreeMap<>();   // <prt, <cId, bid> according to desc priority
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select cId , bId, preference from studentSelections where sId = " + sId + " order by preference asc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				choices.put(rs.getInt(3), new Pair<Integer, Integer>(rs.getInt(1), rs.getInt(2)));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Get Branches From Db");
		}
		return choices;
		
	}
	
	public static void removeChoice(int sId, int cId,int bId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "delete from studentSelections where sId = " + sId + " and cId = " + cId + " and bId = " + bId;
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Delete from choices From Db");
		}
	}
	
	public static HashMap<Integer, String> getCollegeNames()
	{
		HashMap<Integer, String> colleges = new HashMap<Integer, String>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				colleges.put(rs.getInt(1), rs.getString(2));
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch College Names from Db");
		}
		
		return colleges;
	}
	
	
	public static void deleteChoices(int sId)
	{
		HashMap<Integer, String> colleges = getCollegeNames();
		HashMap<Integer, String> branches = getBranchNames();
		while(true)
		{
			
			TreeMap<Integer, Pair<Integer,Integer>> choicePriority = getChoices(sId); // <prt, <cid, bid>>
			
			if(choicePriority.size() == 0)
			{
				System.out.println("No Available Choice\n");
				break;
			}
		
			
			Iterator<Integer> itr = choicePriority.keySet().iterator();
			while(itr.hasNext())
			{
				int p = itr.next();
				Pair<Integer, Integer> pair = choicePriority.get(p);
				System.out.println(p + ".  " + colleges.get(pair.a) + "  " + branches.get(pair.b) );
			}
			
			System.out.println("Enter Choice or Exit(0) : ");
			int choice = ino.nextInt();
			if(choice == 0)
				break;
			
			if( !choicePriority.keySet().contains(choice))
			{
				System.out.println("Enter a valid Choice");
				continue;
			}
			
			Pair<Integer, Integer> pair = choicePriority.get(choice);
			
			removeChoice(sId, pair.a, pair.b);
		}
		updatePreferences(sId);
		
	}
	
	public static void updatePreferences(int sId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select preference, sId, cId, bId from studentSelections where sId =  " + sId + " order by preference asc ";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int prt = 1;
			while(rs.next())
			{
				updatePreference(sId, rs.getInt(3), rs.getInt(4), prt);
				prt++;
			}
			
			
			
			
			
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Update Preference to database");
		}
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
	
	public static void displayChoices(int sId)
	{
		updatePreferences(sId);
		HashMap<Integer, String> colleges = getCollegeNames();
		HashMap<Integer, String> branches = getBranchNames();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from studentSelections where sId =  " + sId + " order by preference asc";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			System.out.println("The Selected Choices are : \n");
			while(rs.next())
			{
				System.out.println(rs.getInt(4) +  ".   " + colleges.get(rs.getInt(2)) + "   :    " + branches.get( rs.getInt(3) ) );
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Display Branches");
		}
	}
	
	public static TreeMap<Integer, Pair<Integer, Integer>> getPrefernces(int sId)
	{
		TreeMap<Integer, Pair<Integer, Integer>> preferences = new TreeMap<>();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from studentSelections where sId =  " + sId + " order by preference asc";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				preferences.put(rs.getInt(4), new Pair<Integer, Integer>( rs.getInt(2), rs.getInt(3) ) );
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Fetch preferences");
		}
		
		return preferences;
	}
	
	public static void updatePreference(int sId, int cId, int bId, int pref)
	{
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Update studentSelections set preference = " + pref +  " where sId = " + sId + " and cId = "+ cId + " and  bId = " + bId; 
			PreparedStatement pst = con.prepareStatement(query);	
			pst.executeUpdate();
			
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Update preference");
		}
	}
	
	public static void EditPreferences(int sId)
	{
		updatePreferences(sId);
		TreeMap<Integer, Pair<Integer, Integer> > preferences = getPrefernces(sId);
		HashMap<Integer, String> colleges = getCollegeNames();
		HashMap<Integer, String> branches = getBranchNames();
		while(true)
		{
			Iterator<Integer> itr = preferences.keySet().iterator();
			while(itr.hasNext())
			{
				int pref = itr.next();
				Pair<Integer, Integer> p = preferences.get(pref);
				System.out.println(pref + ".  " + colleges.get( p.a ) + "  :  " + branches.get( p.b) );
			}
			
			System.out.print("Enter 1st Choice : ");
			int ch1 = ino.nextInt();
			System.out.print("Enter 2nd Choice : ");
			int ch2 = ino.nextInt();
			
			if( !preferences.containsKey(ch1) || ! preferences.containsKey(ch2))
			{
				System.out.println("Ënter Valid Choices");
				continue;
			}
			
			Pair<Integer, Integer> p1 = preferences.get(ch1);
			Pair<Integer, Integer> p2 = preferences.get(ch2);
			updatePreference(sId, p1.a, p1.b, ch2);
			updatePreference(sId, p2.a, p2.b, ch1);
			
			System.out.println("Updated .. ");
			System.out.print("Continue or Exit(0) : ");
			ch1 = ino.nextInt();
			if(ch1 == 0)
				break;
		}
	}
	
	public static void submitForm(int sId)
	{
		
		System.out.println("***************** Warning *************************");
		System.out.println("Do You want to Submit Form ");
		System.out.println("You Will Not be able to change Choices.");
		System.out.print("\nContinue or Exit(0) : ");
		int choice = ino.nextInt();
		if(choice == 0)
			return;
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Update students set submit = true where sId = " + sId;
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Submit Form");
		}
		
		System.out.println("******* Congrats Form Submitted Succesfully *************");
		System.out.println("******* Result will be Declared Soon ************");
		
	}
	
	public static void viewResult(int sId)
	{
		HashMap<Integer, String> colleges = getCollegeNames();
		HashMap<Integer, String> branches = getBranchNames();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, bId from finalResult where sId = " + sId;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next() != false)
			{
				System.out.println("\n************* Congrats You have been Alloted a Seat **********");
				System.out.println( colleges.get(rs.getInt(1)) +"  :  " + branches.get(rs.getInt(2)));
			}
			
			else
				System.out.println("****** Sorry You were not alloted any seat ********* \n");
				
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch Student Result");
		}

	}

}
