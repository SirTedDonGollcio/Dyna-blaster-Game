
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

public class GraC {
    public GraC(){
    	final int FRAME_WIDTH = 661;
        final int FRAME_HEIGHT = 845;
        final int GAME_F_DIM = 645;
        final int GAME_DIM = 605;
        final int GAME_START = 20;
        final int OBJ_DIM = 55;
        
        ImageLoader il = new ImageLoader();
        ObjectCreator oc = new ObjectCreator();
        JFrame f = new JFrame("Bombowa Frajda");
        
        
        JLabel userGUI = new JLabel();
        userGUI.setOpaque(true);
        userGUI.setBackground(new Color(64, 62, 60));
        userGUI.setBounds(0,0,FRAME_WIDTH,164);
        HealthBar hb = new HealthBar();
        BombBar bb = new BombBar();
        JLabel healthBar = hb.healthBar();
        JLabel bombBar = bb.bombBar();
        
        BufferedImage image = il.imageL("Images/game_background.png");
        ImageIcon backgroundIcon = new ImageIcon(image);
        JLabel gameFrames = new JLabel();
        gameFrames.setBounds(0, 165, GAME_F_DIM, GAME_F_DIM);
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
        int[][] dane = {{1,1},{3,1},{5,1},{7,1},{9,1},
        		{1,3},{3,3},{5,3},{7,3},{9,3},
        		{1,5},{3,5},{5,5},{7,5},{9,5},
        		{1,7},{3,7},{5,7},{7,7},{9,7},
        		{1,9},{3,9},{5,9},{7,9},{9,9}};        
       JLabel walls[] = new JLabel[iloscScian];
        for(int iter = 0; iter < iloscScian;iter++)
        {
        	walls[iter] = oc.wallCreator(dane[iter][0],dane[iter][1]);
        	game.add(walls[iter]);
        }
        
        
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
                int nHeight = f.getHeight();
                float yScale = (float)nHeight/(float)FRAME_HEIGHT;
                float xScale = (float)nWidth/(float)FRAME_WIDTH;
                
                userGUI.setBounds(0,0,nWidth,165);
                ImageIcon i1 = new ImageIcon(il.scaleI(image,(int)(GAME_F_DIM*xScale),(int)(GAME_F_DIM*yScale)));
                gameFrames.setIcon(i1);
                gameFrames.setBounds(0, 165, (int)(GAME_F_DIM*xScale), (int)(GAME_F_DIM*yScale));
                game.setBounds((int)(GAME_START*xScale),(int)(GAME_START*yScale),(int)(GAME_DIM*xScale),(int)(GAME_DIM*yScale));
                for(int iter = 0; iter <iloscScian;iter++)
                {
                	BufferedImage image = new BufferedImage(
                			walls[iter].getIcon().getIconWidth(),
                			walls[iter].getIcon().getIconHeight(),
                		    BufferedImage.TYPE_INT_ARGB);
                	Graphics g = image.createGraphics();
                	walls[iter].getIcon().paintIcon(null, g, 0,0);
                	g.dispose();
                	ImageIcon imageIcon = new ImageIcon(il.scaleI(image,(int)(OBJ_DIM*xScale),(int)(OBJ_DIM*yScale)));
                	walls[iter].setIcon(imageIcon);
                	walls[iter].setBounds((int)(dane[iter][0]*OBJ_DIM*xScale),(int)(dane[iter][1]*OBJ_DIM*yScale),(int)(OBJ_DIM*xScale),(int)( OBJ_DIM*yScale));
                }
            }
        });
        
    }
}
