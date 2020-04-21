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

import Game.Bomber;

public class KeyFrame extends JFrame implements KeyListener, Runnable{
	
	public Bomber player;
	public int czyRuch=0;
	
	public Thread ruch = null;
	
	public KeyFrame(String s)
	{
		super(s);
		addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
        	czyRuch = 1;
        	
        	player.direction = 1;
        	
        }

        if (key == KeyEvent.VK_D) {
        	czyRuch = 2;
        	
        	player.direction = 2;
        }

        if (key == KeyEvent.VK_W) {
        	czyRuch = 3;
        	
        	player.direction = 3;
        }

        if (key == KeyEvent.VK_S) {
        	czyRuch = 4;
        	
        	player.direction = 4;
        }
    }
	@Override
    public void keyReleased(KeyEvent e) {
    	czyRuch = 0;

        //System.out.print("Puszczono cos");
        
    }
	@Override
    public void keyTyped(KeyEvent e) {

       // System.out.print("Wpisano cos");

    }
	private void sleeep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (ruch == Thread.currentThread()) {
    		if(czyRuch == 1)
	    	{
    			player.l.setBounds(player.posX -player.speedX ,player.posY,player.l.getWidth(),player.l.getHeight());
            	player.posX = player.posX -player.speedX;	
	    	}
	    	else if(czyRuch == 2)
	    	{
	    		player.l.setBounds(player.posX + player.speedX,player.posY,player.l.getWidth(),player.l.getHeight());
	        	player.posX = player.posX + player.speedX;	
	    	}
	    	else if(czyRuch == 3)
	    	{
	    		player.l.setBounds(player.posX ,player.posY-player.speedY,player.l.getWidth(),player.l.getHeight());
	        	player.posY = player.posY - player.speedY;	
	    	}
	    	else if(czyRuch == 4)
	    	{
	    		player.l.setBounds(player.posX,player.posY+ player.speedY,player.l.getWidth(),player.l.getHeight());
	        	player.posY = player.posY + player.speedY;	
	    	}
	    	else
	    	{
	    		
	    	}
	    	
    		 
    		sleeep();
        }
    }
}
