
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class MyFrame2 extends JFrame {

	// Components of the Form
	private Container c;
	private JLabel title;
	private JLabel name,senderId, unit, enterdata;
	private JTextField tname, tname1,tname12, tunit, tdate, tenterdata,sender;
	private JLabel email;
	private JTextField temail;
	private JLabel pass;
	private JPasswordField tpass;
	private JLabel mno;
	private JTextField tmno;
	private JLabel gender;
	private JRadioButton male;
	private JRadioButton female;
	private ButtonGroup gengp;
	private JLabel dob;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
	private JLabel add;
	private JTextArea tadd;
	private JCheckBox term;
	private JButton setunit, search,submit12,submit13,transfer, addmoney, withdraw, submit, set, submit1, refresh,refresh1;

	private JButton reset;
	private JTextArea tout;
	private JLabel res;


	// socket connection
	static ObjectOutputStream clientOutputStream;
	static ObjectInputStream clientInputStream;
	static Socket socketConnection;

	// constructor, to initialize the components
	// with default values.

	public static String CID = "";

	public MyFrame2(String id) {
		CID = id;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// System.out.println("Hiiiii");
					socketConnection = new Socket("127.0.0.1", 11111);
					clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
					clientInputStream = new ObjectInputStream(socketConnection.getInputStream());

					clientOutputStream.writeObject("customer");
					clientOutputStream.writeObject(CID);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		setTitle("Welcome Customer");
		setBounds(300, 30, 900, 600 + 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(47, 79, 79));
		setResizable(false);

		setLayout(null);

		setunit = new JButton("Show My Details");
		setunit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		setunit.setSize(300, 50);
		setunit.setLocation(50, 30);
		setunit.addActionListener(new Regi());
		add(setunit);

		addmoney = new JButton("Add Money");
		addmoney.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addmoney.setSize(300, 50);
		addmoney.setLocation(50, 130);
		addmoney.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				name.setVisible(true);
				tname.setVisible(true);
				
				submit.setVisible(true);
				submit12.setVisible(false);
				transfer.setLocation(50,480);
				withdraw.setLocation(50, 380);
				refresh.setLocation(50, 580);
				name.setLocation(50, 200);
				tname.setLocation(140, 200);
				submit13.setVisible(false);
				sender.setVisible(false);
				senderId.setVisible(false);

			}
		});
		add(addmoney);

		name = new JLabel("Amount");
		name.setForeground(new Color(255, 255, 255));
		name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		name.setSize(100, 20);
		name.setLocation(50, 200);
		add(name);
		name.setVisible(false);

		tname = new JTextField();
		tname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tname.setSize(150, 20);
		tname.setLocation(140, 200);
		add(tname);
		tname.setVisible(false);
		
		senderId = new JLabel("sender-ID");
		senderId.setForeground(new Color(255, 255, 255));
		senderId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		senderId.setSize(100, 20);
		senderId.setLocation(50, 200);
		add(senderId);
		senderId.setVisible(false);

		sender = new JTextField();
		sender.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sender.setSize(150, 20);
		sender.setLocation(140, 200);
		add(sender);
		sender.setVisible(false);

		

		submit = new JButton("Submit");
		submit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		submit.setSize(100, 30);
		submit.setLocation(50, 280);
		submit.addActionListener(new addsubmit());
		add(submit);
		submit.setVisible(false);
		

		submit12 = new JButton("Submit");
		submit12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		submit12.setSize(100, 30);
		submit12.setLocation(50, 480);
		submit12.addActionListener(new addsubmit12());
		add(submit12);
		submit12.setVisible(false);
		
		submit13 = new JButton("Submit13");
		submit13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		submit13.setSize(100, 30);
		submit13.setLocation(50, 480);
		submit13.addActionListener(new addsubmit13());
		add(submit13);
		submit13.setVisible(false);


		withdraw = new JButton("Cash Withdrawal");
		withdraw.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		withdraw.setSize(300, 50);
		withdraw.setLocation(50, 230);
		withdraw.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				name.setVisible(true);
				name.setLocation(50,300);
				withdraw.setLocation(50, 230);
				transfer.setLocation(50,470);
				tname.setVisible(true);
				tname.setLocation(150,300);
				submit12.setVisible(true);
				submit12.setLocation(50,370);
				submit13.setVisible(false);
				senderId.setVisible(false);
				sender.setVisible(false);
				//withdraw.setLocation(50, 380);
				transfer.setLocation(50,480);
				refresh.setLocation(50, 580);
				submit.setVisible(false);

				
				//dispose();
				//new Payment(CID);

			}
		});
		add(withdraw);
		
		transfer=new JButton("Transfer money");
		transfer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		transfer.setSize(300, 50);
		transfer.setLocation(50, 330);
		transfer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				name.setVisible(true);
				name.setLocation(50,460);
				tname.setVisible(true);
				tname.setLocation(150,460);
				senderId.setVisible(true);
				senderId.setLocation(50, 400);
				sender.setVisible(true);
				sender.setLocation(150, 400);
				transfer.setLocation(50, 330);
				submit13.setVisible(true);
				submit.setVisible(false);
				submit12.setVisible(false);
				submit13.setLocation(50,530);
				withdraw.setLocation(50, 230);
				refresh.setLocation(50, 600);
				submit.setVisible(false);

				
				//dispose();
				//new Payment(CID);

			}
		});
		add(transfer);

		refresh = new JButton("Refresh");
		// refresh.setForeground(new Color(255, 255, 255));
		refresh.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		refresh.setSize(300, 50);
		refresh.setLocation(50, 430);
		refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
				new UserFunctions(CID);

			}
		});
		add(refresh);
		
//		refresh1 = new JButton("Refresh");
//		// refresh.setForeground(new Color(255, 255, 255));
//		refresh1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		refresh1.setSize(300, 50);
//		refresh1.setLocation(50, 330);
//		refresh1.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				dispose();
//				new UserFunctions(CID);
//
//			}
//		});
//		add(refresh1);

		tout = new JTextArea();
		tout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tout.setSize(300, 400 + 100);
		tout.setLocation(500, 30);
		tout.setLineWrap(true);
		tout.setEditable(false);
		add(tout);

		setVisible(true);
	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public class Regi implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				

				withdraw.setLocation(50, 230);
				refresh.setLocation(50, 430);

				// clientOutputStream.writeObject(Login1.id);
				clientOutputStream.writeObject("show");

				String reply = (String) clientInputStream.readObject();

				if (!reply.equals("-1")) {
					tout.setText(reply);
					tout.setEditable(false);

				} 
				JOptionPane.showMessageDialog(null, "Please do refresh before doing next task!!");

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public class addsubmit implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			try {

				// clientOutputStream.writeObject(Login1.id);
				clientOutputStream.writeObject("add money");

				String data1, mobile, email, pass, type;
				String data = tname.getText();
				
			

				clientOutputStream.writeObject(data);
				
				String reply = (String) clientInputStream.readObject();

				if (!reply.equals("-1")) {
					tout.setText(reply);
					tout.setEditable(false);
					JOptionPane.showMessageDialog(null, "Your updated information is in Box");

				} else {
					JOptionPane.showMessageDialog(null, "Please enter valid input!!");

				}

				JOptionPane.showMessageDialog(null, "Please do refresh");

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class addsubmit12 implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			try {

				// clientOutputStream.writeObject(Login1.id);
				clientOutputStream.writeObject("withdraw money");
				String data = tname.getText();
				
			

				clientOutputStream.writeObject(data);
				
				String reply = (String) clientInputStream.readObject();

				if (!reply.equals("-1")) {
					tout.setText(reply);
					tout.setEditable(false);
					JOptionPane.showMessageDialog(null, "Your updated information is in Box");

				} else {
					JOptionPane.showMessageDialog(null, "Please enter valid input!!");

				}

				JOptionPane.showMessageDialog(null, "Please do refresh");

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class addsubmit13 implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			try {

				// clientOutputStream.writeObject(Login1.id);
				clientOutputStream.writeObject("transfer money");
				String id=sender.getText();
				String data = tname.getText();
				Integer conid=Integer.parseInt(id);
				Integer amt=Integer.parseInt(data);

				clientOutputStream.writeObject(conid);
				clientOutputStream.writeObject(amt);
				
				String reply = (String) clientInputStream.readObject();

				if (!reply.equals("-1")) {
					tout.setText(reply);
					tout.setEditable(false);
					JOptionPane.showMessageDialog(null, "Your updated information is in Box");

				} else {
					JOptionPane.showMessageDialog(null, "Please enter valid input!!");

				}

				JOptionPane.showMessageDialog(null, "Please do refresh");

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

// Driver Code
public class UserFunctions extends Thread {
	public static String id;

	UserFunctions(String id) {
		new MyFrame2(id);
	}

	public static void main(String[] args) throws Exception {
		new UserFunctions("1");
	}

}