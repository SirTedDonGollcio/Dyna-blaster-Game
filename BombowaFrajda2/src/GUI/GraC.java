
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
import Game.Spider;
import Client.Timer;
import Client.FileOperator;
import Client.Level;

public class GraC implements Runnable{
    
	final int FRAME_WIDTH = 645;
    final int FRAME_HEIGHT = 810;
    final int GAME_F_DIM = 645;
    final int GAME_DIM = 605;
    final int GAME_START = 20;
    final int OBJ_DIM = 55;
	
    FileOperator fo = new FileOperator("levelFile.txt");
    Timer myTimer = new Timer(1000);
    ImageLoader il = new ImageLoader();
    KeyFrame f = new KeyFrame("Bombowa Frajda");
    JLabel userGUI = new JLabel();
    HealthBar hb = new HealthBar();
    BombBar bb = new BombBar();
    
    BufferedImage image = il.imageL("Images/game_background.png");
    ImageIcon backgroundIcon = new ImageIcon(image);
    JLabel gameFrames = new JLabel();
    JLabel game = new JLabel();
    
    //tymaczowe dane do kreacji obiektow
    /*int iloscScian = 25;
    //int[][] dane = new int[iloscScian][2];
    int[][] daneW = {{1,1},{3,1},{5,1},{7,1},{9,1},
    		{1,3},{3,3},{5,3},{7,3},{9,3},
    		{1,5},{3,5},{5,5},{7,5},{9,5},
    		{1,7},{3,7},{5,7},{7,7},{9,7},
    		{1,9},{3,9},{5,9},{7,9},{9,9}};        
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
    int iloscPajakow = 5;
    //int[][] dane = new int[iloscPajakow][2];
    int[][] daneP = {{5,0},{7,2},{2,4},{5,6},{5,10}};
    */
    Level level = fo.readLevel();
    int iloscScian = level.ileScian;
    int[][] daneW = new int[iloscScian][2];
    int iloscFragscian = level.ileFragScian;
    int[][] daneF = new int[iloscFragscian][2];
    int iloscPajakow = level.ilePajakow;
    int[][] daneP = new int[iloscPajakow][2];

    Wall walls[] = new Wall[iloscScian];
    FragileWall fragWalls[] = new FragileWall[iloscFragscian];
    Spider spiders[] = new Spider[iloscPajakow];
    Thread[] pajeczaki = new Thread[iloscPajakow];
    
    Bomber bomber;
    
    Thread kicker = null;
    
    JLabel healthBar;
    JLabel bombBar;
    //Level level = new Level(iloscScian,iloscFragscian,iloscPajakow);
    
    
    
	public GraC(){
		/*level.iloscBomb = bomber.iloscBomb;
		level.iloscZycia = bomber.iloscZycia;
		level.pozycjaBomberaX = 0;
		level.pozycjaBomberaY=0;
		fo.saveLevel(level);*/
		for(int iter=0;iter<iloscScian;iter++)
		{
			daneW[iter][0]=level.daneW[iter][0];
			daneW[iter][1]=level.daneW[iter][1];
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			daneF[iter][0]=level.daneF[iter][0];
			daneF[iter][1]=level.daneF[iter][1];
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			daneP[iter][0]=level.daneP[iter][0];
			daneP[iter][1]=level.daneP[iter][1];
		}
		bomber = new Bomber(level.pozycjaBomberaX,level.pozycjaBomberaY);
		bomber.iloscBomb = level.iloscBomb;
		bomber.iloscZycia = level.iloscZycia;
		healthBar = hb.healthBar(bomber);
	    bombBar = bb.bombBar(bomber);
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
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
        (hb.kicker = new Thread(hb)).start();
        (bb.kicker = new Thread(bb)).start();
        
        game.setBounds(GAME_START,GAME_START, GAME_DIM, GAME_DIM);
        
        gameFrames.add(game);
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setBackground(new Color(64, 62, 60));
        f.setLocationRelativeTo(null);
        f.game = game;
        
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
        
        for(int iter = 0;iter<iloscPajakow;iter++)
        {
        	Spider spider = new Spider(daneP[iter][0],daneP[iter][1]);
        	spiders[iter] = spider;
        	(spiders[iter].kicker= new Thread(spider)).start();
        	/*try {
                spiders[iter].kicker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        	//spiders[iter].kicker.run();
        	game.add(spiders[iter].getLabel());
        }     
        
        game.add(bomber.getLabel());
        f.player = bomber;
        bomber.lX = f.getWidth();
        bomber.lY = f.getHeight();
        

        for(int iter = 0;iter<iloscPajakow;iter++)
        {
        	spiders[iter].walls = walls;
        	spiders[iter].fragileWalls = fragWalls;
        	spiders[iter].spiders = spiders;
        	spiders[iter].player = bomber;
        }
        
        bomber.iloscScian = iloscScian;
        bomber.iloscFragScian = iloscFragscian;
        bomber.iloscPajakow = iloscPajakow;
        bomber.walls = walls;
        bomber.fragWalls = fragWalls;
        bomber.spiders = spiders;
        
        updateOffscreenSize();
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.ruch=null;
            	f.dispose();
                kicker = null;
                for(int iter=0;iter<iloscPajakow;iter++)
                {
                	spiders[iter].kicker = null;
                }
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
        	walls[iter].posX = (int)(daneW[iter][0]*OBJ_DIM*xScale);
        	walls[iter].posY = (int)(daneW[iter][1]*OBJ_DIM*yScale);
        	walls[iter].sizeX = (int)(OBJ_DIM*xScale);
        	walls[iter].sizeY = (int)(OBJ_DIM*yScale);
        }
        for(int iter = 0; iter <iloscFragscian;iter++)
        {
        	
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(fragWalls[iter].i,(int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
        	fragWalls[iter].getLabel().setIcon(imageIcon);
        	fragWalls[iter].getLabel().setBounds((int)(daneF[iter][0]*OBJ_DIM*xScale),(int)(daneF[iter][1]*OBJ_DIM*yScale),(int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
        	fragWalls[iter].posX = (int)(daneF[iter][0]*OBJ_DIM*xScale);
        	fragWalls[iter].posY = (int)(daneF[iter][1]*OBJ_DIM*yScale);
        	fragWalls[iter].sizeX = (int)(OBJ_DIM*xScale);
        	fragWalls[iter].sizeY = (int)(OBJ_DIM*yScale);
        }
        for(int iter = 0; iter <iloscPajakow;iter++)
        {
        	
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(spiders[iter].i,(int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
        	spiders[iter].getLabel().setIcon(imageIcon);
        	spiders[iter].getLabel().setBounds((int)(daneP[iter][0]*OBJ_DIM*xScale),(int)(daneP[iter][1]*OBJ_DIM*yScale),(int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
        	spiders[iter].posX = (int)(daneP[iter][0]*OBJ_DIM*xScale);
        	spiders[iter].posY = (int)(daneP[iter][1]*OBJ_DIM*yScale);
        	spiders[iter].sizeX = (int)(OBJ_DIM*xScale);
        	spiders[iter].sizeY = (int)(OBJ_DIM*yScale);
        }
        bomber.bombSizeX = (int)(OBJ_DIM*xScale);
    	bomber.bombSizeY = (int)(OBJ_DIM*yScale);
        for(int iter = 0; iter<bomber.currentBombs;iter++)
        {
        	
        	
        	bomber.bombs[iter].sizeX = (int)(OBJ_DIM*xScale);
        	bomber.bombs[iter].sizeY = (int)(OBJ_DIM*yScale);
        	
        	bomber.bombs[iter].posX = (int)(bomber.bombs[iter].posX*nWidth/bomber.bombs[iter].lX);
        	bomber.bombs[iter].posY = (int)(bomber.bombs[iter].posY*nHeight/bomber.bombs[iter].lY);
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(bomber.bombs[iter].i,bomber.bombs[iter].sizeX,bomber.bombs[iter].sizeY));
        	bomber.bombs[iter].getLabel().setIcon(imageIcon);
        	bomber.bombs[iter].getLabel().setBounds(bomber.bombs[iter].posX,bomber.bombs[iter].posY,bomber.bombs[iter].sizeX,bomber.bombs[iter].sizeY);
        	bomber.bombs[iter].lX = nWidth;
        	bomber.bombs[iter].lY = nHeight;
        }
        
        
        bomber.frontS=il.scaleI(bomber.front, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.backS=il.scaleI(bomber.back, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.leftS=il.scaleI(bomber.left, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        bomber.rightS=il.scaleI(bomber.right, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale));
        
        bomber.ii = new ImageIcon(bomber.i);
        bomber.getLabel().setIcon(bomber.ii);
        bomber.getLabel().setSize((int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
        bomber.sizeX =(int)(OBJ_DIM*xScale);
        bomber.sizeY =(int)(OBJ_DIM*yScale);
 
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
            Thread.sleep(25);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run() {
    	while (kicker == Thread.currentThread()) {
    		if(!hb.alive)
    		{
    			
            	f.dispose();
                
                
                MenuC f1 = new MenuC();
                (f1.kicker = new Thread(f1)).start();
                f.ruch=null;
                for(int iter=0;iter<iloscPajakow;iter++)
                {
                	spiders[iter].kicker = null;
                }
                kicker = null;
                
    		}
    		
    		sleeep();
    		//System.out.print(bomber.currentBombs + "\n");
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
    		//bomber.l.setOpaque(true);
            //bomber.l.setBackground(new Color(64, 62, 60));
    		bomber.isColD = isColidingDown();
    		bomber.isColU = isColidingUp();
    		bomber.isColR = isColidingRight();
    		bomber.isColL = isColidingLeft();
    		
        }
    }
    
	public void setKicker(Thread kicker) {
        this.kicker = kicker;
    }
	
		
		
	public boolean isColidingDown()
	{
		boolean b=false;
		for(int iter=0;iter<iloscScian;iter++)
		{
			if(((walls[iter].posY - (bomber.posY+bomber.sizeY) <= 0)&&(walls[iter].posY - (bomber.posY+bomber.sizeY) >= -8*bomber.sizeY/55))&&((((walls[iter].posX+walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((walls[iter].posX+walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<walls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-walls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-walls[iter].posX<walls[iter].sizeX))))
			{
				b=true;
				//System.out.print("Koliduje na dole od bomber - sciana\n");
			}
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			if(((fragWalls[iter].posY - (bomber.posY+bomber.sizeY) <= 0)&&(fragWalls[iter].posY - (bomber.posY+bomber.sizeY) >= -8*bomber.sizeY/55))&&((((fragWalls[iter].posX+fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((fragWalls[iter].posX+fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<fragWalls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-fragWalls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-fragWalls[iter].posX<fragWalls[iter].sizeX))))
			{
				b=true;
				//System.out.print("Koliduje na dole od bomber - kamien\n");
			}
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			if(((spiders[iter].posY - (bomber.posY+bomber.sizeY) <= 0)&&(spiders[iter].posY - (bomber.posY+bomber.sizeY) >= -8*bomber.sizeY/55))&&((((spiders[iter].posX+spiders[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((spiders[iter].posX+spiders[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<spiders[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-spiders[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-spiders[iter].posX<spiders[iter].sizeX))))
			{
				if(myTimer.flag==0)
				{
				bomber.iloscZycia--;
				myTimer.wlacz();
				}
				b=true;
				//System.out.print("Koliduje na dole od bomber - pajak\n");
			}
		}
		
		if(game.getHeight() - (bomber.posY+bomber.sizeY)<=0)
		{
			b=true;
			//System.out.print("Koliduje na dole od bomber - granica\n");
		}
		
		return b;
	}
	public boolean isColidingUp()
	{
		boolean b=false;
		for(int iter=0;iter<iloscScian;iter++)
		{
			if((((bomber.posY) - (walls[iter].posY+walls[iter].sizeY) <= 0)&&((bomber.posY) - (walls[iter].posY+walls[iter].sizeY) >= -8*bomber.sizeY/55))&&((((walls[iter].posX+walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((walls[iter].posX+walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<walls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-walls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-walls[iter].posX<walls[iter].sizeX))))
			{
				b=true;
				//System.out.print("Koliduje na gorze od bomber - sciana\n");
			}	
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			if((((bomber.posY) - (fragWalls[iter].posY+fragWalls[iter].sizeY) <= 0)&&((bomber.posY) - (fragWalls[iter].posY+fragWalls[iter].sizeY) >= -8*bomber.sizeY/55))&&((((fragWalls[iter].posX+fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((fragWalls[iter].posX+fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<fragWalls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-fragWalls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-fragWalls[iter].posX<fragWalls[iter].sizeX))))
			{
				b=true;
				//System.out.print("Koliduje na gorze od bomber - kamien\n");
			}
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			if((((bomber.posY) - (spiders[iter].posY+spiders[iter].sizeY) <= 0)&&((bomber.posY) - (spiders[iter].posY+spiders[iter].sizeY) >= -8*bomber.sizeY/55))&&((((spiders[iter].posX+spiders[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((spiders[iter].posX+spiders[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<spiders[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-spiders[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-spiders[iter].posX<spiders[iter].sizeX))))
			{
				if(myTimer.flag==0)
				{
				bomber.iloscZycia--;
				myTimer.wlacz();
				}
				b=true;
				//System.out.print("Koliduje na gorze od bomber - pajak\n");
			}
		}
		
		if(bomber.posY<=0)
		{
			b=true;
			//System.out.print("Koliduje na gorze od bomber - granica\n");
		}
		
		return b;
	}
	public boolean isColidingRight()
	{
		boolean b=false;
		for(int iter=0;iter<iloscScian;iter++)
		{
			if(((walls[iter].posX - (bomber.posX+bomber.sizeX) <= 0)&&(walls[iter].posX - (bomber.posX+bomber.sizeX) >= -8*bomber.sizeX/55))&&((((walls[iter].posY+walls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((walls[iter].posY+walls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<walls[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-walls[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-walls[iter].posY<walls[iter].sizeY))))
			{
				b=true;
				//System.out.print("Koliduje na prawo od bomber - sciana\n");
			}
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			if(((fragWalls[iter].posX - (bomber.posX+bomber.sizeX) <= 0)&&(fragWalls[iter].posX - (bomber.posX+bomber.sizeX) >= -8*bomber.sizeX/55))&&((((fragWalls[iter].posY+fragWalls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((fragWalls[iter].posY+fragWalls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<fragWalls[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-fragWalls[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-fragWalls[iter].posY<fragWalls[iter].sizeY))))
			{
				b=true;
				//System.out.print("Koliduje na prawo od bomber - kamien\n");
			}
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			if(((spiders[iter].posX - (bomber.posX+bomber.sizeX) <= 0)&&(spiders[iter].posX - (bomber.posX+bomber.sizeX) >= -8*bomber.sizeX/55))&&((((spiders[iter].posY+spiders[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((spiders[iter].posY+spiders[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<spiders[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-spiders[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-spiders[iter].posY<spiders[iter].sizeY))))
			{
				if(myTimer.flag==0)
				{
				bomber.iloscZycia--;
				myTimer.wlacz();
				}
				b=true;
				//System.out.print("Koliduje na prawo od bomber - pajak\n");
			}
		}
		
		if(game.getWidth() - (bomber.posX+bomber.sizeX)<=0)
		{
			b=true;
			//System.out.print("Koliduje na prawo od bomber - granica\n");
		}
		return b;
	}
	public boolean isColidingLeft()
	{
		boolean b=false;
		for(int iter=0;iter<iloscScian;iter++)
		{
			if(((((bomber.posX)- (walls[iter].posX + walls[iter].sizeX)) <= 0)&&(((bomber.posX)- (walls[iter].posX + walls[iter].sizeX)) >= -8*bomber.sizeX/55))&&((((walls[iter].posY+walls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((walls[iter].posY+walls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<walls[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-walls[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-walls[iter].posY<walls[iter].sizeY))))
			{
				b=true;
				//System.out.print("Koliduje na lewo od bomber - sciana\n");
			}
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			if(((((bomber.posX)- (fragWalls[iter].posX + fragWalls[iter].sizeX)) <= 0)&&(((bomber.posX)- (fragWalls[iter].posX + fragWalls[iter].sizeX)) >= -8*bomber.sizeX/55))&&((((fragWalls[iter].posY+fragWalls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((fragWalls[iter].posY+fragWalls[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<fragWalls[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-fragWalls[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-fragWalls[iter].posY<fragWalls[iter].sizeY))))
			{
				b=true;
				//System.out.print("Koliduje na lewo od bomber - kamien\n");
			}
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			if(((((bomber.posX)- (spiders[iter].posX + spiders[iter].sizeX)) <= 0)&&(((bomber.posX)- (spiders[iter].posX + spiders[iter].sizeX)) >= -8*bomber.sizeX/55))&&((((spiders[iter].posY+spiders[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))>0)&&((spiders[iter].posY+spiders[iter].sizeY)-(bomber.posY+(2*bomber.sizeY/55))<spiders[iter].sizeY))||(((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-spiders[iter].posY>0)&&((bomber.posY + bomber.sizeY-(2*bomber.sizeY/55))-spiders[iter].posY<spiders[iter].sizeY))))
			{
				if(myTimer.flag==0)
				{
				bomber.iloscZycia--;
				myTimer.wlacz();
				}
				b=true;
				//System.out.print("Koliduje na lewo od bomber - pajak\n");
			}
		}
		
		if(bomber.posX<=0)
		{
			b=true;
			//System.out.print("Koliduje na lewo od bomber - granica\n");
		}
		return b;
	}
	
}
