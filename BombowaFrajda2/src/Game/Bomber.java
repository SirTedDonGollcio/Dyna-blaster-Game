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
	
	public Bomber(int x, int y) {
		
		//l= new KeyLabel();
		posX=x*55;
		posY=y*55;
		speedX=3;
		speedY=3;
		
		
		i = il.imageL("Images/bomber_front.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(posX,posY,55,55);	    
		}
	
}	
	
	
	

