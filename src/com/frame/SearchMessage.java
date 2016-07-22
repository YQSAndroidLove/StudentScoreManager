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
import com.constant.Constant;
import com.data.Student;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class SearchMessage extends JPanel {

	private DriveSQL sql;
	private SearchAssist search;
	private FixAssist showMessage;
	private JLabel space;
	private JButton back;

	public SearchMessage(DriveSQL sql) {
		this.sql = sql;
		this.setOpaque(false);
		//this.setBackground(Constant.ADDMENU_COLOR);
		
		initialize();
		refresh();
	}

	public void addSearchMenu() {
		initialize();
		space.updateUI();
		search.updateUI();
	}

	public void addResultMenu() {

		this.setLayout(new BorderLayout());
		back.setToolTipText("点我返回!");
		back.addMouseListener(new OwnListener());
		
		this.add(showMessage, BorderLayout.CENTER);
		this.add(back,BorderLayout.SOUTH);
		showMessage.updateUI();
	}

	public void removeSearchMenu() {
		this.remove(search);
		this.remove(space);
		this.repaint();
	}

	public void removeResultMenu() {
		this.remove(showMessage);
		this.remove(back);
		this.repaint();
	}

	public void refresh() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (search.isClick()) {
						Student student = search.getStudent();
						if (student != null) {
							showMessage = new FixAssist(sql, student,Constant.SHOW_STUDENT_MESSAGE);
							removeSearchMenu();
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

	public void initialize() {
		search = new SearchAssist(sql, Constant.SEARCH);

		space = new JLabel();
		back = new JButton("返    回");
		back.setForeground(Color.white);
		back.setBackground(Color.blue);
		
		this.setLayout(new GridLayout(7, 1, 0, 20));

		this.add(space);
		this.add(search);
	}
	

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(GainImage.background.get(1), 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT, this);
	}
	
	private class OwnListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == back){
				removeResultMenu();
				addSearchMenu();
			}
		}
	}
}
