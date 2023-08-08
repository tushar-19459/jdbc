import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;
public class putimg {
    public static void main(String[] args) {
       try (Scanner s = new Scanner(System.in)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DriverManager.getConnection(null, null, null);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fromthetop","root","root");
            if (con.isClosed()) {
                System.out.println("error");
            } else {
                System.out.println("connected");
            }
            String q1 ="create table image(slno int primary key auto_increment,img longblob)";
            Statement st = con.createStatement();
            st.execute(q1);
            String q2 = "insert into image(img)value(?)";
            PreparedStatement ps = con.prepareStatement(q2);
            System.out.println("enter the path");
            String path = s.next();
            FileInputStream fs = new FileInputStream(path);
            ps.setBinaryStream(1,fs,fs.available());
            ps.executeUpdate();
            con.close();
            System.out.println("image uploaded");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
// C:\Users\Dell\Downloads\DSC_5517.JPG