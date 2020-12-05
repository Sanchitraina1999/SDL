package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import connection.ConnectionClass;

public class AdminSignIn extends JFrame implements ActionListener{
	
	JLabel heading, u, p;
	JTextField userId;
	JPasswordField passWord;
	JButton sIn, sUp, clr;

	
	public AdminSignIn() {
		// TODO Auto-generated constructor stub
		heading = new JLabel("Admin Login Page");
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
        
        sIn.setFont(new Font("Arial", Font.BOLD, 20));
        sIn.setBounds(250,450,150,50);
        add(sIn);
        
        clr.setFont(new Font("Arial", Font.BOLD, 20));
        clr.setBounds(420,450,150,50);
        add(clr);
        
        clr.addActionListener(this);
        sIn.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,750);
        setLocation(500,200);
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == clr) {
			u.setText("");
			p.setText("");
		}
		else if( e.getSource() == sIn)
		{
			String uName = userId.getText();
			String pWord = new String(passWord.getPassword());
			if(uName.hashCode() != "admin".hashCode() || pWord.hashCode() != "admin".hashCode())
			{
				JOptionPane.showMessageDialog(null, "Enter a Valid UserName and Password");
				return;
			}
			else { 
				new AdminLogin().setVisible(true);
				setVisible(false);
			}	
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminSignIn();

	}

}
