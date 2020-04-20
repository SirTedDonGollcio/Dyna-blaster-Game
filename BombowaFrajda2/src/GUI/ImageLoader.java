package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class ImageLoader {
	
	
	
	public BufferedImage imageL(String imageLocation){
		
		BufferedImage image = null;
	    try
	    {
	      image = ImageIO.read(new File(imageLocation));
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    
	    return image;
	}
	
	public BufferedImage imageL(String imageLocation,int width,int high){
			
			BufferedImage image = new BufferedImage(width,high,BufferedImage.TYPE_INT_ARGB);
		    try
		    {
		      image = ImageIO.read(new File(imageLocation));
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		      System.exit(1);
		    }
		    
		    return image;
	}
	
	public BufferedImage scaleI(BufferedImage srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
