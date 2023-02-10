package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class WriteText implements Information {
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public WriteText(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj= new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{
	WebElement writetext=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	//obj.getJavaScript().highlight(writetext);							// highlighting specific element
	writetext.clear();
	//Thread.sleep(2000);
	writetext.sendKeys(record[VALUE]);
	cond= true;
	ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
	return Information.PASS;
	}
	catch(Exception ne){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}

public String texting(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
	WebElement calendar=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(calendar);
	cond= obj.getJavaScript().jsWrite(calendar, record[VALUE]);
	String testResult = cond? Information.PASS : Information.FAIL;
	ru.updateResult(cond, sh, row, resultRow, testResult, imp, record);
	return testResult;
	}
catch(Exception ne){
	ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
	ne.printStackTrace();
	return Information.FAIL;
	}
}

}
