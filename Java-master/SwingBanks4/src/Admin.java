import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Admin {

	Admin(){
		F f1=new F();
		
		f1.setVisible(true);
		

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin();
	}

}

class F extends JFrame{

	Thread t1;
	JButton customer,admin,login,register;
	JPanel p1;
	F(){
		super("Admin Login");
		
		JLabel userLabel = new JLabel("ID");
	    JLabel passwordLabel = new JLabel("PASSWORD");
	    JTextField userTextField = new JTextField();
	    JPasswordField passwordField = new JPasswordField();
		setLayout(null);
		setBounds(450, 150, 370, 600);
		
		userLabel.setBounds(50, 150, 100, 30);
       
        
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        
        add(passwordField);
        add(userTextField);
        add(userLabel);
        add(passwordLabel);
        
		customer=new JButton("Login");
		customer.setBounds(50, 300, 100, 30);
		customer.setBackground(Color.BLACK);
		customer.setForeground(Color.YELLOW);
		add(customer);
		
		
		
		admin=new JButton("Reset");
		admin.setBounds(200, 300, 100, 30); 
		admin.setBackground(Color.BLACK);
		admin.setForeground(Color.YELLOW);
		add(admin);
		
		
		
		customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String username=userTextField.getText();
				String password=passwordField.getText();
				if(username.equals("1234") && password.equals("1234")) {
					JOptionPane.showMessageDialog(null, "Login Successfull!!");
					setVisible(false);
					new AdminLogin();
				}else {
					JOptionPane.showMessageDialog(null, "Ooops!! Something went wrong!!");
				}
            }
        });
		
		admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	userTextField.setText("");
   	         	passwordField.setText("");
   	         	
            }
        });
		
		
		
		JLabel lblNewJgoodiesTitle = new JLabel("Please Login");
		lblNewJgoodiesTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewJgoodiesTitle.setBounds(80, 22, 572, 66);
		add(lblNewJgoodiesTitle);
		
	}

	public class Regi implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			new Login1();
        	setVisible(false);
		}
	}

	
}
