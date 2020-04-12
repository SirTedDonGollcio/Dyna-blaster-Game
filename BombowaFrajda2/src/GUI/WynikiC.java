
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class WynikiC {
    public WynikiC(){
        JFrame f = new JFrame("Tablica Rankingowa");
        
        Label l = new Label("Tablica najlepszych wyników");
        l.setFont(new Font("Lucida",Font.PLAIN,33));
        
        l.setBounds(140, -50, 600, 200);
        //l.setLocation(0, 0);
        f.add(l);
        
        String[] kolumny = new String[] {
                "Id", "Nick", "Punkty", "Czas", "Etap"
        };
        
        Object[][] data = new Object[][] {
            {1, "John", 40, "1:03:00", 5 },
            {2, "Rambo", 30, "1:51:00", 5 },
            {3, "Zorro", 20, "1:43:00", 3 },
        };
        JTable t = new JTable(data, kolumny);
        
        JScrollPane scroll = new JScrollPane(t);
        scroll.setBounds(145, 180, 400, 200);
        f.add(scroll);
        
        
        f.setSize(700, 500);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                MenuC f1 = new MenuC();
            }
        });
    }
    
}
