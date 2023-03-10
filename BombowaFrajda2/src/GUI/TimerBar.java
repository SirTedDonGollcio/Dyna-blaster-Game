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
import Client.Stoper;

public class TimerBar extends JLabel implements Runnable{
	
Dimensions p = new Dimensions();
	
	final int TIMEBAR_X_MAIN=p.TIMEBAR_X_MAIN;
	final int TIMEBAR_Y_MAIN=p.TIMEBAR_Y_MAIN;
	final int TIMEBAR_XS_MAIN=p.TIMEBAR_XS_MAIN;
	final int TIMEBAR_YS_MAIN=p.TIMEBAR_YS_MAIN;
	final int TIMEBAR_X_ST=p.TIMEBAR_X_ST;
	final int TIMEBAR_Y_ST=p.TIMEBAR_Y_ST;
	final int TIMEBAR_XS_ST=p.TIMEBAR_XS_ST;
	final int TIMEBAR_YS_ST=p.TIMEBAR_YS_ST;
	final int TIMEBAR_X_VAR=p.TIMEBAR_X_VAR;
	final int TIMEBAR_Y_VAR=p.TIMEBAR_Y_VAR;
	final int TIMEBAR_XS_VAR=p.TIMEBAR_XS_VAR;
	final int TIMEBAR_YS_VAR=p.TIMEBAR_YS_VAR;
	
	public Bomber bomber;
	public Thread kicker = null;
	JLabel stable = new JLabel("Time: ");
    JLabel variable = new JLabel("0");
    JLabel tb = new JLabel();
    Stoper stoper = new Stoper();
     /*
      * Klasa odpowiedzialna za wyswietlanie czasu rozgrywki na ekranie rozgrywki na nieskalowalnym pasku interfejsu   
      */
	JLabel timerBar(Bomber bomber) {
		this.bomber = bomber;
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	    stable.setFont(new Font("System", Font.PLAIN, 48));
	    stable.setForeground(Color.WHITE);
	    variable.setFont(new Font("System", Font.PLAIN, 34));
	    variable.setForeground(Color.WHITE);
	    tb.setBounds(TIMEBAR_X_MAIN,TIMEBAR_Y_MAIN,TIMEBAR_XS_MAIN,TIMEBAR_YS_MAIN);
        stable.setBounds(TIMEBAR_X_ST,TIMEBAR_Y_ST , TIMEBAR_XS_ST, TIMEBAR_YS_ST);
        variable.setBounds(TIMEBAR_X_VAR,TIMEBAR_Y_VAR,TIMEBAR_XS_VAR,TIMEBAR_YS_VAR);
        stable.setText("Time: ");
        tb.add(stable);
        tb.add(variable);
        stable.setVisible(true);
        check();
        
		
	        };
		});
		return tb;
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
		variable.setText(dodatkoweZero(stoper.godziny)+String.valueOf(stoper.godziny)+":"+dodatkoweZero(stoper.minuty)+String.valueOf(stoper.minuty)+":"+dodatkoweZero(stoper.sekundy)+String.valueOf(stoper.sekundy));
		variable.setVisible(true);
	}
	public String dodatkoweZero(int test)
	{
		if(test<10)
		{
			return "0";
		}
		else
		{
			return"";
		}
	}
}
