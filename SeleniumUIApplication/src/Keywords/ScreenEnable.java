package Keywords;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class ScreenEnable implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public ScreenEnable(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		Thread.sleep(2000);
		By parameter=obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]);
		if(obj.getVisible().isElementPresent(parameter)){
			WebElement enable = obj.getFluent().fluentWait(parameter);
			obj.getJavaScript().highlight(enable);
			obj.getJavaScript().mark(enable);
			obj.getResultScreenshot().demand(
					enable,
					record[STEPNUMBER],
					"Description: "+record[DESCRIPTION]+" --- Output: "+record[EXPECTED_COLUMN],
					Information.PASS,imp);
			obj.getJavaScript().nomark(enable);
			cond= true;
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			return Information.PASS;
		}
		else{
			cond= false;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], record[OBJECTNAME]+" is not displaying");
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.FAIL;
		}
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}

}
