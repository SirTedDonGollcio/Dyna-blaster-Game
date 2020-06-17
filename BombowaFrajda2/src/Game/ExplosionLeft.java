package Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ExplosionLeft extends ObjectCreator implements Runnable{
	public Thread kicker = null;
	public boolean isEnded=false;
	public JLabel game;
	Bomber player;
	
	
	public ExplosionLeft(int x,int y,int sX,int sY,Bomber bombi,JLabel game)
	{
		this.game = game;
		player = bombi;
		posX=x;
		posY=y;
		sizeX=sX;
		sizeY=sY;
		i = il.imageL("Images/explosionLeft.png");
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
		//	System.out.print("Left");
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
			if(((posX<(player.walls[iter].posX+player.walls[iter].sizeX))&&(posX>player.walls[iter].posX))&&(((posY>player.walls[iter].posY)&&(posY<(player.walls[iter].posY+player.walls[iter].sizeY)))||(((posY+sizeY)>player.walls[iter].posY)&&((posY+sizeY)<(player.walls[iter].posY+player.walls[iter].sizeY)))))
			{
				b=true;
				//System.out.print("LEFT Wybuch na scianie sie konczy\n");
				return b;
			}	
		}
		for(int iter=0;iter<player.iloscFragScian;iter++)
		{
			if(((posX<(player.fragWalls[iter].posX+player.fragWalls[iter].sizeX))&&(posX>player.fragWalls[iter].posX))&&(((posY>player.fragWalls[iter].posY)&&(posY<(player.fragWalls[iter].posY+player.fragWalls[iter].sizeY)))||(((posY+sizeY)>player.fragWalls[iter].posY)&&((posY+sizeY)<(player.fragWalls[iter].posY+player.fragWalls[iter].sizeY)))))
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
				player.fragWalls[iter].posX=5000;
				player.fragWalls[iter].posY=5000;
				player.fragWalls[iter].l.setVisible(false);
				//System.out.print("LEFTZniszczenie Kamienia\n");
				return b;
			}
		}
		for(int iter=0;iter<player.iloscPajakow;iter++)
		{
			if(((posX<(player.spiders[iter].posX+player.spiders[iter].sizeX))&&(posX>player.spiders[iter].posX))&&(((posY>player.spiders[iter].posY)&&(posY<(player.spiders[iter].posY+player.spiders[iter].sizeY)))||(((posY+sizeY)>player.spiders[iter].posY)&&((posY+sizeY)<(player.spiders[iter].posY+player.spiders[iter].sizeY)))))
			{
				b=true;
				player.spiders[iter].posX=5000;
				player.spiders[iter].posY=5000;
				player.spiders[iter].l.setVisible(false);
				//System.out.print("LEFT MORTE\n");
				return b;
			}
		}
		if(((posX<(player.posX+player.sizeX))&&(posX>player.posX))&&(((posY>player.posY)&&(posY<(player.posY+player.sizeY)))||(((posY+sizeY)>player.posY)&&((posY+sizeY)<(player.posY+player.sizeY)))))
		{
			b=true;
			player.iloscZycia--;
			//System.out.print("UP BUM\n");
			return b;
		}
		if(posX<0)
		{
			b=true;
			//System.out.print("Rozbite o graniceLeft\n");
			kicker = null;
			return b;
		}
		return b;
	}
	
	public void expand()
	{
		
		sizeX++;
		posX--;
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
		l.setBounds(posX,posY,sizeX,sizeY);
	        
	}
}
