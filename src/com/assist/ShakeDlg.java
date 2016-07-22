package com.assist;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ShakeDlg{

	private Point start;
	private Timer shakeTimer;
	private JDialog dialog; 
	private JOptionPane pane;
	private long elapsed;
	
	public ShakeDlg(JOptionPane pane){		
		dialog = pane.createDialog(null,"Ñ¯ÎÊ......");
		dialog.pack();
		dialog.setModal(false);
		dialog.setVisible(true);
		this.startShake();		
	}

	public void startShake(){
		final long startTimer = System.currentTimeMillis();
		start = dialog.getLocation();
		shakeTimer = new Timer(10,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				elapsed = System.currentTimeMillis() - startTimer;
				Random random = new Random(elapsed);
				int change = random.nextInt(10);
				dialog.setLocation(change + start.x,change + start.y);
				if(elapsed >= 1000){
					stopShake();
				}
			}			
		});
		shakeTimer.start();
	}
	
	public void stopShake(){
		shakeTimer.stop();
		dialog.setLocation(start);
	//	dialog.dispose();
	}
}
