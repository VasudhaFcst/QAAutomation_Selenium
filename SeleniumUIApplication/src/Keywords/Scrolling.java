package Keywords;

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

public class Scrolling implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public Scrolling(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	 try{
	 WebElement wes = driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	 obj.getJavaScript().scroll(wes, record[VALUE]);
	 obj.getJavaScript().highlight(wes);
	 cond= true;
	 VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
	 obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
	 obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
	 return Information.PASS;
	 }
	 catch(Exception e){
		 	ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.FAIL;
	 }
}

public String end(String[] record ) throws Exception{
	try{
	float scrollFloat=Float.parseFloat(record[VALUE]);
	int scrollInt=(int) scrollFloat;
	for(int i=0;i<=scrollInt;i++){
	 Actions actions = new Actions(driver);
	 actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	 Thread.sleep(1000);
	}
	return Information.PASS;
	}
	catch(Exception e){
			e.printStackTrace();
			return Information.FAIL;
	}
	}
}

