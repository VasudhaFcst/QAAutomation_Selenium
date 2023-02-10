package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class MouseHover implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public MouseHover(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
	Actions action = new Actions(driver);
    WebElement we = driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
    obj.getJavaScript().highlight(we);
    action.moveToElement(we).build().perform();
	cond= true;
	VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
	obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
	if(record[VALUE].equalsIgnoreCase("Screen")){
		obj.getJavaScript().mark(we);
		obj.getResultScreenshot().demand(record[STEPNUMBER],record[DESCRIPTION]+" --- Output: "+record[EXPECTED_COLUMN],Information.PASS,imp);
		obj.getJavaScript().nomark(we);
	}
	else
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);	
    Thread.sleep(500);
    return Information.PASS;
}
	catch(Exception e){
		
	ResultUpdation noe=new ResultUpdation(obj);
	noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
	e.printStackTrace();
	VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
	return Information.FAIL;
}
}
}
