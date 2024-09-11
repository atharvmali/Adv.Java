import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Student extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSid;
	private JTextField txtSname;
	private JTextField txtCname;
	private JTable table;
	
	Connection con;
	Statement st;
	PreparedStatement ps, ps1;
	ResultSet rs;
	
	String sname, cname;
	int sid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name:");
		lblNewLabel.setBounds(36, 28, 100, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Id:");
		lblNewLabel_1.setBounds(36, 56, 100, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Name:");
		lblNewLabel_2.setBounds(36, 84, 100, 16);
		contentPane.add(lblNewLabel_2);
		
		txtSid = new JTextField();
		txtSid.setBounds(170, 51, 130, 26);
		contentPane.add(txtSid);
		txtSid.setColumns(10);
		
		txtSname = new JTextField();
		txtSname.setBounds(170, 79, 130, 26);
		contentPane.add(txtSname);
		txtSname.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = Integer.parseInt(txtSid.getText());
				sname = txtSname.getText();
				
				cname=txtCname.getText();
				
				try {
					con = SqlConnection.connectDb();
					ps = con.prepareStatement("insert into student values(?,?,?)");
					
					ps.setInt(1, sid);
					ps.setString(2, sname);
					ps.setString(3, cname);
					
					int n = ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Added");
					
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnAdd.setBounds(6, 124, 103, 29);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con=SqlConnection.connectDb();
					if(txtSid.getText().equals(""))
					{
					JOptionPane.showMessageDialog(null, "Enter accno");

					}
					sid=Integer.parseInt(txtSid.getText());
					ps1=con.prepareStatement("delete from student WHERE sid=?;");

					ps1.setInt(1,sid);
					int n=ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		});
		btnDelete.setBounds(121, 124, 95, 29);
		contentPane.add(btnDelete);
		
		JButton btnView = new JButton("View All");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con=SqlConnection.connectDb();
					st=con.createStatement();
					rs=st.executeQuery("select * from student");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		btnView.setBounds(229, 124, 100, 29);
		contentPane.add(btnView);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = Integer.parseInt(txtSid.getText());
				cname=txtCname.getText();
				try {
					con=SqlConnection.connectDb();
					st=con.createStatement();

					ps=con.prepareStatement("select * from student where sid=? or cname=?");

					ps.setInt(1,sid);
					ps.setString(2, cname);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					}
					catch(Exception e1)
					{
					e1.printStackTrace();
					}
			}
		});
		btnSearch.setBounds(341, 124, 103, 29);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 157, 438, 109);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtCname = new JTextField();
		txtCname.setBounds(170, 23, 130, 26);
		contentPane.add(txtCname);
		txtCname.setColumns(10);
	}
}
