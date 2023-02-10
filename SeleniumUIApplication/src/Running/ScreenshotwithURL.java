package Running;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;


public class ScreenshotwithURL {
	public String imagepath;
public ScreenshotwithURL(WebDriver driver, String dest){
	try {
		imagepath=dest+".png";
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(dest+"_new.png"));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
