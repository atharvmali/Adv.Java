import java.io.*;
import java.sql.*;
import java.lang.*;


public class CallableSt3 {
	
	public static void main(String args[]) throws Exception {
		
		Connection con;
		CallableStatement cs;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ResultSet rs;
		int id;
		String name, d;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "mysqlroot");
			
			String query = "{call get_cmp(?)}";
			
			cs = con.prepareCall(query);
			
			System.out.println("Enter Company department name");
			d = br.readLine();
			
			cs.setString(1, d);
			rs = cs.executeQuery();
			
				while(rs.next())
				{
					id = rs.getInt("cid");
					name = rs.getString("cname");
					System.out.println("ID "+ id + " Name " + name);
				}
				
			con.close();
		}
		
		catch(SQLDataException e) {
			e.printStackTrace();
		}
	}

}
