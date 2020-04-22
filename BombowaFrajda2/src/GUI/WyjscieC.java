
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WyjscieC implements Runnable{
    
	final int FRAME_WIDTH = 400;
    final int FRAME_HEIGHT = 300;
    final int B1_X = 50;
    final int B_Y = 150;
    final int B_WIDTH = 100;
    final int B_HEIGHT = 50;
    final int B2_X = 250;
    final int T_X = 45;
    final int T_Y =50;
    final int T_WIDTH =370;
    final int T_HEIGHT =80;
    final int FONT_SIZE =21;
    final int FONTB_SIZE =12;
    
    JFrame f = new JFrame("Wyjscie?");
    Label l = new Label("Czy na pewno chcesz opuscic gre?");
    Button b1 = new Button("Tak");
    Button b2 = new Button("Nie");
    
    public Thread kicker = null;
	
	public WyjscieC(Frame fx,Thread tempKick){
    	
        
        
        
        l.setBounds(T_X, T_Y, T_WIDTH, T_HEIGHT);
        
        b1.setBounds(B1_X, B_Y, B_WIDTH, B_HEIGHT);
        b2.setBounds(B2_X, B_Y, B_WIDTH, B_HEIGHT);
        b1.setFont(new Font("Lucida",Font.PLAIN,FONTB_SIZE));
        b2.setFont(new Font("Lucida",Font.PLAIN,FONTB_SIZE));
        l.setFont(new Font("Lucida",Font.PLAIN,FONT_SIZE));
        
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(1);
            	//fx.dispose();
                //tempKick.stop();
                //f.dispose();
                //setKicker(null);
                
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setKicker(null);
            }
        });
        
        f.add(b1);
        f.add(b2);
        f.add(l);
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                setKicker(null);
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
        
        l.setFont(new Font("Lucida",Font.PLAIN,(int)(FONT_SIZE*xScale)));
        l.setBounds((int)(T_X*xScale), (int)(T_Y*yScale), (int)(T_WIDTH*xScale), (int)(T_HEIGHT*yScale));
        b1.setBounds((int)(B1_X*xScale), (int)(B_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HEIGHT*yScale));
        b2.setBounds((int)(B2_X*xScale), (int)(B_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HEIGHT*yScale));
        b1.setFont(new Font("Lucida",Font.PLAIN,(int)(FONTB_SIZE*xScale)));
        b2.setFont(new Font("Lucida",Font.PLAIN,(int)(FONTB_SIZE*xScale)));
        
        (kicker = new Thread(this)).start();
    }
	
	private void sleeep() {
        try {
            Thread.sleep(3000);
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
