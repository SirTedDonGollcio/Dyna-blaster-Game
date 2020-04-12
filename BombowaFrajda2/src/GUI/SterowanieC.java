
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class SterowanieC {
    public SterowanieC(){
        JFrame f = new JFrame("Sterowanie");
        ImageLoader il = new ImageLoader();
        
        f.setSize(700, 500);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        
        
        ImageIcon i1 = new ImageIcon(il.imageL("Images/bomber_front.png"));
        JLabel lFront = new JLabel();
        lFront.setIcon(i1);

        ImageIcon i2 = new ImageIcon(il.imageL("Images/bomber_back.png"));
        JLabel lBack = new JLabel();
        lBack.setIcon(i2);
        
        ImageIcon i3 = new ImageIcon(il.imageL("Images/bomber_right.png"));
        JLabel lRight = new JLabel();
        lRight.setIcon(i3);

        ImageIcon i4 = new ImageIcon(il.imageL("Images/bomber_left.png"));
        JLabel lLeft = new JLabel();
        lLeft.setIcon(i4);
        
        ImageIcon i5 = new ImageIcon(il.imageL("Images/w.png"));
        JLabel lW = new JLabel();
        lW.setIcon(i5);
        
        ImageIcon i6 = new ImageIcon(il.imageL("Images/a.png"));
        JLabel lA = new JLabel();
        lA.setIcon(i6);
        
        ImageIcon i7 = new ImageIcon(il.imageL("Images/s.png"));
        JLabel lS = new JLabel();
        lS.setIcon(i7);
        
        ImageIcon i8 = new ImageIcon(il.imageL("Images/d.png"));
        JLabel lD = new JLabel();
        lD.setIcon(i8);
        
        ImageIcon i9 = new ImageIcon(il.imageL("Images/space.png"));
        JLabel lSpace = new JLabel();
        lSpace.setIcon(i9);
        
        ImageIcon i10 = new ImageIcon(il.imageL("Images/esc.png"));
        JLabel lEsc = new JLabel();
        lEsc.setIcon(i10);
        
        ImageIcon i11 = new ImageIcon(il.imageL("Images/bomb.png"));
        JLabel lBomb = new JLabel();
        lBomb.setIcon(i11);
        
        ImageIcon i12 = new ImageIcon(il.imageL("Images/q.png"));
        JLabel lQ = new JLabel();
        lQ.setIcon(i12);
        
        ImageIcon i13 = new ImageIcon(il.imageL("Images/e.png"));
        JLabel lE = new JLabel();
        lE.setIcon(i13);
        
        lFront.setBounds(180, 296,55,55);
        lBack.setBounds(181, 116,55,55);
        lLeft.setBounds(69, 232,55,55);
        lRight.setBounds(303, 235,55,55);
        lW.setBounds(180, 175,55,55);
        lA.setBounds(126, 235,55,55);
        lS.setBounds(184, 234,55,55);
        lD.setBounds(243, 236,55,55);
        lSpace.setBounds(440, 323,158,30);
        lEsc.setBounds(25, 17,55,55);
        lBomb.setBounds(499, 258,55,55);
        lQ.setBounds(440, 165,55,55);
        lE.setBounds(520, 165,55,55);
        
        JLabel lText1 = new JLabel("Wyjœcie");
        JLabel lText2 = new JLabel("Zmiana");
        JLabel lText3 = new JLabel("¯ywio³ów");
        
        
        lText1.setBounds(105,19,160,54);
        lText2.setBounds(459,28,154,52);
        lText3.setBounds(440,90,175,50);
        
        lText1.setFont(new Font("Lucida",Font.PLAIN,43));
        lText2.setFont(new Font("Lucida",Font.PLAIN,43));
        lText3.setFont(new Font("Lucida",Font.PLAIN,43));
        
        f.add(lFront);
        f.add(lBack);
        f.add(lRight);
        f.add(lLeft);
        f.add(lW);
        f.add(lA);
        f.add(lS);
        f.add(lD);
        f.add(lSpace);
        f.add(lEsc);
        f.add(lText1);
        f.add(lText2);
        f.add(lText3);
        f.add(lBomb);
        f.add(lQ);
        f.add(lE);
        
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                MenuC f1 = new MenuC();
            }
        });
    }
    
}
