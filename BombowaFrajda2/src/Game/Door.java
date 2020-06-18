package Game;

import javax.swing.ImageIcon;

public class Door extends ObjectCreator implements Runnable{
	
	public Bomber player;
	public int lX=645;
	public int lY=645;
	public Thread kicker = null;
	/*
	 * Klasa mówi¹ca gdzie i jak du¿e s¹ drzwi po znalezieniu ich pod ktoryms z kamieni - pozwala na przejscie przez nie dopiero po podniesieniu klucza
	 */
	public Door(int x, int y,Bomber bomber) {
		
		posX=x;
		posY=y;
		player=bomber;
		sizeX=player.bombSizeX;
		sizeY=player.bombSizeY;
		i = il.imageL("Images/door.png");
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
	    l.setBounds(x,y,sizeX,sizeY);
		}
	
	private void sleeep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ie) {
        	//System.out.println("przrwanie sleep");
        }
    }
	
	public void run()
	{
		while (kicker == Thread.currentThread()) {
			
			sleeep();
			
			if((((player.posX+player.sizeX)>=(posX)&&(player.posX+player.sizeX)<=(posX+sizeX)))||((player.posX<=(posX+sizeX)&&player.posX>=(posX))))
			{
				if((((player.posY+player.sizeY)>=(posY))&&((player.posY+player.sizeY)<=(posY+sizeY)))||((player.posY<=(posY+sizeY)&&player.posY>=(posY))))
				{
					if(player.czyMamKlucz)
					{
						player.nextLevel=true;
					}
				}
			}
			
		}
	}
	
}


