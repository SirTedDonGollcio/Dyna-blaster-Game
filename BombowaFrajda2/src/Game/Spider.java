package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import Game.ObjectCreator;

public class Spider  extends ObjectCreator implements Runnable{
	public Wall walls[];
	public FragileWall fragileWalls[];
	public Spider spiders[];
	public Bomber player;
	
	public Thread kicker = null;
	public BufferedImage left = il.imageL("Images/spiderL.png");
	public BufferedImage right = il.imageL("Images/spiderR.png");
	public int speed=3;
	public boolean haveKey=false;
	/*
	 * Klasa opisuj¹ca paj¹ki, które to poruszaj¹ siê zawsze w lewo i prawo dopóki nie odbij¹ siê od jakiejœ sta³ej powiezchni. Dodtyk paj¹ka skutkuje zmniejszeniem siê zycia gracza
	 */
	public Spider(int x, int y) {
		tempX=x;
		tempY=y;
		posX=x*55;
		posY=y*55;
		sizeX=55;
		sizeY=55;
		i = left;
		ii = new ImageIcon(i);
		l.setIcon(ii);
	    l.setBounds(x*55,y*55,55,55);
	}
	
	private void sleeep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run()
	{
		while (kicker == Thread.currentThread()) {
			sleeep();
			boolean x=isColisionLeft();
			while(!x)
    		{
				
		        l.setBounds(posX -speed*sizeX/55 ,posY,l.getWidth(),l.getHeight());
		        posX = posX -speed*sizeX/55;
		        x=isColisionLeft();
		        sleeep();
			        
    		}
			
			i = right;
			ii = new ImageIcon(il.scaleI(i, l.getWidth(), l.getHeight()));
			l.setIcon(ii); 
			sleeep();
		        
			
			x=isColisionRight();
			while(!x)
    		{
				
				l.setBounds(posX +speed*sizeX/55 ,posY,l.getWidth(),l.getHeight());
		        posX = posX +speed*sizeX/55;
				sleeep();
		        x=isColisionRight();
			       
    		}
			
			i = left;
			ii = new ImageIcon(il.scaleI(i, l.getWidth(), l.getHeight()));
			l.setIcon(ii); 
		        
        }
	}
	public boolean isColisionLeft()
	{
		boolean b = false;
		
		for(int iter=0;iter<walls.length;iter++)
		{
			if((posX-(walls[iter].posX+walls[iter].sizeX)<=0)&&(posX-(walls[iter].posX+walls[iter].sizeX)>(-10*sizeX/55))&&(posY-walls[iter].posY<10*sizeY/55)&&(posY-walls[iter].posY>-10*sizeY/55))
			{
				b = true;
				//System.out.print("1scian\n");
			}
		}
		for(int iter=0;iter<fragileWalls.length;iter++)
		{
			if((posX-(fragileWalls[iter].posX+fragileWalls[iter].sizeX)<=0)&&(posX-(fragileWalls[iter].posX+fragileWalls[iter].sizeX)>(-10*sizeX/55))&&(posY-fragileWalls[iter].posY<10*sizeY/55)&&(posY-fragileWalls[iter].posY>(-10*sizeY/55)))
			{
				b = true;
				//System.out.print("1kam\n");
			}
		}
		for(int iter=0;iter<spiders.length;iter++)
		{
			if((posX-(spiders[iter].posX+spiders[iter].sizeX)<=0)&&(posX-(spiders[iter].posX+spiders[iter].sizeX)>(-10*sizeX/55))&&(posY-spiders[iter].posY<10*sizeY/55)&&(posY-spiders[iter].posY>-10*sizeY/55)&&(posX!=spiders[iter].posX)&&(posY!=spiders[iter].posY))
			{
				b = true;
				//System.out.print("1paj\n");
			}
		}
		if((posX-player.posX-player.sizeX<=0)&&(posX-player.posX-player.sizeX>-10*sizeX/55)&&(posY-player.posY<sizeY)&&(posY-player.posY>-sizeY))
		{
			b=true;
			//System.out.print("1gra\n");
		}
		if(posX<=0)
		{
			b=true;
			//System.out.print("1end\n");
		}
		return b;
	}
	public boolean isColisionRight()
	{
		boolean b = false;
		
		for(int iter=0;iter<walls.length;iter++)
		{
			if(((walls[iter].posX)-(posX+sizeX)<=0)&&((walls[iter].posX)-(posX+sizeX)>(-10*sizeX/55))&&(posY-walls[iter].posY<10*sizeY/55)&&(posY-walls[iter].posY>-10*sizeY/55))
			{
				b = true;
				//System.out.print("2scian\n");
			}
		}
		for(int iter=0;iter<fragileWalls.length;iter++)
		{
			if(((fragileWalls[iter].posX)-(posX+sizeX)<=0)&&((fragileWalls[iter].posX)-(posX+sizeX)>(-10*sizeX/55))&&(posY-fragileWalls[iter].posY<10*sizeY/55)&&(posY-fragileWalls[iter].posY>-10*sizeY/55))
			{
				b = true;
				//System.out.print("2kam\n");
			}
		}
		for(int iter=0;iter<spiders.length;iter++)
		{
			if(((spiders[iter].posX)-(posX+sizeX)<=0)&&((spiders[iter].posX)-(posX+sizeX)>(-10*sizeX/55))&&(posY-spiders[iter].posY<10*sizeY/55)&&(posY-spiders[iter].posY>-10*sizeY/55)&&(posX!=spiders[iter].posX)&&(posY!=spiders[iter].posY))
			{
				b = true;
				//System.out.print("2paj\n");
			}
		}
		if(player.posX-(posX+sizeX)<=0&&((player.posX-(posX+sizeX))>-10*sizeX/55)&&(posY-player.posY<=sizeY)&&(posY-player.posY>-sizeY))
		{
			b=true;
			//System.out.print("2gracz\n");
		}
		if(posX+sizeX>=645*sizeX/55)
		{
			b=true;
			//System.out.print("2end\n");
		}
		return b;
	}
	@Override
	public String toString() {
		return "X:" + tempX + "\nY: " + tempY + "\nPosition x: " + posX + "\nPosiotion y: " + posY;
	}
}
