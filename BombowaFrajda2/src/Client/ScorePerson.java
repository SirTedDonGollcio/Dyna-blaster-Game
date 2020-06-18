package Client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
/*
 * Klasa przechowuj¹ca dane po ukoñczeniu rozgrywki: nick gracza, iloœc punktów, czas gry
 */
public class ScorePerson implements Serializable{
	
	public int id;
	public String nick;
	public int score;
	public String time;
	public int stage;
	public ScorePerson next;
	
	public ScorePerson() {
		id = 0;
		nick = "Default";
		score = 0;
		time = "00:00:00";
		stage = 0;
		next = null;
	}
	public ScorePerson(int id,String nick,int score,String time,int stage)
	{
		this.id = id;
		this.nick=nick;
		this.score=score;
		this.stage=stage;
		this.time=time;
		next =null;
	}
	
	@Override
	public String toString() {
		return "Id:" + id + "\nNick: " + nick + "\nScore: " + score + "\nStage: " + stage + "\nTime: " + time + "\n";
	}
}
