
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class GraC {
    public GraC(){
        JFrame f = new JFrame("Bombowa Frajda");
        f.setSize(645, 810);
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
