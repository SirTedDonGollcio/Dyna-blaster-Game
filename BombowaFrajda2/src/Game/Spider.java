package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import Game.ObjectCreator;

public class Spider  extends ObjectCreator{
	
	
	public Spider(int x, int y) {
		
		posX=x*55;
		posY=y*55;
		i = il.imageL("Images/spider.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
		}
}
