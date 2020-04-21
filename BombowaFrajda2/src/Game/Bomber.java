package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Game.ObjectCreator;


public class Bomber extends ObjectCreator{
	
	public int posX;
	public int posY;
	public int lX;
	public int lY;
	public int speedX;
	public int speedY;
	public BufferedImage front = il.imageL("Images/bomber_front.png");
	public BufferedImage back = il.imageL("Images/bomber_back.png");
	public BufferedImage left = il.imageL("Images/bomber_left.png");
	public BufferedImage right = il.imageL("Images/bomber_right.png");
	public BufferedImage frontS = front;
	public BufferedImage backS = back;
	public BufferedImage leftS = left;
	public BufferedImage rightS = right;
	public int direction=4;
			
	
	public Bomber(int x, int y) {
		
		//l= new KeyLabel();
		posX=x*55;
		posY=y*55;
		speedX=3;
		speedY=3;
		
		
		i = front;
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(posX,posY,55,55);	    
		}
	
}	
	
	
	

