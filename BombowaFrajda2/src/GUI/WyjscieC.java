
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
import Parameters.Dimensions;

public class WyjscieC implements Runnable{
    
	Dimensions p = new Dimensions();
	
	final int W_FRAME_WIDTH = p.W_FRAME_WIDTH;
    final int W_FRAME_HEIGHT = p.W_FRAME_HEIGHT;
    final int W_B1_X = p.W_B1_X;
    final int W_B_Y = p.W_B_Y;
    final int W_B_WIDTH = p.W_B_WIDTH;
    final int W_B_HEIGHT = p.W_B_HEIGHT;
    final int W_B2_X = p.W_B2_X;
    final int W_T_X = p.W_T_X;
    final int W_T_Y =p.W_T_Y;
    final int W_T_WIDTH =p.W_T_WIDTH;
    final int W_T_HEIGHT =p.W_T_HEIGHT;
    final int W_FONT_SIZE =p.W_FONT_SIZE;
    final int W_FONTB_SIZE =p.W_FONTB_SIZE;
    
    JFrame f = new JFrame("Wyjscie?");
    Label l = new Label("Czy na pewno chcesz opuscic gre?");
    Button b1 = new Button("Tak");
    Button b2 = new Button("Nie");
    
    public Thread kicker = null;
	
	public WyjscieC(Frame fx,Thread tempKick){
    	
        
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        l.setBounds(W_T_X, W_T_Y, W_T_WIDTH, W_T_HEIGHT);
        
        b1.setBounds(W_B1_X, W_B_Y, W_B_WIDTH, W_B_HEIGHT);
        b2.setBounds(W_B2_X, W_B_Y, W_B_WIDTH, W_B_HEIGHT);
        b1.setFont(new Font("Lucida",Font.PLAIN,W_FONTB_SIZE));
        b2.setFont(new Font("Lucida",Font.PLAIN,W_FONTB_SIZE));
        l.setFont(new Font("Lucida",Font.PLAIN,W_FONT_SIZE));
        
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
        f.setSize(W_FRAME_WIDTH, W_FRAME_HEIGHT);
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
        float yScale = (float)nHeight/(float)W_FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)W_FRAME_WIDTH;
        
        l.setFont(new Font("Lucida",Font.PLAIN,(int)(W_FONT_SIZE*xScale)));
        l.setBounds((int)(W_T_X*xScale), (int)(W_T_Y*yScale), (int)(W_T_WIDTH*xScale), (int)(W_T_HEIGHT*yScale));
        b1.setBounds((int)(W_B1_X*xScale), (int)(W_B_Y*yScale), (int)(W_B_WIDTH*xScale), (int)(W_B_HEIGHT*yScale));
        b2.setBounds((int)(W_B2_X*xScale), (int)(W_B_Y*yScale), (int)(W_B_WIDTH*xScale), (int)(W_B_HEIGHT*yScale));
        b1.setFont(new Font("Lucida",Font.PLAIN,(int)(W_FONTB_SIZE*xScale)));
        b2.setFont(new Font("Lucida",Font.PLAIN,(int)(W_FONTB_SIZE*xScale)));
        
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
