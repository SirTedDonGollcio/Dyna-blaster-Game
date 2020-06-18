package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import GUI.ImageLoader;
import Game.Bomber;
import Parameters.Dimensions;

/*
 * klasa s³u¿aca do wyœwitlania ile ¿yæ aktualnie posiada gracz w trakcie rozgrywki. Jest wyœwietlana w oknie nieskalowalnego intefeejsu
 */
public class HealthBar extends JLabel implements Runnable{
	Dimensions p = new Dimensions();
	
	final int HPBAR_XY_MAIN = p.HPBAR_XY_MAIN;
	final int HPBAR_XS_MAIN= p.HPBAR_XS_MAIN;
	final int HPBAR_SIZE= p.HPBAR_SIZE;
	final int HPBAR_X_1= p.HPBAR_X_1;
	final int HPBAR_X_2= p.HPBAR_X_2;
	final int HPBAR_X_3= p.HPBAR_X_3;
	final int HPBAR_X_4= p.HPBAR_X_4;
	final int HPBAR_X_5= p.HPBAR_X_5;
	final int HPBAR_Y= p.HPBAR_Y;
	
	public Bomber bomber;
	public Thread kicker = null;
	JLabel h1 = new JLabel();
    JLabel h2 = new JLabel();
    JLabel h3 = new JLabel();
    JLabel h4 = new JLabel();
    JLabel h5 = new JLabel();
    JLabel hb = new JLabel();
	ImageLoader il = new ImageLoader();
    
	public boolean alive = true;
	JLabel healthBar(Bomber bomber) {
		this.bomber = bomber;
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		BufferedImage ib1 = il.imageL("Images/heart.png");
        ImageIcon i1 = new ImageIcon(ib1);
        hb.setBounds(HPBAR_XY_MAIN, HPBAR_XY_MAIN, HPBAR_XS_MAIN, HPBAR_SIZE);
        h1.setBounds(HPBAR_X_1,HPBAR_Y,HPBAR_SIZE,HPBAR_SIZE);
        h2.setBounds(HPBAR_X_2,HPBAR_Y,HPBAR_SIZE,HPBAR_SIZE);
        h3.setBounds(HPBAR_X_3,HPBAR_Y,HPBAR_SIZE,HPBAR_SIZE);
        h4.setBounds(HPBAR_X_4,HPBAR_Y,HPBAR_SIZE,HPBAR_SIZE);
        h5.setBounds(HPBAR_X_5,HPBAR_Y,HPBAR_SIZE,HPBAR_SIZE);
        h1.setIcon(i1);
        h2.setIcon(i1);
        h3.setIcon(i1);
        h4.setIcon(i1);
        h5.setIcon(i1);
        hb.add(h1);
        hb.add(h2);
        hb.add(h3);
        hb.add(h4);
        hb.add(h5);
        check();
        
		
	        };
		});
		return hb;
	}
	
	private void sleeep() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (kicker == Thread.currentThread()) {
    		sleeep();
    		check();
        }
    }
	public void check()
	{
		if(bomber.iloscZycia == 5) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(true);
        	h5.setVisible(true);
        }
        else if(bomber.iloscZycia == 4) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(true);
        	h5.setVisible(false);
        }
        else if(bomber.iloscZycia == 3) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(false);
        	h5.setVisible(false);
        }
        else if(bomber.iloscZycia == 2) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(false);
        	h4.setVisible(false);
        	h5.setVisible(false);
        }
        else if(bomber.iloscZycia == 1) {
        	h1.setVisible(true);
        	h2.setVisible(false);
        	h3.setVisible(false);
        	h4.setVisible(false);
        	h5.setVisible(false);
        }
        else {
        	h1.setVisible(false);
        	h2.setVisible(false);
        	h3.setVisible(false);
        	h4.setVisible(false);
        	h5.setVisible(false);
        	alive = false;
        }
	}
}
