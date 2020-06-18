
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
import Game.Key;
import Game.KeyFrame;
import Game.Spider;
import Client.Timer;
import Client.FileOperator;
import Client.Level;
import Game.Diamond;
import Game.Door;
import Parameters.Dimensions;

public class GraC implements Runnable{
    
	Dimensions p = new Dimensions();
	
	final int FRAME_WIDTH = p.FRAME_WIDTH;
    final int FRAME_HEIGHT = p.FRAME_HEIGHT;
    final int GAME_F_DIM = p.GAME_F_DIM;
    final int GAME_DIM = p.GAME_DIM;
    final int GAME_START = p.GAME_START;
    final int OBJ_DIM = p.OBJ_DIM;
	final int BGD_CLR_A = p.BGD_CLR_A;
	final int BGD_CLR_B = p.BGD_CLR_B;
	final int BGD_CLR_C = p.BGD_CLR_C;
	final int FRAME_HEIGHT_GUI= p.FRAME_HEIGHT_GUI;
	final int WEIRD_X_CONSTANT= p.WEIRD_X_CONSTANT;
	final int WEIRD_Y_CONSTANT= p.WEIRD_Y_CONSTANT;
	final int ANOTHER_WEIRD_Y_CONSTANT= p.ANOTHER_WEIRD_Y_CONSTANT;
    
    
    public FileOperator fo = new FileOperator("levelFile.txt");
    public Timer myTimer = new Timer(1000);
    public ImageLoader il = new ImageLoader();
    public KeyFrame f = new KeyFrame("Bombowa Frajda");
    public JLabel userGUI = new JLabel();
    public HealthBar hb = new HealthBar();
    public BombBar bb = new BombBar();
    public ScoreBar sb = new ScoreBar();
    public TimerBar tb = new TimerBar();
    
    public BufferedImage image = il.imageL("Images/game_background.png");
    public ImageIcon backgroundIcon = new ImageIcon(image);
    public JLabel gameFrames = new JLabel();
    public JLabel game = new JLabel();
    
    public Level level = fo.readLevel2("levelFile2.txt");
    public int iloscScian = level.ileScian;
    public int[][] daneW = new int[iloscScian][2];
    public int iloscFragscian = level.ileFragScian;
    public int[][] daneF = new int[iloscFragscian][2];
    public int iloscPajakow = level.ilePajakow;
    public int[][] daneP = new int[iloscPajakow][2];

    public Wall walls[] = new Wall[iloscScian];
    public FragileWall fragWalls[] = new FragileWall[iloscFragscian];
    public Spider spiders[] = new Spider[iloscPajakow];
    public Thread[] pajeczaki = new Thread[iloscPajakow];
    
    public Bomber bomber;
    
    public Thread kicker = null;
     
    public JLabel healthBar;
    public JLabel bombBar;
    public JLabel scoreBar;
    public JLabel timerBar;
    
    
    
    
	public GraC(){
		
		
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
		bomber.diamonds = new Diamond[iloscFragscian];
		healthBar = hb.healthBar(bomber);
	    bombBar = bb.bombBar(bomber);
	    scoreBar = sb.scoreBar(bomber);
	    timerBar = tb.timerBar(bomber);
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	    (f.ruch = new Thread(f)).start();
        userGUI.setOpaque(true);
        userGUI.setBackground(new Color(BGD_CLR_A,BGD_CLR_B,BGD_CLR_C));
        userGUI.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT_GUI);
        
        gameFrames.setBounds(0, FRAME_HEIGHT_GUI+1, GAME_F_DIM, GAME_F_DIM);
        gameFrames.setIcon(backgroundIcon);
       
        f.add(gameFrames);
        f.add(userGUI);
        userGUI.add(healthBar);
        userGUI.add(bombBar);
        userGUI.add(scoreBar);
        userGUI.add(timerBar);
        (hb.kicker = new Thread(hb)).start();
        (bb.kicker = new Thread(bb)).start();
        (sb.kicker = new Thread(sb)).start();
        (tb.kicker = new Thread(tb)).start();
        
        game.setBounds(GAME_START,GAME_START, GAME_DIM, GAME_DIM);
        
        gameFrames.add(game);
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setBackground(new Color(BGD_CLR_A, BGD_CLR_B, BGD_CLR_C));
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
        	
        	game.add(spiders[iter].getLabel());
        }

        for(int iter=0;iter<iloscFragscian;iter++)
		{
			fragWalls[iter].wypadnik = level.daneB[iter];
		}
        
        spiders[level.monsterKeyNumber].haveKey = true;
        
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
        
        bomber.iloscBomb = 5;
        
        updateOffscreenSize();
        EventQueue.invokeLater(() -> f.setVisible(true)); 
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.ruch=null;
            	f.dispose();
                kicker = null;
                /*for(int iter=0;iter<iloscPajakow;iter++)
                {
                	spiders[iter].kicker = null;
                }*/
                MenuC f1 = new MenuC();
                //KoniecGry kg = new KoniecGry(bomber.iloscPunktow,tb.stoper.godziny,tb.stoper.minuty,tb.stoper.sekundy);
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
    	
    	int nWidth = f.getWidth()-WEIRD_X_CONSTANT;
        int nHeight = f.getHeight()-WEIRD_Y_CONSTANT;
        float yScale = (float)nHeight/((float)GAME_F_DIM);
        float xScale = (float)nWidth/(float)GAME_F_DIM;
        
        userGUI.setBounds(0,0,nWidth,165);
        ImageIcon i1 = new ImageIcon(il.scaleI(image,(int)(GAME_F_DIM*xScale),(int)(GAME_F_DIM*yScale)));
        gameFrames.setIcon(i1);
        gameFrames.setBounds(0, FRAME_HEIGHT_GUI, (int)(GAME_F_DIM*xScale), (int)(GAME_F_DIM*yScale));
        game.setBounds((int)(GAME_START*xScale),(int)(GAME_START*yScale),(int)(GAME_DIM*xScale),(int)(GAME_DIM*yScale));
        
        nWidth = nWidth+WEIRD_X_CONSTANT;
        nHeight = nHeight +ANOTHER_WEIRD_Y_CONSTANT;
        
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
        
        for(int iter = 0; iter<bomber.iloscDiamentowNaMapie;iter++)
        {
        	bomber.diamonds[iter].sizeX = (int)(OBJ_DIM*xScale);
        	bomber.diamonds[iter].sizeY = (int)(OBJ_DIM*yScale);
        	
        	bomber.diamonds[iter].posX = (int)(bomber.diamonds[iter].posX*nWidth/bomber.diamonds[iter].lX);
        	bomber.diamonds[iter].posY = (int)(bomber.diamonds[iter].posY*nHeight/bomber.diamonds[iter].lY);
        	ImageIcon imageIcon = new ImageIcon(il.scaleI(bomber.diamonds[iter].i,bomber.diamonds[iter].sizeX,bomber.diamonds[iter].sizeY));
        	bomber.diamonds[iter].getLabel().setIcon(imageIcon);
        	bomber.diamonds[iter].getLabel().setBounds(bomber.diamonds[iter].posX,bomber.diamonds[iter].posY,bomber.diamonds[iter].sizeX,bomber.diamonds[iter].sizeY);
        	bomber.diamonds[iter].lX = nWidth;
        	bomber.diamonds[iter].lY = nHeight;
        }
        
        if(bomber.key!=null)
        {
	        bomber.key.sizeX = (int)(OBJ_DIM*xScale);
	    	bomber.key.sizeY = (int)(OBJ_DIM*yScale);
	    	
	    	bomber.key.posX = (int)(bomber.key.posX*nWidth/bomber.key.lX);
	    	bomber.key.posY = (int)(bomber.key.posY*nHeight/bomber.key.lY);
	    	ImageIcon imageIcon = new ImageIcon(il.scaleI(bomber.key.i,bomber.key.sizeX,bomber.key.sizeY));
	    	bomber.key.getLabel().setIcon(imageIcon);
	    	bomber.key.getLabel().setBounds(bomber.key.posX,bomber.key.posY,bomber.key.sizeX,bomber.key.sizeY);
	    	bomber.key.lX = nWidth;
	    	bomber.key.lY = nHeight;
        }
        
        if(bomber.door!=null)
        {
	        bomber.door.sizeX = (int)(OBJ_DIM*xScale);
	    	bomber.door.sizeY = (int)(OBJ_DIM*yScale);
	    	
	    	bomber.door.posX = (int)(bomber.door.posX*nWidth/bomber.door.lX);
	    	bomber.door.posY = (int)(bomber.door.posY*nHeight/bomber.door.lY);
	    	ImageIcon imageIcon = new ImageIcon(il.scaleI(bomber.door.i,bomber.door.sizeX,bomber.door.sizeY));
	    	bomber.door.getLabel().setIcon(imageIcon);
	    	bomber.door.getLabel().setBounds(bomber.door.posX,bomber.door.posY,bomber.door.sizeX,bomber.door.sizeY);
	    	bomber.door.lX = nWidth;
	    	bomber.door.lY = nHeight;
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
                
            	KoniecGry kg = new KoniecGry(bomber.iloscPunktow,tb.stoper.godziny,tb.stoper.minuty,tb.stoper.sekundy);
                //MenuC f1 = new MenuC();
                //(f1.kicker = new Thread(f1)).start();
                f.ruch=null;
                for(int iter=0;iter<iloscPajakow;iter++)
                {
                	spiders[iter].kicker = null;
                }
                kicker = null;
                
    		}
    		if(bomber.nextLevel)
    		{
    			bomber.nextLevel=false;
    			if(level.nextLevelName=="koniec")
    			{
    				KoniecGry kg = new KoniecGry(bomber.iloscPunktow,tb.stoper.godziny,tb.stoper.minuty,tb.stoper.sekundy);
    			}
    			else
    			{
    			levelChange();
    			}
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

    		
    		bomber.isColR = isColidingRight();
    		bomber.isColL = isColidingLeft();
    		bomber.isColD = isColidingDown();
    		bomber.isColU = isColidingUp();
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
			if(((bomber.walls[iter].posY - (bomber.posY+bomber.sizeY) <= 0)&&(bomber.walls[iter].posY - (bomber.posY+bomber.sizeY) >= -8*bomber.sizeY/55))&&((((bomber.walls[iter].posX+bomber.walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((bomber.walls[iter].posX+walls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<bomber.walls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-bomber.walls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-bomber.walls[iter].posX<bomber.walls[iter].sizeX))))
			{
				b=true;
				//System.out.print("Koliduje na dole od bomber - sciana\n");
			}
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			if(((bomber.fragWalls[iter].posY - (bomber.posY+bomber.sizeY) <= 0)&&(bomber.fragWalls[iter].posY - (bomber.posY+bomber.sizeY) >= -8*bomber.sizeY/55))&&((((bomber.fragWalls[iter].posX+bomber.fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))>0)&&((bomber.fragWalls[iter].posX+fragWalls[iter].sizeX)-(bomber.posX+(7*bomber.sizeX/55))<bomber.fragWalls[iter].sizeX))||(((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-bomber.fragWalls[iter].posX>0)&&((bomber.posX + bomber.sizeX-(7*bomber.sizeX/55))-bomber.fragWalls[iter].posX<bomber.fragWalls[iter].sizeX))))
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
	
	public void levelChange()
	{
		bomber.nextLevel = false;
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		bomber.door.l.setBounds(5000,5000,GAME_DIM,GAME_DIM);
		sleeep();
		for(int iter = 0; iter < iloscScian;iter++)
        {
        	game.remove(walls[iter].l);
        }
        
        for(int iter = 0;iter<iloscFragscian;iter++)
        {
        	game.remove(fragWalls[iter].l);
        }
        
        for(int iter = 0;iter<iloscPajakow;iter++)
        {
        	game.remove(spiders[iter].l);
        }
		Level level2 = fo.readLevel2(level.nextLevelName);
		//System.out.println(level.nextLevelName);
	    iloscScian = level2.ileScian;
	    daneW = new int[iloscScian][2];
	    iloscFragscian = level2.ileFragScian;
	    daneF = new int[iloscFragscian][2];
	    iloscPajakow = level2.ilePajakow;
	    daneP = new int[iloscPajakow][2];

	    walls = new Wall[iloscScian];
	    fragWalls = new FragileWall[iloscFragscian];
	    spiders = new Spider[iloscPajakow];
	    pajeczaki = new Thread[iloscPajakow];
	    
		gameFrames.remove(game);
		game = new JLabel();
    	game.setBounds(GAME_START,GAME_START, GAME_DIM, GAME_DIM);
        gameFrames.add(game);
        
		bomber.door.kicker=null;
		/*for(int iter=0;iter<bomber.iloscPajakow;iter++)
		{
			bomber.spiders[iter].kicker=null;
		}
		for(int iter=0;iter<bomber.iloscDiamentowNaMapie;iter++)
		{
			bomber.diamonds[iter].kicker=null;
		}*/
		
		bomber.diamonds = new Diamond[121];
		bomber.iloscDiamentowNaMapie=0;
		bomber.key=null;
		bomber.door=null;
		bomber.czyMamKlucz = false;
		for(int iter=0;iter<iloscScian;iter++)
		{
			daneW[iter][0]=level2.daneW[iter][0];
			daneW[iter][1]=level2.daneW[iter][1];
		}
		for(int iter=0;iter<iloscFragscian;iter++)
		{
			daneF[iter][0]=level2.daneF[iter][0];
			daneF[iter][1]=level2.daneF[iter][1];
		}
		for(int iter=0;iter<iloscPajakow;iter++)
		{
			daneP[iter][0]=level2.daneP[iter][0];
			daneP[iter][1]=level2.daneP[iter][1];
		}
		
		
	        	
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

	            for(int iter=0;iter<iloscFragscian;iter++)
	    		{
	    			fragWalls[iter].wypadnik = level2.daneB[iter];
	    		}
	            
	            spiders[level2.monsterKeyNumber].haveKey = true;
	            
	            bomber.posX = (1+level2.pozycjaBomberaX)*55*gameFrames.getWidth()/645;
	            bomber.posY = (1+level2.pozycjaBomberaY)*55*gameFrames.getHeight()/645;
	            game.add(bomber.getLabel());
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
	            level = level2;
	        }
	        
		});
		
	}
	
}
