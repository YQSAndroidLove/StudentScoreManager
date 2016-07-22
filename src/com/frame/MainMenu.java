	package com.frame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class MainMenu extends JTabbedPane {
	
	private DriveSQL sql;
	private MainFrame frame;
	
	private AddMessage addMessage;
	private ShowMessage showMessage;
	private SQLManager sqlManager;
	
	public MainMenu(DriveSQL sql,MainFrame frame){
		this.sql = sql;
		this.frame = frame;
		
		this.setTabPlacement(JTabbedPane.RIGHT);
		this.setBackground(Color.green);
		this.setOpaque(false);
		
		addMessage = new AddMessage(sql);
		showMessage = new ShowMessage(sql);
		sqlManager = new SQLManager(sql);
		
		this.addChangeListener(new Listener());
		
		initialize();
	}
	
	public void initialize(){
		
		this.addTab(null,GainImage.searchMessage,new SearchMessage(this.sql),"查找学生");	
		this.addTab(null,GainImage.addMessage,addMessage,"添加学生信息");
		this.addTab(null,GainImage.fixMessage,new FixMessage(sql),"修改学生信息");
		this.addTab(null,GainImage.showMessage,showMessage,"查看所有学生信息");	
		this.addTab(null,GainImage.deleteMessage,new DeleteMessage(sql),"删除学生信息");
		this.addTab(null,GainImage.sqlManager,sqlManager,"数据库管理");		
		this.addTab(null,GainImage.exitSystem,new ExitSystem(frame,this.sql),"注销用户");		
	}
	
	private class Listener implements ChangeListener{

		public void stateChanged(ChangeEvent e) {
			if(getSelectedComponent() == showMessage){
				showMessage.removeAll();
			}else if(getSelectedComponent() == sqlManager){
				JOptionPane.showMessageDialog(null,"操作后不可恢复，请谨慎操作！");				
			}
		}		
	}
}
