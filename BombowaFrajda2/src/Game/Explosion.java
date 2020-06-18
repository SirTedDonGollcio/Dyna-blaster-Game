package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import Game.ExplosionUp;

public class Explosion extends ObjectCreator implements Runnable{
	
	public Thread kicker = null;
	public Bomber player;
	public JLabel game;
	public ExplosionUp eUp;
	public ExplosionDown eDown;
	public ExplosionLeft eLeft;
	public ExplosionRight eRight;
	public int zmniejsznikX;
	public int zmniejsznikY;
	/*
	 * Klasa odpowiadaj¹ca za rozrastanie siê poszczególnych odnóg wybuchu, jest jego centrum i od niego wybuch rozrasta siê w góre dó³ prawo i lewo
	 */
	public Explosion(int x,int y,Bomber player,JLabel game)
	{
		zmniejsznikX = player.bombSizeX/5;
		zmniejsznikY = player.bombSizeY/5;

		this.game = game;
		this.player = player;
		posX=x+zmniejsznikX;
		posY=y+zmniejsznikY;
		sizeX=player.bombSizeX-2*zmniejsznikX;
		sizeY=player.bombSizeY-2*zmniejsznikY;
		
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	i = il.imageL("Images/explosion.png");
	    		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
	    		
	    	    eUp = new ExplosionUp(posX,posY-1,sizeX,1,player,game);
	    	    (eUp.kicker= new Thread(eUp)).start();
	    	    eDown = new ExplosionDown(posX,posY+sizeY,sizeX,1,player,game.getHeight(),game);
	    	    (eDown.kicker= new Thread(eDown)).start();
	    	    eLeft = new ExplosionLeft(posX-1,posY,1,sizeY,player,game);
	    	    (eLeft.kicker= new Thread(eLeft)).start();
	    	    eRight = new ExplosionRight(posX+sizeX,posY,1,sizeY,player,game.getWidth(),game);
	    	    (eRight.kicker= new Thread(eRight)).start();
	        	
	    	    l.setIcon(ii);
	    	    l.setBounds(posX,posY,sizeX,sizeY);
	    	    game.add(l);    	
	    game.add(eUp.l);
	    game.add(eDown.l);
	    game.add(eLeft.l);
	    game.add(eRight.l);
	        };
	    });
	}
	
	private void sleeep() {
        try {
            Thread.sleep(15);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	private void sleeeep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run()
	{
		sleeep();
		while(!(eUp.isEnded&&eDown.isEnded&&eLeft.isEnded&&eRight.isEnded))
		{
			sleeep();
			//System.out.print("Lol\n" + eUp.isEnded + eDown.isEnded + eLeft.isEnded + eRight.isEnded + "\n");
		};
		//if(eUp.isEnded&&eDown.isEnded&&eLeft.isEnded&&eRight.isEnded)
		//{
			//System.out.print("Lol");
			sleeeep();
			l.setVisible(false);
			eUp.l.setVisible(false);
			eDown.l.setVisible(false);
			eLeft.l.setVisible(false);
			eRight.l.setVisible(false);
			eUp.kicker = null;
			eDown.kicker = null;
			eLeft.kicker = null;
			eRight.kicker = null;
			kicker = null;
		//}
	}
}