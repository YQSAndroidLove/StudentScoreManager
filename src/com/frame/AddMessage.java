package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.assist.MyFunction;
import com.constant.Constant;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class AddMessage extends JPanel {
	
	private DriveSQL sql;

	private JLabel[] label;
	private JTextField[] text;
	private JComboBox comboBoxSex;
	
	private JButton back;
	private JButton okBtn;
	
	private int fontSize;
	private String sex = null;
	private int selectSexIndex = 0;

	public AddMessage(DriveSQL sql) {
		
		this.sql = sql;		
		this.setOpaque(false);		
		
		initialize();
	}

	public void initialize() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		JPanel[] panel;		
		fontSize = ((Constant.WINDOW_WIDTH / 34)+(Constant.WINDOW_HEIGHT / 21))/2 - 2;

		p1.setLayout(new GridLayout(3, 2, 20, 40));
		p2.setLayout(new GridLayout(3, 2, 30, 40));

		p1.setOpaque(false);
		p2.setOpaque(false);

		panel = new JPanel[Constant.SHOW_STRING.length];
		label = new JLabel[Constant.SHOW_STRING.length];
		text = new JTextField[Constant.SHOW_STRING.length];

		for (int i = 0; i < 6; i++) {
			
			label[i] = new JLabel(Constant.SHOW_STRING[i] +" :    ");
			label[i].setFont(new Font("华文楷体", Font.BOLD, fontSize));
			label[i].setForeground(Color.red);
			
			panel[i] = new JPanel();
			panel[i].setOpaque(false);
			panel[i].setLayout(new BorderLayout());	
			panel[i].add(label[i], BorderLayout.WEST);
			
			if(2 == i){
				comboBoxSex = new JComboBox(Constant.SELECT_SEX);
				comboBoxSex.setOpaque(false);
				panel[i].add(comboBoxSex, BorderLayout.CENTER);
			}else{				
				text[i] = new JTextField();
				text[i].setOpaque(false);
				panel[i].add(text[i], BorderLayout.CENTER);				
			}
			p1.add(panel[i]);
		}

		for (int i = 6; i < Constant.SHOW_STRING.length; i++) {
			
			label[i] = new JLabel(Constant.SHOW_STRING[i] +" :      ");
			label[i].setFont(new Font("华文楷体", Font.BOLD, fontSize));
			
			panel[i] = new JPanel();
			panel[i].setOpaque(false);
			panel[i].setLayout(new BorderLayout());	
			
			text[i] = new JTextField();
			text[i].setText("0");
			text[i].setOpaque(false);
			panel[i].add(text[i], BorderLayout.CENTER);
			panel[i].add(label[i], BorderLayout.WEST);
			
			p2.add(panel[i]);
		}

		p1.setBorder(new TitledBorder("学生基本信息"));
		p2.setBorder(new TitledBorder("学生成绩信息"));
	
		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		p3.setLayout(new GridLayout(2,1,0,10));		
		
		p3.add(p1);
		p3.add(p2);
		
		okBtn = new JButton("确    定");
		back = new JButton("清    空");
		
		okBtn.setForeground(Color.white);
		okBtn.setBackground(Color.blue);
		back.setForeground(Color.white);
		back.setBackground(Color.red);
		
		back.setToolTipText("点我返回!");
		okBtn.setToolTipText("你傻呀，没看见是删除啊!");
		back.addMouseListener(new OwnListener());
		okBtn.addMouseListener(new OwnListener());
		
		comboBoxSex.addItemListener(new ItemListener() {
			 public void itemStateChanged(final ItemEvent e) {
				  int index = comboBoxSex.getSelectedIndex();
				  if (index != 0) {							 // ==0表示选中的是第一个					  
					  selectSexIndex = index;
					  sex = comboBoxSex.getSelectedItem().toString();
				     }
				  }
			});		
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new GridLayout(1,2));
		
		p.add(okBtn);
		p.add(back);
		
		this.setLayout(new BorderLayout());
		
		this.add(p3,BorderLayout.CENTER);
		this.add(p,BorderLayout.SOUTH);		
	}
	
	public void clearMenu(){
		for(int i=0;i<Constant.SHOW_STRING.length;i++){
			if(2 != i){
				text[i].setText("");
			}
		}
		comboBoxSex.setSelectedIndex(0);
		selectSexIndex = 0;
	}
	public void temp(int index){
			JOptionPane.showMessageDialog(null,Constant.SHOW_STRING[index]+"不合法!", "错误......", JOptionPane.ERROR_MESSAGE);
	}
	public boolean isArea(int i){
		if(MyFunction.isNumber(text[i].getText())){
			if(Integer.parseInt(text[3].getText()) < 0 ||  Integer.parseInt(text[i].getText()) > 150){
				temp(i);
				return false;
			}
		}else{
			JOptionPane.showMessageDialog(null,Constant.SHOW_STRING[i]+"只能为数字!", "错误......", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean isFail(){
		for(int i=0;i<Constant.SHOW_STRING.length;i++){
			if(2 == i)
				continue;
			if(!text[i].getText().equals("")){
				switch(i){
				case 0:
					if(!MyFunction.isNumber(text[i].getText())){
						temp(i);
						return false;
					}
					break;
				case 3:
					if(MyFunction.isNumber(text[i].getText())){
						if(Integer.parseInt(text[3].getText()) < 5 ||  Integer.parseInt(text[i].getText()) > 30){
							temp(i);
							return false;
						}
					}else{
						JOptionPane.showMessageDialog(null,Constant.SHOW_STRING[i]+"只能为数字!", "错误......", JOptionPane.ERROR_MESSAGE);
						return false;
					}	
					break;
				case 6: case 7:case 8: case 9: case 10: case 11:
					if(!isArea(i)) return false;
				}
			}else{				
				JOptionPane.showMessageDialog(null,Constant.SHOW_STRING[i]+"不能为空!", "错误......", JOptionPane.ERROR_MESSAGE);
				return false;			
			}
		}
		if(selectSexIndex == 0){
			JOptionPane.showMessageDialog(null,"请选择性别!", "错误......", JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		return true;
	}
	
	private class OwnListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == okBtn){
				if(isFail()){
					String strSql = "select * from Student where stuNumber='"+text[0].getText()+"';";
					if(sql.judgeStudentMsg(strSql)){						
						JOptionPane.showMessageDialog(null,"该学号已存在!", "错误......", JOptionPane.ERROR_MESSAGE);
						return;
					}
					strSql = "insert into student values('"+text[0].getText()+"','"+text[1].getText()+
							"','"+Constant.SELECT_SEX[selectSexIndex]+"',"+Integer.parseInt(text[3].getText())+
							",'"+text[4].getText()+"','"+text[5].getText()+"',"+Float.parseFloat(text[6].getText())+
							","+Float.parseFloat(text[7].getText())+","+Float.parseFloat(text[8].getText())+
							","+Float.parseFloat(text[9].getText())+","+Float.parseFloat(text[10].getText())+
							","+Float.parseFloat(text[11].getText())+");";					
					if(sql.insertMessage(strSql)){
						JOptionPane.showMessageDialog(null,"创建成功!", "成功......", JOptionPane.OK_CANCEL_OPTION);
					}else{
						JOptionPane.showMessageDialog(null, "创建失败!", "错误......", JOptionPane.ERROR_MESSAGE);
					}
				}
			}else if(e.getSource() == back){
				clearMenu();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(GainImage.background.get(1), 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT, this);
	}
}
