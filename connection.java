import java.sql.*;

public class connection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection con = DriverManager.getConnection(null, null, null)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fromthetop","root","root");
            if (con.isClosed()) {
                System.out.println("error");
            } else {
                System.out.println("connected");
            }
            String q = "create table data(slno int primary key auto_increment,name varchar(10) not null,rollno int )";
            Statement stmt = con.createStatement();
            stmt.execute(q);
            System.out.println("tables are created..");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}