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
import Game.Bomb;

public class Bomber extends ObjectCreator{
	
	
	public int lX;
	public int lY;
	public int speedX;
	public int speedY;
	public int direction;
	public int iloscZycia = 5;
	public int iloscBomb = 3;
	public int currentBombs = 0;
	public Bomb[] bombs = new Bomb[5];
	public int bombSizeX=55;
	public int bombSizeY=55;
	public BufferedImage front = il.imageL("Images/bomber_front.png");
	public BufferedImage back = il.imageL("Images/bomber_back.png");
	public BufferedImage right = il.imageL("Images/bomber_right.png");
	public BufferedImage left = il.imageL("Images/bomber_left.png");
	public BufferedImage frontS = front;
	public BufferedImage backS = back;
	public BufferedImage rightS = right;
	public BufferedImage leftS = left;
	public boolean isColD = false;
	public boolean isColU = false;
	public boolean isColR = false;
	public boolean isColL = false;
	public int iloscScian;
	public int iloscFragScian;
	public int iloscPajakow;
	public Wall[] walls;
	public FragileWall[] fragWalls;
	public Spider spiders[];
	
	public Bomber(int x, int y) {
		
		//l= new KeyLabel();
		posX=x*55;
		posY=y*55;
		speedX=3;
		speedY=3;
		sizeX=55;
		sizeY=55;
		direction=1;
		i = frontS;
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(posX,posY,55,55);	    
		}
	
}	
	
	
	

