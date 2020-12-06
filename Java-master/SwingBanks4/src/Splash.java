import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Splash {

	Splash(){
		Fframe f1=new Fframe();
		
		f1.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Splash();
	}

}

class Fframe extends JFrame{
	JButton user,admin,login,register;

	Fframe(){
		super("Bank Management Sytstem");
		setLayout(null);
		setBounds(300, 90, 900, 600+100);
		
		
		user=new JButton("Customer");
		user.setLocation(150+100-50, 400);
		user.setSize(200, 50); 
		user.setBackground(Color.BLACK);
		user.setForeground(Color.YELLOW);
		add(user);
		
		
		
		admin=new JButton("Admin");
		admin.setLocation(450+100-50, 400);
		admin.setSize(200, 50); 
		admin.setBackground(Color.BLACK);
		admin.setForeground(Color.YELLOW);
		add(admin);
		
		
		login=new JButton("Login");
		login.setLocation(150+100-200, 470);
		login.setSize(200, 40); 
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		add(login);
		login.setVisible(false);
		
		register=new JButton("Signup");
		register.setLocation(150+100+100, 470);
		register.setSize(200, 40); 
		register.setBackground(Color.BLACK);
		register.setForeground(Color.WHITE);
		add(register);
		register.setVisible(false);
		
		JButton login1=new JButton("Login");
		login1.setLocation(450+100-50, 470);
		login1.setSize(200, 40); 
		login1.setBackground(Color.BLACK);
		login1.setForeground(Color.WHITE);
		add(login1);
		login1.setVisible(false);
		
		user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	login.setVisible(true);
            	register.setVisible(true);
            	login1.setVisible(false);
            }
        });
		
		admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	login.setVisible(false);
            	register.setVisible(false);
            	login1.setVisible(true);
            }
        });
		
		login.addActionListener(new Regi());
		login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	new Admin();
            	setVisible(false);
            }
        });
		register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	new Register();
            	setVisible(false);
            }
        });
		
		
		
		JLabel heading = new JLabel("Welcome to My Bank");

		
		
		heading.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 38));
		heading.setBounds(300, 22, 572, 66);
		add(heading);

		
		JLabel imglabel = new JLabel("backimg");
		imglabel.setIcon(new ImageIcon("C:\\Users\\Dell\\Downloads\\background.jpeg"));
		imglabel.setBounds(0, 84, 1023, 610);
		add(imglabel);


	}

	
	public class Regi implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			new Login1();
        	setVisible(false);
		}
	}

	
}


