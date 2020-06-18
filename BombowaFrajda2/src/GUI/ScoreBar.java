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

public class ScoreBar extends JLabel implements Runnable{
	
	Dimensions p = new Dimensions();
	
	final int SCOREBAR_X_MAIN=p.SCOREBAR_X_MAIN;
	final int SCOREBAR_Y_MAIN=p.SCOREBAR_Y_MAIN;
	final int SCOREBAR_XS_MAIN=p.SCOREBAR_XS_MAIN;
	final int SCOREBAR_YS_MAIN=p.SCOREBAR_YS_MAIN;
	final int SCOREBAR_X_ST=p.SCOREBAR_X_ST;
	final int SCOREBAR_Y_ST=p.SCOREBAR_Y_ST;
	final int SCOREBAR_XS_ST=p.SCOREBAR_XS_ST;
	final int SCOREBAR_YS_ST=p.SCOREBAR_YS_ST;
	final int SCOREBAR_X_VAR=p.SCOREBAR_X_VAR;
	final int SCOREBAR_Y_VAR=p.SCOREBAR_Y_VAR;
	final int SCOREBAR_XS_VAR=p.SCOREBAR_XS_VAR;
	final int SCOREBAR_YS_VAR=p.SCOREBAR_YS_VAR;
	
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
	    sb.setBounds(SCOREBAR_X_MAIN,SCOREBAR_Y_MAIN,SCOREBAR_XS_MAIN,SCOREBAR_YS_MAIN);
        stable.setBounds(SCOREBAR_X_ST,SCOREBAR_Y_ST,SCOREBAR_XS_ST,SCOREBAR_YS_ST);
        variable.setBounds(SCOREBAR_X_VAR,SCOREBAR_Y_VAR,SCOREBAR_XS_VAR,SCOREBAR_YS_VAR);
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
