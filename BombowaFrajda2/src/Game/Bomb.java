package Game;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;


public class Bomb extends ObjectCreator implements Runnable{
	
	public Thread kicker = null;
	public Bomber player;
	public JLabel game;
	public Explosion explosion;
	public int lX=645;
	public int lY=645;
	/*
	 * Klasa opisuj¹ca stawiane przez gracza bomby, które po okreœlonym czasie eksploduj¹
	 */
	public Bomb(int x, int y,Bomber player,JLabel game) {
		this.game = game;
		this.player = player;
		posX=x;
		posY=y;
		sizeX=player.bombSizeX;
		sizeY=player.bombSizeY;
		i = il.imageL("Images/bomb.png");
		
		//ii = new ImageIcon(i);
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
	    l.setBounds(x,y,sizeX,sizeY);
	    
	}
	
	private void sleeep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run()
	{
		while (kicker == Thread.currentThread()) {
		sleeep();
		l.setVisible(false);
		explosion = new Explosion(posX,posY,player,game);
	    (explosion.kicker= new Thread(explosion)).start();
		kicker = null;
		//System.out.print(player.currentBombs);
		player.currentBombs--;
		//System.out.print(player.currentBombs);
		for(int iter=0;iter<player.currentBombs;iter++)
		{
			player.bombs[iter]=player.bombs[iter+1];
		}
		
		}
	}
}
