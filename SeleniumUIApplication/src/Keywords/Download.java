package Keywords;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class Download implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Download(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj =new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
		Robot robot=new Robot();
		WebElement download=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(download);
		obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE])).click();
		
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "Downloaded");
		obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
		return Information.PASS;
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
