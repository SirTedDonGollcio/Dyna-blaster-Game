
package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import GUI.HealthBar;
import Game.ObjectCreator;
import Game.Wall;
import Game.Bomber;
import Game.FragileWall;
import Game.KeyFrame;

public class GraC implements Runnable{
    
	final int FRAME_WIDTH = 645;
    final int FRAME_HEIGHT = 810;
    final int GAME_F_DIM = 645;
    final int GAME_DIM = 605;
    final int GAME_START = 20;
    final int OBJ_DIM = 55;
	
    ImageLoader il = new ImageLoader();
    KeyFrame f = new KeyFrame("Bombowa Frajda");
    JLabel userGUI = new JLabel();
    HealthBar hb = new HealthBar();
    BombBar bb = new BombBar();
    JLabel healthBar = hb.healthBar();
    JLabel bombBar = bb.bombBar();
    BufferedImage image = il.imageL("Images/game_background.png");
    ImageIcon backgroundIcon = new ImageIcon(image);
    JLabel gameFrames = new JLabel();
    JLabel game = new JLabel();
    
    //tymaczowe dane do kreacji obiektow
    int iloscScian = 25;
    //int[][] dane = new int[iloscScian][2];
    int[][] daneW = {{1,1},{3,1},{5,1},{7,1},{9,1},
    		{1,3},{3,3},{5,3},{7,3},{9,3},
    		{1,5},{3,5},{5,5},{7,5},{9,5},
    		{1,7},{3,7},{5,7},{7,7},{9,7},
    		{1,9},{3,9},{5,9},{7,9},{9,9}};        
    Wall walls[] = new Wall[iloscScian];
    
    int iloscFragscian = 53;
    //int[][] dane = new int[iloscScian][2];
    int[][] daneF = {{3,0},{7,0},{8,0},
    				{6,1},
    				{1,2},{2,2},{3,2},{4,2},{5,2},{6,2},{9,2},{10,2},
    				{0,3},{2,3},{4,3},{6,3},
    				{0,4},{1,4},{6,4},{9,4},
    				{0,5},{6,5},{7,5},
    				{0,6},{1,6},{2,6},{3,6},{4,6},{8,6},
    				{0,7},{2,7},{4,7},{6,7},{8,7},{10,7},
    				{0,8},{1,8},{2,8},{3,8},{4,8},{5,8},{7,8},{8,8},{9,8},{10,8},
    				{2,9},{4,9},{8,9},{10,9},
    				{2,10},{3,10},{4,10},{9,10},{10,10},};
    FragileWall fragWalls[] = new FragileWall[iloscFragscian];
    
    Bomber bomber = new Bomber(0,0);
    
    Thread kicker = null;
    
    
	public GraC(){
		(f.ruch = new Thread(f)).start();
		
        userGUI.setOpaque(true);
        userGUI.setBackground(new Color(64, 62, 60));
        userGUI.setBounds(0,0,FRAME_WIDTH,165);
        
        gameFrames.setBounds(0, 166, GAME_F_DIM, GAME_F_DIM);
        gameFrames.setIcon(backgroundIcon);
       
        f.add(gameFrames);
        f.add(userGUI);
        userGUI.add(healthBar);
        userGUI.add(bombBar);
        
        game.setBounds(GAME_START,GAME_START, GAME_DIM, GAME_DIM);
        
        gameFrames.add(game);
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setBackground(new Color(64, 62, 60));
        f.setLocationRelativeTo(null);
        
     	
        for(int iter = 0; iter < iloscScian;iter++)
        {
        	Wall wall = new Wall(daneW[iter][0],daneW[iter][1]);
        	walls[iter] = wall;
        	game.add(walls[iter].getLabel());
        }
        
        for(int iter = 0;iter<iloscFragscian;iter++)
        {
        	FragileWall fragileWall = new FragileWall(daneF[iter][0],daneF[iter][1]);
        	fragWalls[iter] = fragileWall;
        	game.add(fragWalls[iter].getLabel());
        }
        
        
        game.add(bomber.getLabel());
        f.player = bomber;
        bomber.lX = f.getWidth();
        bomber.lY = f.getHeight();
        
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
    	
    	int nWidth = f.getWidth()-16;
        int nHeight = f.getHeight()-205;
        float yScale = (float)nHeight/((float)GAME_F_DIM);
        float xScale = (float)nWidth/(float)GAME_F_DIM;
        
        userGUI.setBounds(0,0,nWidth,165);
        ImageIcon i1 = new ImageIcon(il.scaleI(image,(int)(GAME_F_DIM*xScale),(int)(GAME_F_DIM*yScale)));
        gameFrames.setIcon(i1);
        gameFrames.setBounds(0, 165, (int)(GAME_F_DIM*xScale), (int)(GAME_F_DIM*yScale));
        game.setBounds((int)(GAME_START*xScale),(int)(GAME_START*yScale),(int)(GAME_DIM*xScale),(int)(GAME_DIM*yScale));
        
        nWidth = nWidth+16;
        nHeight = nHeight +40;
        
        for(int iter = 0; iter <iloscScian;iter++)
        {
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(walls[iter].i,(int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
        	walls[iter].getLabel().setIcon(imageIcon);
        	walls[iter].getLabel().setBounds((int)(daneW[iter][0]*OBJ_DIM*xScale),(int)(daneW[iter][1]*OBJ_DIM*yScale),(int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
        }
        for(int iter = 0; iter <iloscFragscian;iter++)
        {
        	
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(fragWalls[iter].i,(int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
        	fragWalls[iter].getLabel().setIcon(imageIcon);
        	fragWalls[iter].getLabel().setBounds((int)(daneF[iter][0]*OBJ_DIM*xScale),(int)(daneF[iter][1]*OBJ_DIM*yScale),(int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
        }
        bomber.frontS=il.scaleI(bomber.front, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.backS=il.scaleI(bomber.back, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.leftS=il.scaleI(bomber.left, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.rightS=il.scaleI(bomber.right, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        
        bomber.ii = new ImageIcon(bomber.i);
        bomber.getLabel().setIcon(bomber.ii);
        bomber.getLabel().setSize((int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
 
        bomber.posX = bomber.posX*nWidth/bomber.lX;
        bomber.posY = bomber.posY*nHeight/bomber.lY;
        
        bomber.speedX =(int)(3*xScale);
        if(bomber.speedX==0){bomber.speedX=1;};
        bomber.speedY =(int)(3*yScale);
        if(bomber.speedY==0){bomber.speedY=1;};
        bomber.l.setBounds(bomber.posX , bomber.posY , bomber.l.getWidth() , bomber.l.getHeight());
        bomber.lX = nWidth;
        bomber.lY = nHeight;
        
        (kicker = new Thread(this)).start();
    }
	
	private void sleeep() {
        try {
            Thread.sleep(15);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (kicker == Thread.currentThread()) {
    		if(bomber.direction == 1)
    		{
    			bomber.i=bomber.leftS;
    		}
    		else if(bomber.direction == 2)
    		{
    			bomber.i=bomber.rightS;
    		}
    		else if(bomber.direction == 3)
    		{
    			bomber.i=bomber.backS;
    		}
    		else
    		{
    			bomber.i=bomber.frontS;
    		}
    		
    		bomber.ii = new ImageIcon(bomber.i);
    		bomber.l.setIcon(bomber.ii); 
    		sleeep();
        }
    }
    
	public void setKicker(Thread kicker) {
        this.kicker = kicker;
    }
}
