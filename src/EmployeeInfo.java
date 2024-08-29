import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import java.awt.*;
import javax.swing.*; 

public class EmployeeInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con = null;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldDesig;
	private JTextField textFieldBasic;
	private JTextField textFieldAge;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;

	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		
		try {
			con = SqlConnection.connectDb();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 693);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Employee Data");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from employees";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs)); 
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
			}
		});
		btnLoadTable.setBounds(320, 6, 201, 29);
		contentPane.add(btnLoadTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 881, 338);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEID = new JLabel("EID");
		lblEID.setBounds(16, 402, 26, 16);
		contentPane.add(lblEID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(54, 397, 67, 26);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblEname = new JLabel("Ename");
		lblEname.setBounds(141, 402, 50, 16);
		contentPane.add(lblEname);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(191, 397, 296, 26);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDesig = new JLabel("Designation");
		lblDesig.setBounds(519, 402, 85, 16);
		contentPane.add(lblDesig);
		
		textFieldDesig = new JTextField();
		textFieldDesig.setBounds(616, 397, 271, 26);
		contentPane.add(textFieldDesig);
		textFieldDesig.setColumns(10);
		
		JLabel lblBasic = new JLabel("Basic Salary");
		lblBasic.setBounds(16, 463, 75, 16);
		contentPane.add(lblBasic);
		
		textFieldBasic = new JTextField();
		textFieldBasic.setBounds(101, 458, 90, 26);
		contentPane.add(textFieldBasic);
		textFieldBasic.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(219, 463, 42, 16);
		contentPane.add(lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(273, 458, 67, 26);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(362, 463, 96, 16);
		contentPane.add(lblUserName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(436, 458, 239, 26);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(685, 463, 75, 16);
		contentPane.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(757, 458, 130, 26);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "insert into employees (eid, ename, desig, basic, age, username, password) values(?,?,?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldDesig.getText());
					pst.setString(4, textFieldBasic.getText());
					pst.setString(5, textFieldAge.getText());
					pst.setString(6, textFieldUserName.getText());
					pst.setString(7, textFieldPassword.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Inserted");
					
					pst.close();
					
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(44, 535, 117, 29);
		contentPane.add(btnInsert);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					String query = "upadte employees set ename = ?, desig = ?, basic = ?, age = ?, username = ?, password = ? where eid = ? ";
					PreparedStatement pst = con.prepareStatement(query);
					
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldDesig.getText());
					pst.setString(4, textFieldBasic.getText());
					pst.setString(5, textFieldAge.getText());
					pst.setString(6, textFieldUserName.getText());
					pst.setString(7, textFieldPassword.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
					
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(273, 535, 117, 29);
		contentPane.add(btnUpdate);
	}
}
