
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import Client.FileOperator;
import Client.ScoreList;
import Client.ScorePerson;
import Parameters.Dimensions;

public class WynikiC implements Runnable{
	
	Dimensions p = new Dimensions();
	
	final int S_FRAME_WIDTH = p.S_FRAME_WIDTH;
    final int S_FRAME_HEIGHT = p.S_FRAME_HEIGHT;
    final int S_FONT_SIZE = p.S_FONT_SIZE;
    final int S_FONTS_SIZE = p.S_FONTS_SIZE;
    final int S_T_X = p.S_T_X;
    final int S_T_Y = p.S_T_Y;
    final int S_T_WIDTH = p.S_T_WIDTH;
    final int S_T_HEIGHT = p.S_T_HEIGHT;
    final int S_SCROLL_X = p.S_SCROLL_X;
    final int S_SCROLL_Y = p.S_SCROLL_Y;
    final int S_SCROLL_WIDTH = p.S_SCROLL_WIDTH;
    final int S_SCROLL_HEIGHT = p.S_SCROLL_HEIGHT;
	
    JFrame f = new JFrame("Tablica Rankingowa");
    Label l = new Label("Tablica najlepszych wyników");
    String[] kolumny = new String[] {
            "Id", "Nick", "Punkty", "Czas", "Etap"
    };
    
    
    public JTable t;
    public JScrollPane scroll;
    public FileOperator fo = new FileOperator("recordFiles.txt");
    Thread kicker = null;
    /*
     * Klasa odpowiedzialna za okno wyœiwtlaj¹ce najlepsze wyniki
     */
	public WynikiC(){
    	
		/*ScoreList scoreList = new ScoreList(3);
        scoreList.addToList(new ScorePerson(1, "John", 40, "1:03:00", 5 ));
        scoreList.addToList(new ScorePerson(2, "Rambo", 30, "1:51:00", 5));
        scoreList.addToList(new ScorePerson(3, "Zorro", 20, "1:43:00", 3));
        Object[][] data = new Object[3][5];*/
		
		
		ScoreList scoreList = fo.readScore();
		Object[][] data = new Object[scoreList.howMany][5];
		
        for(int iter=0;iter<scoreList.howMany;iter++)
        {
        	data[iter][0] = scoreList.scorePeople[iter].id;
        	data[iter][1] = scoreList.scorePeople[iter].nick;
        	data[iter][2] = scoreList.scorePeople[iter].score;
        	data[iter][3] = scoreList.scorePeople[iter].time;
        	data[iter][4] = scoreList.scorePeople[iter].stage;
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        t = new JTable(data, kolumny);
        
        scroll = new JScrollPane(t);
        
        l.setFont(new Font("Lucida",Font.PLAIN,S_FONT_SIZE));
        
        l.setBounds(S_T_X, S_T_Y, S_T_WIDTH, S_T_HEIGHT);
        //l.setLocation(0, 0);
        f.add(l);
        
       
        scroll.setBounds(S_SCROLL_X, S_SCROLL_Y, S_SCROLL_WIDTH, S_SCROLL_HEIGHT);
        scroll.setFont(new Font("Lucida",Font.PLAIN,S_FONTS_SIZE));
        f.add(scroll);
        
        
        f.setSize(S_FRAME_WIDTH, S_FRAME_HEIGHT);
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
        float yScale = (float)nHeight/(float)S_FRAME_HEIGHT;
        float xScale = (float)nWidth/(float)S_FRAME_WIDTH;
        
        l.setFont(new Font("Lucida",Font.PLAIN,(int)(S_FONT_SIZE*xScale)));
        l.setBounds((int)(S_T_X*xScale), (int)(S_T_Y*yScale), (int)(S_T_WIDTH*xScale), (int)(S_T_HEIGHT*yScale));
        scroll.setBounds((int)(S_SCROLL_X*xScale), (int)(S_SCROLL_Y*yScale), (int)(S_SCROLL_WIDTH*xScale), (int)(S_SCROLL_HEIGHT*yScale));
        scroll.setFont(new Font("Lucida",Font.PLAIN,(int)(S_FONTS_SIZE*xScale)));
        
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
