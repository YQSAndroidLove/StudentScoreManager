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
		
		this.addTab(null,GainImage.searchMessage,new SearchMessage(this.sql),"����ѧ��");	
		this.addTab(null,GainImage.addMessage,addMessage,"���ѧ����Ϣ");
		this.addTab(null,GainImage.fixMessage,new FixMessage(sql),"�޸�ѧ����Ϣ");
		this.addTab(null,GainImage.showMessage,showMessage,"�鿴����ѧ����Ϣ");	
		this.addTab(null,GainImage.deleteMessage,new DeleteMessage(sql),"ɾ��ѧ����Ϣ");
		this.addTab(null,GainImage.sqlManager,sqlManager,"���ݿ����");		
		this.addTab(null,GainImage.exitSystem,new ExitSystem(frame,this.sql),"ע���û�");		
	}
	
	private class Listener implements ChangeListener{

		public void stateChanged(ChangeEvent e) {
			if(getSelectedComponent() == showMessage){
				showMessage.removeAll();
			}else if(getSelectedComponent() == sqlManager){
				JOptionPane.showMessageDialog(null,"�����󲻿ɻָ��������������");				
			}
		}		
	}
}
