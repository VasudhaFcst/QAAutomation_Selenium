package MobileKeywords;

import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Excelresult;
import AdditionalSetup.Fluent;
import AdditionalSetup.ResultUpdation;
import AndroidMobile.MobileLocators;
import Common.Information;
import io.appium.java_client.android.AndroidDriver;

public class MobileWrite implements Information{
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	MobileLocators ml;
	Fluent f;
	ExtentTest test;
	Excelresult exr;
	@SuppressWarnings("rawtypes")
	public MobileWrite(AndroidDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		ml=new MobileLocators(driver);
		f=new Fluent(driver);
		exr=new Excelresult();
	}
	
	public String write(Properties p,String[] data,int row,String sheet, int testrow, String[] params) throws Exception {
		try {
			WebElement element=f.fluent(ml.getObject(p, data[OBJECTNAME], data[OBJECTTYPE]));
			driver.hideKeyboard();
			element.clear();
			element.sendKeys(data[VALUE]);
			driver.hideKeyboard();
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
}
