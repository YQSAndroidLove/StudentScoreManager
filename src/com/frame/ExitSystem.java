package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.constant.Constant;
import com.image.GainImage;
import com.sqlservice.DriveSQL;

public class ExitSystem extends JPanel {

	private DriveSQL sql;
	private JLabel back;
	private PrograssBar pane;
	private MainFrame frame;
	private int wideX = 0;
	private boolean isStart = true;	
	private int index = 0;
	private Font font;

	public ExitSystem(MainFrame frame,DriveSQL sql) {
		this.sql = sql;
		this.frame = frame;
		
		back = new JLabel(new ImageIcon("image\\exitBack.gif"));
		
		font = new Font("华文楷体", 0, 24);
		back.setFont(new Font("",0,30));
		back.setToolTipText("点击我退出!");
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == back){	
					sql.closeSQL();				
					pane = new PrograssBar();					
					ExitSystem.this.add(pane,BorderLayout.CENTER);
					refresh();

				}
			}
		});
	
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(back,BorderLayout.CENTER);		
	}
	
	public void refresh(){
		pane.updateUI();
		if(wideX >= Constant.WINDOW_WIDTH - 140){
			isStart = false;
			frame.dispose();
			System.exit(0);
		}
	}
	
	private class PrograssBar extends JPanel implements Runnable{			
		public PrograssBar(){
		//	this.setSize(10,100);
			new Thread(this).start();
		}
		public void paint(Graphics g){
			BufferedImage image  = new BufferedImage(Constant.WINDOW_WIDTH,30,BufferedImage.TYPE_3BYTE_BGR);
			Graphics g2 = image.getGraphics(); 
			
			g2.setColor(Constant.ADDMENU_COLOR);
			g2.fillRect(0,0, Constant.WINDOW_WIDTH, 30);
			
			g2.drawImage(GainImage.prograssBar,0,0,wideX,30,this);			
			g2.setFont(font);
			g2.setColor(Color.red);
			g2.drawString(Constant.EXIT_TIP[index/100], (Constant.WINDOW_WIDTH - 450)/2, 20);
			
			g.drawImage(image, 0, 0, this);
		}
		public void run() {
			while(isStart){
				wideX += 3;
				index+=10;
				if(index / 100 >= 7){
					index = 0;
				}
				refresh();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(GainImage.background.get(1), 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT, this);
	}
}
