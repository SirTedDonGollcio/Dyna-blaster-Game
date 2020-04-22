
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class SterowanieC implements Runnable{
	final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 500;
    final int SIZE = 55;
    final int SPACE_HEIGHT = 30;
    final int SPACE_WIDTH = 158;
    final int FRONT_X = 180;
    final int BACK_X = 181;
    final int LEFT_X = 69;
    final int RIGHT_X = 303;
    final int W_X = 180;
    final int A_X = 126;
    final int S_X = 184;
    final int D_X = 243;
    final int SPACE_X = 440;
    final int ESC_X = 25;
    final int BOMB_X = 499;
    final int Q_X = 440;
    final int E_X = 520;
    final int T1_X = 105;
    final int T2_X = 459;
    final int T3_X = 440;
    final int FRONT_Y = 296;
    final int BACK_Y = 116;
    final int LEFT_Y = 232;
    final int RIGHT_Y = 235;
    final int W_Y = 175;
    final int A_Y = 235;
    final int S_Y = 234;
    final int D_Y = 236;
    final int SPACE_Y = 323;
    final int ESC_Y = 17;
    final int BOMB_Y = 258;
    final int Q_Y = 165;
    final int E_Y = 165;
    final int T1_Y = 19;
    final int T2_Y = 28;
    final int T3_Y = 90;
    final int T1_HEIGHT = 54;
    final int T1_WIDTH = 160;
    final int T2_HEIGHT = 52;
    final int T2_WIDTH = 154;
    final int T3_HEIGHT = 50;
    final int T3_WIDTH = 195;
    final int FONT_SIZE = 43;
    
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
    	
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
        
        
        lFront.setBounds(FRONT_X, FRONT_Y,SIZE,SIZE);
        lBack.setBounds(BACK_X, BACK_Y,SIZE,SIZE);
        lLeft.setBounds(LEFT_X, LEFT_Y,SIZE,SIZE);
        lRight.setBounds(RIGHT_X, RIGHT_Y,SIZE,SIZE);
        lW.setBounds(W_X, W_Y,SIZE,SIZE);
        lA.setBounds(A_X, A_Y,SIZE,SIZE);
        lS.setBounds(S_X, S_Y,SIZE,SIZE);
        lD.setBounds(D_X, D_Y,SIZE,SIZE);
        lSpace.setBounds(SPACE_X, SPACE_Y,SPACE_WIDTH,SPACE_HEIGHT);
        lEsc.setBounds(ESC_X, ESC_Y,SIZE,SIZE);
        lBomb.setBounds(BOMB_X, BOMB_Y,SIZE,SIZE);
        lQ.setBounds(Q_X, Q_Y,SIZE,SIZE);
        lE.setBounds(E_X, E_Y,SIZE,SIZE);

        
        lText1.setBounds(T1_X,T1_Y,T1_WIDTH,T1_HEIGHT);
        lText2.setBounds(T2_X,T2_Y,T2_WIDTH,T2_HEIGHT);
        lText3.setBounds(T3_X,T3_Y,T3_WIDTH,T3_HEIGHT);
        
        lText1.setFont(new Font("Lucida",Font.PLAIN,FONT_SIZE));
        lText2.setFont(new Font("Lucida",Font.PLAIN,FONT_SIZE));
        lText3.setFont(new Font("Lucida",Font.PLAIN,FONT_SIZE));
        
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
    }
    
	void updateOffscreenSize() {
    	if (kicker != null) {
    		Thread k = kicker;
    		kicker = null;
    		k.interrupt();
    	}
    	int nWidth = f.getWidth();
        int nHeight = f.getHeight();
        float yScale = (float)nHeight/(float)FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)FRAME_WIDTH;
        
        ImageIcon nI1 = new ImageIcon(il.scaleI(ib1,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lFront.setIcon(nI1);
        ImageIcon nI2 = new ImageIcon(il.scaleI(ib2,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lBack.setIcon(nI2);
        ImageIcon nI3 = new ImageIcon(il.scaleI(ib3,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lRight.setIcon(nI3);
        ImageIcon nI4 = new ImageIcon(il.scaleI(ib4,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lLeft.setIcon(nI4);
        ImageIcon nI5 = new ImageIcon(il.scaleI(ib5,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lW.setIcon(nI5);
        ImageIcon nI6 = new ImageIcon(il.scaleI(ib6,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lA.setIcon(nI6);
        ImageIcon nI7 = new ImageIcon(il.scaleI(ib7,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lS.setIcon(nI7);
        ImageIcon nI8 = new ImageIcon(il.scaleI(ib8,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lD.setIcon(nI8);
        ImageIcon nI9 = new ImageIcon(il.scaleI(ib9,(int)(SPACE_WIDTH*xScale),(int)(SPACE_HEIGHT*yScale)));
        lSpace.setIcon(nI9);
        ImageIcon nI10 = new ImageIcon(il.scaleI(ib10,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lEsc.setIcon(nI10);
        ImageIcon nI11 = new ImageIcon(il.scaleI(ib11,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lBomb.setIcon(nI11);
        ImageIcon nI12 = new ImageIcon(il.scaleI(ib12,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lQ.setIcon(nI12);
        ImageIcon nI13 = new ImageIcon(il.scaleI(ib13,(int)(SIZE*xScale),(int)(SIZE*yScale)));
        lE.setIcon(nI13);
        
        lFront.setBounds((int)(FRONT_X*xScale), (int)(FRONT_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lBack.setBounds((int)(BACK_X*xScale), (int)(BACK_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lLeft.setBounds((int)(LEFT_X*xScale), (int)(LEFT_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lRight.setBounds((int)(RIGHT_X*xScale), (int)(RIGHT_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lW.setBounds((int)(W_X*xScale), (int)(W_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lA.setBounds((int)(A_X*xScale), (int)(A_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lS.setBounds((int)(S_X*xScale), (int)(S_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lD.setBounds((int)(D_X*xScale), (int)(D_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lSpace.setBounds((int)(SPACE_X*xScale), (int)(SPACE_Y*yScale),(int)(SPACE_WIDTH*xScale),(int)(SPACE_HEIGHT*yScale));
        lEsc.setBounds((int)(ESC_X*xScale), (int)(ESC_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lBomb.setBounds((int)(BOMB_X*xScale), (int)(BOMB_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lQ.setBounds((int)(Q_X*xScale), (int)(Q_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lE.setBounds((int)(E_X*xScale), (int)(E_Y*yScale),(int)(SIZE*xScale),(int)(SIZE*yScale));
        lText1.setBounds((int)(T1_X*xScale),(int)(T1_Y*yScale),(int)(T1_WIDTH*xScale),(int)(T1_HEIGHT*yScale));
        lText2.setBounds((int)(T2_X*xScale),(int)(T2_Y*yScale),(int)(T2_WIDTH*xScale),(int)(T2_HEIGHT*yScale));
        lText3.setBounds((int)(T3_X*xScale),(int)(T3_Y*yScale),(int)(T3_WIDTH*xScale),(int)(T3_HEIGHT*yScale));
        lText1.setFont(new Font("Lucida",Font.PLAIN,(int)(FONT_SIZE*xScale)));
        lText2.setFont(new Font("Lucida",Font.PLAIN,(int)(FONT_SIZE*xScale)));
        lText3.setFont(new Font("Lucida",Font.PLAIN,(int)(FONT_SIZE*xScale)));
        
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
