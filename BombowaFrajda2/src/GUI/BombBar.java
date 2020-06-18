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
 * klasa s³u¿aca do wyœwitlania ile bomb na raz aktualnie posiada gracz w trakcie rozgrywki. Jest wyœwietlana w oknie nieskalowalnego intefeejsu
 */
public class BombBar extends JLabel implements Runnable{
	
	Dimensions p = new Dimensions();
	
	final int BOMBBAR_X_MAIN = p.BOMBBAR_X_MAIN;
	final int BOMBBAR_Y_MAIN = p.BOMBBAR_Y_MAIN;
	final int BOMBBAR_SX_MAIN = p.BOMBBAR_SX_MAIN;
	final int BOMBBAR_SY_MAIN = p.BOMBBAR_SY_MAIN;
	final int BOMBBAR_X_B1 = p.BOMBBAR_X_B1;
	final int BOMBBAR_Y_B1 = p.BOMBBAR_Y_B1;
	final int BOMBBAR_SX_B1 = p.BOMBBAR_SX_B1;
	final int BOMBBAR_SY_B1 = p.BOMBBAR_SY_B1;
	final int BOMBBAR_X_B2 = p.BOMBBAR_X_B2;
	final int BOMBBAR_Y_B2 = p.BOMBBAR_Y_B2;
	final int BOMBBAR_SX_B2 = p.BOMBBAR_SX_B2;
	final int BOMBBAR_SY_B2 = p.BOMBBAR_SY_B2;
	final int BOMBBAR_X_B3 = p.BOMBBAR_X_B3;
	final int BOMBBAR_Y_B3 = p.BOMBBAR_Y_B3;
	final int BOMBBAR_SX_B3 = p.BOMBBAR_SX_B3;
	final int BOMBBAR_SY_B3 = p.BOMBBAR_SY_B3;
	final int BOMBBAR_X_B4 = p.BOMBBAR_X_B4;
	final int BOMBBAR_Y_B4 = p.BOMBBAR_Y_B4;
	final int BOMBBAR_SX_B4 = p.BOMBBAR_SX_B4;
	final int BOMBBAR_SY_B4 = p.BOMBBAR_SY_B4;
	final int BOMBBAR_X_B5 = p.BOMBBAR_X_B5;
	final int BOMBBAR_Y_B5 = p.BOMBBAR_Y_B5;
	final int BOMBBAR_SX_B5 = p.BOMBBAR_SX_B5;
	final int BOMBBAR_SY_B5 = p.BOMBBAR_SY_B5;
	
	
	
	JLabel bb = new JLabel();
	ImageLoader il = new ImageLoader();
	JLabel b1 = new JLabel();
    JLabel b2 = new JLabel();
    JLabel b3 = new JLabel();
    JLabel b4 = new JLabel();
    JLabel b5 = new JLabel();
    public Thread kicker = null;
    public Bomber bomber;
    
	JLabel bombBar(Bomber bomber) {
		
		this.bomber = bomber;
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		BufferedImage ib1 = il.imageL("Images/bomb.png");
        ImageIcon i1 = new ImageIcon(ib1);
        bb.setBounds(BOMBBAR_X_MAIN,BOMBBAR_Y_MAIN,BOMBBAR_SX_MAIN,BOMBBAR_SY_MAIN);
        b1.setBounds(BOMBBAR_X_B1,BOMBBAR_Y_B1,BOMBBAR_SX_B1,BOMBBAR_SY_B1);
        b2.setBounds(BOMBBAR_X_B2,BOMBBAR_Y_B2,BOMBBAR_SX_B2,BOMBBAR_SY_B2);
        b3.setBounds(BOMBBAR_X_B3,BOMBBAR_Y_B3,BOMBBAR_SX_B3,BOMBBAR_SY_B3);
        b4.setBounds(BOMBBAR_X_B4,BOMBBAR_Y_B4,BOMBBAR_SX_B4,BOMBBAR_SY_B4);
        b5.setBounds(BOMBBAR_X_B5,BOMBBAR_Y_B5,BOMBBAR_SX_B5,BOMBBAR_SY_B5);
        b1.setIcon(i1);
        b2.setIcon(i1);
        b3.setIcon(i1);
        b4.setIcon(i1);
        b5.setIcon(i1);
        bb.add(b1);
        bb.add(b2);
        bb.add(b3);
        bb.add(b4);
        bb.add(b5);
        
       
		check();
	        };
		});
		return bb;
		
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
		 if(bomber.iloscBomb == 5) {
	        	b1.setVisible(true);
	        	b2.setVisible(true);
	        	b3.setVisible(true);
	        	b4.setVisible(true);
	        	b5.setVisible(true);
	        }
	        else if(bomber.iloscBomb == 4) {
	        	b1.setVisible(true);
	        	b2.setVisible(true);
	        	b3.setVisible(true);
	        	b4.setVisible(true);
	        	b5.setVisible(false);
	        }
	        else if(bomber.iloscBomb == 3) {
	        	b1.setVisible(true);
	        	b2.setVisible(true);
	        	b3.setVisible(true);
	        	b4.setVisible(false);
	        	b5.setVisible(false);
	        }
	        else if(bomber.iloscBomb == 2) {
	        	b1.setVisible(true);
	        	b2.setVisible(true);
	        	b3.setVisible(false);
	        	b4.setVisible(false);
	        	b5.setVisible(false);
	        }
	        else if(bomber.iloscBomb == 1) {
	        	b1.setVisible(true);
	        	b2.setVisible(false);
	        	b3.setVisible(false);
	        	b4.setVisible(false);
	        	b5.setVisible(false);
	        }
	        else {
	        	b1.setVisible(false);
	        	b2.setVisible(false);
	        	b3.setVisible(false);
	        	b4.setVisible(false);
	        	b5.setVisible(false);
	        }
	}
}
