package Client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;
import Client.ScorePerson;
/*
 * klasa s³u¿¹ca do przechowywania wielu rekordów w formie listy
 */
public class ScoreList {
	public int howMany = 0;
	public int howManyMax;
	public ScorePerson scorePeople[];
	public ScorePerson first;
	public ScorePerson last;
	
	public ScoreList(int howMany)
	{
		this.howManyMax = howMany;
		scorePeople = new ScorePerson[this.howManyMax];
		first=null;
		last = null;
	}
	/*
	 * Funkcja s³u¿¹ca doodawania kolejnego najlepszego wyniku do listy
	 */
	public void addToList(ScorePerson newPerson)
	{		
		scorePeople[howMany] = newPerson;
		scorePeople[howMany].id = howMany+1;
		if(howMany==0)
		{
			first = scorePeople[howMany];
		}
		else
		{
			scorePeople[howMany-1].next = scorePeople[howMany];
			last = scorePeople[howMany];
		}
		howMany++;
	}
	/*
	 * Funkcja s³u¿¹ca do sortowania najlepszych wyników wzglêdem zdobytych punktów
	 */
	public void sortList()
	{
		int posortowane =1;
		ScorePerson temp = new ScorePerson();
		while(posortowane!=0)
		{
			posortowane=0;
			for(int iter=0;iter<howMany-1;iter++)
			{
				if(scorePeople[iter].score < scorePeople[iter+1].score)
				{
					temp = scorePeople[iter];
					scorePeople[iter] = scorePeople[iter+1];
					scorePeople[iter+1] = temp;
					posortowane++;
				}
			}
			first = scorePeople[0];
			last = scorePeople[howMany-1];
		}
	}
}
