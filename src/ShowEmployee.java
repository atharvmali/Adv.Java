import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	JComboBox <Integer>comboBox ;
	
	Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowEmployee frame = new ShowEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void fillCombobox()
	{
	try {
	con=SqlConnection.connectDb();
	st=con.createStatement();
	rs=st.executeQuery("select * from employees");
	while(rs.next())
	{
	comboBox.addItem(rs.getInt("eid"));
	}
	}
	catch(Exception e1)
	{
	JOptionPane.showMessageDialog(null, e1);
	}
	}
	
	

	/**
	 * Create the frame.
	 */
	public ShowEmployee() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 659);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Employee ID");
		lblNewLabel.setBounds(37, 87, 142, 16);
		contentPane.add(lblNewLabel);
		
		 comboBox = new JComboBox<Integer>();
		comboBox.setBounds(180, 83, 142, 27);
		contentPane.add(comboBox);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					con=SqlConnection.connectDb();
					st=con.createStatement();
					id=(int) comboBox.getSelectedItem();
					ps=con.prepareStatement("select * from employees where eid=?");

					ps.setInt(1,id);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					}
					catch(Exception e1)
					{
					JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		btnSearch.setBounds(334, 82, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Load Employee Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con=SqlConnection.connectDb();
					st=con.createStatement();
					rs=st.executeQuery("select * from employees");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
					}
				
				
			}
		});
		btnNewButton.setBounds(554, 82, 260, 29);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 129, 810, 479);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		fillCombobox();
	}
}
