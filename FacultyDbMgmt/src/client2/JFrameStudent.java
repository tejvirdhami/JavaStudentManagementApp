package client2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

import bus.Student;
import data.ConnectionDB;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameStudent {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtFn;
	private JTextField txtLn;
	private JTextField txtResult;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameStudent window = new JFrameStudent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JFrameStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(92, 46, 86, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(50, 49, 21, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtFn = new JTextField();
		txtFn.setColumns(10);
		txtFn.setBounds(92, 77, 86, 20);
		frame.getContentPane().add(txtFn);
		
		JLabel lblFn = new JLabel("Fn:");
		lblFn.setBounds(50, 80, 21, 14);
		frame.getContentPane().add(lblFn);
		
		txtLn = new JTextField();
		txtLn.setColumns(10);
		txtLn.setBounds(92, 108, 86, 20);
		frame.getContentPane().add(txtLn);
		
		JLabel lblLn = new JLabel("Ln:");
		lblLn.setBounds(50, 111, 21, 14);
		frame.getContentPane().add(lblLn);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Student> studentList = null;
				String query = "";
				Statement stmt = null;
				ResultSet rs = null;
				
				Student astudent = null;
				
				// 1. Connect to oracle database: schooldb database
				Connection con = null;
				
				try {
					con = ConnectionDB.getConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String id, fn, ln;
				
				String request = null;
				id = txtId.getText();
				fn = txtFn.getText();
				ln = txtLn.getText();
				
				astudent = new Student(Integer.parseInt(id), fn, ln);
				
				request = "INSERT into Student(id, fn, ln) values(" + astudent.getId()
																	+ ",\'" + astudent.getFn() + "\'"
																	+ ",\'" + astudent.getLn() + "\')";
				
				try {
					stmt.executeUpdate(request);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("\n--------------------------\n");
				
				//DISPLAY TABLE
				studentList =  new ArrayList<>();
				try {
					while(rs.next())
					{
						id = rs.getString(1);
						fn = rs.getString(2);
						ln = rs.getString(3);
						
						astudent = new Student(Integer.parseInt(id), fn, ln);
						studentList.add(astudent);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtResult.setText(studentList.toString());
				//System.out.println(studentList);
				
			}
		});
		btnAdd.setBounds(352, 64, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(352, 84, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Student> studentList = null;
				String query = "";
				Statement stmt = null;
				ResultSet rs = null;
				
				Student astudent = null;
				
				String id, fn, ln;
				
				Connection con = null;
				
				try {
					con = ConnectionDB.getConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				query = "SELECT * FROM student";
				try {
					stmt =  con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				studentList =  new ArrayList<>();
				try {
					while(rs.next())
					{
						id = rs.getString(1);
						fn = rs.getString(2);
						ln = rs.getString(3);
						
						astudent = new Student(Integer.parseInt(id), fn, ln);
						studentList.add(astudent);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtResult.setText(studentList.toString());
				//System.out.println(studentList);
			}
		});
		btnDisplay.setBounds(352, 107, 89, 23);
		frame.getContentPane().add(btnDisplay);
		
		txtResult = new JTextField();
		txtResult.setEditable(false);
		txtResult.setBounds(48, 152, 403, 164);
		frame.getContentPane().add(txtResult);
		txtResult.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Student> studentList = null;
				String query = "";
				Statement stmt = null;
				ResultSet rs = null;
				
				Student astudent = null;
				
				String id, fn, ln;
				
				Connection con = null;
				
				int id_key;
				System.out.println("Id? : ");
				id_key = Integer.parseInt(txtId.getText());
				query = "SELECT * FROM student WHERE id = " + id_key;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				studentList =  new ArrayList<>();
				try {
					while(rs.next())
					{
						id = rs.getString(1);
						fn = rs.getString(2);
						ln = rs.getString(3);
						
						astudent = new Student(Integer.parseInt(id), fn, ln);
						studentList.add(astudent);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				txtResult.setText(studentList.toString());
				//System.out.println(studentList);
				
			}
		});
		btnSearch.setBounds(352, 33, 89, 23);
		frame.getContentPane().add(btnSearch);
	}
}
