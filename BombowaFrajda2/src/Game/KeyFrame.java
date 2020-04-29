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
	public int direction = 4;
	public Thread ruch = null;
	public int ruchFlag;
	public JLabel game;
	public KeyFrame(String s)
	{
		super(s);
		addKeyListener(this);
		ruchFlag=0;
    	
	}
	@Override
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
        	
        	player.direction = 1;
        	direction = 1;
        	ruchFlag=1;
        }

        if (key == KeyEvent.VK_D) {
        	
        	player.direction = 2;
        	direction = 2;
        	ruchFlag=1;
        }

        if (key == KeyEvent.VK_W) {
        	
        	player.direction = 3;
        	direction = 3;
        	ruchFlag=1;
        	

        }

        if (key == KeyEvent.VK_S) {
        	
        	player.direction = 4;
        	direction = 4;
        	ruchFlag=1;

        }
        if (key == KeyEvent.VK_SPACE) {
        	
        	if(player.currentBombs<player.iloscBomb)
        	{
        	Bomb bomb = new Bomb(player.posX,player.posY,player,game);
        	SwingUtilities.invokeLater(new Runnable() {
    	        @Override
    	        public void run() {
        	game.add(bomb.l);
    	        };
        	});
        	(bomb.kicker= new Thread(bomb)).start();
        	player.bombs[player.currentBombs] = bomb;
        	player.currentBombs++;
        	}
        }
    }
	@Override
    public void keyReleased(KeyEvent e) {

        ruchFlag=0;
        
    }
	@Override
    public void keyTyped(KeyEvent e) {

        //System.out.print("Wpisano cos");

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
    		move();
    		sleeep();
	    	
        }
    }
	
	public void moveLeft()
	{
		player.l.setBounds(player.posX -player.speedX ,player.posY,player.l.getWidth(),player.l.getHeight());
        player.posX = player.posX -player.speedX;
	}
	public void moveRight()
	{
		player.l.setBounds(player.posX + player.speedX,player.posY,player.l.getWidth(),player.l.getHeight());
        player.posX = player.posX + player.speedX;
	}
	public void moveUp()
	{
		player.l.setBounds(player.posX ,player.posY-player.speedY,player.l.getWidth(),player.l.getHeight());
        player.posY = player.posY - player.speedY;
	}
	public void moveDown()
	{
		player.l.setBounds(player.posX,player.posY+ player.speedY,player.l.getWidth(),player.l.getHeight());
        player.posY = player.posY + player.speedY;
	}
	
	public void move()
	{
		if(ruchFlag == 1)
    	{
    		if(direction == 1&&!player.isColL)
	    	{
    			
    	        moveLeft();
	    	}
	    	else if(direction == 2&&!player.isColR)
	    	{
	    		
	    		moveRight();
	    	}
	    	else if(direction == 3&&!player.isColU)
	    	{
	    		
	    		moveUp();
	    	}
	    	else if(direction == 4&&!player.isColD)
	    	{
	    		
	    		moveDown();
	    	}
    		
		}
	}
}
