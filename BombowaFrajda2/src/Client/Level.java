package Client;

import Game.Wall;
import Game.FragileWall;
import Game.Spider;
/*
 * Klasa s³u¿aca do przechowywania danych potrzebnych do stworzenia poziomu
 */
public class Level {
	public int ileScian;
	public int ileFragScian;
	public int ilePajakow;
	public int pozycjaBomberaX;
	public int pozycjaBomberaY;
	public int iloscZycia;
	public int iloscBomb;
	public int monsterKeyNumber;
	public String nextLevelName;
	public int[][] daneW= new int[121][2];
	public int[][] daneF = new int[121][2];
	public int[][] daneP= new int[121][2];
	public int[] daneB= new int[121];
	
	public Level(int iScian,int iFragScian,int iPajakow)
	{
		this.ileScian = iScian;
		this.ileFragScian = iFragScian;
		this.ilePajakow = iPajakow;
	}
}
