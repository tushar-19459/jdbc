import java.sql.*;
import java.util.Scanner;

public class updatedata {
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection con = DriverManager.getConnection(null, null, null)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fromthetop","root","root");
            if (con.isClosed()) {
                System.out.println("error");
            } else {
                System.out.println("connected");
            }
            String q1 ="update data set name=?,rollno=? where slno=?";
            PreparedStatement pst = con.prepareStatement(q1);
            System.out.println("enter new name ");
            String name = s.next();
            pst.setString(1, name);
            System.out.println("enter new rollno ");
            int rollno = s.nextInt();
            pst.setInt(2, rollno);
            System.out.println("enter slno ");
            int slno = s.nextInt();
            pst.setInt(3, slno);
            pst.executeUpdate();
            con.close();
            System.out.println("new data loaded");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


