package Game;

import javax.swing.ImageIcon;

public class Diamond extends ObjectCreator implements Runnable{
	
	public Bomber player;
	public int lX=645;
	public int lY=645;
	public Thread kicker = null;
	/*
	 * Klasa opisuj¹ca diamenty które wypdaj¹ z kamieni,
	 *  oraz nas³uchiwacz akcji który pilnuje czy gracz znajduje
	 *   siê w ich polu umozliwiaj¹c zebranie ich oraz zwi¹zane z nimi punktami
	 */
	public Diamond(int x, int y,Bomber bomber) {
		
		posX=x;
		posY=y;
		player=bomber;
		sizeX=player.bombSizeX;
		sizeY=player.bombSizeY;
		i = il.imageL("Images/diamond.png");
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
					l.setVisible(false);
					l.setBounds(5000,5000,0,0);
					player.iloscPunktow+=30;
					kicker=null;
				}
			}
			
		}
	}
	
}


