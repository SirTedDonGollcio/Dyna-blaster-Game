package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ExplosionRight extends ObjectCreator implements Runnable{
	public Thread kicker = null;
	public boolean isEnded=false;
	public JLabel game;
	Bomber player;
	public int xBound;
	/*
	 * Klasa odpowiedzialna za rozrastanie si? wybuchu w prawo
	 */
	public ExplosionRight(int x,int y,int sX,int sY,Bomber bombi, int maxWidth,JLabel game)
	{
		this.game = game;
		xBound = maxWidth;
		player = bombi;
		posX=x;
		posY=y;
		sizeX=sX;
		sizeY=sY;
		i = il.imageL("Images/explosionRight.png");
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		l.setIcon(ii);
	    l.setBounds(x,y,sizeX,sizeY);
	        }
		});
	}
	private void sleeep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run()
	{
		while(!isEnded)
		{
			sleeep();
		//	System.out.print("Right");	
		expand();
		isEnded = isColiding();
		}
		kicker=null;

	}
	
	public boolean isColiding()
	{
		boolean b = false;
		for(int iter=0;iter<player.iloscScian;iter++)
		{
			if((((posX+sizeX)>(player.walls[iter].posX))&&((posX+sizeX)<(player.walls[iter].posX+player.walls[iter].sizeX)))&&(((posY>player.walls[iter].posY)&&(posY<(player.walls[iter].posY+player.walls[iter].sizeY)))||(((posY+sizeY)>player.walls[iter].posY)&&((posY+sizeY)<(player.walls[iter].posY+player.walls[iter].sizeY)))))
			{
				b=true;
				//System.out.print("RIG Wybuch na scianie sie konczy\n");
				return b;
			}	
		}
		for(int iter=0;iter<player.iloscFragScian;iter++)
		{
			if((((posX+sizeX)>(player.fragWalls[iter].posX))&&((posX+sizeX)<(player.fragWalls[iter].posX+player.fragWalls[iter].sizeX)))&&(((posY>player.fragWalls[iter].posY)&&(posY<(player.fragWalls[iter].posY+player.fragWalls[iter].sizeY)))||(((posY+sizeY)>player.fragWalls[iter].posY)&&((posY+sizeY)<(player.fragWalls[iter].posY+player.fragWalls[iter].sizeY)))))
			{
				b=true;
				if(player.fragWalls[iter].wypadnik==1)
				{
					//System.out.print("Diamencik\n");
					Diamond diamond = new Diamond(player.fragWalls[iter].posX,player.fragWalls[iter].posY,player);
					game.add(diamond.getLabel());
					player.diamonds[player.iloscDiamentowNaMapie] = diamond;
					player.iloscDiamentowNaMapie+=1;
					(diamond.kicker= new Thread(diamond)).start();
				}
				else if(player.fragWalls[iter].wypadnik==2)
				{
					//System.out.print("Drzwiczki\n");
					Door door = new Door(player.fragWalls[iter].posX,player.fragWalls[iter].posY,player);
					game.add(door.getLabel());
					player.door = door;
					(door.kicker= new Thread(door)).start();
				}
				player.fragWalls[iter].posX=5000;
				player.fragWalls[iter].posY=5000;
				player.fragWalls[iter].l.setVisible(false);
				//System.out.print("RIG Zniszczenie Kamienia\n" + sizeX);
				return b;
			}
		}
		for(int iter=0;iter<player.iloscPajakow;iter++)
		{
			if((((posX+sizeX)>(player.spiders[iter].posX))&&((posX+sizeX)<(player.spiders[iter].posX+player.spiders[iter].sizeX)))&&(((posY>player.spiders[iter].posY)&&(posY<(player.spiders[iter].posY+player.spiders[iter].sizeY)))||(((posY+sizeY)>player.spiders[iter].posY)&&((posY+sizeY)<(player.spiders[iter].posY+player.spiders[iter].sizeY)))))
			{
				b=true;
				if(player.spiders[iter].haveKey)
				{
					//System.out.print("Diamencik\n");
					Key key = new Key(player.spiders[iter].posX,player.spiders[iter].posY,player);
					game.add(key.getLabel());
					player.key=key;
					(key.kicker= new Thread(key)).start();
				}
				player.spiders[iter].posX=5000;
				player.spiders[iter].posY=5000;
				player.spiders[iter].l.setVisible(false);
				player.spiders[iter].kicker=null;
				player.iloscPunktow+=10;
				//System.out.print("RIG MORTE\n");
				return b;
			}
		}
		if((((posX+sizeX)>(player.posX))&&((posX+sizeX)<(player.posX+player.sizeX)))&&(((posY>player.posY)&&(posY<(player.posY+player.sizeY)))||(((posY+sizeY)>player.posY)&&((posY+sizeY)<(player.posY+player.sizeY)))))
		{
			b=true;
			player.iloscZycia--;
			//System.out.print("UP BUM\n");
			return b;
		}
		if(posX+sizeX>xBound)
		{
			b=true;
			//System.out.print("Rozbite o graniceRight\n");
			kicker = null;
			return b;
		}
		return b;
	}
	
	public void expand()
	{
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		sizeX++;
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
		l.setBounds(posX,posY,sizeX,sizeY);
	        };
		});
	}
}
