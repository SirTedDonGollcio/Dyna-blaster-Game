package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import Game.Bomber;

public class KeyFrame extends JFrame implements KeyListener{
	
	public Bomber player;
	public int direction = 4;
	
	public KeyFrame(String s)
	{
		super(s);
		addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
        	
        	player.l.setBounds(player.posX -player.speedX ,player.posY,player.l.getWidth(),player.l.getHeight());
        	player.posX = player.posX -player.speedX;
        	direction = 1;
        	
        }

        if (key == KeyEvent.VK_D) {
        	player.l.setBounds(player.posX + player.speedX,player.posY,player.l.getWidth(),player.l.getHeight());
        	player.posX = player.posX + player.speedX;
        	direction = 2;

        }

        if (key == KeyEvent.VK_W) {
        	player.l.setBounds(player.posX ,player.posY-player.speedY,player.l.getWidth(),player.l.getHeight());
        	player.posY = player.posY - player.speedY;
        	direction = 3;

        }

        if (key == KeyEvent.VK_S) {
        	player.l.setBounds(player.posX,player.posY+ player.speedY,player.l.getWidth(),player.l.getHeight());
        	player.posY = player.posY + player.speedY;
        	direction = 4;

        }
    }
	@Override
    public void keyReleased(KeyEvent e) {

        //System.out.print("Puszczono cos");
        
    }
	@Override
    public void keyTyped(KeyEvent e) {

        //System.out.print("Wpisano cos");

    }

}
