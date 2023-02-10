package Keywords;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class Clear implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public Clear(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
		WebElement clk=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(clk);							// highlighting specific element
		clk.clear();
		Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		cond= true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Not able to clear the data "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}
}
