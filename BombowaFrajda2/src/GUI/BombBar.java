package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import GUI.ImageLoader;

public class BombBar extends JLabel{
	public int iloscBomb = 1;
	
	JLabel bombBar() {
		JLabel bb = new JLabel();
		ImageLoader il = new ImageLoader();
		
		BufferedImage ib1 = il.imageL("Images/bomb.png");
        ImageIcon i1 = new ImageIcon(ib1);
        
        bb.setBounds(20, 95, 335, 55);
        JLabel b1 = new JLabel();
        JLabel b2 = new JLabel();
        JLabel b3 = new JLabel();
        JLabel b4 = new JLabel();
        JLabel b5 = new JLabel();
        
        b1.setBounds(10,0,55,55);
        b2.setBounds(75,0,55,55);
        b3.setBounds(140,0,55,55);
        b4.setBounds(205,0,55,55);
        b5.setBounds(270,0,55,55);
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
        
        if(iloscBomb == 5) {
        	b1.setVisible(true);
        	b2.setVisible(true);
        	b3.setVisible(true);
        	b4.setVisible(true);
        	b5.setVisible(true);
        }
        else if(iloscBomb == 4) {
        	b1.setVisible(true);
        	b2.setVisible(true);
        	b3.setVisible(true);
        	b4.setVisible(true);
        	b5.setVisible(false);
        }
        else if(iloscBomb == 3) {
        	b1.setVisible(true);
        	b2.setVisible(true);
        	b3.setVisible(true);
        	b4.setVisible(false);
        	b5.setVisible(false);
        }
        else if(iloscBomb == 2) {
        	b1.setVisible(true);
        	b2.setVisible(true);
        	b3.setVisible(false);
        	b4.setVisible(false);
        	b5.setVisible(false);
        }
        else if(iloscBomb == 1) {
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
		
		
		return bb;
		
	}
	
}
