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
			Class.forName("com.mysql.jdbc.Driver"); // ��̬����mysql����
			// System.out.println("�ɹ�����MySQL��������"); // һ��Connection����һ�����ݿ�����
			conn = DriverManager.getConnection(url); // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
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
	
	public boolean creatTable(String sql) { // executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
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
		} // executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
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
