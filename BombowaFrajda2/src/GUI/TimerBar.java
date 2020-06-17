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
import Client.Stoper;

public class TimerBar extends JLabel implements Runnable{
	public Bomber bomber;
	public Thread kicker = null;
	JLabel stable = new JLabel("Time: ");
    JLabel variable = new JLabel("0");
    JLabel tb = new JLabel();
    Stoper stoper = new Stoper();
        
	JLabel timerBar(Bomber bomber) {
		this.bomber = bomber;
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	    stable.setFont(new Font("System", Font.PLAIN, 48));
	    stable.setForeground(Color.WHITE);
	    variable.setFont(new Font("System", Font.PLAIN, 34));
	    variable.setForeground(Color.WHITE);
	    tb.setBounds(370,80,350,50);
        stable.setBounds(0, 0, 170, 50);
        variable.setBounds(125,5,150,50);
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
