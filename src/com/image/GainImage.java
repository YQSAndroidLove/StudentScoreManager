package com.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.constant.Constant;

public class GainImage {
	
	public static BufferedImage head;
	public static BufferedImage prograssBar;
	public static List<BufferedImage> background = new ArrayList<BufferedImage>();
	
	public static ImageIcon addMessage;
	public static ImageIcon deleteMessage;
	public static ImageIcon exitSystem;
	public static ImageIcon fixMessage;
	public static ImageIcon searchMessage;
	public static ImageIcon sqlManager;
	public static ImageIcon showMessage;
	public static ImageIcon fixTip;
	public static ImageIcon exitBack;
	
	public static void imageInitialize(){
		
		try {
			for(int i=0;i<Constant.BACKGROUND_COUNT;i++){
				background.add(ImageIO.read(new File("image\\background"+i+".jpg")));
			}
			head = ImageIO.read(new File("image\\head.png"));
			prograssBar = ImageIO.read(new File("image\\prograssBar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMessage = new ImageIcon("tipImage\\addMessage.png");
		deleteMessage = new ImageIcon("tipImage\\deleteMessage.png");
		exitSystem = new ImageIcon("tipImage\\exitSystem.png");
		fixMessage = new ImageIcon("tipImage\\fixMessage.png");
	    searchMessage = new ImageIcon("tipImage\\searchMessage.png");
	    sqlManager = new ImageIcon("tipImage\\sqlManager.png");
		showMessage = new ImageIcon("tipImage\\showMessage.png");
		fixTip = new ImageIcon("tipImage\\fixTip.png");
		exitBack = new ImageIcon("tipImage\\exitBack.gif");
	}
}
