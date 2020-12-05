package clients;

import java.io.Serializable;
import java.util.*;

public class CollegeInfo implements Serializable{
	
	String name;
	String city;
	String email;
	int collegeId;
	String password;
	public static HashMap<Integer, String> branches = new HashMap<Integer, String>();
	public static HashMap<Integer,Integer> selectedBranches;
	
	public CollegeInfo()
	{
		branches.put(1, "Aeronautical Engineering");
		branches.put(2, "Bio MEchanical Engineering");
		branches.put(3, "Civil Engineering");
		branches.put(4, "Chemical Engineering");
		branches.put(5, "Computer Science");
		branches.put(6, "ENTC Enginnering");
		branches.put(7, "Electrical Engineering");
		branches.put(8, "Mechanical Engineering");
	}
	
}
