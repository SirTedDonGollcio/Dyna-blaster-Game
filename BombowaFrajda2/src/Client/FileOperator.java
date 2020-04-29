package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Client.ScorePerson;
import Client.ScoreList;
import Game.Wall;
import Game.FragileWall;
import Game.Spider;

public class FileOperator {

	
	public String path;
	FileOutputStream fo;
	ObjectOutputStream o;
	FileInputStream fi;
	ObjectInputStream i;
	
	public FileOperator(String path)
	{
		this.path = path;
		
	}
	
	public void saveScore(ScoreList list)
	{
		try {
			fo = new FileOutputStream(new File(path));
			o = new ObjectOutputStream(fo);
			
			list.sortList();
			o.writeInt(list.howMany);
			
			for(int iter=0;iter<list.howMany;iter++)
			{
				
				o.writeObject(list.scorePeople[iter]);
			}
			
			o.close();
			fo.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
		
	}
	public ScoreList readScore()
	{
		ScoreList scoreList = new ScoreList(1);
		try {
			fi = new FileInputStream(new File(path));
			i = new ObjectInputStream(fi);
			int ile = i.readInt();
			
			scoreList = new ScoreList(ile);

			for(int iter=0;iter<ile;iter++)
			{
				scoreList.addToList((ScorePerson) i.readObject());
			}

			i.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return scoreList;
	}
	public void saveLevel(Level level)
	{
		try {
			fo = new FileOutputStream(new File(path));
			o = new ObjectOutputStream(fo);
			
			o.writeInt(level.ileScian);
			o.writeInt(level.ileFragScian);
			o.writeInt(level.ilePajakow);
			
			
			for(int iter=0;iter<level.ileScian;iter++)
			{
				
				o.writeInt(level.daneW[iter][0]);
				o.writeInt(level.daneW[iter][1]);
			}
			for(int iter=0;iter<level.ileFragScian;iter++)
			{
				
				o.writeInt(level.daneF[iter][0]);
				o.writeInt(level.daneF[iter][1]);
			}
			for(int iter=0;iter<level.ilePajakow;iter++)
			{
				
				o.writeInt(level.daneP[iter][0]);
				o.writeInt(level.daneP[iter][1]);
			}
			
			for(int iter=0;iter<level.ileScian;iter++)
			{
				
				o.writeObject(level.walls[iter]);
			}
			for(int iter=0;iter<level.ileFragScian;iter++)
			{
				
				o.writeObject(level.fragWalls[iter]);
			}
			for(int iter=0;iter<level.ilePajakow;iter++)
			{
				
				o.writeObject(level.spiders[iter]);
			}
			o.writeInt(level.pozycjaBomberaX);
			o.writeInt(level.pozycjaBomberaY);
			o.writeInt(level.iloscZycia);
			o.writeInt(level.iloscBomb);
			o.close();
			fo.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
	
	public Level readLevel()
	{
		Level level = new Level(1,1,1);
		try {
			fi = new FileInputStream(new File(path));
			i = new ObjectInputStream(fi);
			int iScian = i.readInt();
			int iFragScian = i.readInt();
			int iPajakow = i.readInt();
			
			level = new Level(iScian,iFragScian,iPajakow);
			
			for(int iter=0;iter<iScian;iter++)
			{
				level.daneW[iter][0]=i.readInt();
				level.daneW[iter][1]=i.readInt();
			}
			for(int iter=0;iter<iFragScian;iter++)
			{
				level.daneF[iter][0]=i.readInt();
				level.daneF[iter][1]=i.readInt();
			}
			for(int iter=0;iter<iPajakow;iter++)
			{
				level.daneP[iter][0]=i.readInt();
				level.daneP[iter][1]=i.readInt();
			}
			
			for(int iter=0;iter<iScian;iter++)
			{
				level.walls[iter]=(Wall) i.readObject();
			}
			for(int iter=0;iter<iFragScian;iter++)
			{
				level.fragWalls[iter]=(FragileWall) i.readObject();
			}
			for(int iter=0;iter<iPajakow;iter++)
			{
				level.spiders[iter]=(Spider) i.readObject();
			}
			
			level.pozycjaBomberaX = i.readInt();
			level.pozycjaBomberaY = i.readInt();
			level.iloscZycia = i.readInt();
			level.iloscBomb = i.readInt();

			i.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return level;
	}
}
