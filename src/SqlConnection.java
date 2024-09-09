
import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;


public class SqlConnection {
	
	static Connection con;

	public static Connection connectDb() throws IOException, SQLException, ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "mysqlroot");
//			JOptionPane.showMessageDialog(null, "Connection Successful");

		} catch(SQLDataException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return con;
	}
}
