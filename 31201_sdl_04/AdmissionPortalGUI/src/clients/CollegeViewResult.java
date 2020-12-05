package clients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connection.ConnectionClass;

public class CollegeViewResult extends JFrame implements ActionListener{
	
	public JLabel lblFillTheFollowing,lblCollegeName,lblBranchName,lblPriority,lblThisChoiceIs,lblbr,lblCol;
	public JComboBox<String> comboBox,comboBox_1;
	public JButton btnBack;
	JScrollPane sp;
	JTable jt;
	JPanel jp;
	int CID = CollegeMainLogin.CID;
	DefaultTableModel model;
	
	public static TreeSet<Integer> getBranches(int cId)
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
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Get Branches From Db");
		}
		return availableBranches;
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
	
	public static ArrayList< Pair< Integer , Pair<String, Integer> > > addStudentsToTable(int cId, int bId )
	{
		ArrayList< Pair< Integer , Pair<String, Integer> > > tableData = new ArrayList<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();			
			
			String query = "select students.sId, students.name, students.marks " + 
					" from students inner join finalResult ON " + 
					" finalResult.sId = students.sId where cId = " + cId + " and bId = " + bId + 
					" order by students.marks desc"; 
			
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
	
			while(rs.next())
			{
				Pair<String, Integer> p = new Pair<String, Integer>(rs.getString(2), rs.getInt(3));
				tableData.add(new Pair<Integer, Pair<String,Integer>>(rs.getInt(1),p));
				System.out.println(rs.getInt(1) + ".  " + rs.getString(2) + " :  " + rs.getInt(3));
			}
			
			
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Display Applied Students");
		}
		return tableData;
	}
	
	public CollegeViewResult() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		setBounds(100,10,1500,1000);
		
		lblFillTheFollowing = new JLabel("Final Result ");
		lblFillTheFollowing.setBounds(600, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		
		lblBranchName = new JLabel("Branch Name");
		lblBranchName.setFont(new Font("Serif", Font.PLAIN, 20));
		lblBranchName.setBounds(120, 150, 153, 25);
		add(lblBranchName);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(450, 150, 430, 30);
		comboBox_1.addItem("<Select Branch>");
		HashMap<Integer, String> branches = getBranches(); 
		TreeSet<Integer> branchIds = getBranches(CID);
		Iterator<Integer> itr = branchIds.iterator();
		while(itr.hasNext())
		{
			int bId = itr.next();
			String bName = branches.get(bId);
			comboBox_1.addItem(bName);
		}
		add(comboBox_1);
		
		
		jp = new JPanel();
		jp.setBounds(0, 400, 1500, 500);
		jp.setBackground(Color.cyan);
		add(jp);
		jp.setLayout(null);
		
		model = new DefaultTableModel();
		jt = new JTable(model);
		sp = new JScrollPane(jt);
		model.addColumn("Student Id");
		model.addColumn("Name");
		model.addColumn("Marks");

		sp.setBounds(200,0,1000,600);
		jp.add(sp);
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBack.setFocusable(false);
		btnBack.setBounds(20, 20, 80, 30);
		add(btnBack);
		
		comboBox_1.addActionListener(this);
		btnBack.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == comboBox_1)
		{
			HashMap<String, Integer> branchNames = getBranchNames();
			int bId =  branchNames.get((String)comboBox_1.getSelectedItem());
			ArrayList< Pair< Integer , Pair<String, Integer> > > tableData = addStudentsToTable(CID, bId);
			int i = model.getRowCount() - 1;
			while(i >= 0)
			{
				model.removeRow(i);
				i = i - 1;
			}
			
			if(tableData.size() == 0) {
				JOptionPane.showMessageDialog(null, "No Student Opted for this COurse");
				return;
			}
			Iterator<Pair<Integer, Pair<String, Integer>>> itr = tableData.iterator();
			while(itr.hasNext())
			{
				Pair<Integer, Pair<String, Integer>> p = itr.next();
				int sId = p.a;
				
				String name = p.b.a;
				Integer marks = p.b.b;
				model.addRow(new Object[] {sId, name, marks});
			}
			return;
		}
	 	else if(e.getSource() == btnBack) {
	 		setVisible(false);
	 	}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CollegeViewResult();

	}

}
