package Game;

import javax.swing.ImageIcon;

public class Diamond extends ObjectCreator{
	
	public Bomber player;
	public int lX=645;
	public int lY=645;
	
	public Diamond(int x, int y,Bomber bomber) {
		
		posX=x;
		posY=y;
		sizeX=player.bombSizeX;
		sizeY=player.bombSizeY;
		i = il.imageL("Images/diamond.png");
		ii = new ImageIcon(il.scaleI(i, sizeX, sizeY));
		l.setIcon(ii);
	    l.setBounds(x,y,sizeX,sizeY);
		}

}
