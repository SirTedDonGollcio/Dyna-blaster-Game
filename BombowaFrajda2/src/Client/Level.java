package Client;

import Game.Wall;
import Game.FragileWall;
import Game.Spider;

public class Level {
	public int ileScian;
	public int ileFragScian;
	public int ilePajakow;
	public int pozycjaBomberaX;
	public int pozycjaBomberaY;
	public Wall[] walls;
	public FragileWall[] fragWalls;
	public Spider[] spiders;
	public int iloscZycia;
	public int iloscBomb;
	public int[][] daneW= {{1,1},{3,1},{5,1},{7,1},{9,1},
    		{1,3},{3,3},{5,3},{7,3},{9,3},
    		{1,5},{3,5},{5,5},{7,5},{9,5},
    		{1,7},{3,7},{5,7},{7,7},{9,7},
    		{1,9},{3,9},{5,9},{7,9},{9,9}};
	public int[][] daneF = {{3,0},{7,0},{8,0},
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
	public int[][] daneP= {{5,0},{7,2},{2,4},{5,6},{5,10}};
	
	public Level(int iScian,int iFragScian,int iPajakow)
	{
		this.ileScian = iScian;
		this.ileFragScian = iFragScian;
		this.ilePajakow = iPajakow;
		walls = new Wall[ileScian];
		fragWalls = new FragileWall[ileFragScian];
		spiders = new  Spider[ilePajakow];
	}
}
