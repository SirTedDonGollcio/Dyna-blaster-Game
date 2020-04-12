
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
        JFrame f = new JFrame("Bombowa Frajda");
        Button b1 = new Button("Rozpocznij Gre");
        Button b2 = new Button("Najlepsze Wyniki");
        Button b3 = new Button("Sterowanie");
        Button b4 = new Button("Wyjscie");
        ImageLoader il = new ImageLoader();
        
        b1.setBounds(250, 225, 200, 50);
        b2.setBounds(250, 280, 200, 50);
        b3.setBounds(250, 335, 200, 50);
        b4.setBounds(250, 390, 200, 50);
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
        
        
        
        ImageIcon imageIcon = new 		
        ImageIcon(il.imageL("Images/title.jpg"));
        f.setSize(700,500);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jLabel.setBounds(200, 0,300,300);
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
        
        f.setVisible(true);
        
        
    }
    
}
