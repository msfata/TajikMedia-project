package com.projectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

@SuppressWarnings("all")
public class Database {
	public static void main(String[] args) {
//		connect();
//		Database.insert(15,"jhdjh", "employer");
//		drop("fata");
//		create("fata");
//		delete(1);
//		update("fata3", 1, "mr","shahed", "shahzad", "5454545", "@", "19 hudso", "tailor", "detailssss");

		/*
		 * "Create Table " + tableName + "(id Integer,gender varchar(50),fname
		 * varchar(50) , lname varchar(50), phone varchar(50), email varchar(50),
		 * address varchar(10), occupations varchar(10), details varchar(10))");
		 */
//		insert("fata", 1, "Mr ","Shahed", "Fata", "07421110333", "msfata@me.com", "19 Hudson", "Tailor", "no details");
//		selectAll("fata3");
	}

	protected static void connect() {
        
		java.sql.ResultSet res = null;
		derbyDriver = "jdbc:derby:msfata;create=true";
		try {
			con = DriverManager.getConnection(derbyDriver);
			stmt = con.createStatement();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
//			System.out.println(e.getMessage());
		}

	}

	public static void select(String tableName) {
		java.sql.ResultSet rs;
		java.sql.PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from " + tableName + " order by id");
			rs = ps.executeQuery();
			TajikData.table.setModel(TableReader.resultSetToTableModel(rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void search(String tableName, String lname) {
		java.sql.ResultSet rs;
		java.sql.PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from " + tableName + " where lname like'"+lname+"%'");
			rs = ps.executeQuery();
			TajikData.table.setModel(TableReader.resultSetToTableModel(rs));
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	

//	protected static void selectAll(String tableName) {
//		try {
//			PreparedStatement ps = con.prepareStatement("select * from " + tableName + " order by id");
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String fname = rs.getString("fname");
//				System.out.println("ID : " + id + "  fname : " + fname);
//			}
//			rs.close();
//			ps.close();
//
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
//		}
//	}

	protected static void drop(String tableName) {
		try {
			stmt.executeUpdate("drop table " + tableName + "");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	protected static void create(String tableName) {
		try {
			stmt.executeUpdate("Create Table " + tableName
					+ "(id Integer Primary key, gender varchar(250) ,fname varchar(250), lname varchar(250), phone varchar(250), email varchar(250), address varchar(250), occupations varchar(250), details varchar(250))");
		} catch (SQLException e) {
			Database.select(TajikData.nameOfTable);
//			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected static void delete(int i, String tableName) {
		try {
			stmt.executeUpdate("delete from "+tableName+" where id=" + i + "");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	
	

	// Create Table " + tableName + "(id Integer,gender varchar(50),
	// fname varchar(50), lname varchar(50), phone varchar(50), email varchar(50),
	// address varchar(10), occupations varchar(10), details varchar(10))"

	protected static void update(String tableName, int i, String gender, String fname, String lname, String phone,
			String email, String address, String occupations, String details) {
		try {
			stmt.executeUpdate("update " + tableName + " set gender ='" + gender + "',fname='" + fname + "',lname ='"
					+ lname + "',phone='" + phone + "',email='" + email + "',address='" + address + "',occupations ='"
					+ occupations + "',details ='" + details + "'where id = " + i + "");

		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage()+115);
		}
	}

	protected static void insert(String tableName, int id, String gender, String fname, String lname, String phone,
			String email, String occupations, String address, String details) {
		// Create Table " + tableName + "(id Integer,gender varchar(50),
		// fname varchar(50), lname varchar(50), phone varchar(50), email varchar(50),
		// address varchar(10), occupations varchar(10), details varchar(10))"
		try {
			stmt.executeUpdate(
					"insert into " + tableName + " values(" + id + ",'" + gender + "','" + fname + "','" + lname + "','"
							+ phone + "','" + email + "','" + occupations + "','" + address + "','" + details + "')");
//			System.out.println("inserted ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
//			System.out.println(e.getMessage());
		}
	}

	static String derbyDriver;
	static Connection con;
	public static Statement stmt;
}