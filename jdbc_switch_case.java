package mysql;

import java.sql.*;
import java.util.Scanner;

public class psvm {
	public static void main(String[] args) {
		try (Scanner s = new Scanner(System.in)) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "root");
			if (con == null) {
				System.out.println("error");
			} else {
				System.out.println("Connected");
			}
			functions fun = new functions(con);
			while (true) {
				System.out.println(
						"1. create a new table \n2. add new data to existing tabel \n3. update an existing table\n4. delete a table\n5.display table\n");
				int op = s.nextInt();
				String name = null;
				switch (op) {
					case 1:
						System.out.print("enter name for the table :");
						name = s.next();
						fun.createtable(name);
						System.out.print("\nenter the no of column :");
						int no = s.nextInt();
						for (int i = 0; i < no; i++) {
							System.out.print("\ncolumn " + (i + 1) + " name : ");
							String column = s.next();
							fun.create(column, name);
						}
						break;
					case 2:
						System.out.print("enter the table name ");
						name = s.next();
						fun.data(name);
						break;
					case 3:
						System.out.println("enter the table name for updating ");
						name = s.next();
						fun.update(name);
						break;
					case 4:
						System.out.println("enter the table name");
						name = s.next();
						fun.delete(name);
						break;
					case 5:
						System.out.println("enter the table name ");
						name = s.next();
						fun.display(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
			System.out.println("exiting..");
		}
	}
}
