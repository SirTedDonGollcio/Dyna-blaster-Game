package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import GUI.ImageLoader;

public class HealthBar extends JLabel{
	public int iloscZycia = 5;
	public boolean alive = true;
	JLabel healthBar() {
		JLabel hb = new JLabel();
		ImageLoader il = new ImageLoader();
		
		BufferedImage ib1 = il.imageL("Images/heart.png");
        ImageIcon i1 = new ImageIcon(ib1);
        
        hb.setBounds(20, 20, 335, 55);
        JLabel h1 = new JLabel();
        JLabel h2 = new JLabel();
        JLabel h3 = new JLabel();
        JLabel h4 = new JLabel();
        JLabel h5 = new JLabel();
        
        h1.setBounds(10,0,55,55);
        h2.setBounds(75,0,55,55);
        h3.setBounds(140,0,55,55);
        h4.setBounds(205,0,55,55);
        h5.setBounds(270,0,55,55);
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
        
        if(iloscZycia == 5) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(true);
        	h5.setVisible(true);
        }
        else if(iloscZycia == 4) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(true);
        	h5.setVisible(false);
        }
        else if(iloscZycia == 3) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(true);
        	h4.setVisible(false);
        	h5.setVisible(false);
        }
        else if(iloscZycia == 2) {
        	h1.setVisible(true);
        	h2.setVisible(true);
        	h3.setVisible(false);
        	h4.setVisible(false);
        	h5.setVisible(false);
        }
        else if(iloscZycia == 1) {
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
		
		
		return hb;
		
	}
	
}
