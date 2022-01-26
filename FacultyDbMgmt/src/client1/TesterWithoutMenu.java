package client1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import bus.*;

import data.ConnectionDB;

public class TesterWithoutMenu {

	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Student> studentList = null;
		String query = "";
		Statement stmt = null;
		ResultSet rs = null;
		
		Student astudent = null;
		
		// 1. Connect to oracle database: schooldb database
		Connection con = null;
		
		con = ConnectionDB.getConnection();
		if(con != null)
		{
			System.out.println("\n\n Connection established successfully!\n\n");
		}
		else
		{
			System.out.println("\n\n Connection failed!\n\n");
		}
		
		// 2. Display students from schooldb database:--------------------------------------------
		// QUERY the schooldb: SELECT statement
		// Implementation:
		
		//Temp variables
		String id, fn, ln;
		
		query = "SELECT * FROM student";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		
		studentList =  new ArrayList<>();
		while(rs.next())
		{
			id = rs.getString(1);
			fn = rs.getString(2);
			ln = rs.getString(3);
			
			astudent = new Student(Integer.parseInt(id), fn, ln);
			studentList.add(astudent);
		}
		
		System.out.println(studentList);
		
		
		// 3. Search student by id:------------------------------------------------
		
		int id_key;
		System.out.println("Id? : ");
		id_key = scan.nextInt();
		query = "SELECT * FROM student WHERE id = " + id_key;
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		
		studentList =  new ArrayList<>();
		while(rs.next())
		{
			id = rs.getString(1);
			fn = rs.getString(2);
			ln = rs.getString(3);
			
			astudent = new Student(Integer.parseInt(id), fn, ln);
			studentList.add(astudent);
		}
		
		System.out.println(studentList);
		
		// 4. Add new student---------------------------------------
		
		String request = null;
		System.out.println("Id? : ");
		id = scan.next();
		System.out.println("Fn? : ");
		fn = scan.next();
		System.out.println("Ln? : ");
		ln = scan.next();
		
		astudent = new Student(Integer.parseInt(id), fn, ln);
		
		request = "INSERT into Student(id, fn, ln) values(" + astudent.getId()
															+ ",\'" + astudent.getFn() + "\'"
															+ ",\'" + astudent.getLn() + "\')";
		
		stmt.executeUpdate(request);
		con.commit();
		
		System.out.println("\n--------------------------\n");
		
		//DISPLAY TABLE
		studentList =  new ArrayList<>();
		while(rs.next())
		{
			id = rs.getString(1);
			fn = rs.getString(2);
			ln = rs.getString(3);
			
			astudent = new Student(Integer.parseInt(id), fn, ln);
			studentList.add(astudent);
		}
		
		System.out.println(studentList);
		
		// 5. Remove student
		System.out.println("Id? : ");
		id_key = scan.nextInt();
		request = "delete FROM student WHERE id = " + id_key;
		
		stmt.executeUpdate(request);
		con.commit();
		
		
		//DISPLAY AGAIN
		studentList = new ArrayList<Student>();
		while(rs.next())
		{
			id = rs.getString(1);
			fn = rs.getString(2);
			ln = rs.getString(3);
			
			astudent = new Student(Integer.parseInt(id), fn, ln);
			studentList.add(astudent);
		}
		System.out.println(studentList);
	}

}
