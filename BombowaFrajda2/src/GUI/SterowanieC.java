
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import Parameters.Dimensions;

public class SterowanieC implements Runnable{
	
	Dimensions p = new Dimensions();
	
	final int SS_FRAME_WIDTH = p.SS_FRAME_WIDTH ;
    final int SS_FRAME_HEIGHT = p.SS_FRAME_HEIGHT ;
    final int SS_SIZE = p.SS_SIZE ;
    final int SS_SPACE_HEIGHT = p.SS_SPACE_HEIGHT ;
    final int SS_SPACE_WIDTH = p.SS_SPACE_WIDTH ;
    final int SS_FRONT_X = p.SS_FRONT_X ;
    final int SS_BACK_X = p.SS_BACK_X ;
    final int SS_LEFT_X = p.SS_LEFT_X ;
    final int SS_RIGHT_X = p.SS_RIGHT_X ;
    final int SS_W_X = p.SS_W_X ;
    final int SS_A_X = p.SS_A_X ;
    final int SS_S_X = p.SS_S_X ;
    final int SS_D_X = p.SS_D_X ;
    final int SS_SPACE_X = p.SS_SPACE_X ;
    final int SS_ESC_X = p.SS_ESC_X ;
    final int SS_BOMB_X = p.SS_BOMB_X ;
    final int SS_Q_X = p.SS_Q_X ;
    final int SS_E_X = p.SS_E_X ;
    final int SS_T1_X = p.SS_T1_X ;
    final int SS_T2_X = p.SS_T2_X ;
    final int SS_T3_X = p.SS_T3_X ;
    final int SS_FRONT_Y = p.SS_FRONT_Y ;
    final int SS_BACK_Y = p.SS_BACK_Y ;
    final int SS_LEFT_Y = p.SS_LEFT_Y ;
    final int SS_RIGHT_Y = p.SS_RIGHT_Y ;
    final int SS_W_Y = p.SS_W_Y ;
    final int SS_A_Y = p.SS_A_Y ;
    final int SS_S_Y = p.SS_S_Y ;
    final int SS_D_Y = p.SS_D_Y ;
    final int SS_SPACE_Y = p.SS_SPACE_Y ;
    final int SS_ESC_Y = p.SS_ESC_Y ;
    final int SS_BOMB_Y = p.SS_BOMB_Y ;
    final int SS_Q_Y = p.SS_Q_Y ;
    final int SS_E_Y = p.SS_E_Y ;
    final int SS_T1_Y = p.SS_T1_Y ;
    final int SS_T2_Y = p.SS_T2_Y ;
    final int SS_T3_Y = p.SS_T3_Y ;
    final int SS_T1_HEIGHT = p.SS_T1_HEIGHT ;
    final int SS_T1_WIDTH = p.SS_T1_WIDTH ;
    final int SS_T2_HEIGHT = p.SS_T2_HEIGHT ;
    final int SS_T2_WIDTH = p.SS_T2_WIDTH ;
    final int SS_T3_HEIGHT = p.SS_T3_HEIGHT ;
    final int SS_T3_WIDTH = p.SS_T3_WIDTH ;
    final int SS_FONT_SIZE = p.SS_FONT_SIZE ;
    
    Thread kicker = null;
    
    JFrame f = new JFrame("Sterowanie");
    ImageLoader il = new ImageLoader();
    
    BufferedImage ib1 = il.imageL("Images/bomber_front.png");
    ImageIcon i1 = new ImageIcon(ib1);
    JLabel lFront = new JLabel();
    BufferedImage ib2 = il.imageL("Images/bomber_back.png");
    ImageIcon i2 = new ImageIcon(ib2);
    JLabel lBack = new JLabel();
    BufferedImage ib3 = il.imageL("Images/bomber_right.png");
    ImageIcon i3 = new ImageIcon(ib3);
    JLabel lRight = new JLabel();
    BufferedImage ib4 = il.imageL("Images/bomber_left.png");
    ImageIcon i4 = new ImageIcon(ib4);
    JLabel lLeft = new JLabel();
    BufferedImage ib5 = il.imageL("Images/w.png");
    ImageIcon i5 = new ImageIcon(ib5);
    JLabel lW = new JLabel();
    BufferedImage ib6 = il.imageL("Images/a.png");
    ImageIcon i6 = new ImageIcon(ib6);
    JLabel lA = new JLabel();
    BufferedImage ib7 = il.imageL("Images/s.png");
    ImageIcon i7 = new ImageIcon(ib7);
    JLabel lS = new JLabel();
    BufferedImage ib8 = il.imageL("Images/d.png");
    ImageIcon i8 = new ImageIcon(ib8);
    JLabel lD = new JLabel();
    BufferedImage ib9 = il.imageL("Images/space.png");
    ImageIcon i9 = new ImageIcon(ib9);
    JLabel lSpace = new JLabel();
    BufferedImage ib10 = il.imageL("Images/esc.png");
    ImageIcon i10 = new ImageIcon(ib10);
    JLabel lEsc = new JLabel();
    BufferedImage ib11 = il.imageL("Images/bomb.png");
    ImageIcon i11 = new ImageIcon(ib11);
    JLabel lBomb = new JLabel();
    BufferedImage ib12 = il.imageL("Images/q.png");
    ImageIcon i12 = new ImageIcon(ib12);
    JLabel lQ = new JLabel();
    BufferedImage ib13 = il.imageL("Images/e.png");
    ImageIcon i13 = new ImageIcon(ib13);
    JLabel lE = new JLabel();        
    
    JLabel lText1 = new JLabel("Wyjœcie");
    JLabel lText2 = new JLabel("Zmiana");
    JLabel lText3 = new JLabel("¯ywio³ów");
    
	
	public SterowanieC(){
    	
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        f.setSize(SS_FRAME_WIDTH, SS_FRAME_HEIGHT);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        
       
        lFront.setIcon(i1);
        lBack.setIcon(i2);
        lRight.setIcon(i3);
        lLeft.setIcon(i4);
        lW.setIcon(i5);
        lA.setIcon(i6);
        lS.setIcon(i7);
        lD.setIcon(i8);
        lSpace.setIcon(i9);
        lEsc.setIcon(i10);
        lBomb.setIcon(i11);
        lQ.setIcon(i12);
        lE.setIcon(i13);
        
        
        lFront.setBounds(SS_FRONT_X, SS_FRONT_Y,SS_SIZE,SS_SIZE);
        lBack.setBounds(SS_BACK_X, SS_BACK_Y,SS_SIZE,SS_SIZE);
        lLeft.setBounds(SS_LEFT_X, SS_LEFT_Y,SS_SIZE,SS_SIZE);
        lRight.setBounds(SS_RIGHT_X, SS_RIGHT_Y,SS_SIZE,SS_SIZE);
        lW.setBounds(SS_W_X, SS_W_Y,SS_SIZE,SS_SIZE);
        lA.setBounds(SS_A_X, SS_A_Y,SS_SIZE,SS_SIZE);
        lS.setBounds(SS_S_X, SS_S_Y,SS_SIZE,SS_SIZE);
        lD.setBounds(SS_D_X, SS_D_Y,SS_SIZE,SS_SIZE);
        lSpace.setBounds(SS_SPACE_X, SS_SPACE_Y,SS_SPACE_WIDTH,SS_SPACE_HEIGHT);
        lEsc.setBounds(SS_ESC_X, SS_ESC_Y,SS_SIZE,SS_SIZE);
        lBomb.setBounds(SS_BOMB_X, SS_BOMB_Y,SS_SIZE,SS_SIZE);
        lQ.setBounds(SS_Q_X, SS_Q_Y,SS_SIZE,SS_SIZE);
        lE.setBounds(SS_E_X, SS_E_Y,SS_SIZE,SS_SIZE);

        
        lText1.setBounds(SS_T1_X,SS_T1_Y,SS_T1_WIDTH,SS_T1_HEIGHT);
        lText2.setBounds(SS_T2_X,SS_T2_Y,SS_T2_WIDTH,SS_T2_HEIGHT);
        lText3.setBounds(SS_T3_X,SS_T3_Y,SS_T3_WIDTH,SS_T3_HEIGHT);
        
        lText1.setFont(new Font("Lucida",Font.PLAIN,SS_FONT_SIZE));
        lText2.setFont(new Font("Lucida",Font.PLAIN,SS_FONT_SIZE));
        lText3.setFont(new Font("Lucida",Font.PLAIN,SS_FONT_SIZE));
        
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
        
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                kicker = null;
                MenuC f1 = new MenuC();
                (f1.kicker = new Thread(f1)).start();
            }
        });
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
            	updateOffscreenSize();
            }
        });
        };
        });
    }
    
	void updateOffscreenSize() {
    	if (kicker != null) {
    		Thread k = kicker;
    		kicker = null;
    		k.interrupt();
    	}
    	int nWidth = f.getWidth();
        int nHeight = f.getHeight();
        float yScale = (float)nHeight/(float)SS_FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)SS_FRAME_WIDTH;
        
        ImageIcon nI1 = new ImageIcon(il.scaleI(ib1,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lFront.setIcon(nI1);
        ImageIcon nI2 = new ImageIcon(il.scaleI(ib2,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lBack.setIcon(nI2);
        ImageIcon nI3 = new ImageIcon(il.scaleI(ib3,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lRight.setIcon(nI3);
        ImageIcon nI4 = new ImageIcon(il.scaleI(ib4,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lLeft.setIcon(nI4);
        ImageIcon nI5 = new ImageIcon(il.scaleI(ib5,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lW.setIcon(nI5);
        ImageIcon nI6 = new ImageIcon(il.scaleI(ib6,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lA.setIcon(nI6);
        ImageIcon nI7 = new ImageIcon(il.scaleI(ib7,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lS.setIcon(nI7);
        ImageIcon nI8 = new ImageIcon(il.scaleI(ib8,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lD.setIcon(nI8);
        ImageIcon nI9 = new ImageIcon(il.scaleI(ib9,(int)(SS_SPACE_WIDTH*xScale),(int)(SS_SPACE_HEIGHT*yScale)));
        lSpace.setIcon(nI9);
        ImageIcon nI10 = new ImageIcon(il.scaleI(ib10,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lEsc.setIcon(nI10);
        ImageIcon nI11 = new ImageIcon(il.scaleI(ib11,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lBomb.setIcon(nI11);
        ImageIcon nI12 = new ImageIcon(il.scaleI(ib12,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lQ.setIcon(nI12);
        ImageIcon nI13 = new ImageIcon(il.scaleI(ib13,(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale)));
        lE.setIcon(nI13);
        
        lFront.setBounds((int)(SS_FRONT_X*xScale), (int)(SS_FRONT_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lBack.setBounds((int)(SS_BACK_X*xScale), (int)(SS_BACK_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lLeft.setBounds((int)(SS_LEFT_X*xScale), (int)(SS_LEFT_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lRight.setBounds((int)(SS_RIGHT_X*xScale), (int)(SS_RIGHT_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lW.setBounds((int)(SS_W_X*xScale), (int)(SS_W_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lA.setBounds((int)(SS_A_X*xScale), (int)(SS_A_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lS.setBounds((int)(SS_S_X*xScale), (int)(SS_S_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lD.setBounds((int)(SS_D_X*xScale), (int)(SS_D_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lSpace.setBounds((int)(SS_SPACE_X*xScale), (int)(SS_SPACE_Y*yScale),(int)(SS_SPACE_WIDTH*xScale),(int)(SS_SPACE_HEIGHT*yScale));
        lEsc.setBounds((int)(SS_ESC_X*xScale), (int)(SS_ESC_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lBomb.setBounds((int)(SS_BOMB_X*xScale), (int)(SS_BOMB_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lQ.setBounds((int)(SS_Q_X*xScale), (int)(SS_Q_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lE.setBounds((int)(SS_E_X*xScale), (int)(SS_E_Y*yScale),(int)(SS_SIZE*xScale),(int)(SS_SIZE*yScale));
        lText1.setBounds((int)(SS_T1_X*xScale),(int)(SS_T1_Y*yScale),(int)(SS_T1_WIDTH*xScale),(int)(SS_T1_HEIGHT*yScale));
        lText2.setBounds((int)(SS_T2_X*xScale),(int)(SS_T2_Y*yScale),(int)(SS_T2_WIDTH*xScale),(int)(SS_T2_HEIGHT*yScale));
        lText3.setBounds((int)(SS_T3_X*xScale),(int)(SS_T3_Y*yScale),(int)(SS_T3_WIDTH*xScale),(int)(SS_T3_HEIGHT*yScale));
        lText1.setFont(new Font("Lucida",Font.PLAIN,(int)(SS_FONT_SIZE*xScale)));
        lText2.setFont(new Font("Lucida",Font.PLAIN,(int)(SS_FONT_SIZE*xScale)));
        lText3.setFont(new Font("Lucida",Font.PLAIN,(int)(SS_FONT_SIZE*xScale)));
        
        (kicker = new Thread(this)).start();
    }
	
	private void sleeep() {
        try {
            Thread.sleep(90);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (kicker == Thread.currentThread()) {
    		sleeep();
        }
    }
    
	public void setKicker(Thread kicker) {
        this.kicker = kicker;
    }
}
