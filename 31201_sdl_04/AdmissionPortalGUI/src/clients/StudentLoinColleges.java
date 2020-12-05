package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;


import connection.ConnectionClass;

public class StudentLoinColleges extends JFrame implements ActionListener{

	
	public JLabel lblFillTheFollowing,lblCollegeName,lblBranchName,lblPriority,lblThisChoiceIs,lblbr,lblCol;
	public JComboBox<String> comboBox,comboBox_1;
	public JTextField t1, txtCol, txtBranch;
	public JButton btnAdd,btnSubmit,btnDelete;
	JScrollPane sp;
	JTable jt;
	JPanel jp;
	int SID = StudentMainLogin.SID;
	DefaultTableModel model;
	
	
	public static HashMap<String, Integer> getCollegeNames()
	{
		HashMap<String, Integer> colleges = new HashMap<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			
			ResultSet rs = pst.executeQuery();
			while(rs.next())
				colleges.put( rs.getString(2),  rs.getInt(1));
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch College Names from Db");
		}
		
		return colleges;
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
	
	public static HashMap<String, Integer> getBranchNames()
	{
		HashMap<String, Integer> branches = new HashMap<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select * from branches";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				branches.put( rs.getString(2), rs.getInt(1));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Fetch Branch Names");
		}
		return branches;
	}
	
	public static TreeMap<String, Integer> getCollegesFomDb() // Returns All Colleges in Db
	{
		TreeMap<String, Integer> availableColleges = new TreeMap<>();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Select cId, name from colleges";
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs =pst.executeQuery();
			while(rs.next())
			{
				availableColleges.put(rs.getString(2), rs.getInt(1));
			}
		
		} catch (Exception e) {
			System.out.println("Could Not Get Colleges from Db");
		}
		
		return availableColleges;
	}
	
	public static TreeSet<Integer> getBranchesFromDb(int sId, int cId)
	{
		TreeSet<Integer> availableBranches = new TreeSet<Integer>();
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			
			String query = "Select bid from branchCapacity where cid = " + cId;
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs =pst.executeQuery();
			
			while(rs.next())
				availableBranches.add(rs.getInt(1));
	
			query = "select bId from studentSelections where sId = " + sId + " and cId = " + cId;
			pst = con.prepareStatement(query);
			
			
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				availableBranches.remove(rs.getInt(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Get Branches From Db");
		}
		
		return availableBranches;
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
	
	public static void addStudentToDb(String query)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Could Add Data to Student");
		}
	}
	
	public static void updatePreference(int sId, int cId, int bId, int pref)
	{
		
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "Update studentSelections set preference = " + pref +  " where sId = " + sId + " and cId = "+ cId + " and  bId = " + bId; 
			PreparedStatement pst = con.prepareStatement(query);	
			pst.executeUpdate();
			
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could not Update preference");
		}
	}
	
	public static void updatePreferences(int sId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select preference, sId, cId, bId from studentSelections where sId =  " + sId + " order by preference asc ";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int prt = 1;
			while(rs.next())
			{
				updatePreference(sId, rs.getInt(3), rs.getInt(4), prt);
				prt++;
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Update Preference to database");
		}
	}
	
	public static TreeMap<Integer, Pair<Integer, Integer>> getpreftable(int sId)
	{
		TreeMap<Integer, Pair<Integer, Integer>> prefTable = new TreeMap<Integer, Pair<Integer,Integer>>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "select preference, sId, cId, bId from studentSelections where sId =  " + sId + " order by preference asc ";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				prefTable.put(rs.getInt(1), new Pair<Integer, Integer>(rs.getInt(3), rs.getInt(4)));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Update Preference to database");
		}
		return prefTable;
	}
	
	public static void removeChoice(int sId, int cId,int bId)
	{
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
			
			String query = "delete from studentSelections where sId = " + sId + " and cId = " + cId + " and bId = " + bId;
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Delete from choices From Db");
		}
	}
	
	
	public StudentLoinColleges(){
		// TODO Auto-generated constructor stub
		
		setLayout(null);
		setBounds(100,10,1500,1000);
		
		lblFillTheFollowing = new JLabel("Fill the Following Information");
		lblFillTheFollowing.setBounds(600, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblCollegeName = new JLabel("College Name");
		lblCollegeName.setBounds(120, 158, 153, 25);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName);
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblBranchName.setBounds(120, 239, 153, 25);
		add(lblBranchName);
		
		lblPriority = new JLabel("Priority : ");
		lblPriority.setFont(new Font("Serif", Font.PLAIN, 20));
		lblPriority.setBounds(1000, 200, 100, 30);
		add(lblPriority);
		
		lblCol = new JLabel("College : ");
		lblCol.setBounds(150, 400, 100, 30);
		lblCol.setFont(new Font("Arial", Font.PLAIN, 20));
		add(lblCol);
		
		txtCol = new JTextField();
        txtCol.setFont(new Font("Raleway", Font.BOLD, 14));
        txtCol.setBounds(250,400,450,30);
        add(txtCol);
		
		lblbr = new JLabel("Branch : ");
		lblbr.setBounds(750, 400, 100, 30);
		lblbr.setFont(new Font("Arial", Font.PLAIN, 20));
		add(lblbr);
		
		txtBranch = new JTextField();
		txtBranch.setFont(new Font("Raleway", Font.BOLD, 14));
		txtBranch.setBounds(850,400,350,30);
        add(txtBranch);
        
        btnDelete = new JButton("Delete");
        btnDelete.setFocusable(false);
        btnDelete.setBounds(650, 480, 120, 30);
        btnDelete.setFont(new Font("Arial",Font.PLAIN,20));
		add(btnDelete);
		
		t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 14));
        t1.setBounds(1100,200,150,30);
        add(t1);
		
		lblThisChoiceIs = new JLabel("This choice is already added");
		lblThisChoiceIs.setForeground(Color.RED);
		lblThisChoiceIs.setBounds(385, 300, 211, 15);
		add(lblThisChoiceIs);
		lblThisChoiceIs.setVisible(false);
		
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(329, 157, 428, 30);
		comboBox.addItem("<Select College>");
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(329, 239, 428, 30);
		comboBox_1.addItem("<Select Branch>");
		
		add(comboBox);
		add(comboBox_1);
		
		btnAdd = new JButton("Add");
		btnAdd.setFocusable(false);
		btnAdd.setBounds(301, 330, 117, 30);
		btnAdd.setFont(new Font("Arial",Font.PLAIN,20));
		add(btnAdd);
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFocusable(false);
		btnSubmit.setBounds(559, 330, 117, 30);
		btnSubmit.setFont(new Font("Arial",Font.PLAIN,20));
		add(btnSubmit);
		
		jp = new JPanel();
		jp.setBounds(0, 600, 1500, 500);
		jp.setBackground(Color.cyan);
		add(jp);
		jp.setLayout(null);
		
		
		updatePreferences(SID);
		
		model = new DefaultTableModel();
		jt = new JTable(model);
		sp = new JScrollPane(jt);
		model.addColumn("Preference");
		model.addColumn("College");
		model.addColumn("Branch");
		sp.setBounds(200,0,1000,600);
		jp.add(sp);
		
		TreeMap<String, Integer> availableColleges = getCollegesFomDb();
		HashMap<Integer, String> colleges = getCollegesNames();
		HashMap<Integer, String> branches = getBranches();
// 		int n = availableColleges.size();
//		while(n != 0)
//		{
//			model.removeRow(n);
//			n--;
//		}
		Iterator<String> itr = availableColleges.keySet().iterator();
		while(itr.hasNext())
			comboBox.addItem(itr.next());
		
		TreeMap<Integer, Pair<Integer, Integer>> prefTable = getpreftable(SID);
		Iterator<Integer> itr2= prefTable.keySet().iterator();
		while(itr2.hasNext())
		{
			int pref = itr2.next();
			Pair<Integer, Integer> p = prefTable.get(pref);
			model.addRow(new Object[] { pref, colleges.get(p.a), branches.get(p.b)  });
		}
		
		
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				int row = jt.getSelectedRow();
				int col = jt.getSelectedColumn();
//				System.out.println(col);
//				if(col == 0)
//					System.out.println(jt.getModel().getValueAt(row, col));
//				else {
//					String value = jt.getModel().getValueAt(row, col).toString();
//					System.out.println(value);
//				}
				
				txtCol.setText(jt.getModel().getValueAt(row, 1).toString());
				txtBranch.setText(jt.getModel().getValueAt(row, 2).toString());
			}
		});
	    comboBox.addActionListener(this);
		btnAdd.addActionListener(this);
		comboBox_1.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}
	
	Integer CID = 0,BID = 0,PREF = 100;
	String collegeName = "";
	String branchName = "";
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource() == comboBox)
		{
			String college = (String)comboBox.getSelectedItem();
			collegeName = college;
			HashMap<String, Integer> colleges = getCollegeNames();
			Integer cId = colleges.get(college);
			
			TreeSet<Integer> availableBranches = getBranchesFromDb(SID, cId);
			System.out.println(availableBranches);
			HashMap<Integer, String> branches = getBranches();
			Iterator<Integer> itr = availableBranches.iterator();
			comboBox_1.removeAllItems();
			comboBox_1.addItem("<Select Branch>");
			while(itr.hasNext())
			{
				comboBox_1.addItem(branches.get(itr.next()));
			}
			CID = cId;
			System.out.println(cId + " : " + college);
			return;
		}
		else if(e.getSource() == comboBox_1)
		{
			HashMap<String, Integer> branchNames = getBranchNames();
			BID =  branchNames.get((String)comboBox_1.getSelectedItem());
			System.out.println(PREF + " : " + CID + " : " + BID);
			branchName = (String)comboBox_1.getSelectedItem();
			return;
		}
		else if(e.getSource() == btnAdd) {
			
			if(t1.getText().hashCode() == "".hashCode())
				return;
			PREF = Integer.parseInt(t1.getText());
			System.out.println(PREF + ": " + collegeName + " : " + branchName);
			String query = "insert into studentSelections(sId, cId, bId, preference) values( " + SID + " , " + CID + "," + BID + ", " + PREF + " )" ;
			addStudentToDb(query);
			model.addRow(new Object[] {PREF, collegeName, branchName });
		}
		else if(e.getSource() == btnDelete)
		{
			if(txtBranch.getText().equals("") || txtCol.getText().equals(""))
				return;
			HashMap<String, Integer> colleges = getCollegeNames();
			HashMap<String, Integer> branchNames = getBranchNames();
			Integer bId = branchNames.get(txtBranch.getText());
			Integer cId  = colleges.get( txtCol.getText() );
			removeChoice(SID, cId, bId);
		}
		
		else if(e.getSource() == btnSubmit)
		{
			setVisible(false);
			return;
		}
		new StudentLoinColleges().setVisible(true);
		setVisible(false);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentLoinColleges();
	}

	

}
