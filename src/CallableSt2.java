import java.io.*;
import java.sql.*;
import java.lang.*;


public class CallableSt2 {
	
	public static void main(String args[]) throws Exception {
		
		Connection con;
		CallableStatement cs;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ResultSet rs;
		int id, num;
		String name;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "mysqlroot");
			
			String query = "{call get_emp(?)}";
			
			cs = con.prepareCall(query);
			
			System.out.println("Enter employee id");
			num = Integer.parseInt(br.readLine());
			
			cs.setInt(1, num);
			rs = cs.executeQuery();
			
				while(rs.next())
				{
					id = rs.getInt("eid");
					name = rs.getString("ename");
					System.out.println("ID "+ id + " Name " + name);
				}
				
			con.close();
		}
		
		catch(SQLDataException e) {
			e.printStackTrace();
		}
	}

}
