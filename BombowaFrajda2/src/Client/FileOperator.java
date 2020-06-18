package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

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
			
			scoreList = new ScoreList(ile+5);

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
	public void saveLevel(Level level,String path)
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
	
	public Level readLevel(String path)
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
		} 
		
		return level;
	}
	
	public Level readLevel2(String pathName)
	{
		Level level = new Level(1,1,1);
		try {
			Path path = Paths.get(pathName);
	        Scanner scanner = new Scanner(path);
	        
			int iScian = scanner.nextInt();
			int iFragScian = scanner.nextInt();
			int iPajakow = scanner.nextInt();
			
			level = new Level(iScian,iFragScian,iPajakow);
			
			for(int iter=0;iter<iScian;iter++)
			{
				level.daneW[iter][0]=scanner.nextInt();
				level.daneW[iter][1]=scanner.nextInt();
			}
			for(int iter=0;iter<iFragScian;iter++)
			{
				level.daneF[iter][0]=scanner.nextInt();
				level.daneF[iter][1]=scanner.nextInt();
			}
			for(int iter=0;iter<iPajakow;iter++)
			{
				level.daneP[iter][0]=scanner.nextInt();
				level.daneP[iter][1]=scanner.nextInt();
			}
			
			level.pozycjaBomberaX = scanner.nextInt();
			level.pozycjaBomberaY = scanner.nextInt();
			level.iloscZycia = scanner.nextInt();
			level.iloscBomb = scanner.nextInt();
			for(int iter=0;iter<iFragScian;iter++)
			{
				level.daneB[iter]=scanner.nextInt();
			}
			level.monsterKeyNumber = scanner.nextInt();
			scanner.nextLine();
			level.nextLevelName=scanner.nextLine();
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
		
		return level;
	}
}
