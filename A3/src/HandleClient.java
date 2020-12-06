import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class HandleClient implements Runnable {
	Socket client;
	char ch;
	Integer choice;
	String msg;
	Sample ob = null;
	InputStream is;
	ObjectInputStream ois;
	OutputStream os;
	ObjectOutputStream oos;
	Statement st, st1, st2, st3;
	ResultSet rs;
	String query = "";
	Scanner sc = new Scanner(System.in);

	public HandleClient(Socket socket) throws Exception {
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
		st = Server.con.createStatement();
		st1 = Server.con.createStatement();
		st2 = Server.con.createStatement();
		st3 = Server.con.createStatement();
	}

	public void run() {
		try {
			do {
				choice = (Integer) ois.readObject();
				switch (choice) {
					case 1:
						do {
							choice = (Integer) ois.readObject();
							switch (choice) {
								case 1:
									ob = (Sample) ois.readObject();
									st.execute("insert into depositors(username,email,password) " + "values( " + "'"
											+ ob.name + "'" + "," + "'" + ob.email + "'" + " , " + "'"
											+ ob.password + "'" + " )");
									rs = st.executeQuery(
											"select dep_id from depositors where email=" + "'" + ob.email + "'");
									rs.next();
									msg = "SignUp Successfull for " + ob.name + "!!\n USER-ID IS "
											+ rs.getInt("dep_id");
									oos.writeObject(msg);
									break;
								case 2:
									String email1, pass1;
									Integer id, flag = 1;
									while (true) {
										id = (Integer) ois.readObject();
										query = "select dep_id from depositors where dep_id=" + id;
										rs = st1.executeQuery(query);
										if (rs.next()) {
											oos.writeObject(flag);
										} else {
											flag = -1;
											oos.writeObject(flag);
										}
										email1 = (String) ois.readObject();
										query = "select email from depositors where email=" + "'" + email1 + "'";
										ResultSet rs1 = st1.executeQuery(query);
										if (rs1.next()) {
											oos.writeObject(flag);
										} else {
											flag = -1;
											oos.writeObject(flag);
										}
										pass1 = (String) ois.readObject();
										query = "select password from depositors where password=" + "'" + pass1 + "'";
										ResultSet rs2 = st1.executeQuery(query);
										if (rs2.next()) {
											msg = "Login successful";
											oos.writeObject(msg);
											oos.writeObject(flag);
										} else {
											msg = "Password incorrect";
											oos.writeObject(msg);
											flag = 0;
											oos.writeObject(flag);
										}
										break;
									}
									do {
										choice = (Integer) ois.readObject();
										switch (choice) {
											case 1:
												Integer inputBal = (Integer) ois.readObject();
												query = "select balance from depositors where dep_id=" + id;
												rs = st2.executeQuery(query);
												rs.next();
												Integer temp = rs.getInt("balance");
												temp += inputBal;
												query = "update depositors set balance=" + temp + " where dep_id=" + id;
												st3.executeUpdate(query);
												msg = "Successfully added ₹ " + inputBal;
												oos.writeObject(msg);
												break;
											case 2:
												query = "select balance from depositors where dep_id=" + id;
												rs = st.executeQuery(query);
												rs.next();
												Integer balance = rs.getInt("balance");
												msg = "Your current balance is: " + balance;
												oos.writeObject(msg);
												break;
											case 3:
												Integer withdrawAmt;
												withdrawAmt = (Integer) ois.readObject();
												query = "select balance from depositors where dep_id=" + id;
												rs = st2.executeQuery(query);
												rs.next();
												Integer temp2 = rs.getInt("balance");
												if (withdrawAmt < temp2) {
													temp2 -= withdrawAmt;
													query = "update depositors set balance=" + temp2 + " where dep_id="
															+ id;
													st3.executeUpdate(query);
													msg = "Successfully withdrawn Rs. " + withdrawAmt;
												} else
													msg = "Hey... You don't have enough amount for this transaction";
												oos.writeObject(msg);
												break;
										}
										ch = (char) ois.readObject();
									} while (ch == 'y');
									break;
							}
							ch = (char) ois.readObject();
						} while (ch == 'y');
						break;
					case 2:
						do {
							choice = (Integer) ois.readObject();
							switch (choice) {
								case 1:
									query = "select * from depositors";
									String userdata = "";
									ArrayList<String> str = new ArrayList<String>();
									rs = st3.executeQuery(query);
									while (rs.next()) {
										userdata = rs.getString(1) + "\t\t" + rs.getString(2)+"\t\t" + rs.getInt(4)+ "\t\t" + rs.getInt(5);
										str.add(userdata);
									}
									oos.writeObject(str);
									break;
							}
							ch = (char) ois.readObject();
						} while (ch == 'y');
						break;
					case 3:
						String msgin = "", msgout = "";
						while (true) {
							msgin = (String) ois.readObject();
							if (!msgin.equals("EXIT")) {
								System.out.println(msgin + "\n");
								msgout = "SERVER : "+sc.nextLine();
								oos.writeObject(msgout);
							} else{
								System.out.println("CLIENT HAS ENDED THE CHAT\n");
								break;
							}
						}
						break;
				}
				ch = (char) ois.readObject();
			} while (ch == 'y');
			System.out.println("\nClosing sockets in use");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
