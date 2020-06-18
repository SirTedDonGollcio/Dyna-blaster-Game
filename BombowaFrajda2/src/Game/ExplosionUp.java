package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ExplosionUp extends ObjectCreator implements Runnable{
	public Thread kicker = null;
	public boolean isEnded=false;
	public JLabel game;
	Bomber player;
	
	public ExplosionUp(int x,int y,int sX,int sY,Bomber bombi,JLabel game)
	{
		this.game = game;
		player = bombi;
		posX=x;
		posY=y;
		sizeX=sX;
		sizeY=sY;
		i = il.imageL("Images/explosionUp.png");
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
		l.setIcon(ii);
	    l.setBounds(x,y,sizeX,sizeY);
	        };
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
		//System.out.print("Up");
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
			if(((posY<(player.walls[iter].posY+player.walls[iter].sizeY))&&(posY>player.walls[iter].posY))&&(((posX>player.walls[iter].posX)&&(posX<(player.walls[iter].posX+player.walls[iter].sizeX)))||(((posX+sizeX)>player.walls[iter].posX)&&((posX+sizeX)<(player.walls[iter].posX+player.walls[iter].sizeX)))))
			{
				b=true;
				//System.out.print("UP Wybuch na scianie sie konczy\n");
				return b;
			}	
		}
		for(int iter=0;iter<player.iloscFragScian;iter++)
		{
			if(((posY<(player.fragWalls[iter].posY+player.fragWalls[iter].sizeY))&&(posY>player.fragWalls[iter].posY))&&(((posX>player.fragWalls[iter].posX)&&(posX<(player.fragWalls[iter].posX+player.fragWalls[iter].sizeX)))||(((posX+sizeX)>player.fragWalls[iter].posX)&&((posX+sizeX)<(player.fragWalls[iter].posX+player.fragWalls[iter].sizeX)))))
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
				//System.out.print("UP Zniszczenie Kamienia\n");
				return b;
			}
		}
		for(int iter=0;iter<player.iloscPajakow;iter++)
		{
			if(((posY<(player.spiders[iter].posY+player.spiders[iter].sizeY))&&(posY>player.spiders[iter].posY))&&(((posX>player.spiders[iter].posX)&&(posX<(player.spiders[iter].posX+player.spiders[iter].sizeX)))||(((posX+sizeX)>player.spiders[iter].posX)&&((posX+sizeX)<(player.spiders[iter].posX+player.spiders[iter].sizeX)))))
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
				//System.out.print("UP MORTE\n");
				return b;
			}
		}
		if(((posY<(player.posY+player.sizeY))&&(posY>player.posY))&&(((posX>player.posX)&&(posX<(player.posX+player.sizeX)))||(((posX+sizeX)>player.posX)&&((posX+sizeX)<(player.posX+player.sizeX)))))
		{
			b=true;
			player.iloscZycia--;
			//System.out.print("UP BUM\n");
			return b;
		}
		if(posY<0)
		{
			b=true;
			//System.out.print("Rozbite o graniceUp\n");
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
		sizeY++;
		posY--;
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
		l.setBounds(posX,posY,sizeX,sizeY);
	        };
		});
	}
}
