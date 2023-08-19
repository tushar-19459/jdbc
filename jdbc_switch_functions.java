package mysql;

import java.sql.*;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;

public class functions {
	public String primatykey;
	public Connection con;
	public String q;
	public Statement st;
	public ResultSet r;
	Scanner s = new Scanner(System.in);

	public functions(Connection con) {
		this.con = con;
	}

	public void createtable(String name) throws SQLException {
		String q = "create table " + name + "(slno int );";
		st = con.createStatement();
		st.execute(q);
	}

	public boolean create(String column, String name) throws SQLException {
		if (name == "rollno" || name == "id") {
			primatykey = name;
			q = "alter table " + name + " add(" + column + " varchar(10) primary key);";
		} else {
			q = "alter table " + name + " add(" + column + " varchar(10));";
		}
		st = con.createStatement();
		st.execute(q);
		return false;
	}

	public boolean update(String name) throws SQLException {
		display(name);
		q = "SHOW COLUMNS FROM " + name + " ;";
		st = con.createStatement();
		r = st.executeQuery(q);
		while (r.next()) {
			System.out.print(r.getString(1) + "\t");
		}
		System.out.print("name : ");
		String n = s.next();
		System.out.print("\nrollno :");
		String r = s.next();
		System.out.print("\nslno :");
		String slno = s.next();
		q = "UPDATE " + name + " SET name='" + n + "', rollno='" + r + "' WHERE slno=" + slno;
		PreparedStatement pst = con.prepareStatement(q);
		pst.executeUpdate();
		display(name);
		return false;
	}

	public void display(String name) throws SQLException {
		q = "SHOW COLUMNS FROM " + name + " ;";
		st = con.createStatement();
		r = st.executeQuery(q);
		int i = 1;
		while (r.next()) {
			System.out.print(r.getString(1) + "\t");
		}
		System.out.print("\n");
		q = "SELECT *FROM " + name;
		st = con.createStatement();
		r = st.executeQuery(q);
		i = 1;
		while (r.next()) {
			System.out.print(i + "\t" + r.getString(2) + "\t" + r.getString(3) + "\t\n");
			i++;
		}
		System.out.print("\n");
	}

	public void data(String name) throws SQLException {
		q = "SHOW COLUMNS FROM " + name + " ;";
		st = con.createStatement();
		r = st.executeQuery(q);
		int i = 1;
		int c = 4;
		String key[] = new String[c];
		String value[] = new String[c];

		while (r.next()) {

			if (r.getString(1).equals("slno")) {
				continue;
			} else {
				key[i] = r.getString(1) + ",";
				System.out.print("\nenter " + r.getString(1) + " : ");
				value[i] = "'" + s.next() + "',";
				i++;
				// System.out.print("\nenter "+r.getString(1)+":");
				// String value=s.next();
				// q="insert into "+name+"("+r.getString(1)+")value('"+value+"')";
				// st=con.createStatement();
				// st.execute(q);
			}
		}
		String quar = key[1];
		String ans = value[1];
		for (i = 2; i < key.length; i++) {
			quar = quar + key[i];
			ans = ans + value[i];
		}
		System.out.println(quar);
		System.out.println(ans);
		quar = quar.substring(0, quar.length() - 1);
		ans = ans.substring(0, ans.length() - 1);
		System.out.println(quar);
		System.out.println(ans);
		q = "insert into " + name + "(" + quar + ")value(" + ans + ")";
		st = con.createStatement();
		st.execute(q);
		// for(String a:array) {
		// System.out.println(a);
		// }
		// display(name);
		// System.out.print("\nenter :");
		// String n=s.next();
		// st=con.createStatement();
		// st.execute(q);
		// display(name);
	}

	public boolean delete(String name) throws SQLException {
		display(name);
		System.out.print("\nenter " + primatykey + " :");
		String value = s.next();
		q = "delete from " + name + "where " + primatykey + " = " + value + ";";
		st = con.createStatement();
		st.execute(q);
		display(name);
		return false;
	}

	public int getnoofcolumns(String name) throws SQLException {
		int size = 0;
		q = "SHOW COLUMNS FROM " + name + " ;";
		st = con.createStatement();
		r = st.executeQuery(q);
		while (r.next()) {
			size++;
		}
		System.out.println(size);
		return size;
	}
}
