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
	public JLabel l = new JLabel();
	public ImageLoader il = new ImageLoader();
	public BufferedImage i;
	public ImageIcon ii;
	public int sizeX=55;
	public int sizeY=55;
	public int posX;
	public int posY;
	public int tempX;
	public int tempY;
	/*
	 * Klasa abstrakcyjna s³u¿¹ca jako klasa -rodzic dla wszystkich obiuektów pojawiaj¹cych siê na ekranie
	 */
	public JLabel getLabel()
	{
		return l;
	}
}
