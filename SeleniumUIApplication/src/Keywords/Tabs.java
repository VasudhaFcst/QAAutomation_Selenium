package Keywords;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class Tabs implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public Tabs(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		if(record[VALUE].trim().equalsIgnoreCase("w")){
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
		}
		else if(record[VALUE].trim().equalsIgnoreCase("t")){
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
		}
		else if(record[VALUE].trim().equalsIgnoreCase("tab")){
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
		robot.keyRelease(KeyEvent.VK_CONTROL);
		if(record[VALUE].trim().equalsIgnoreCase("link")){
			WebElement rightClick= obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			obj.getJavaScript().highlight(rightClick);
			Actions act1= new Actions(driver);
			act1.contextClick(rightClick).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			cond= true;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
		}
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
