package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.assist.FixAssist;
import com.assist.SearchAssist;
import com.assist.ShakeDlg;
import com.constant.Constant;
import com.data.Student;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class DeleteMessage extends JPanel {

	private DriveSQL sql;
	private FixAssist deleteMessage;
	private SearchAssist search;
	private JLabel space;
	
	private Student student;
	private JButton back;
	private JButton delBtn;
	private JPanel p;

	public DeleteMessage(DriveSQL sql) {
		this.sql = sql;
		this.setOpaque(false);
		
		initialize();		
		refresh();
	}
	
	public void initialize() {
		search = new SearchAssist(sql, Constant.SEARCH);

		space = new JLabel();

		this.setLayout(new GridLayout(7, 1, 0, 20));		

		this.add(space);
		this.add(search);
	}

	public void addDeleteMenu() {
		initialize();
		space.updateUI();
		search.updateUI();
	}

	public void addResultMenu() {
		this.setLayout(new BorderLayout());
		p = new JPanel();
		
		p.setLayout(new GridLayout(1,2));
		delBtn = new JButton("删     除");
		back = new JButton("返    回");	
		p.add(delBtn);
		p.add(back);
		
		delBtn.setForeground(Color.white);
		delBtn.setBackground(Color.red);
		back.setForeground(Color.white);
		back.setBackground(Color.blue);
		
		back.setToolTipText("点我返回!");
		delBtn.setToolTipText("你傻呀，没看见是删除啊!");
		back.addMouseListener(new OwnListener());
		delBtn.addMouseListener(new OwnListener());
		
		this.add(deleteMessage, BorderLayout.CENTER);
		this.add(p,BorderLayout.SOUTH);
		deleteMessage.updateUI();
	}

	public void removeDeleteMenu() {
		this.remove(search);
		this.remove(space);
		this.repaint();
	}

	public void removeResultMenu() {
		this.remove(deleteMessage);
		this.remove(back);
		this.remove(delBtn);
		this.remove(p);
		this.repaint();
	}

	public void refresh() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (search.isClick()) {
						student = search.getStudent();
						if (student != null) {
							deleteMessage = new FixAssist(sql, student, Constant.SHOW_STUDENT_MESSAGE);
							removeDeleteMenu();
							addResultMenu();
						} else {
							JOptionPane.showMessageDialog(null, "该学生不存在!",
									"错误......", JOptionPane.ERROR_MESSAGE);
						}
						search.setClick(false);
					}

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void deleteMessage(){
		String strSql = "delete from Student where stuNumber='"+student.getStuNumber()+"';";
		if(sql.tempMessage(strSql)){
			JOptionPane.showMessageDialog(null,"删除成功!", "成功......", JOptionPane.OK_CANCEL_OPTION);
		}else{
			JOptionPane.showMessageDialog(null, "删除失败!", "错误......", JOptionPane.ERROR_MESSAGE);
		}
		removeResultMenu();
		addDeleteMenu();
	}
	
	private class OwnListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == back){
				removeResultMenu();
				addDeleteMenu();
			}else if(e.getSource() == delBtn){				
				JOptionPane  pane = new JOptionPane("你确定要删除吗!",  JOptionPane.QUESTION_MESSAGE,JOptionPane.OK_CANCEL_OPTION);	
				int count=0;
				new ShakeDlg(pane);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				if(pane.OK_OPTION == JOptionPane.OK_OPTION){
					System.out.println("YYYYYYYYY");
					//deleteMessage();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(GainImage.background.get(1), 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT, this);
	}
}
