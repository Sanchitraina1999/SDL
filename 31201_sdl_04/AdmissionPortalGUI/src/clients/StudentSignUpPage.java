package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import connection.ConnectionClass;


public class StudentSignUpPage extends JFrame implements ActionListener{
	
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    JTextField t1,t2,t3,t4,t5,t6, t8;
    JPasswordField t7;
    JRadioButton r1,r2,r3,r4,r5;
    JButton submit;
    JComboBox c1,c2,c3;
    public static int SID = 0;
    
	public StudentSignUpPage() {
	
	        
	        l2 = new JLabel("Personal Details");
	        l2.setFont(new Font("Raleway", Font.BOLD, 22));
	        
	        l3 = new JLabel("Name : ");
	        l3.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l4 = new JLabel("Father's Name : ");
	        l4.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l5 = new JLabel("Marks : ");
	        l5.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l6 = new JLabel("Gender : ");
	        l6.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l7 = new JLabel("Email Address : ");
	        l7.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l8 = new JLabel("Marital Status : ");
	        l8.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l9 = new JLabel("Address : ");
	        l9.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l10 = new JLabel("State : ");
	        l10.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l11 = new JLabel("PassWord : ");
	        l11.setFont(new Font("Raleway", Font.BOLD, 20));
	        
	        l12 = new JLabel("ReEnter PassWord : ");
	        l12.setFont(new Font("Raleway", Font.BOLD, 20));
	                
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
	        
	        t7 = new JPasswordField();
	        t7.setFont(new Font("Raleway", Font.BOLD, 14));
	        
	        t8 = new JTextField();
	        t8.setFont(new Font("Raleway", Font.BOLD, 14));
	        
	       
	        
	        submit = new JButton("Submit");
	        submit.setFont(new Font("Raleway", Font.BOLD, 14));
	        submit.setBackground(Color.BLACK);
	        submit.setForeground(Color.WHITE);
	        
	        r1 = new JRadioButton("Male");
	        r1.setFont(new Font("Raleway", Font.BOLD, 14));
	        r1.setBackground(Color.WHITE);
	        
	        r2 = new JRadioButton("Female");
	        r2.setFont(new Font("Raleway", Font.BOLD, 14));
	        r2.setBackground(Color.WHITE);
	        
	        r4 = new JRadioButton("Married");
	        r4.setFont(new Font("Raleway", Font.BOLD, 14));
	        r4.setBackground(Color.WHITE);
	        
	        r5 = new JRadioButton("Unmarried");
	        r5.setFont(new Font("Raleway", Font.BOLD, 14));
	        r5.setBackground(Color.WHITE);
	        
	        r3 = new JRadioButton("Other");
	        r3.setFont(new Font("Raleway", Font.BOLD, 14));
	        r3.setBackground(Color.WHITE);
	        
	        
	        
	        setLayout(null);
	        
	        l2.setBounds(290,70,600,30);
	        add(l2);
	        
	        l3.setBounds(100,140,100,30); // name start
	        add(l3);
	        
	        t1.setBounds(300,140,400,30);
	        add(t1);
	        
	        l4.setBounds(100,190,200,30);
	        add(l4);
	        
	        t2.setBounds(300,190,400,30);
	        add(t2);
	        
	        l5.setBounds(100,240,200,30);
	        add(l5);
	        
	        t3.setBounds(300,240,400,30);
	        add(t3);
	        
//	        l13.setBounds(300,240,40,30);
//	        add(l13);
	        
//	        c1.setBounds(340,240,60,30);
//	        add(c1);
//	        
//	        l14.setBounds(410,240,50,30);
//	        add(l14);
//	        
//	        c2.setBounds(460,240,100,30);
//	        add(c2);
//	        
//	        l15.setBounds(570,240,40,30);
//	        add(l15);
//	        
//	        c3.setBounds(610,240,90,30);
//	        add(c3);
	        
	        l6.setBounds(100,290,200,30);
	        add(l6);
	        
	        ButtonGroup grp1 = new ButtonGroup();
	        r1.setBounds(300,290,60,30);
	        add(r1);
	        
	        r2.setBounds(450,290,90,30);
	        add(r2);
	        
	        r3.setBounds(600, 290, 90, 30);
	        add(r3);
	        
	        grp1.add(r1);
	        grp1.add(r2);
	        grp1.add(r3);
	        
	        l7.setBounds(100,340,200,30);
	        add(l7);
	        
	        t4.setBounds(300,340,400,30);
	        add(t4);
	        
	        l8.setBounds(100,390,200,30);
	        add(l8);
	        
	        r4.setBounds(300,390,100,30);
	        add(r4);
	        
	        r5.setBounds(450,390,100,30);
	        add(r5);
	        
	        ButtonGroup grp2 = new ButtonGroup();
	        grp2.add(r4);
	        grp2.add(r5);
	        
	        l9.setBounds(100,440,200,30);
	        add(l9);
	        
	        t5.setBounds(300,440,400,30);
	        add(t5);
	        
	        l10.setBounds(100,490,200,30);
	        add(l10);
	        
	        t6.setBounds(300,490,400,30);
	        add(t6);
	        
	        l11.setBounds(100,540,200,30);
	        add(l11);
	        
	        t7.setBounds(300,540,400,30);
	        add(t7);
	        
	        l12.setBounds(100,590,200,30);
	        add(l12);
	        
	        t8.setBounds(300,590,400,30);
	        add(t8);
	        
	        submit.setBounds(400,660,100,50);
	        add(submit);
	        
	        submit.addActionListener(this); 
	        
	        getContentPane().setBackground(Color.WHITE);
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(850,850);
	        setLocation(500,90);
	        setVisible(true);
	        
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		 	String name = t1.getText();
	        String fname = t2.getText();
	        
	        String gender = null;
	        if(r1.isSelected()){ 
	            gender = "Male";
	        }
	        else if(r2.isSelected()){ 
	            gender = "Female";
	        }
	        else if(r4.isSelected()) {
	        	gender = "Other";
	        }
	            
	        Integer marks = Integer.parseInt(t3.getText());
	        String maritialStatus = null;
	        if(r3.isSelected()){ 
	            maritialStatus = "Married";
	        }else if(r4.isSelected()){ 
	            maritialStatus = "Unmarried";
	        }else if(r5.isSelected()){ 
	            maritialStatus = "Other";
	        }
	           
	        String email = t4.getText();
	        String address = t5.getText();
	        String state = t6.getText();
	        String  password = new String(t7.getPassword());
	        String rpassWord = t8.getText();

	        if(e.getSource() == submit)
	        {
        	 	if(t3.getText().equals("") == true || t6.getText().equals("") == true ){
	                JOptionPane.showMessageDialog(null, "Fill all the required fields");
	                return;
	            }
        	 	
        	 	else if(password.hashCode() != rpassWord.hashCode())
        	 	{
        	 		JOptionPane.showMessageDialog(null, "Password Field Not Matched");
        	 		return;
        	 	}
	            
	            else{
	                
	            	try {
	        			ConnectionClass newConnection = new ConnectionClass();
	        			Connection con =  newConnection.returnConnection();
	        			String query = "insert into students(sId, name, marks, address, email) values (?,?,?,?,?)";
	        			
	        			
	        			PreparedStatement pst = con.prepareStatement(query);
	        			
	        			int sId = getsId();
	        			SID = sId;
	        			pst.setInt(1, sId);
	        			pst.setString(2, name);
	        			pst.setInt(3, marks);
	        			pst.setString(4, address);
	        			pst.setString(5, email);
	        			pst.executeUpdate();
	        			
	        			query = "Insert into studentPasswords values(?, ?)";
	        			pst = con.prepareStatement(query);
	        			pst.setInt(1, sId);
	        			pst.setString(2, password);
	        			pst.executeUpdate();
	        			con.close();
	        			
	        		} catch (Exception exp) {
	        			// TODO: handle exception
	        			System.out.println("Could Not add to Database");
	        			exp.printStackTrace();
	        		}
	            }
        	 	JOptionPane.showMessageDialog(null,"Your Id is " + SID );
        	 	setVisible(false);
        	 	new StudentMainLogin().setVisible(true);
        	 	
	        }       
		
	}
	
	public static int getsId()
	{
		int id = 0;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select sId from staticIds";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			
			query = "update staticIds set sId = " + (id + 1) + " where sid = " + id;
			pst.executeUpdate(query);
			con.close();
		
		} catch (Exception e) {
			System.out.println("Student Id could not be accesed");
			return 0;
		}
		return id;
		
	}
	
	public static void main(String[] args) {
		new StudentSignUpPage();
	}

}


