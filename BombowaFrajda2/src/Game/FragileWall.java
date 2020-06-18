package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import Game.ObjectCreator;

public class FragileWall extends ObjectCreator{
	
	public int wypadnik=0;
	/*
	 * Klasa odpowiedzialna za kruche �ciany,tj. �ciany kt�re gracz mo�e uszkodzi� oraz znalezc r�ne obiekty pod nimi
	 */
	public FragileWall(int x, int y) {
		tempX=x;
		tempY=y;
		posX=x*55;
		posY=y*55;
		sizeX=55;
		sizeY=55;
		i = il.imageL("Images/rock1.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
		}
	
	@Override
	public String toString() {
		return "X:" + tempX + "\nY: " + tempY + "\nPosition x: " + posX + "\nPosiotion y: " + posY;
	}
}
