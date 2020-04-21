
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import GUI.GraC;
import GUI.WynikiC;
import GUI.SterowanieC;
import GUI.WyjscieC;
import GUI.ImageLoader;



public class MenuC {
    public MenuC(){
        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 500;
        final int B_WIDTH =200;
        final int B_HIGH =50;
        final int B_X =250;
        final int B1_Y =225;
        final int B2_Y =280;
        final int B3_Y =335;
        final int B4_Y =390;
        		
    	
    	JFrame f = new JFrame("Bombowa Frajda");
        Button b1 = new Button("Rozpocznij Gre");
        Button b2 = new Button("Najlepsze Wyniki");
        Button b3 = new Button("Sterowanie");
        Button b4 = new Button("Wyjscie");
        ImageLoader il = new ImageLoader();
        
        b1.setBounds(B_X, B1_Y, B_WIDTH, B_HIGH);
        b2.setBounds(B_X, B2_Y, B_WIDTH, B_HIGH);
        b3.setBounds(B_X, B3_Y, B_WIDTH, B_HIGH);
        b4.setBounds(B_X, B4_Y, B_WIDTH, B_HIGH);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraC f1 = new GraC();
                f.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WynikiC f2 = new WynikiC();
                f.dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SterowanieC f3 = new SterowanieC();
                f.dispose();
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WyjscieC f4 = new WyjscieC(f);
            }
        });
        
        
        BufferedImage image = il.imageL("Images/title.jpg");
        ImageIcon imageIcon = new ImageIcon(image);
        f.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        f.add(jLabel);
        f.setContentPane(jLabel);
        f.pack();
        f.add(b1);
        f.add(b2); 
        f.add(b3); 
        f.add(b4); 
        
        
        
        //f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                WyjscieC f5 = new WyjscieC(f);
            }
        });
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int nWidth = f.getWidth();
                int nHeight = f.getHeight();
                float yScale = (float)nHeight/(float)FRAME_HEIGHT;
                float xScale = (float)nWidth/(float)FRAME_WIDTH;
                
                ImageIcon imageIcon = new ImageIcon(il.scaleI(image,(int)(700*xScale),(int)(500*yScale)));
                jLabel.setIcon(imageIcon);
                f.setContentPane(jLabel);
                b1.setBounds((int)(B_X*xScale), (int)(B1_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
                b2.setBounds((int)(B_X*xScale), (int)(B2_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
                b3.setBounds((int)(B_X*xScale), (int)(B3_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
                b4.setBounds((int)(B_X*xScale), (int)(B4_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
            
            }
        });
        
        f.setVisible(true);
        
        
    }
    
}
