package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.ObjectCreator;

public class Bomber extends ObjectCreator implements KeyListener{
	
	public Bomber(int x, int y) {
		
		i = il.imageL("Images/bomber_front.png");
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
	    l.addKeyListener(this);
	    
		}
	@Override
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	System.out.print("Lewo");;
        }

        if (key == KeyEvent.VK_RIGHT) {
        	System.out.print("Prawo");
        }

        if (key == KeyEvent.VK_UP) {
        	System.out.print("Gora");;
        }

        if (key == KeyEvent.VK_DOWN) {
        	System.out.print("Dol");
        }
    }
	@Override
    public void keyReleased(KeyEvent e) {

        System.out.print("Puszczono cos");
    }
	@Override
    public void keyTyped(KeyEvent e) {

        System.out.print("Wpisano cos");

    }
	
}
