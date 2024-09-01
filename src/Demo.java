import java.sql.*;

public class Demo {
    public static void main(String args[]) throws Exception {
        Connection con;
        Statement st;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "mysqlroot");
            st = con.createStatement();
            rs = st.executeQuery("select * from student");
            while (rs.next()) {
                int id = rs.getInt("rollno");
                String nm = rs.getString("sname");
                String cls = rs.getString("course");
                System.out.println("ID: " + id + " Name: " + nm + " Course: " + cls);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}