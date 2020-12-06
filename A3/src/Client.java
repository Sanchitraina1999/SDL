import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class Client {
	public static void main(String[] args) throws Exception {
		char ch;
		Integer Choice;
		Scanner sc = new Scanner(System.in);
		ResultSet rs;
		String msg;
		Integer id_user = 1;
		Socket socket = new Socket("localhost", 7041);
		System.out.println("Connection established with the server\n");
		Sample obj = null;
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		InputStream is = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);

		System.out.println("\n|\tWelcome To the Post Office Management System\t\t|\n\n");
		do {
			System.out.println("1)Depositor\n2)Agent\n3)Chatbox\n4)Exit");
			Choice = sc.nextInt();
			oos.writeObject(Choice);
			switch (Choice) {
				case 1:
					do {
						System.out.println("1) SignUp\n" + "2) Login\n" + "3) Exit User Mode");
						Choice = sc.nextInt();
						oos.writeObject(Choice);
						switch (Choice) {
							case 1:
								String email, pass, username, confirm;
								System.out.println("Welcome to the Signup Page for Depositor!!! Enter Your details...\n");
								sc.nextLine();
								while (true) {
									System.out.print("Username:");
									username = sc.nextLine();
									if (username.equals(""))
										System.out.println("Username can't be empty");
									else
										break;
								}
								while (true) {
									System.out.print("Enter emailid:");
									email = sc.nextLine();
									if (email.equals(""))
										System.out.println("Email field can't be empty");
									else
										break;
								}
								while (true) {
									System.out.print("Enter Password:");
									pass = sc.nextLine();
									if (pass.equals(""))
										System.out.println("Password can't be empty");
									else
										break;
								}
								while (true) {
									System.out.print("Confirm Password:");
									confirm = sc.nextLine();
									if (pass.equals(confirm)) {
										break;
									} else
										System.out.println("Password Does not match.. Please try again");
								}
								obj = new Sample(username, email, pass, id_user);
								oos.writeObject(obj);
								id_user++;
								msg = (String) ois.readObject();
								System.out.println("\n" + msg);
								break;

							case 2:
								String email1, pass1;
								Integer id, flag;
								System.out.println("Login Page!! \n");
								sc.nextLine();
								while (true) {
									System.out.print("User-id:");
									id = sc.nextInt();
									sc.nextLine();
									oos.writeObject(id);
									flag = (Integer) ois.readObject();
									if (flag == 1) {
										System.out.print("Enter Email-id:");
										email1 = sc.nextLine();
										oos.writeObject(email1);
										flag = (Integer) ois.readObject();
										if (flag == 1) {
											System.out.print("Enter Password:");
											pass1 = sc.nextLine();
											oos.writeObject(pass1);
											msg = (String) ois.readObject();
											flag = (Integer) ois.readObject();
											System.out.println(msg);
											break;
										} else {
											System.out.println("Email-id incorrect\n");
											flag = 0;
											break;
										}
									} else {
										System.out.println("User-id incorrect\n");
										flag = 0;
										break;
									}
								}
								if (flag != 0) {
									do {
										System.out.println(
												"1) Add Money\n2)Check Balance\n3) Withdraw Money\n");
										Choice = sc.nextInt();
										oos.writeObject(Choice);
										switch (Choice) {
											case 1:
												Integer inputBal;
												System.out.print("Enter Amount to be added:");
												inputBal = sc.nextInt();
												oos.writeObject(inputBal);
												msg = (String) ois.readObject();
												System.out.println("\n" + msg);
												break;
											case 2:
												msg = (String) ois.readObject();
												System.out.println("\n" + msg);
												break;
											case 3:
												Integer withdrawAmt;
												System.out.print("Enter amount to be withdrawn:");
												withdrawAmt = sc.nextInt();
												oos.writeObject(withdrawAmt);
												msg = (String) ois.readObject();
												System.out.println("\n" + msg);
												break;

										}
										System.out.println("\nDo you wish to continue with the current User (y/n)?");
										ch = sc.next().charAt(0);
										oos.writeObject(ch);
									} while (ch == 'y');
								}
								break;

						}
						System.out.println("\nDo you wish to continue with the User MENU (y/n)?");
						ch = sc.next().charAt(0);
						oos.writeObject(ch);
					} while (ch == 'y');
					break;
				case 2:
					do {
						int f=0;
						ch='y';
						System.out.print("1) List of Account Holders\n2) Exit to Main Menu\n");
						Choice = sc.nextInt();
						oos.writeObject(Choice);
						switch (Choice) {
							case 1:
								ArrayList<String> str = new ArrayList<String>();
								str = (ArrayList<String>) ois.readObject();
								if(str.size()>0){
									System.out.println("username\t\temail-id\t\tbalance\t\tdepositor_id");
									System.out.println("________\t\t_________\t\t_______\t\t____________");
									Iterator it = str.iterator();
									while (it.hasNext()) {
										System.out.println(it.next());
									}
								}
								else{
									System.out.println("No depositors\n");
								}
								f=1;
								break;
							default:
								f=0;
								ch='n';
								break;
						}
						if(f==1){
							System.out.println("Continue in Agent Mode? (y/n): ");
							ch=sc.next().charAt(0);
						}
						oos.writeObject(ch);
					} while (ch == 'y');
					break;
				case 3:
					sc.nextLine();
					String msgin = "", msgout = "";
					System.out.println("Talk to server");
					msgout = "CLIENT: "+sc.nextLine();
					while (true) {
						if (!msgout.equals("EXIT")) {
							oos.writeObject(msgout);
							msgin = (String) ois.readObject();
							System.out.println(msgin + "\n");
							msgout = sc.nextLine();
						} else {
							oos.writeObject(msgout);
							break;
						}
					}
					break;
			}
			System.out.println("\nDo you wish to continue with the MAIN MENU (y/n)?");
			ch = sc.next().charAt(0);
			oos.writeObject(ch);
		} while (ch == 'y');
		System.out.println("\nClosing socket and terminating program.");
		socket.close();
	}

}
