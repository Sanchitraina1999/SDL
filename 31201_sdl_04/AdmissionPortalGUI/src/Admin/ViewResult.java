package Admin;

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
import clients.Pair;
import clients.StudentMainLogin;

public class ViewResult extends JFrame implements ActionListener{
	public JLabel lblFillTheFollowing,lblCollegeName,lblBranchName,lblPriority,lblThisChoiceIs,lblbr,lblCol;
	public JComboBox<String> comboBox,comboBox_1;
	public JTextField t1, txtCol, txtBranch;
	public JButton btnAdd,btnBack;
	JScrollPane sp;
	JTable jt;
	JPanel jp;
	DefaultTableModel model;
	int CID = 0;
	int BID = 0;
	
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
	
	public static TreeSet<Integer> getBranchesFromDb(int cId)
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
		
	
	public ViewResult() {
		// TODO Auto-generated constructor stub
		setBounds(100,10,1500,1000);
		
		lblFillTheFollowing = new JLabel("Select College and Branches");
		lblFillTheFollowing.setBounds(600, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(329, 157, 428, 30);
		comboBox.addItem("<Select College>");
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(329, 239, 428, 30);
		comboBox_1.addItem("<Select Branch>");
		
		add(comboBox);
		add(comboBox_1);
		
		
		TreeMap<String, Integer> availableColleges = getCollegesFomDb();
		Iterator<String> itr = availableColleges.keySet().iterator();
		while(itr.hasNext())
			comboBox.addItem(itr.next());
		
		

		btnAdd = new JButton("Search");
		btnAdd.setFocusable(false);
		btnAdd.setBounds(301, 330, 117, 30);
		btnAdd.setFont(new Font("Arial",Font.PLAIN,20));
		add(btnAdd);
		
		jp = new JPanel();
		jp.setBounds(0, 400, 1500, 500);
		jp.setBackground(Color.cyan);
		add(jp);
		jp.setLayout(null);
		
		
		model = new DefaultTableModel();
		jt = new JTable(model);
		sp = new JScrollPane(jt);
		model.addColumn("SID");
		model.addColumn("Name");
		model.addColumn("Marks");
		sp.setBounds(200,0,1000,600);
		jp.add(sp);
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBack.setFocusable(false);
		btnBack.setBounds(20, 20, 80, 30);
		add(btnBack);
		
		comboBox.addActionListener(this);
		btnAdd.addActionListener(this);
		comboBox_1.addActionListener(this);
		btnBack.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == comboBox)
		{
			String college = (String)comboBox.getSelectedItem();
			TreeMap<String, Integer> availableColleges = getCollegesFomDb();
			Integer cId = availableColleges.get(college);
			CID = cId;
			TreeSet<Integer> availableBranches = getBranchesFromDb(cId);
			System.out.println(availableBranches);
			HashMap<Integer, String> branches = getBranches();
			Iterator<Integer> itr = availableBranches.iterator();
			comboBox_1.removeAllItems();
			comboBox_1.addItem("<Select Branch>");
			while(itr.hasNext())
			{
				comboBox_1.addItem(branches.get(itr.next()));
			}
		}
		
		
		else if(e.getSource() == comboBox_1)
		{
			HashMap<String, Integer> branchNames = getBranchNames();
			String bName = (String)comboBox_1.getSelectedItem();
			Integer bId =  branchNames.get(bName);
//			System.out.println(bName);
			
		}
		else if(e.getSource() == btnAdd)
		{
			
			HashMap<String, Integer> branchNames = getBranchNames();
			int bId =  branchNames.get((String)comboBox_1.getSelectedItem());
//			System.out.println(CID + " : " + bId);
			ArrayList< Pair< Integer , Pair<String, Integer> > > tableData = addStudentsToTable(CID, bId);
			int i = model.getRowCount() - 1;
			while(i >= 0)
			{
				model.removeRow(i);
				i = i - 1;
			}
			if(tableData.size() == 0) {
				JOptionPane.showMessageDialog(null, "No Student Opted for this Course");
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
		else if(e.getSource() == btnBack)
			setVisible(false);
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewResult();
	}

}
