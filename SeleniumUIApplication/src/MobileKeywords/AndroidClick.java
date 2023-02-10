package MobileKeywords;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Excelresult;
import AdditionalSetup.Fluent;
import AdditionalSetup.ResultUpdation;
import AdditionalSetup.ResultScreenshot;
import AndroidMobile.MobileLocators;
import Common.Information;
import io.appium.java_client.android.AndroidDriver;

public class AndroidClick implements Information{
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	MobileLocators ml;
	Fluent f;
	ExtentTest test;
	Excelresult exr;
	ResultScreenshot rs;
	@SuppressWarnings("rawtypes")
	public AndroidClick(AndroidDriver driver,ExtentTest test) throws Exception {
		this.driver=driver;
		this.test=test;
		ml=new MobileLocators(driver);
		f=new Fluent(driver);
		rs=new ResultScreenshot(driver,test);
		exr=new Excelresult();
	}
	
	public String click(Properties p,String[] data,int row,String sheet, int testrow, String[] params) throws Exception {
		try {
			TouchActions ta=new TouchActions(driver);
			WebElement element=f.fluent(ml.getObject(p, data[OBJECTNAME], data[OBJECTTYPE]));
			switch(data[KEYWORD].toUpperCase()) {
			case "CLICK":
				element.click();
				break;
				
			case "LONGPRESS":
				ta.longPress(element).build().perform();
				break;
				
			case "TAP":
				ta.singleTap(element).build().perform();
				break;
				
			case "DOUBLE TAP":
				ta.doubleTap(element).build().perform();
				break;
			}
			exr.setData(true,row,sheet,testrow,Information.PASS,params);
			test.log(LogStatus.PASS, data[STEPNUMBER],"Description: "+data[DESCRIPTION]+"\r\n Output: "+data[EXPECTED_COLUMN]);
			return Information.PASS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e+" at "+data[STEPNUMBER]+" in "+sheet+" sheet " );
			ResultUpdation noe=new ResultUpdation(driver,test);
			noe.withoutBy(p, data, row, sheet, testrow, FAIL, params);
			return Information.FAIL;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String randomClick(Properties p,String[] data,int row,String sheet, int testrow, String[] params) throws Exception {
		try{
			Random random=new Random();
			List<WebElement> rclick=driver.findElements(ml.getObject(p,data[OBJECTNAME],data[OBJECTTYPE]));
			int randnumb=random.nextInt(rclick.size());
			WebElement webele=rclick.get(randnumb);
			System.out.println(webele);
			System.out.println(randnumb);
			System.out.println(rclick.size());
			
			if(data[VALUE].equalsIgnoreCase("screenshot")) {
				
				rs.demand(
						webele,
						data[STEPNUMBER],
						"Description: "+data[DESCRIPTION]+" --- Output: "+data[EXPECTED_COLUMN],
						Information.PASS,params);
				
				webele.click();
			}
			else {
				test.log(LogStatus.PASS, data[STEPNUMBER],"Description: "+data[DESCRIPTION]+"\nOutput: "+data[EXPECTED_COLUMN]);
				webele.click();
			}
			
			exr.setData(true,row,sheet,testrow,Information.PASS,params);
			return Information.PASS;
		}
		catch(Exception e){

			ResultUpdation noe=new ResultUpdation(driver,test);
			noe.withoutBy(p, data, row, sheet, testrow, FAIL, params);
			System.out.println(e+" at "+data[STEPNUMBER]+" in "+sheet+" sheet " );
			return Information.FAIL;
		}
	}
	
	
}
