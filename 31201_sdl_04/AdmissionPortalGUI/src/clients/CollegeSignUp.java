package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import connection.ConnectionClass;



public class CollegeSignUp extends JFrame implements ActionListener {
	
	static public int cId = 0;
	JLabel l1,l2,l3,l4,l5, l6,l7;
	JTextField t1,t2,t3,t4,t6;
	JPasswordField t5;
	JButton submit;
	
	
	
	public CollegeSignUp() {
		// TODO Auto-generated constructor stub
		
		l1 = new JLabel("College Details");
		l1.setFont(new Font("Raleway", Font.BOLD, 24));
		    
		l2 = new JLabel("College Name : ");
		l2.setFont(new Font("Raleway", Font.BOLD, 20));
		 
		l3 = new JLabel("Email Address : ");
		l3.setFont(new Font("Raleway", Font.BOLD, 20));
		 
		l4 = new JLabel("Address : ");
		l4.setFont(new Font("Raleway", Font.BOLD, 20)); 
		
		l5 = new JLabel("State : ");
		l5.setFont(new Font("Raleway", Font.BOLD, 20));
		
		l6 = new JLabel("PassWord : ");
        l6.setFont(new Font("Raleway", Font.BOLD, 20));
        
        l7 = new JLabel("ReEnter PassWord : ");
        l7.setFont(new Font("Raleway", Font.BOLD, 20));
		 
		t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t2 = new JTextField();
		t2.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t3 = new JTextField();
		t3.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t4 = new JTextField();
		t4.setFont(new Font("Raleway", Font.BOLD, 14));
		
		t5 = new JPasswordField();
		t5.setFont(new Font("Raleway", Font.BOLD, 14));
		
        t6 = new JTextField();
        t6.setFont(new Font("Raleway", Font.BOLD, 14));
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        
        setLayout(null);
        
        l1.setBounds(300,100,600,60);
        add(l1);
        
        l2.setBounds(100,200,200,40); // name start
        add(l2);
        
        t1.setBounds(350,200,400,40);
        add(t1);
        
        l3.setBounds(100,300,200,40);
        add(l3);
        
        t2.setBounds(350,300,400,40);
        add(t2);
        
        l4.setBounds(100,400,200,40);
        add(l4);
        
        t3.setBounds(350,400,400,40);
        add(t3);
        
        l5.setBounds(100,500,200,40);
        add(l5);
        
        t4.setBounds(350,500,400,40);
        add(t4);
        
        l6.setBounds(100,600,200,40);
        add(l6);
        
        t5.setBounds(350,600,400,40);
        add(t5);
        
        l7.setBounds(100,700,200,40);
        add(l7);
        
        t6.setBounds(350,700,400,40);
        add(t6);
        
        submit = new JButton("Next");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(400,800,150,60);
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
			String  password = new String(t5.getPassword());
			if(password.equals("")){
				return;
			}
			int id = 0;
			try {
				ConnectionClass newConnection = new ConnectionClass();
				Connection con =  newConnection.returnConnection();
				
				String query = "Select cId from staticIds";
				PreparedStatement pst = con.prepareStatement(query);
				
				ResultSet rs =pst.executeQuery();
				rs.next();
				id = rs.getInt(1);
				cId = id;
				
				query = "update staticIds set cId = " + (id + 1) + " where cid = " + id;
				pst.executeUpdate(query);
				
				query = "insert into colleges(cId, name, city, email) values (?,?,?,?)";
				pst = con.prepareStatement(query);
				JOptionPane.showMessageDialog(null, "Your College Id is " + cId );
				
				pst.setInt(1, cId);
				pst.setString(2, t1.getText());
				pst.setString(3, t4.getText());
				pst.setString(4, t2.getText());
				pst.executeUpdate();
				
				query = "Insert into collegeLogin(cId, password) values(?, ?)";
				pst = con.prepareStatement(query);
				pst.setInt(1, cId);
				
				pst.setString(2, password);
				pst.executeUpdate();
				
				con.close();
				System.out.println("Succesfully Added to Database");
				con.close();
			
			} catch (Exception exp) {
				System.out.println("Student Id could not be accesed");
			}
			
			new CollegeSignUpPage_2().setVisible(true);
			setVisible(false);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}


}
