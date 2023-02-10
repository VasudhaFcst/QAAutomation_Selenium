package Application;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Picture {
	BufferedImage myPicture=null;
	
	public Image getImage(Component label,URL pic) {
		try {
			myPicture = ImageIO.read(pic);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image image=new ImageIcon(myPicture).getImage();
		Image newImage=image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		return newImage;
	}
}
