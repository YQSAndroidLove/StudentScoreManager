package com.assist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.constant.Constant;
import com.data.Student;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class FixAssist extends JPanel {

	private DriveSQL sql;
	private Student student;
	private MyFunction function;

	private ImageIcon backIcon;
	private JLabel[] label;
	private JButton[] button;
	
	private int type;
	private int fontSize;

	public FixAssist(DriveSQL sql,Student student, int type) {
		
		this.sql = sql;
		this.student = student;
		this.type = type;
		this.setOpaque(false);
		
		function = new MyFunction();
		initialize();
	}

	public void initialize() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		JPanel[] panel;		
		if (type == Constant.SHOW_STUDENT_MESSAGE) {			
			fontSize = ((Constant.WINDOW_WIDTH / 34)+(Constant.WINDOW_HEIGHT / 21))/2;
		} else if(type == Constant.FIX_STUDENT_MESSAGE){
			fontSize = ((Constant.WINDOW_WIDTH / 34)+(Constant.WINDOW_HEIGHT / 21))/2 - 2;
		}
		p1.setLayout(new GridLayout(3, 2, 10, 13));
		p2.setLayout(new GridLayout(3, 2, 30, 13));

		p1.setOpaque(false);
		p2.setOpaque(false);

		button = new JButton[Constant.SHOW_STRING.length];
		panel = new JPanel[Constant.SHOW_STRING.length];
		label = new JLabel[Constant.SHOW_STRING.length];

		String obj;
		for (int i = 0; i < 6; i++) {
			obj = null;
			switch (i) {
			case 0:
				obj = " " + student.getStuNumber();
				break;
			case 1:
				obj = " " + student.getStuName();
				break;
			case 2:
				obj = " " + student.getStuSex();
				break;
			case 3:
				obj = " " + student.getStuAge();
				break;
			case 4:
				obj = " " + student.getStuClass();
				break;
			case 5:
				obj = " " + student.getStuCall();
				break;
			}
			
			label[i] = new JLabel(Constant.SHOW_STRING[i] +":"+ obj);
			label[i].setFont(new Font("华文楷体", Font.BOLD, fontSize));
			label[i].setForeground(Color.red);
			
			if (type == Constant.FIX_STUDENT_MESSAGE) {
				panel[i] = new JPanel();
				panel[i].setOpaque(false);
				panel[i].setLayout(new BorderLayout());			
				button[i] = new JButton(GainImage.fixTip);
				function.setButtonStyle(button[i]);
				button[i].addMouseListener(new OwnListener());
				panel[i].add(button[i], BorderLayout.EAST);
				panel[i].add(label[i], BorderLayout.CENTER);
				p1.add(panel[i]);
			} else {
				p1.add(label[i]);
			}
		}

		for (int i = 6; i < Constant.SHOW_STRING.length; i++) {
			obj = null;
			switch (i) {
			case 6:
				obj = " " + student.getStuSQL();
				break;
			case 7:
				obj = " " + student.getStuJava();
				break;
			case 8:
				obj = " " + student.getStuSystem();
				break;
			case 9:
				obj = " " + student.getStuEnglish();
				break;
			case 10:
				obj = " " + student.getStuEnglish();
				break;
			case 11:
				obj = " " + student.getStuComputer();
				break;
			}
			label[i] = new JLabel(Constant.SHOW_STRING[i] +":"+ obj);
			label[i].setFont(new Font("华文楷体", Font.BOLD, fontSize));
			if (type == Constant.FIX_STUDENT_MESSAGE) {
				panel[i] = new JPanel();
				panel[i].setOpaque(false);
				panel[i].setLayout(new BorderLayout());	
				
				button[i] = new JButton(GainImage.fixTip);
				function.setButtonStyle(button[i]);
				button[i].addMouseListener(new OwnListener());
				
				panel[i].add(button[i], BorderLayout.EAST);
				panel[i].add(label[i], BorderLayout.CENTER);
				p2.add(panel[i]);
			} else {
				p2.add(label[i]);
			}
		}

		p1.setBorder(new TitledBorder("学生基本信息"));
		p2.setBorder(new TitledBorder("学生成绩信息"));
	
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(2,1,0,10));		
		p3.setOpaque(false);
		p3.add(p1);
		p3.add(p2);
		
		
		this.setLayout(new BorderLayout());
		
		this.add(p3,BorderLayout.CENTER);
		
	}

	private class OwnListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {			
			String strSql = "update student set ";
			String result = null;
			if (e.getSource() == button[0]) {
				result = function.inputFixMsg("请输入新的学号:");
				if (result != null) {
					if(function.isNumber(result)){
						strSql += "stuNumber='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					}else{
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			}else if (e.getSource() == button[1]) {
				result = function.inputFixMsg("请输入新的姓名:");
				if (result != null) {					
						strSql += "stuName='" + result
								+ "' where stuName='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
				}
			}else if (e.getSource() == button[2]) {
				result = function.inputFixMsg("请输入新的性别（男/女）:");
				if (result != null) {
					if (result.equals("男") || result.equals("女")) {
						strSql += "stuSex='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[3]) {
				result = function.inputFixMsg("请输入新的年龄(15~30):");
				if (result != null) {
					if (Integer.parseInt(result) >= 15
							&& Integer.parseInt(result) <= 30) {
						strSql += "stuAge='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[4]) {
				result = function.inputFixMsg("请输入新的专业:");
				if (result != null) {
					if (function.isPhoneNumberValid(result)) {
						strSql += "stuClass='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} 
				}
			} else if (e.getSource() == button[5]) {
				result = function.inputFixMsg("请输入新的联系方式:");
				if (result != null) {
					if (function.isPhoneNumberValid(result)) {
						strSql += "stuCall='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					}
				}
			} else if (e.getSource() == button[6]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += " stuSQL='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			}else if (e.getSource() == button[7]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += "stuJava='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[8]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += "stuSystem='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[9]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += "stuEnglish='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[10]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += "stuPE='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (e.getSource() == button[11]) {
				result = function.inputFixMsg("请输入新的成绩(0~150):");
				if (result != null) {
					if (Float.parseFloat(result) >= 0 && Float.parseFloat(result) <= 150) {
						strSql += "stuComputer='" + result
								+ "' where stuNumber='"
								+ student.getStuNumber() + "';";
						sql.tempMessage(strSql);
					} else {
						JOptionPane.showMessageDialog(null, "你填写的内容不合法!",
								"错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			} 
		}	
	}	
}
