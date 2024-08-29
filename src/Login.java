import java.awt.EventQueue;
import java.io.*;
import java.sql.*; 
import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	
	Connection con;
	
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel lblLoginImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		try {
			con = SqlConnection.connectDb();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(306, 148, 130, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(306, 248, 130, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(449, 143, 249, 26);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		Image img2 = new ImageIcon(this.getClass().getResource("./ok.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img2));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select * from employees where username = ? and password = ? ";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()) {
						count = count + 1;
					}
						
						
						if(count == 1) {
							JOptionPane.showMessageDialog(null, "Username and Password is Correct");
							frame.dispose();
							EmployeeInfo emplInfo = new EmployeeInfo();
							emplInfo.setVisible(true);
						}
						
						else if(count > 1) {
							JOptionPane.showMessageDialog(null, "Username and Password is Duplicate");
						}
						
						else {
							JOptionPane.showMessageDialog(null, "Username and Password is Not Correct");
						}
						
						rs.close();
						pst.close();
						
					
					
				} catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1);

					
				}
				
			}
		});
		btnLogin.setBounds(406, 326, 90, 28);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(449, 243, 249, 26);
		frame.getContentPane().add(passwordField);
		
		lblLoginImage = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("./login.png")).getImage();
		lblLoginImage.setIcon(new ImageIcon(img1));
		lblLoginImage.setBounds(103, 148, 96, 116);
		frame.getContentPane().add(lblLoginImage);
	}
}
