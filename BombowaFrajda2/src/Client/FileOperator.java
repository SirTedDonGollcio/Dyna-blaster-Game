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
}
