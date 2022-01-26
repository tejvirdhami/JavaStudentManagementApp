package client1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import bus.*;

import data.ConnectionDB;

public class TesterWithMenu {

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
		
		//-----------------------MENU-------------------------------------
		
		System.out.println("Enter one of the following commands:");
		System.out.println("1 - Display the existing students.");
		System.out.println("2 - Search a student.");
		System.out.println("3 - Add a new student");
		System.out.println("4 - Delete a student.");
		System.out.println("5 - To Quit");
		
		//Temp variables
				String id, fn, ln;
		int choice = scan.nextInt();
		
		
		
		while (choice != 5) {

		    if(choice == 1) {			

				
				System.out.println(" The students are as follows:\n");
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
				break;
				
		    }
		    else if(choice == 2) {
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
				break;
		    }
		    else if(choice == 3) {
		
				
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
				
		    	break;
		    }
		    else if(choice == 4) {			
		    	int id_key;
		    	String request = null;
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
				break;
		    }
		}
		
	}

}
