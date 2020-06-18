package Animations;
import Game.KeyFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.ImageLoader;
import Game.Bomber;

public class BomberAnim implements Runnable{
	
	ImageLoader il = new ImageLoader();
	Bomber bomber;
	KeyFrame kf;
	public Thread kicker = null;
	public BufferedImage front1 = il.imageL("Images/bomber_front1.png");
	public BufferedImage back1 = il.imageL("Images/bomber_back1.png");
	public BufferedImage right1 = il.imageL("Images/bomber_right1.png");
	public BufferedImage left1 = il.imageL("Images/bomber_left1.png");
	public BufferedImage front2 = il.imageL("Images/bomber_front2.png");
	public BufferedImage back2 = il.imageL("Images/bomber_back2.png");
	public BufferedImage right2 = il.imageL("Images/bomber_right2.png");
	public BufferedImage left2 = il.imageL("Images/bomber_left2.png");
	/**
	 * 
	 * Klasa odpowiedzalna za animacje bombera podczas ruchu.
	 */
	public BomberAnim(KeyFrame kf,Bomber bomber)
	{
		this.bomber = bomber;
		this.kf = kf;
	}
	
	public void run()
	{
		while (kicker == Thread.currentThread()) {
		sleeep();
		if(kf.ruchFlag == 1)
    	{
    		if(kf.direction == 1)
	    	{
    			
    	        //moveLeft();
    			bomber.i = left1;
	    		bomber.ii= new ImageIcon(il.scaleI(left1,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.left;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = left2;
	    		bomber.ii= new ImageIcon(il.scaleI(left2,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.left;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    	}
	    	else if(kf.direction == 2)
	    	{
	    		
	    		//moveRight();
	    		bomber.i = right1;
	    		bomber.ii= new ImageIcon(il.scaleI(right1,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.right;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = right2;
	    		bomber.ii= new ImageIcon(il.scaleI(right2,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.right;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    	}
	    	else if(kf.direction == 3)
	    	{
	    		
	    		//moveUp();
	    		bomber.i = back1;
	    		bomber.ii= new ImageIcon(il.scaleI(back1,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.back;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = back2;
	    		bomber.ii= new ImageIcon(il.scaleI(back2,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.back;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    	}
	    	else if(kf.direction == 4)
	    	{
	    		
	    		//moveDown();
	    		bomber.i = front1;
	    		bomber.ii= new ImageIcon(il.scaleI(front1,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.front;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = front2;
	    		bomber.ii= new ImageIcon(il.scaleI(front2,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		bomber.i = bomber.front;
	    		bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
	    		bomber.l.setIcon(bomber.ii);
	    		sleeeep();
	    		
	    	}
    		
		}
		else
		{
			if(kf.direction == 1)
	    	{
    			
				bomber.i = bomber.left;
	    	}
	    	else if(kf.direction == 2)
	    	{
	    		
	    		bomber.i = bomber.right;
	    	}
	    	else if(kf.direction == 3)
	    	{
	    		
	    		bomber.i = bomber.back;
	    	}
	    	else if(kf.direction == 4)
	    	{
	    		
	    		bomber.i = bomber.front;
	    		
	    		
	    	}
			bomber.ii= new ImageIcon(il.scaleI(bomber.i,bomber.sizeX,bomber.sizeY));
    		bomber.l.setIcon(bomber.ii);
		}
		}
	}
	
	private void sleeep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	private void sleeeep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }

}
