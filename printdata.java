import java.sql.*;
import java.util.Scanner;
public class printdata {
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
            String q ="select *from data";
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(q);
            System.out.println("____________Data______________");
            System.out.println("slno\tname\t\trollno");
            while(res.next()){
                System.out.println("  "+res.getInt(1)+"\t"+res.getString(2)+"\t\t"+res.getInt(3));
            }
            System.out.println(".........................................");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
