package com.login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.frame.MainFrame;

public class LoginDlg extends JDialog implements MouseListener, KeyListener{
	
	private JButton LoginBtn = new JButton("Login");
	private JButton Resert = new JButton("Resert");
	private JLabel nameLabel = new JLabel("User Name:   ");
	private JLabel passwordLabel = new JLabel("Password :    ");
	private JTextField nameText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();
	
	public LoginDlg(){
		
		this.setTitle("User Login");
		passwordText.setEchoChar('*');            //Set echo sign
		LoginBtn.setFocusPainted(false);         //È¥µôÎÄ×Ö±ß¿ò
		Resert.setFocusPainted(false);
		//LoginBtn.setBorderPainted(false);         //È¥µô°´Å¥±ß¿ò
		//Resert.setBorderPainted(false);
		initialize();
		
		nameText.setText("2013083225");
		passwordText.setText("2013083225");
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(350, 220);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public int JudgeEmpty(){                          //password and user name proving
		String nameStr = nameText.getText();
		char[] pw = passwordText.getPassword();
		String passwordStr = new String(pw);
		if(nameStr.equals("") || passwordStr.equals("")){
			return 0;
		}
		else{
			if(nameStr.equals("2013083225") && passwordStr.equals("2013083225")){
				return 1;
			}
			else 
				return -1;
		}
	}
	//MouseListener
	public void mousePressed(MouseEvent e) {            //Use 'Login' key Login
		if(e.getSource() == Resert){
			nameText.setText("");
			passwordText.setText("");
		}
		if(e.getSource() == LoginBtn){   
			int judge = JudgeEmpty();
			if(judge == 0){
				JOptionPane.showMessageDialog(null, "Dear:User name or password doesn't null!", "Waring......", JOptionPane.ERROR_MESSAGE);
			}
			else if(judge == -1)
				JOptionPane.showMessageDialog(null, "Dear:User name or password error!", "Waring......", JOptionPane.ERROR_MESSAGE);		
			else{
				this.dispose();
				new MainFrame();
			}
		}
	}
	
	public void initialize(){
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		p1.setOpaque(false);                   //Set transparent
		p2.setOpaque(false);
		p3.setOpaque(false);
		
		Icon icon = new ImageIcon("image\\readFilebackground.png");
		JLabel iconLabel = new JLabel(icon);
		p4.add(iconLabel);
		
		p1.setLayout(new BorderLayout());
		p1.add(nameLabel,BorderLayout.WEST);
		p1.add(nameText,BorderLayout.CENTER);
		
		p2.setLayout(new BorderLayout());
		p2.add(passwordLabel,BorderLayout.WEST);
		p2.add(passwordText,BorderLayout.CENTER);
		
		p3.setLayout(new GridLayout(1,2,15,0));
		p3.add(LoginBtn);
		p3.add(Resert);
		
		nameText.addKeyListener(this);                    //Add to actionlistener
		passwordText.addKeyListener(this);
		LoginBtn.addMouseListener(this);
		Resert.addMouseListener(this);
		LoginBtn.addKeyListener(this);
		
		this.setLayout(null);
		p1.setBounds(50, 40, 230, 25);
		p2.setBounds(50, 80, 230, 25);
		p3.setBounds(110, 120, 170, 30);
		p4.setBounds(0,-30,340,220);
		
		
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.pack();
	}
	
	public void mouseClicked(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {  }
	//KeyListener
	public void keyPressed(KeyEvent e) {      //Use 'Enter' key Login
		if(e.getKeyCode() == 10){
			int judge = JudgeEmpty();
			if(judge == 0){
				JOptionPane.showMessageDialog(null, "      User name or password \n   doesn't null !", "Waring......", JOptionPane.ERROR_MESSAGE);
			}
			else if(judge == -1)
				JOptionPane.showMessageDialog(null, "User name or password error !", "Waring......", JOptionPane.ERROR_MESSAGE);		
			else
			{
				this.dispose();
				new MainFrame();
			}
		}
	}
	public void keyTyped(KeyEvent e) {	 }
	public void keyReleased(KeyEvent e) {	}
}
