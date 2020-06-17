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

public class ScoreBar extends JLabel implements Runnable{
	public Bomber bomber;
	public Thread kicker = null;
	JLabel stable = new JLabel("Score: ");
    JLabel variable = new JLabel("0");
    JLabel sb = new JLabel();
        
	JLabel scoreBar(Bomber bomber) {
		this.bomber = bomber;
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	    stable.setFont(new Font("System", Font.PLAIN, 48));
	    stable.setForeground(Color.WHITE);
	    variable.setFont(new Font("System", Font.PLAIN, 48));
	    variable.setForeground(Color.WHITE);
	    sb.setBounds(370,20,350,100);
        stable.setBounds(0, 0, 170, 50);
        variable.setBounds(150,0,150,50);
        stable.setText("Score: ");
        sb.add(stable);
        sb.add(variable);
        stable.setVisible(true);
        check();
        
		
	        };
		});
		return sb;
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
		variable.setText(String.valueOf(bomber.iloscPunktow));
		variable.setVisible(true);
	}
}
