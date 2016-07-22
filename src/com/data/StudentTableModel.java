package com.data;

import java.awt.print.Book;

import javax.swing.table.AbstractTableModel;

import com.constant.Constant;
import com.sqlservice.DriveSQL;

public class StudentTableModel extends AbstractTableModel {
	
	private DriveSQL sql;

	public StudentTableModel(DriveSQL sql) {
		this.sql = sql;
	}

	public void showAllBook() {
		String opSql = "select * from Student;";
		sql.showStudent(opSql);
		this.fireTableStructureChanged();
	}

	public int getRowCount() {
		return sql.getStudent().size();
	}

	public String getColumnName(int column) {
		return Constant.SHOW_STRING[column];
	}

	public int getColumnCount() {
		return Constant.SHOW_STRING.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = sql.getStudent().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return student.getStuNumber();
		case 1:
			return student.getStuName();
		case 2:
			return student.getStuSex();
		case 3:
			return student.getStuAge();
		case 4:
			return student.getStuClass();
		case 5:
			return student.getStuCall();
		case 6:
			return student.getStuSQL();
		case 7:
			return student.getStuJava();
		case 8:
			return student.getStuSystem();
		case 9:
			return student.getStuEnglish();
		case 10:
			return student.getStuEnglish();
		case 11:
			return student.getStuComputer();
		}
		return null;
	}

}
