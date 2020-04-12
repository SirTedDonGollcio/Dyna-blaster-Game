
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class WyjscieC {
    public WyjscieC(Frame fx){
        JFrame f = new JFrame("Wyjscie?");
        Label l = new Label("Czy na pewno chcesz opuscic gre?");
        Button b1 = new Button("Tak");
        Button b2 = new Button("Nie");
        
        l.setBounds(45, 50, 370, 80);
        
        b1.setBounds(50, 150, 100, 50);
        b2.setBounds(250, 150, 100, 50);
        l.setFont(new Font("Lucida",Font.PLAIN,21));
        
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fx.dispose();
                f.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        
        f.add(b1);
        f.add(b2);
        f.add(l);
        f.setSize(400, 300);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
            }
        });
    }
    
}
