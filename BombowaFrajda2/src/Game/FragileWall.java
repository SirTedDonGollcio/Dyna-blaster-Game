package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import Game.ObjectCreator;

public class FragileWall extends ObjectCreator{
	public FragileWall(int x, int y) {
		
		i = il.imageL("Images/rock1.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
		}
}
