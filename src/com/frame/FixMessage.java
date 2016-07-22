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

public class FixMessage extends JPanel {

	private DriveSQL sql;
	private FixAssist fixMessage;
	private SearchAssist search;
	private JLabel space;
	private JButton back;
	private Student student;

	public FixMessage(DriveSQL sql) {
		this.sql = sql;
		this.setOpaque(false);
		
		initialize();		
		refresh();
	}
	
	public void initialize() {
		search = new SearchAssist(sql, Constant.SEARCH);

		space = new JLabel();

		this.setLayout(new GridLayout(7, 1, 0, 20));
		back = new JButton("返    回");
		back.setForeground(Color.white);
		back.setBackground(Color.blue);

		this.add(space);
		this.add(search);
	}

	public void addFixMenu() {
		initialize();
		space.updateUI();
		search.updateUI();
	}

	public void addResultMenu() {
		this.setLayout(new BorderLayout());

		back.setToolTipText("点我返回!");
		back.addMouseListener(new OwnListener());
		
		this.add(fixMessage, BorderLayout.CENTER);
		this.add(back,BorderLayout.SOUTH);
		fixMessage.updateUI();
	}

	public void removeFixMenu() {
		this.remove(search);
		this.remove(space);
		this.repaint();
	}

	public void removeResultMenu() {
		this.remove(fixMessage);
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
							fixMessage = new FixAssist(sql, student, Constant.FIX_STUDENT_MESSAGE);
							removeFixMenu();
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
	
	private class OwnListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getSource() == back){
				removeResultMenu();
				addFixMenu();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(GainImage.background.get(1), 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT, this);
	}
}
