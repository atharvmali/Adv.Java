import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame1 {

	private JFrame frame;
	private JTextField textFieldNum1;
	private JTextField textFieldNum2;
	private JTextField textFieldAns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldNum1 = new JTextField();
		textFieldNum1.setBounds(64, 53, 130, 26);
		frame.getContentPane().add(textFieldNum1);
		textFieldNum1.setColumns(10);
		
		textFieldNum2 = new JTextField();
		textFieldNum2.setBounds(264, 53, 130, 26);
		frame.getContentPane().add(textFieldNum2);
		textFieldNum2.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				 int num1, num2, ans; 
				 
				 try {
					 num1 = Integer.parseInt(textFieldNum1.getText()); 
					 num2 = Integer.parseInt(textFieldNum2.getText());
					 ans = num1 + num2;
					 textFieldAns.setText(Integer.toString(ans));
				 }
				 catch(Exception ex) {
					 JOptionPane.showMessageDialog(null, "Please enter valid number");
				 }
			}
		});
		btnNewButton.setBounds(77, 132, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("MINUS");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num1, num2, ans; 
				 
				 try {
					 num1 = Integer.parseInt(textFieldNum1.getText()); 
					 num2 = Integer.parseInt(textFieldNum2.getText());
					 ans = num1 - num2;
					 textFieldAns.setText(Integer.toString(ans));
				 }
				 catch(Exception ex) {
					 JOptionPane.showMessageDialog(null, "Please enter valid number");
				 }
			}
		});
		btnNewButton_1.setBounds(264, 132, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		textFieldAns = new JTextField();
		textFieldAns.setBounds(229, 204, 130, 26);
		frame.getContentPane().add(textFieldAns);
		textFieldAns.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("The Answer Is");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 16));
		lblNewLabel.setBounds(64, 209, 130, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
