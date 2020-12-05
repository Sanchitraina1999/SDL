package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;

import connection.ConnectionClass;

// Student Main Login Page with all functionalities

public class StudentLogin  extends JFrame implements ActionListener{
	
	public JPanel contentPane;
	
	public boolean flag=true,flag2=true;
	
	public Dimension screenSize;
	public ImageIcon icon,icon2,icon3,icon4;
	public Object obj;
	public JLabel lblAdmission,lblAdmission2,lblAdmission3,test1, test2, test3,test4;
	public JSeparator separator,separator_1;
	public JPanel panel,panel_2,panel_1;
	public JButton btnPersonal,buttonChoices,btnLogout,buttonHome,btnDisplayCutoffList,btnResult;
	int SID = StudentMainLogin.SID;
	
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
	
	public static HashMap<Integer, String> getBranches()
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
	
	public static HashMap<Integer, String> getCollegesNames()
	{
		HashMap<Integer, String> colleges = new HashMap<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				colleges.put( rs.getInt(1), rs.getString(2));
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch College Names from Db");
		}
		
		return colleges;
	}
	
	public static void Result(int sId)
	{
		Boolean result = false;
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select * from finalResult where sId = " + sId;
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				result = true;
				int cId = rs.getInt(2);
				int bId = rs.getInt(3);
				HashMap<Integer, String> colleges = getCollegesNames();
				HashMap<Integer, String> branches = getBranches();
				String message = "Congrats\n";
				String m2 = "College : " + colleges.get(cId) + "\n Branch : " + branches.get(bId);
				JOptionPane.showMessageDialog(null, (message + m2));
			}
			
			con.close();
		
		} catch (Exception e) {
			System.out.println("Student Id could not be accesed");
		}
		
		if(result == false)
			JOptionPane.showMessageDialog(null, "Sorry, No Course was Allocated");
		
	}
	
	public StudentLogin() {
		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Student Admission System");
		setLayout(null);

		
		lblAdmission = new JLabel("College Admission Portal");
		lblAdmission.setBounds(800, 15, 300, 20);
		lblAdmission.setFont(new Font("Arial", Font.PLAIN, 17));
		add(lblAdmission);
		
		lblAdmission2 = new JLabel("Welcome To Student Portal");
		lblAdmission2.setBounds(750, 55, 800, 25);
		lblAdmission2.setFont(new Font("Bold", Font.PLAIN, 25));
		add(lblAdmission2);
		
		lblAdmission3 = new JLabel("Student Portal");
		lblAdmission3.setBounds(1000,127, 300, 32);
		lblAdmission3.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblAdmission3);
		
		test1= new JLabel("Test1");
		test1.setBounds(450, 0, 500, 200);
		test1.setFont(new Font("Bold", Font.PLAIN, 25));
		
		test2= new JLabel("Test2");
		test2.setBounds(450, 200, 500, 200);
		test2.setFont(new Font("Bold", Font.PLAIN, 25));
		
		test3= new JLabel("Test3");
		test3.setBounds(450, 400, 500, 200);
		test3.setFont(new Font("Bold", Font.PLAIN, 25));
		
		test4= new JLabel("Test4");
		test4.setBounds(450, 600, 500, 200);
		test4.setFont(new Font("Bold", Font.PLAIN, 25));
		
		separator = new JSeparator();
		separator.setBounds(0, 115, 1920, 100);
		separator.setForeground(Color.BLACK);
		add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 103, 1920, 100);
		separator_1.setForeground(Color.BLACK);
		add(separator_1);
		
		panel = new JPanel();
		panel.setBounds(0, 115, 300, 758);
		add(panel);
		panel.setBackground(new Color(51, 153, 255));
		panel.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(350, 160, 1500, 850);
		panel_2.setBackground(new Color(51, 153, 255));
		panel_2.setLayout(null);
		add(panel_2);
		
		
		btnResult = new JButton("Check the Result");
		btnResult.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnResult.setFocusable(false);
		btnResult.setBounds(0, 120, 300, 60);
		panel.add(btnResult);
		btnResult.addActionListener(this);
		
		buttonChoices = new JButton("Select the Choices");
		buttonChoices.setFont(new Font("Dialog", Font.PLAIN, 23));
		buttonChoices.setFocusable(false);
		buttonChoices.setBounds(0, 60, 300, 60);
		panel.add(buttonChoices);
		buttonChoices.addActionListener(this);
		
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(0, 180, 300, 60);
		panel.add(btnLogout);
		btnLogout.addActionListener(this);
		
		buttonHome = new JButton("Home");
		buttonHome.setFont(new Font("Dialog", Font.PLAIN, 23));
		buttonHome.setFocusable(false);
		buttonHome.setBounds(0, 0, 300, 60);
		panel.add(buttonHome);
		buttonHome.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 1920, 10);
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1);
		setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonHome)
		{
			
			
		}
		else if(e.getSource() == btnResult)
		{
			if(isResultDeclared() == false) {
				JOptionPane.showMessageDialog(null, "Result Has Not been Declared Yet");
				return;
			}
			else
			{
				Result(SID);
			}
			
		}
		
		if(e.getSource() == buttonChoices)
		{
			new StudentLoinColleges().setVisible(true);
			
		}
		if(e.getSource() == btnLogout)
		{
			new StudentMainLogin().setVisible(true);
			setVisible(false);
		}
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentLogin();

	}

	
}
