package Running;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import Common.Information;
import io.appium.java_client.android.AndroidDriver;


public class ScreenshotPage implements Information{
	public String imagepath;
	File f;
//	ReadProp rp= new ReadProp();
public ScreenshotPage(WebDriver driver, String dest,String name) throws Exception{
	imagepath=dest+name+".png";
//			rp.getPath().getProperty(SCREENSHOT_PATH)+"\\"+dest+".png";
	try {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		if(browserName.equalsIgnoreCase(Information.IE)){
		TakesScreenshot ts=(TakesScreenshot)driver;
		f=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(imagepath) );
		}
		else {
		Shutterbug.shootPage(driver, ScrollStrategy.HORIZONTALLY).withName(name).save(dest);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@SuppressWarnings("rawtypes")
public ScreenshotPage(AndroidDriver driver, String dest) throws Exception{
	imagepath=dest+".png";
	try {
		TakesScreenshot ts=(TakesScreenshot)driver;
		f=ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(f, new File(imagepath) );
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public String subImage(WebElement element, String dest) throws Exception{
	String subPath="C:\\testing\\screen\\"+dest+".png";
	int width=element.getSize().getWidth();
	int height= element.getSize().getHeight();
	
	int x=element.getLocation().getX();
	int y=element.getLocation().getY();
	
	BufferedImage image=ImageIO.read(f);
	BufferedImage test=image.getSubimage(x, y, width, height);
	ImageIO.write(test, "png", f);
	FileUtils.copyFile(f, new File(subPath));
	return subPath;
}
}
