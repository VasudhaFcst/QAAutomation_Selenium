package MobileKeywords;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Excelresult;
import AdditionalSetup.Fluent;
import AdditionalSetup.ResultUpdation;
import AdditionalSetup.ResultScreenshot;
import AndroidMobile.MobileLocators;
import Common.Information;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Swipe implements Information{
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	MobileLocators ml;
	Fluent f;
	ExtentTest test;
	Excelresult exr;
	ResultScreenshot rs;
	@SuppressWarnings("rawtypes")
	public Swipe(AndroidDriver driver,ExtentTest test) throws Exception {
		this.driver=driver;
		this.test=test;
		ml=new MobileLocators(driver);
		f=new Fluent(driver);
		rs=new ResultScreenshot(driver,test);
		exr=new Excelresult();
	}
	public String scroll(Properties p,String[] data,int row,String sheet, int testrow, String[] params) throws Exception {
		try {
			Dimension dim=driver.manage().window().getSize();
			int height=dim.getHeight();
			boolean display=false;
			for(int i=0;i<=height;i++) {
				
			try {
				if(f.fluent(ml.getObject(p, data[OBJECTNAME], data[OBJECTTYPE])).isDisplayed()) {
					display=true;
					break;
				}
				else {
					TouchAction ta=new TouchAction(driver);
					String[] value=data[VALUE].split(";");
					
					int widthx=(int)(dim.getWidth()*Float.parseFloat(value[0]));
					int starty=(int)(dim.getHeight()*Float.parseFloat(value[1]));
					int endy=(int)(dim.getHeight()*Float.parseFloat(value[2]));
					int widthy=(int)(dim.getWidth()*Float.parseFloat(value[3]));
					ta.press(widthx, starty).waitAction(Duration.ofSeconds(1)).moveTo(widthy, endy).release().perform();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				TouchAction ta=new TouchAction(driver);
				String[] value=data[VALUE].split(";");
				
				int widthx=(int)(dim.getWidth()*Float.parseFloat(value[0]));
				int starty=(int)(dim.getHeight()*Float.parseFloat(value[1]));
				int endy=(int)(dim.getHeight()*Float.parseFloat(value[2]));
				int widthy=(int)(dim.getWidth()*Float.parseFloat(value[3]));
				ta.press(widthx, starty).waitAction(Duration.ofSeconds(1)).moveTo(widthy, endy).release().perform();
			}
			
			}
			if(display) 
				return PASS;
			else
				return FAIL;
		} catch (Exception e) {
			ResultUpdation noe=new ResultUpdation(driver,test);
			noe.withoutBy(p, data, row, sheet, testrow, FAIL, params);
			return Information.FAIL;
		}
	}
	
}
