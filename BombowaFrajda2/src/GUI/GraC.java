
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

public class GraC {
    public GraC(){
    	final int FRAME_WIDTH = 645;
        final int FRAME_HEIGHT = 810;
        final int GAME_F_DIM = 645;
        final int GAME_DIM = 605;
        final int GAME_START = 20;
        final int OBJ_DIM = 55;
        
        ImageLoader il = new ImageLoader();
        JFrame f = new JFrame("Bombowa Frajda");
        
        
        JLabel userGUI = new JLabel();
        userGUI.setOpaque(true);
        userGUI.setBackground(new Color(64, 62, 60));
        userGUI.setBounds(0,0,FRAME_WIDTH,165);
        HealthBar hb = new HealthBar();
        BombBar bb = new BombBar();
        JLabel healthBar = hb.healthBar();
        JLabel bombBar = bb.bombBar();
        
        BufferedImage image = il.imageL("Images/game_background.png");
        ImageIcon backgroundIcon = new ImageIcon(image);
        JLabel gameFrames = new JLabel();
        gameFrames.setBounds(0, 166, GAME_F_DIM, GAME_F_DIM);
        gameFrames.setIcon(backgroundIcon);
       
        f.add(gameFrames);
        f.add(userGUI);
        userGUI.add(healthBar);
        userGUI.add(bombBar);
        
        
        JLabel game = new JLabel();
        game.setBounds(GAME_START,GAME_START, GAME_DIM, GAME_DIM);
        //game.setOpaque(true);
        //game.setBackground(new Color(64, 62, 60));
        
        gameFrames.add(game);
        
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setBackground(new Color(64, 62, 60));
        f.setLocationRelativeTo(null);
        
     // tymaczowe dane do kreacji obiektow
        int iloscScian = 25;
        //int[][] dane = new int[iloscScian][2];
        int[][] daneW = {{1,1},{3,1},{5,1},{7,1},{9,1},
        		{1,3},{3,3},{5,3},{7,3},{9,3},
        		{1,5},{3,5},{5,5},{7,5},{9,5},
        		{1,7},{3,7},{5,7},{7,7},{9,7},
        		{1,9},{3,9},{5,9},{7,9},{9,9}};        
       Wall walls[] = new Wall[iloscScian];
        for(int iter = 0; iter < iloscScian;iter++)
        {
        	Wall wall = new Wall(daneW[iter][0],daneW[iter][1]);
        	walls[iter] = wall;
        	game.add(walls[iter].getLabel());
        }
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
        for(int iter = 0;iter<iloscFragscian;iter++)
        {
        	FragileWall fragileWall = new FragileWall(daneF[iter][0],daneF[iter][1]);
        	fragWalls[iter] = fragileWall;
        	game.add(fragWalls[iter].getLabel());
        }
        
        Bomber bomber = new Bomber(0,0);
        game.add(bomber.getLabel());
        
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
                MenuC f1 = new MenuC();
            }
        });
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int nWidth = f.getWidth();
                int nHeight = f.getHeight()-165;
                float yScale = (float)nHeight/((float)GAME_F_DIM);
                float xScale = (float)nWidth/(float)GAME_F_DIM;
                
                userGUI.setBounds(0,0,nWidth,165);
                ImageIcon i1 = new ImageIcon(il.scaleI(image,(int)(GAME_F_DIM*xScale),(int)(GAME_F_DIM*yScale)));
                gameFrames.setIcon(i1);
                gameFrames.setBounds(0, 165, (int)(GAME_F_DIM*xScale), (int)(GAME_F_DIM*yScale));
                game.setBounds((int)(GAME_START*xScale),(int)(GAME_START*yScale),(int)(GAME_DIM*xScale),(int)(GAME_DIM*yScale));
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
                ImageIcon imageIcon = new ImageIcon(il.scaleI(bomber.i, (int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
                bomber.getLabel().setIcon(imageIcon);
                bomber.getLabel().setSize((int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
            }
        });
        
    }
}
