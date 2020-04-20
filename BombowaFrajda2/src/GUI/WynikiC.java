
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class WynikiC implements Runnable{
	final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 500;
    final int FONT_SIZE = 33;
    final int FONTS_SIZE = 12;
    final int T_X = 140;
    final int T_Y = 50;
    final int T_WIDTH = 700;
    final int T_HEIGHT = 40;
    final int SCROLL_X = 145;
    final int SCROLL_Y = 180;
    final int SCROLL_WIDTH = 400;
    final int SCROLL_HEIGHT = 200;
	
    JFrame f = new JFrame("Tablica Rankingowa");
    Label l = new Label("Tablica najlepszych wyników");
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
    
    Thread kicker = null;
    
	public WynikiC(){
    	
    	
        
        
        
        l.setFont(new Font("Lucida",Font.PLAIN,FONT_SIZE));
        
        l.setBounds(T_X, T_Y, T_WIDTH, T_HEIGHT);
        //l.setLocation(0, 0);
        f.add(l);
        
       
        scroll.setBounds(SCROLL_X, SCROLL_Y, SCROLL_WIDTH, SCROLL_HEIGHT);
        scroll.setFont(new Font("Lucida",Font.PLAIN,FONTS_SIZE));
        f.add(scroll);
        
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
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
        
        l.setFont(new Font("Lucida",Font.PLAIN,(int)(FONT_SIZE*xScale)));
        l.setBounds((int)(T_X*xScale), (int)(T_Y*yScale), (int)(T_WIDTH*xScale), (int)(T_HEIGHT*yScale));
        scroll.setBounds((int)(SCROLL_X*xScale), (int)(SCROLL_Y*yScale), (int)(SCROLL_WIDTH*xScale), (int)(SCROLL_HEIGHT*yScale));
        scroll.setFont(new Font("Lucida",Font.PLAIN,(int)(FONTS_SIZE*xScale)));
        
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
