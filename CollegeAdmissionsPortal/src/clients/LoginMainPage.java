package clients;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import connection.ConnectionClass;


public class LoginMainPage implements Serializable{
	
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	public static PrintStream out;
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	public static boolean resultDeclared = false;
	public static boolean serverStarted = false;
	
	
	public static boolean isResultDeclared()
	{
		if(resultDeclared == true)
			return true;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select * from result";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			resultDeclared = rs.getBoolean(1);
			con.close();
		
		} catch (Exception e) {
			System.out.println("Student Id could not be accesed");
			return resultDeclared;
		}
		
		return resultDeclared;
	}
	
	public static void main(String[] args) {
		
		Socket client = null;
		try {
			client = new Socket("127.0.0.1", 8000);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
			System.out.println("Connected To Server");
			serverStarted = true;
			
		} catch (Exception e) {
			System.out.println("Could not connect to server");
		}
		
		if(serverStarted == false)
		{
			System.out.println("Could Not Connect to Server");
			System.out.println("Please Switch On Server First");
			return;
		}
		
		
		System.out.println("*****************************************************************");
		System.out.println("		Welcome to Students Admission Portal");
		System.out.println("*****************************************************************\n");
		int choice = 1;
		
		while(choice != 3)
		{
			System.out.println("*****************************  Main Page ****************************\n");
			System.out.println("1. Student Section");
			System.out.println("2. College Section");
			System.out.println("3. Exit");
			
			System.out.print("Enter Choice : ");
			choice = ino.nextInt();

			switch(choice)
			{
				case 1:
				{
					
					int ichoice = 1;
					while(ichoice != 3)
					{
						out.println("In Student Section");
						System.out.println("\n-------------------  Student Section ------------------ \n");
						System.out.println("1. Sign Up");
						System.out.println("2. Sign In");
						System.out.println("3. Exit\n");
						
						System.out.print("Enter Choice : ");
						ichoice = ino.nextInt();
						
						switch(ichoice)
						{
							case 1:
								if(isResultDeclared())
								{
									System.out.println("Time Out");
									break;
								}
								System.out.println("\n------------  Student SignUp --------------\n");
								out.print("Signining Up in Students Section");
								StudentPage.sigup();
								
								break;
								
							case 2:
								int sId = StudentPage.login();
								if(sId != 0)
								{
									out.println("Login in to Students Section");
									System.out.println(" ");
									System.out.println(sId + " -  logged in");
									int lchoice = 1;
									while(lchoice != 7)
									{
										System.out.println("\n-------------------  Student Login ------------------ \n");
										System.out.println("1. Add Colleges and Branches");
										System.out.println("2. Delete Branches");
										System.out.println("3. Display Choices");
										System.out.println("4. Edit Preferences");
										System.out.println("5. Submit Form");
										System.out.println("6. View Result");
										System.out.println("7. Log Out");
										System.out.print("Enter Choice : ");
										lchoice = ino.nextInt();
										
										switch(lchoice)
										{
											case 1:
												if(isResultDeclared())
												{
													System.out.println("Time Out");
													break;
												}
												StudentLogin.addCollegesAndBranches(sId);
												out.println("Added Choices");
												break;
											
											case 2:
												if(isResultDeclared())
												{
													System.out.println("Time Out");
													break;
												}
												StudentLogin.deleteChoices(sId);
												out.println("Performed Deletions of Choices");
												break;
											
											case 3:
												StudentLogin.displayChoices(sId);
												out.println("Displayed Choices");
												break;
												
											case 4:
												if(isResultDeclared())
												{
													System.out.println("Time Out");
													break;
												}
												StudentLogin.EditPreferences(sId);
												out.println("Edited Preferences");
												break;
												
											case 5:
												if(isResultDeclared())
												{
													System.out.println("Time Out");
													break;
												}
												StudentLogin.submitForm(sId);
												out.println("Submitted Form");
												break;
												
											case 6:
												if(isResultDeclared())
												{
													StudentLogin.viewResult(sId);
													break;
												}
												else
												{
													System.out.println("Result has not been declared Yet.");
													break;
												}
												
												
											case 7:
												out.println("Logged Out");
												break;
												
											default:
												System.out.println("Enter a Valid Choice");
										}
									}
								}
								break;
							
							case 3:
								out.println("Logged out of Student Section");
								break;
							
							default:
								System.out.println("Enter a Valid Choice \n");
						}
						
					}
				}
				break;
				
				case 2:
				{
					int ichoice = 1;
					while(ichoice != 3)
					{
						out.println("In College Section");
						System.out.println("\n------------------------ College Section ------------------------\n");
						System.out.println("1. Sign Up");
						System.out.println("2. Sign In");
						System.out.println("3. Exit\n");
						
						System.out.print("Enter Choice : ");
						ichoice = ino.nextInt();
						
						switch(ichoice)
						{
							case 1:
								if(isResultDeclared())
								{
									System.out.println("Time Out");
									break;
								}
								System.out.println("\n----------- College SignUp --------------\n");
								out.println("Singing up for collegeSection");
								CollegePage.sigup();
								break;
								
							case 2:
								int cId = CollegePage.login();
								if(cId != 0)
								{
									out.println(" Login to College Section");
									int lchoice = 1;
									while(lchoice != 3)
									{
										System.out.println("\n------------------------ College Login --------------------- \n");
										System.out.println("1. List of Applied Students");
										System.out.println("2. View Result");
										System.out.println("3. Log Out");
										System.out.print("\nEnter Choice : ");
										lchoice = ino.nextInt();
										switch(lchoice)
										{
											
											case 1:
												CollegeLogin.displayStudents(cId);
												out.println("Displayed List of Applied Student");
												break;
												
											case 2:
												if(isResultDeclared())
													CollegeLogin.displayResult(cId);
												else
													System.out.println("Result has Not been Released Yet");
												out.println("Viewing Result");
												break;
												
											case 3:
												out.println("College Logged Out");
												break;
												
											default :
												System.out.println("Enter a valid Choice");
										}
									}
								}
								break;
							
							case 3:
								out.println("Logging out of College Section");
								break;
							
							default:
								System.out.println("Enter a Valid Choice \n");
						}
						
					}
				}
				break;
				
				case 3:
					out.print("Exit");
					System.out.println("\n************ Logged Out Of Student Admission Portal ***************");
					break;
					
				default :
					System.out.println("Enter a valid choice");
					
			}
		}

	}

}
