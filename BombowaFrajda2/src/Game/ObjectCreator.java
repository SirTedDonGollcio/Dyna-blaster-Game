package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import GUI.ImageLoader;

import java.awt.event.*;

public class ObjectCreator {
	public JLabel wallCreator(int x, int y)
	{
		JLabel l = new JLabel();
		ImageLoader il = new ImageLoader();
		BufferedImage i = il.imageL("Images/wall.png");
        ImageIcon ii = new ImageIcon(i);
        l.setIcon(ii);
        l.setBounds(x*55,y*55,55,55);
		
		return l;
	}
}
