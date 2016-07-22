package com.sqlservice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.data.Student;

public class DriveSQL {
	private int isSuccess;
	private Connection conn = null;
	private String sql;
	private String url = "jdbc:mysql://127.0.0.1:3306/mysql?"
			+ "user=root&password=password&useUnicode=true&characterEncoding=UTF8"; // Create
																					// URL
	private Statement stmt;
	private List<Student> student = new ArrayList<Student>();

	public DriveSQL() {
		
		//createSQL();
		
	}


	public boolean createSQL() {
		boolean isOk = true;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 动态加载mysql驱动
			// System.out.println("成功加载MySQL驱动程序"); // 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url); // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			stmt = conn.createStatement();
		} catch (SQLException e) {
			isOk = false;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isOk)
			return true;
		return false;
	}
	
	public boolean judgeIsTableExists(){
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rsTable = meta.getTables(null, null,"STUDENT",null);
			if(rsTable.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean creatTable(String sql) { // executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
		int isResult = -1;
		try {
			isResult = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isResult != -1) {
			return true;
		}
		return false;
	}

	public boolean insertMessage(String stuMsg) { // Insert student message
		int isResult = -1;
		try {
			isResult = stmt.executeUpdate(stuMsg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isResult != -1) {
			return true;
		}
		return false;
	}

	public boolean tempMessage(String delMsg) {
		int isResult = -1;
		try {
			isResult = stmt.executeUpdate(delMsg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (isResult != -1) {
			return true;
		}
		return false;
	}
	
	public boolean judgeStudentMsg(String sql) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public void showStudent(String showMsg) {
		ResultSet rs;
		removeShowBook();
		try {
			rs = stmt.executeQuery(showMsg);
			while (rs.next()) {
				Student obj = new Student(rs.getString(1),rs.getString(2),rs.getString(3),
						Integer.parseInt(rs.getString(4)),rs.getString(5),rs.getString(6),
						Float.parseFloat(rs.getString(7)),Float.parseFloat(rs.getString(8)),
						Float.parseFloat(rs.getString(9)),Float.parseFloat(rs.getString(10)),
						Float.parseFloat(rs.getString(11)),Float.parseFloat(rs.getString(12)));
				student.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // executeQuery会返回结果的集合，否则返回空值
	}

	public void removeShowBook() {
		for (int i = 0; i < student.size();) {
			student.remove(0);
		}
	}	

	public Student getStudentMsg(String sql) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return new Student(rs.getString(1),rs.getString(2),rs.getString(3),
						Integer.parseInt(rs.getString(4)),rs.getString(5),rs.getString(6),
						Float.parseFloat(rs.getString(7)),Float.parseFloat(rs.getString(8)),
						Float.parseFloat(rs.getString(9)),Float.parseFloat(rs.getString(10)),
						Float.parseFloat(rs.getString(11)),Float.parseFloat(rs.getString(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public void closeSQL() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

	public List<Student> getStudent() {
		return student;
	}
	
}
