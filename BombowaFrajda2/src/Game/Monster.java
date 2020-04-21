package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import Game.ObjectCreator;

public class Monster  extends ObjectCreator{
	
	public Monster(int x, int y) {
		
		i = il.imageL("Images/bomb.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
		}
}
