package com.assist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.constant.Constant;
import com.data.Student;
import com.sqlservice.DriveSQL;

public class SearchAssist extends JPanel {

	public String stuMsg;
	private JTextField text;
	private JButton btn;
	private DriveSQL sql;
	private int type;
	private Student student = null;
	
	private boolean isClick = false;

	public SearchAssist(DriveSQL sql,int type) {
		this.sql = sql;
		this.type = type;
		this.setOpaque(false);
		
		intialize();
	}
	
	public void intialize(){
		text = new JTextField();
		btn = new JButton("搜  索");
		btn.setFocusable(false);
		btn.setBackground(Color.MAGENTA);
		btn.setForeground(Color.white);
		
		JPanel p1 = new JPanel();		
		p1.setLayout(new BorderLayout());
		p1.add(text, BorderLayout.CENTER);
		p1.add(btn, BorderLayout.EAST);
		
		JLabel space1 = new JLabel("");
		JLabel space2 = new JLabel("");
		
		this.setLayout(new GridLayout(1,2,0,20));
		this.add(space1);
		this.add(p1);
		this.add(space2);
		
		btn.addActionListener(new ActionListenerImp());
		text.addKeyListener(new KeyOwnListener());
		
		btn.setToolTipText("当没任何反应是，请在点击我!");
		text.setToolTipText("请输入学生的学号或姓名!");
	}

	public String getsearch() {
		if (!text.getText().isEmpty()) {
			return text.getText();
		}
		return null;
	}

	private class ActionListenerImp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn){
				if(getsearch()==null){
					JOptionPane.showMessageDialog(null, "搜索框不能为空!", "错误......",
							JOptionPane.ERROR_MESSAGE);
				}else{
					isClick = true;
					search();				
			}}
		}
	}
	
	private class KeyOwnListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == 10){
				if(getsearch()==null){
					JOptionPane.showMessageDialog(null, "搜索框不能为空!", "错误......",
							JOptionPane.ERROR_MESSAGE);
				}else{
					isClick = true;
					search();				
			}}
		}
	}
	
	public void search(){
		String strSql;
		if(MyFunction.isChineseChar(text.getText())){
			strSql = "select * from Student where stuName='"+text.getText()+"';";
			student = sql.getStudentMsg(strSql);
		} else {
			if(MyFunction.isNumber(text.getText())){
				strSql = "select * from Student where stuNumber='"+text.getText()+"';";
				student = sql.getStudentMsg(strSql);
			}else{
				isClick = false;
				JOptionPane.showMessageDialog(null, "你的输入不正确!", "错误......",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public Student getStudent() {
		return student;
	}

	public boolean isClick() {
		return isClick;
	}

	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}
	
}
