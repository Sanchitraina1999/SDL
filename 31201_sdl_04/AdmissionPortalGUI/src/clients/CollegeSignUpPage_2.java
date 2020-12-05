package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

import connection.ConnectionClass;

public class CollegeSignUpPage_2 extends JFrame implements ActionListener {
	
	JLabel heading,l1,l2,l3,l4,l5,l6,l7,l8, id15;
	JTextField t1, t2, t3, t4, t5, t6, t7, t8;
	JCheckBox r1, r2, r3, r4, r5, r6, r7, r8;
	JButton submit;
	
	
	
	public CollegeSignUpPage_2(){
		heading = new JLabel("Select Branches");
		heading.setFont(new Font("Raleway", Font.BOLD, 24));
		
		r1 = new JCheckBox("Aeronautical Engineering");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        
        r2 = new JCheckBox("Bio Mechanical Engineering");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        
        r3 = new JCheckBox("Civil Engineering");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);
        
        r4 = new JCheckBox("Chemical Engineering");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);
        
        r5 = new JCheckBox("Computer Science");
        r5.setFont(new Font("Raleway", Font.BOLD, 14));
        r5.setBackground(Color.WHITE);
        
        r6 = new JCheckBox("Electrical Engineering ");
        r6.setFont(new Font("Raleway", Font.BOLD, 14));
        r6.setBackground(Color.WHITE);
        
        r7 = new JCheckBox("ENTC Engineering ");
        r7.setFont(new Font("Raleway", Font.BOLD, 14));
        r7.setBackground(Color.WHITE);
        
        r8 = new JCheckBox("Mechanical Engineering");
        r8.setFont(new Font("Raleway", Font.BOLD, 14));
        r8.setBackground(Color.WHITE);
        
        t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t2 = new JTextField();
		t2.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t3 = new JTextField();
		t3.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t4 = new JTextField();
		t4.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t5 = new JTextField();
		t5.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t6 = new JTextField();
		t6.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t7 = new JTextField();
		t7.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t8 = new JTextField();
		t8.setFont(new Font("Raleway", Font.BOLD, 14));
		
		submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        
        
        setLayout(null);
        
        heading.setBounds(300,100,600,60);
        add(heading);
        
        r1.setBounds(100,200,300,40); // name start
        add(r1);
        
        t1.setBounds(400,200,100,40);
        add(t1);
        
        r2.setBounds(100,260,300,40);
        add(r2);
        
        t2.setBounds(400,260,100,40);
        add(t2);
        
        r3.setBounds(100,320,300,40);
        add(r3);
        
        t3.setBounds(400,320,100,40);
        add(t3);
        
        r4.setBounds(100,380,300,40);
        add(r4);
        
        t4.setBounds(400,380,100,40);
        add(t4);
        
        r5.setBounds(100,440,300,40);
        add(r5);
        
        t5.setBounds(400,440,100,40);
        add(t5);
        
        r6.setBounds(100,500,300,40);
        add(r6);
        
        t6.setBounds(400,500,100,40);
        add(t6);
        
        r7.setBounds(100,560,300,40);
        add(r7);
        
        t7.setBounds(400,560,100,40);
        add(t7);
        
        r8.setBounds(100,620,300,40);
        add(r8);
        
        t8.setBounds(400,620,100,40);
        add(t8);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(370,700,150,60);
        add(submit);
        submit.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850,1000);
        setLocation(400,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == submit)
		{
			
			if(r1.isSelected())
			{
				if(t1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 1 + ", " + Integer.parseInt(t1.getText()) + ")";
					addBranchtoDb(query);
				}
					
			}
			
			if(r2.isSelected())
			{
				if(t2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 2 + ", " + Integer.parseInt(t2.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r3.isSelected())
			{
				if(t3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 3 + ", " + Integer.parseInt(t3.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r4.isSelected())
			{
				if(t4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 4 + ", " + Integer.parseInt(t4.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r5.isSelected())
			{
				if(t5.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 5 + ", " + Integer.parseInt(t5.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r6.isSelected())
			{
				if(t6.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 6 + ", " + Integer.parseInt(t6.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r7.isSelected())
			{
				if(t7.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 7 + ", " + Integer.parseInt(t7.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			if(r8.isSelected())
			{
				if(t8.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter valid Intake for Selected Branch");
					return;
				}
				else {
					String query = "Insert into branchCapacity values (" + CollegeSignUp.cId + ", " + 8 + ", " + Integer.parseInt(t8.getText()) + ")";
					addBranchtoDb(query);
				}
			}
			
			new CollegeMainLogin().setVisible(true);
			setVisible(false);
			
		}
		
		
	}
	
	void addBranchtoDb(String query)
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	

}
