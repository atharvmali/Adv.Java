import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AS9_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	
	Connection con;
	PreparedStatement ps, ps1;
	
	String s1, s2, s3;
	int n1, n2, n3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AS9_1 frame = new AS9_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AS9_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 659);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 62, 645, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account No");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(52, 58, 162, 16);
		panel.add(lblNewLabel);
		
		t1 = new JTextField();
		t1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		t1.setBounds(244, 54, 321, 26);
		panel.add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(52, 137, 162, 16);
		panel.add(lblNewLabel_1);
		
		t2 = new JTextField();
		t2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		t2.setColumns(10);
		t2.setBounds(244, 133, 321, 26);
		panel.add(t2);
		
		JLabel lblNewLabel_2 = new JLabel("Balance");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(52, 215, 162, 16);
		panel.add(lblNewLabel_2);
		
		t3 = new JTextField();
		t3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		t3.setColumns(10);
		t3.setBounds(244, 211, 321, 26);
		panel.add(t3);
		
		JLabel lblNewLabel_3 = new JLabel("Phone No");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(52, 292, 162, 16);
		panel.add(lblNewLabel_3);
		
		t4 = new JTextField();
		t4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		t4.setColumns(10);
		t4.setBounds(244, 288, 321, 26);
		panel.add(t4);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(52, 376, 162, 16);
		panel.add(lblNewLabel_4);
		
		t5 = new JTextField();
		t5.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		t5.setColumns(10);
		t5.setBounds(244, 372, 321, 26);
		panel.add(t5);
		
		JButton insert = new JButton("insert");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s1 = t1.getText();
				s2 = t2.getText();
				n1 = Integer.parseInt(t3.getText());
				n2 = Integer.parseInt(t4.getText());
				s3 = t5.getText();
				
				JOptionPane.showMessageDialog(null, "DATA : " + s1 + " " + s2 + " " + n1 + " " + n2 + " " + s3);
				
				
				try {
					con = SqlConnection.connectDb();
					ps = con.prepareStatement("insert into bank values(?,?,?,?,?)");
					
					ps.setString(1, s1);
					ps.setString(2, s2);
					ps.setInt(3, n1);
					ps.setInt(4, n2);
					ps.setString(5, s3);
					
					int n = ps.executeUpdate();
					
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		insert.setBounds(38, 565, 117, 29);
		contentPane.add(insert);
		
		JButton update = new JButton("update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con=SqlConnection.connectDb();
					if(t1.getText().equals(""))
					{

					JOptionPane.showMessageDialog(null, "Enter acc no");

					}
					
					n1=Integer.parseInt(t1.getText());
					s1=t2.getText();
					n2=Integer.parseInt(t3.getText());
					n3=Integer.parseInt(t4.getText());
					s2=t5.getText();
					ps1=con.prepareStatement("UPDATE bank SET cname=? ,balance=? , phone=? , address=? WHERE accno=?;");
					ps1.setString(1,s1);
					ps1.setInt(2, n2);
					ps1.setInt(3, n3);
					ps1.setString(4, s2);
					ps1.setInt(5, n1);
					
					int n=ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");

					} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		update.setBounds(215, 565, 117, 29);
		contentPane.add(update);
		
		JButton delete = new JButton("delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con=SqlConnection.connectDb();
					if(t1.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null, "Enter accno");

					}
					n1=Integer.parseInt(t1.getText());
					ps1=con.prepareStatement("delete from bank WHERE accno=?;");

					ps1.setInt(1,n1);
					int n=ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
				
			}
		});
		delete.setBounds(384, 565, 117, 29);
		contentPane.add(delete);
		
		JButton show = new JButton("show");
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowBank s=new ShowBank();
				s.setVisible(true);
			}
		});
		show.setBounds(560, 565, 117, 29);
		contentPane.add(show);
		
		JLabel lblNewLabel_5 = new JLabel("Bank Customer Form");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(243, 23, 233, 16);
		contentPane.add(lblNewLabel_5);
	}
}
