package Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clients.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import connection.ConnectionClass;

public class MeritList extends JFrame implements ActionListener{
	
	public JLabel lblFillTheFollowing,lblCollegeName,lblBranchName,lblPriority,lblThisChoiceIs,lblbr,lblCol;
	public JComboBox<String> comboBox;
	public JButton btnBack ;
	public static HashMap<Integer, String> colleges = getCollegesNames();
	TreeMap<String, Integer> availableColleges =  getCollegesFomDb();
	JScrollPane sp;
	JTable jt;
	JPanel jp;
	DefaultTableModel model;
	
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
	
	

	public MeritList() {
		// TODO Auto-generated constructor stub
		
		setLayout(null);
		setBounds(100,10,1500,1000);
		
		lblFillTheFollowing = new JLabel("Merit List ");
		lblFillTheFollowing.setBounds(600, 50, 410, 30);
		lblFillTheFollowing.setForeground(Color.BLUE);
		lblFillTheFollowing.setFont(new Font("Arial", Font.PLAIN, 25));
		add(lblFillTheFollowing);
		
		lblCollegeName = new JLabel("College Name");
		lblCollegeName.setBounds(120, 150, 155, 25);
		lblCollegeName.setFont(new Font("Serif", Font.PLAIN, 20));
		add(lblCollegeName);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(300, 150, 400, 30);
		comboBox.addItem("<Select College>");
		add(comboBox);
		
		
		jp = new JPanel();
		jp.setBounds(0, 250, 1500, 700);
		jp.setBackground(Color.cyan);
		add(jp);
		jp.setLayout(null);
		
		model = new DefaultTableModel();
		jt = new JTable(model);
		sp = new JScrollPane(jt);
		model.addColumn("Student Id");
		model.addColumn("Name");
		model.addColumn("Marks");
		
		
		Iterator<String> itr = availableColleges.keySet().iterator();
		while(itr.hasNext()) {
			String college = itr.next();
			comboBox.addItem(college);
		}

		sp.setBounds(200,0,1000,700);
		jp.add(sp);
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBack.setFocusable(false);
		btnBack.setBounds(20, 20, 80, 30);
		add(btnBack);
	
		comboBox.addActionListener(this);
		btnBack.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
	}
	
	public static ArrayList< Pair< Integer , Pair<String, Integer> > > addStudentsToTable(int cId )
	{
		TreeMap<Integer, Pair<String, Integer>> tableData = new TreeMap<>();
		ArrayList< Pair< Integer , Pair<String, Integer> > > table = new ArrayList<>();
		try {
			ConnectionClass newConnection = new ConnectionClass();
			Connection con =  newConnection.returnConnection();
					
			String query = "select students.sId, students.name, students.marks " + 
					" from students inner join studentSelections ON " + 
					" studentSelections.sId = students.sId where cId = " + cId +
					" order by students.marks desc"; 
			
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Pair<String, Integer> p = new Pair<String, Integer>(rs.getString(2), rs.getInt(3));
				table.add(new Pair<Integer, Pair<String,Integer>>(rs.getInt(1),p));
				tableData.put(rs.getInt(1), new Pair<String, Integer>(rs.getString(2), rs.getInt(3)));
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Could Not Display Applied Students");
		}
		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == comboBox) {
			
			String college = (String)comboBox.getSelectedItem();
			int cId = availableColleges.get(college);
			int i = model.getRowCount() - 1;
			while( i >= 0)
			{
				model.removeRow(i);
				i = i - 1;
			}
			
			ArrayList<Integer> alreadyInserted = new ArrayList<Integer>();
			ArrayList< Pair< Integer , Pair<String, Integer> > > table = addStudentsToTable(cId);
			Iterator<Pair<Integer, Pair<String, Integer>>> itr = table.iterator();
			while(itr.hasNext())
			{
				Pair<Integer, Pair<String, Integer>> p = itr.next();
				int sId = p.a;
				
				String name = p.b.a;
				Integer marks = p.b.b;
				if(alreadyInserted.contains(sId))
					continue;
				alreadyInserted.add(sId);
				model.addRow(new Object[] {sId, name, marks});
			}
			return;
			
		}
		
		else if(e.getSource() == btnBack) {
			setVisible(false);
			return;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MeritList();

	}

}
