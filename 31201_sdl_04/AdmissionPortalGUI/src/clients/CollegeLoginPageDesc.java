package clients;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import connection.ConnectionClass;

public class CollegeLoginPageDesc extends JFrame implements ActionListener {
	
	public JPanel contentPane;
	public boolean flag=true,flag2=true;
	public Dimension screenSize;
	public ImageIcon icon,icon2,icon3,icon4;
	public Object obj;
	public JLabel lblAdmission,lblAdmission2,lblAdmission3,test1, test2, test3,test4;
	public JSeparator separator,separator_1;
	public JPanel panel,panel_2,panel_1;
	public JButton btnMerit,btnLogout,buttonHome,btnResult;
	
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

	
	public CollegeLoginPageDesc() {
		// TODO Auto-generated constructor stub
		
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
		
		lblAdmission3 = new JLabel("College Portal");
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
		
		btnMerit = new JButton("Merit List");
		btnMerit.setBounds(0, 60, 300, 60);
		btnMerit.setFont(new Font("Arial", Font.PLAIN, 23));
		btnMerit.setFocusable(false);
		panel.add(btnMerit);
		
		
		btnLogout = new JButton("LogOut");
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(0, 178, 300, 60);
		panel.add(btnLogout);
		
		
		btnResult = new JButton("View Result");
		btnResult.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnResult.setFocusable(false);
		btnResult.setBounds(0, 119, 300, 60);
		panel.add(btnResult);
		
		
		
		buttonHome = new JButton("Home");
		buttonHome.setFont(new Font("Dialog", Font.PLAIN, 23));
		buttonHome.setFocusable(false);
		buttonHome.setBounds(0, 1, 300, 60);
		panel.add(buttonHome);
		panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 1920, 10);
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1);
		
		
		btnLogout.addActionListener(this);
		btnMerit.addActionListener(this);
		buttonHome.addActionListener(this);
		btnResult.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == buttonHome)
		{
			
		}
		else if(e.getSource() == btnMerit)
		{
			new ViewMeritList().setVisible(true);
		}
		
		else if(e.getSource() == btnResult)
		{
			if(isResultDeclared() == false) {
				JOptionPane.showMessageDialog(null, "Result Has Not been Declared Yet");
			}
			else
				new CollegeViewResult().setVisible(true);
			
		}
		else if(e.getSource() == btnLogout)
		{
			new CollegeMainLogin().setVisible(true);
			setVisible(false);
			return;
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
