
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI.GraC;
import GUI.WynikiC;
import GUI.SterowanieC;
import GUI.WyjscieC;
import GUI.ImageLoader;
import Parameters.Dimensions;



public class MenuC implements Runnable{
    
	Dimensions p = new Dimensions();
	
	private JFrame f = new JFrame("Bombowa Frajda");
	
	public Thread kicker = null;
	
	private Button b1 = new Button("Rozpocznij Gre");
    private Button b2 = new Button("Najlepsze Wyniki");
    private Button b3 = new Button("Sterowanie");
    private Button b4 = new Button("Wyjscie");
    private ImageLoader il = new ImageLoader();
    private JLabel jLabel = new JLabel();
    BufferedImage image = il.imageL("Images/title.jpg");
    ImageIcon imageIcon = new ImageIcon(image);
    
    private final int MENU_FRAME_WIDTH = 700;
    private final int MENU_FRAME_HEIGHT = 500;
    private final int B_WIDTH =200;
    private final int B_HIGH =50;
    private final int B_X =250;
    private final int B1_Y =225;
    private final int B2_Y =280;
    private final int B3_Y =335;
    private final int B4_Y =390;
    private final int ANIMATIONS_CNT = 15;
    
    BufferedImage images[] = new BufferedImage[ANIMATIONS_CNT];
    BufferedImage imagesS[] = new BufferedImage[ANIMATIONS_CNT];
		
    private int flag=0;
    /*
     * Klasa odpowiedzlana za wyœwietlanie okna Menu, daj¹ca mo¿liwoœæ przejœcia do rozgrywki, sterowania i najlepszych wyników, jak i do wyjœcia z programu
     */
	public MenuC(){
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		images[0] = il.imageL("Images/title.jpg");
		imagesS[0]=images[0];
		
	    for(int iter=1;iter<ANIMATIONS_CNT;iter++)
	    {
	    	//System.out.print("Images/title" + (char)(iter+'0') + ".jpg\n");
	    	if(iter<10)
	    	{
	    	images[iter] = il.imageL("Images/title" + (char)(iter+'0') + ".jpg");
	    	}
	    	else
	    	{
		    images[iter] = il.imageL("Images/title1" + (char)(iter-10+'0') + ".jpg");
	    	}
	    	imagesS[iter]=images[iter];
	    }
	    
        		
        b1.setBounds(B_X, B1_Y, B_WIDTH, B_HIGH);
        b2.setBounds(B_X, B2_Y, B_WIDTH, B_HIGH);
        b3.setBounds(B_X, B3_Y, B_WIDTH, B_HIGH);
        b4.setBounds(B_X, B4_Y, B_WIDTH, B_HIGH);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraC f1 = new GraC();
                f.dispose();
                kicker = null;
                sleeep();
                (f1.kicker = new Thread(f1)).start();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WynikiC f2 = new WynikiC();
                f.dispose();
                kicker = null;
                (f2.kicker = new Thread(f2)).start();
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SterowanieC f3 = new SterowanieC();
                f.dispose();
                kicker = null;
                (f3.kicker = new Thread(f3)).start();
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WyjscieC f4 = new WyjscieC(f,kicker);
                (f4.kicker = new Thread(f4)).start();
            }
        });
        
        
       
        f.setSize(MENU_FRAME_WIDTH,MENU_FRAME_HEIGHT);
        jLabel.setIcon(imageIcon);
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
                WyjscieC f5 = new WyjscieC(f,kicker);
            }
        });
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
            	updateOffscreenSize();
            }
        });
        
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        
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
        float yScale = (float)nHeight/(float)MENU_FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)MENU_FRAME_WIDTH;
        for(int iter=0;iter<ANIMATIONS_CNT;iter++)
	    {
        imagesS[iter]=il.scaleI(images[iter],(int)(700*xScale),(int)(500*yScale));
	    }
        f.setContentPane(jLabel);
        b1.setBounds((int)(B_X*xScale), (int)(B1_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
        b2.setBounds((int)(B_X*xScale), (int)(B2_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
        b3.setBounds((int)(B_X*xScale), (int)(B3_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
        b4.setBounds((int)(B_X*xScale), (int)(B4_Y*yScale), (int)(B_WIDTH*xScale), (int)(B_HIGH*yScale));
        
        (kicker = new Thread(this)).start();
    }
	
	private void updateOffScreen()
	{
		image = imagesS[flag];
	    ImageIcon imageIcon = new ImageIcon(image);
	    jLabel.setIcon(imageIcon);
        //f.setContentPane(jLabel);
	    flag++;
	    if(flag==15) {flag=0;};
	}
	
	private void sleeep() {
        try {
            Thread.sleep(90);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	private void sleeeep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (kicker == Thread.currentThread()) {
    		sleeep();
    		updateOffScreen();
    		if(flag==1) {sleeeep();};
        }
    }
    
	public void setKicker(Thread kicker) {
        this.kicker = kicker;
    }
}
