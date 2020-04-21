package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import GUI.ImageLoader;

import java.awt.event.*;

public abstract class ObjectCreator {
	public JLabel l = new JLabel();
	public BufferedImage i;
	public ImageIcon ii = new ImageIcon();
	public ImageLoader il = new ImageLoader();
	
	public JLabel getLabel()
	{
		return l;
	}
}
