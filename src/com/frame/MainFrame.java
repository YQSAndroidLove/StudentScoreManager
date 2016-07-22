package com.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.constant.Constant;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class MainFrame extends JFrame{		

	private String createTable =  "create table Student("
	 		+ "stuNumber char(10) UNIQUE NOT NULL,"
	 		+ "stuName char(30) NOT NULL,"
	 		+ "stuSex char(2) CHECK (readerSex IN('男','女')),"
	 		+ "stuAge SMALLINT CHECK (readerAge >= 15 AND readerAge <= 30),"
	 		+ "stuClass char(20),"
	 		+ "stuCall char(15),"
	 		+ "stuSQL float,"
	 		+ "stuJava float,"
	 		+ "stuSystem float,"
	 		+ "stuEnglish float,"
	 		+ "stuPE float,"
	 		+ "stuComputer float,"
	 		+ "PRIMARY KEY (stuNumber)"
	 	+ ");";
	
	private String message = "insert into Student values("
			  +" '2013083225', '姚鸿', '男', 21, '计算机学院网络工程',"
			  +" '18842685404', 85, 80, 80, 50, 90, 70);";
	
	private DriveSQL mySql;
	private MainMenu menu;
	
	public MainFrame(){		
		this.setTitle("Student System");
		this.setBackground(Constant.ADDMENU_COLOR);
		
		this.setLayout(new BorderLayout());
		JLabel headBack = new JLabel(new ImageIcon("image\\head.png"));
		this.add(headBack,BorderLayout.NORTH);
		mySql = new DriveSQL();
		if(!mySql.createSQL()){
			JOptionPane.showMessageDialog(null, "加载数据库驱动失败，请检查服务是否启动!", "错误......", JOptionPane.ERROR_MESSAGE);
		}else if(!mySql.judgeIsTableExists()){
			mySql.creatTable(createTable);
		}
		
		GainImage.imageInitialize();		
		this.initialize();
		
		this.setPreferredSize(new Dimension(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT));		
		this.addComponentListener(new WindowChange());
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(Constant.WINDOW_LOCATION_X,Constant.WINDOW_LOCATION_Y);
		this.setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		this.setVisible(true);
	}	

	public void initialize(){		
		menu = new MainMenu(mySql,this);	
		this.add(menu,BorderLayout.CENTER);
	}
	
	/*
	 * not use
	 */
	public void removeMen(){
		this.remove(menu);
		initialize();
		menu.updateUI();
	}
	
	private class WindowChange extends ComponentAdapter{
		public void componentResized(ComponentEvent e) {
			Constant.WINDOW_WIDTH = MainFrame.this.getWidth();
			Constant.WINDOW_HEIGHT = MainFrame.this.getHeight();	
			MainFrame.this.repaint();
		}
	}
	
}