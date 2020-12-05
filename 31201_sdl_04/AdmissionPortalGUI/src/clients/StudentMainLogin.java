package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

import connection.ConnectionClass;



public class StudentMainLogin extends JFrame implements ActionListener{
	
	

	JLabel heading, u, p;
	JTextField userId;
	JPasswordField passWord;
	JButton sIn, sUp, clr;
	public static int SID = 0; 
	
	public StudentMainLogin()
	{   
        heading = new JLabel("Student Main Page");
        heading.setFont(new Font("Osward", Font.BOLD, 38));
        
        u = new JLabel("User - Id : ");
        u.setFont(new Font("Raleway", Font.BOLD, 28));
        
        p = new JLabel("Password : ");
        p.setFont(new Font("Raleway", Font.BOLD, 28));
        
        userId = new JTextField(15);
        passWord = new JPasswordField(15);
        
        sIn = new JButton("SIGN IN");
        sIn.setBackground(Color.BLACK);
        sIn.setForeground(Color.WHITE);
        
        clr = new JButton("CLEAR");
        clr.setBackground(Color.BLACK);
        clr.setForeground(Color.WHITE);
        
        sUp = new JButton("SIGN UP");
        sUp.setBackground(Color.BLACK);
        sUp.setForeground(Color.WHITE);
        
        setLayout(null);
        
        heading.setBounds(175,50,450,200);
        add(heading);
        
        u.setBounds(125,150,375,200);
        add(u);
        
        userId.setFont(new Font("Arial", Font.BOLD, 14));
        userId.setBounds(300,235,230,30);
        add(userId);
        
        p.setBounds(125,225,375,200);
        add(p);
        
        
       
        
        passWord.setFont(new Font("Arial", Font.BOLD, 14));
        passWord.setBounds(300,310,230,30);
        add(passWord);
        
        sIn.setFont(new Font("Arial", Font.BOLD, 14));
        sIn.setBounds(300,400,100,30);
        add(sIn);
        
        clr.setFont(new Font("Arial", Font.BOLD, 14));
        clr.setBounds(430,400,100,30);
        add(clr);
        
        sUp.setFont(new Font("Arial", Font.BOLD, 14));
        sUp.setBounds(300,450,230,30);
        add(sUp);
        
        sUp.addActionListener(this);
        clr.addActionListener(this);
        sIn.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,750);
        setLocation(500,200);
        setVisible(true);
        
        
        
	}
	
	public static boolean isResultDeclared()
	{
		Boolean resultDeclared = false;
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

@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	int sId = 0;
	if(e.getSource() == sIn)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			sId = Integer.parseInt(userId.getText());
			String pWord = new String(passWord.getPassword());
			
			System.out.println("sId : " + sId);
			System.out.println("Password : " + pWord );
			String query = "Select * from studentPasswords where sid = " + sId;
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			
			if(rs.next())
			{
				String pw = rs.getString(2);
				if(pw.equals(pWord))
				{
					SID = sId;
					new StudentLogin().setVisible(true);
					setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong Password");
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect userId");
				
			}
			
		} catch (Exception exp) {
			System.out.println("Wrong Login Details");
		}
		
	}
	
	else if(e.getSource() == clr )
	{
		userId.setText("");
		passWord.setText("");
	}
	
	else if(e.getSource() == sUp)
	{
		if(isResultDeclared() == true) {
			JOptionPane.showMessageDialog(null, "Result has been Declared\n No further SignUps");
			return;
		}
		JOptionPane.showMessageDialog(null, "Sign up page");
		new StudentSignUpPage().setVisible(true);
		setVisible(false);
	}
	
	}
	
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	public static PrintStream out;
	public static Scanner ino = new Scanner(System.in);
	public static Scanner ins = new Scanner(System.in);
	public static boolean resultDeclared = false;
	public static boolean serverStarted = false;
	
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
		new StudentMainLogin();
		
	}
}
