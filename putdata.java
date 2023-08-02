import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class putdata {
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)){
            int i=0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection con = DriverManager.getConnection(null, null, null)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fromthetop","root","root");
            if (con.isClosed()) {
                System.out.println("error");
            } else {
                System.out.println("connected");
            }
            String q = "insert into data(name,rollno)value(?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            System.out.println("enter no of new data");
            int n = s.nextInt();
            while(i<n){
            System.out.println("enter student name ");
            String name = s.next();
            System.out.println("enter student rollno ");
            int rollno = s.nextInt();
            ps.setString(1,name);
            ps.setInt(2,rollno);
            ps.executeUpdate();
            i++;
            }
            con.close();
            System.out.println("data entered");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}