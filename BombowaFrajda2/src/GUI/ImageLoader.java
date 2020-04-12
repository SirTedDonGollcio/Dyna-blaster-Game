package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

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
}
