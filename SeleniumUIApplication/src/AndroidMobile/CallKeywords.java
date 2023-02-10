package AndroidMobile;

import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;

import Common.Information;
import MobileKeywords.AndroidClick;
import MobileKeywords.MobileWrite;
import MobileKeywords.Swipe;
import io.appium.java_client.android.AndroidDriver;

public class CallKeywords implements Information{
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	ExtentTest test;
	AndroidClick ac;
	MobileWrite mw;
	Swipe sw;
	
	@SuppressWarnings("rawtypes")
	public CallKeywords(AndroidDriver driver,ExtentTest test) throws Exception {
		this.driver=driver;
		this.test=test;
		ac=new AndroidClick(driver, test);
		mw=new MobileWrite(driver, test);
		sw=new Swipe(driver, test);
	}
	
	public String operation(Properties p,String[] column,int row, String sh, int resultRow,String[] imp) {
		
		try {
			switch(column[KEYWORD].toUpperCase()) {
			case "CLICK":
			case "LONGPRESS":
			case "TAP":
			case "DOUBLE TAP":
				return ac.click(p, column, row, sh, resultRow, imp);
				
			case "WRITETEXT":
				return mw.write(p, column, row, sh, resultRow, imp);
				
			case "SWIPE":
				return sw.scroll(p, column, row, sh, resultRow, imp);
				
			case "RANDOM CLICK":
				return ac.randomClick(p, column, row, sh, resultRow, imp);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		return null;
	}
	
}
