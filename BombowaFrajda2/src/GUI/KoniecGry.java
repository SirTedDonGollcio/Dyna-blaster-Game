
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
import Client.ScorePerson;
import Client.FileOperator;
import Client.ScoreList;
import Parameters.Dimensions;

public class KoniecGry implements Runnable{
    
	Dimensions p = new Dimensions();
	
	final int KG_FRAME_WIDTH = p.KG_FRAME_WIDTH;
    final int KG_FRAME_HEIGHT = p.KG_FRAME_HEIGHT;
    final int KG_B1_X = p.KG_B1_X;
    final int KG_B_Y = p.KG_B_Y;
    final int KG_B_WIDTH = p.KG_B_WIDTH;
    final int KG_B_HEIGHT = p.KG_B_HEIGHT;
    final int KG_B2_X = p.KG_B2_X;
    final int KG_T_X = p.KG_T_X;
    final int KG_T_Y = p.KG_T_Y;
    final int KG_T_WIDTH = p.KG_T_WIDTH;
    final int KG_T_HEIGHT = p.KG_T_HEIGHT;
    final int KG_FONT_SIZE = p.KG_FONT_SIZE;
    final int KG_FONTB_SIZE = p.KG_FONTB_SIZE;
    final int KG_L1_X = p.KG_L1_X;
    final int KG_L1_Y = p.KG_L1_Y;
    final int KG_L1_HEIGHT = p.KG_L1_HEIGHT;
    final int KG_L1_WIDTH = p.KG_L1_WIDTH;
    
    final int KG_L2_X = p.KG_L2_X;
    final int KG_L2_Y = p.KG_L2_Y;
    final int KG_L2_HEIGHT = p.KG_L2_HEIGHT;
    final int KG_L2_WIDTH = p.KG_L2_WIDTH;
    
    final int KG_L3_X = p.KG_L3_X;
    final int KG_L3_Y = p.KG_L3_Y;
    final int KG_L3_HEIGHT = p.KG_L3_HEIGHT;
    final int KG_L3_WIDTH = p.KG_L3_WIDTH;
    
    final int KG_L4_X = p.KG_L4_X;
    final int KG_L4_Y = p.KG_L4_Y;
    final int KG_L4_HEIGHT = p.KG_L4_HEIGHT;
    final int KG_L4_WIDTH = p.KG_L4_WIDTH;
    
    final int KG_TF_X = p.KG_TF_X;
    final int KG_TF_Y = p.KG_TF_Y;
    final int KG_TF_HEIGHT = p.KG_TF_HEIGHT;
    final int KG_TF_WIDTH = p.KG_TF_WIDTH;
    
    
    
    JFrame f = new JFrame("Koniec Gry");
    JLabel l = new JLabel("Ukonczyles gre z wynikami:");
    JLabel scoreStable = new JLabel("Score: ");
    JLabel timeStable = new JLabel("Time: ");
    JLabel score= new JLabel();
    JLabel time= new JLabel();
    JTextField nick= new JTextField();;
    Button b1 = new Button("Zapisz");
    Button b2 = new Button("Anuluj");
    
    public Thread kicker = null;
	
	public KoniecGry(int points,int godziny,int minuty,int sekundy){
    	
        
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        l.setBounds(KG_T_X, KG_T_Y, KG_T_WIDTH, KG_T_HEIGHT);
        scoreStable.setBounds(KG_L1_X,KG_L1_Y,KG_L1_WIDTH,KG_L1_HEIGHT);
        timeStable.setBounds(KG_L4_X,KG_L4_Y,KG_L4_WIDTH,KG_L4_HEIGHT);
        score.setBounds(KG_L3_X,KG_L3_Y,KG_L3_WIDTH,KG_L3_HEIGHT);
        time.setBounds(KG_L2_X,KG_L2_Y,KG_L2_WIDTH,KG_L2_HEIGHT);
        nick.setBounds(KG_TF_X,KG_TF_Y,KG_TF_WIDTH,KG_TF_HEIGHT);
        b1.setBounds(KG_B1_X, KG_B_Y, KG_B_WIDTH, KG_B_HEIGHT);
        b2.setBounds(KG_B2_X, KG_B_Y, KG_B_WIDTH, KG_B_HEIGHT);
        b1.setFont(new Font("Lucida",Font.PLAIN,KG_FONTB_SIZE));
        b2.setFont(new Font("Lucida",Font.PLAIN,KG_FONTB_SIZE));
        l.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        scoreStable.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        timeStable.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        time.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        score.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        nick.setFont(new Font("Lucida",Font.PLAIN,KG_FONT_SIZE));
        score.setText(String.valueOf(points));
        time.setText(String.valueOf(godziny)+":"+String.valueOf(minuty)+":"+String.valueOf(sekundy));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ScorePerson nowyRekord = new ScorePerson(0,nick.getText(),points,String.valueOf(godziny)+":"+String.valueOf(minuty)+":"+String.valueOf(sekundy),3);
                FileOperator fo = new FileOperator("recordFiles.txt");
            	ScoreList lista = fo.readScore();
            	lista.addToList(nowyRekord);
            	lista.sortList();
            	fo.saveScore(lista);
            	f.dispose();
            	MenuC f1 = new MenuC();
            	(f1.kicker = new Thread(f1)).start();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setKicker(null);
                MenuC f1 = new MenuC();
                (f1.kicker = new Thread(f1)).start();
            }
        });
        f.add(scoreStable);
        f.add(timeStable);
        f.add(time);
        f.add(score);
        f.add(nick);
        
        f.add(b1);
        f.add(b2);
        f.add(l);
        f.setSize(KG_FRAME_WIDTH, KG_FRAME_HEIGHT);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                setKicker(null);
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
        float yScale = (float)nHeight/(float)KG_FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)KG_FRAME_WIDTH;
        
        l.setFont(new Font("Lucida",Font.PLAIN,(int)(KG_FONT_SIZE*xScale)));
        l.setBounds((int)(KG_T_X*xScale), (int)(KG_T_Y*yScale), (int)(KG_T_WIDTH*xScale), (int)(KG_T_HEIGHT*yScale));
        b1.setBounds((int)(KG_B1_X*xScale), (int)(KG_B_Y*yScale), (int)(KG_B_WIDTH*xScale), (int)(KG_B_HEIGHT*yScale));
        b2.setBounds((int)(KG_B2_X*xScale), (int)(KG_B_Y*yScale), (int)(KG_B_WIDTH*xScale), (int)(KG_B_HEIGHT*yScale));
        b1.setFont(new Font("Lucida",Font.PLAIN,(int)(KG_FONTB_SIZE*xScale)));
        b2.setFont(new Font("Lucida",Font.PLAIN,(int)(KG_FONTB_SIZE*xScale)));
        
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
