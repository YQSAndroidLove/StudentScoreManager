package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.constant.Constant;
import com.data.StudentTableModel;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class ShowMessage extends JPanel {

//	private JTable studentTable;	
//	private StudentTableModel studentModel;
	private DriveSQL sql;
	
	public ShowMessage(DriveSQL sql){
		this.sql = sql;		
		
		this.initialize();	
	}	
	
	public void initialize(){
		StudentTableModel studentModel = new StudentTableModel(sql);
		JTable studentTable = new JTable(studentModel);
		
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(studentTable),BorderLayout.CENTER);
		studentModel.showAllBook();
		
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		studentTable.setRowHeight(40);
		studentTable.setSelectionBackground(Constant.ADDMENU_COLOR);
		studentTable.setSelectionForeground(Color.white); 
		studentTable.setBackground(Color.pink);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		studentTable.setDefaultRenderer(Object.class, tcr);		
		
		TableColumnModel tcm =  studentTable.getColumnModel();
		for(int i=0;i<tcm.getColumnCount();i++){
			TableColumn tc = tcm.getColumn(i);	
			tc.setMinWidth(Constant.TABLE_COLUM_WIDTH[i]);
			tc.setPreferredWidth(Constant.TABLE_COLUM_WIDTH[i]);			
		}			
	}
	
	public void removeAll(){
		this.remove(this);
		initialize();
		this.repaint();
		this.updateUI();				
	}
}
