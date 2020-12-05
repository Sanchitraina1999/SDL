package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;

import clients.Pair;
import connection.ConnectionClass;

public class AdminLogin extends JFrame implements ActionListener {
	
	public JPanel contentPane;
	public boolean flag=true,flag2=true;
	public Dimension screenSize;
	public ImageIcon icon,icon2,icon3,icon4;
	public Object obj;
	public JLabel lblAdmission,lblAdmission2,lblAdmission3,test1, test2, test3,test4;
	public JSeparator separator,separator_1;
	public JPanel panel,panel_2,panel_1;
	public JButton btnMerit, btnLogout, buttonHome, btnViewResult ,btnResult;
	public static HashMap<Pair<Integer, Integer>, Integer> currentBranchSize = new HashMap<Pair<Integer,Integer>, Integer>();  // <cid, bId>, size
	public static HashMap<Pair<Integer, Integer>, Integer> finalBranchSize = new HashMap<Pair<Integer,Integer>, Integer>();
	
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
	
	public static void declareResult()
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "update result set declared = true where declared = false";
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			
			compileResult();
		
			con.close();
		} catch (Exception e) {
			System.out.println("Can not display Merit List");
		}
	}
	
	public static void compileResult()
	{
		getBranchSizes();
		ArrayList<Integer> students = getStudentsMarkWise();
		
		Iterator<Integer> itr = students.iterator();
		while(itr.hasNext())
		{
			int sId = itr.next();
			ArrayList<Pair<Integer, Integer>> branches = getPriorities(sId);
			Iterator<Pair<Integer, Integer>> itr1 = branches.iterator();
			
			while(itr1.hasNext())
			{
				Pair<Integer, Integer> pair = itr1.next();
				int cId = pair.a;
				int bId = pair.b;
				
				if(currentBranchSize.get(pair) < finalBranchSize.get(pair))
				{
					int size = currentBranchSize.get(pair);
					currentBranchSize.replace(pair, (size + 1) );
					addToResult(sId, cId, bId);
					break;
				}
			}
		}
	}
	
	public static void addToResult(int sId, int cId, int bId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Insert into finalResult values(?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, sId);
			pst.setInt(2, cId);
			pst.setInt(3, bId);
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Add result to db");
		}
		
	}
	
	public static ArrayList<Pair<Integer, Integer>> getPriorities(int sId)
	{
		ArrayList<Pair<Integer, Integer> > priorities = new ArrayList<Pair<Integer,Integer>>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, bId from studentSelections where sId = " + sId + " order by preference asc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Pair<Integer, Integer> pair = new Pair<Integer, Integer>(rs.getInt(1), rs.getInt(2));
				priorities.add(pair);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Priorities from db");
		}
		
		return priorities;
	}
	
	public static void getBranchSizes()
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from branchCapacity";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				int cId = rs.getInt(1);
				int bId = rs.getInt(2);
				int cap = rs.getInt(3);
				currentBranchSize.put(new Pair<Integer, Integer>(cId, bId), 0);
				finalBranchSize.put(new Pair<Integer, Integer>(cId, bId), cap);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Sizes from db");
		}
	}
	
	public static ArrayList<Integer> getStudentsMarkWise()
	{
		ArrayList<Integer> students = new ArrayList<Integer>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select sId from students order by marks desc";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				students.add(rs.getInt(1));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Can not get Branch Sizes from db");
		}
		
		return students;
	}
	
	
	public AdminLogin() {
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
		
		lblAdmission2 = new JLabel("Welcome To College Portal");
		lblAdmission2.setBounds(750, 55, 800, 25);
		lblAdmission2.setFont(new Font("Bold", Font.PLAIN, 25));
		add(lblAdmission2);
		
		lblAdmission3 = new JLabel("Admin Portal");
		lblAdmission3.setBounds(1000,127, 300, 32);
		lblAdmission3.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblAdmission3);
		
		
		
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
		btnMerit .setBounds(0, 60, 300, 60);
		btnMerit .setFont(new Font("Arial", Font.PLAIN, 23));
		btnMerit .setFocusable(false);
		panel.add(btnMerit);
		btnMerit.addActionListener(this);
		
		btnResult = new JButton("Declare Result");
		btnResult.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnResult.setFocusable(false);
		btnResult.setBounds(0, 120, 300, 60);
		panel.add(btnResult);
		btnResult.addActionListener(this);
		
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnLogout.setFocusable(false);
		btnLogout.setBounds(0, 240, 300, 60);
		panel.add(btnLogout);
		btnLogout.addActionListener(this);
		
		buttonHome = new JButton("Home");
		buttonHome.setFont(new Font("Dialog", Font.PLAIN, 23));
		buttonHome.setFocusable(false);
		buttonHome.setBounds(0, 0, 300, 60);
		panel.add(buttonHome);
		buttonHome.addActionListener(this);
		
		btnViewResult = new JButton("Display Allotment List");
		btnViewResult.setBounds(0, 180, 300, 60);
		panel.add(btnViewResult);
		btnViewResult.setFont(new Font("Dialog", Font.PLAIN, 23));
		btnViewResult.setFocusable(false);
		btnViewResult.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 1920, 10);
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == buttonHome) {
			
		}
		else if(e.getSource() == btnLogout)
		{
			new AdminSignIn().setVisible(true);
			setVisible(false);
		}
		else if(e.getSource() == btnMerit) {
			new MeritList().setVisible(true);
		}
		else if(e.getSource() == btnViewResult)
		{
			if(isResultDeclared() == false) {
				JOptionPane.showMessageDialog(null, "Result is not Declared yet");
				return;
			}
			new ViewResult().setVisible(true);
		}
		else if(e.getSource() == btnResult){
			int result = JOptionPane.showConfirmDialog(null, "Are You Sure to Compile Result ");
			if(result == JOptionPane.YES_OPTION)
				declareResult();
			return;
		}
		
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminLogin();

	}


}
