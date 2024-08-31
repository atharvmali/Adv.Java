import java.io.*;
import java.sql.*;

public class AS8_1 {
	public static void main(String args[]) throws Exception {
		
		Connection con;
		PreparedStatement ps, ps1;
		ResultSet rs;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "mysqlroot");
			ps = con.prepareStatement("update employees set basic = basic + 500 where age > 40");
			
			int n = ps.executeUpdate();
			System.out.println("Data updated successsfully");
			
			ps1 = con.prepareStatement("select * from employees");
			rs = ps1.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("eid");
				String nm = rs.getString("ename");
				int age = rs.getInt("age");
				double sal = rs.getDouble("basic");
				System.out.println("Id : " + id + " Name : " + nm + " Age : " + age + " Salary : " + sal);
			}
			con.close();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
