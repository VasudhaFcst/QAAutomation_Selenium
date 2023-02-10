package Keywords;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class Menu implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public Menu(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		cond=false;
		List<WebElement> menulist=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME], record[OBJECTTYPE]));
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
		for(WebElement ele:menulist){
			if(ele.getText().contains(record[VALUE])){
				obj.getJavaScript().highlight(ele);
				ele.click();
				VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
				return Information.PASS;
			}
		}
		if(!cond){
			cond= false;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
			return Information.FAIL;
		}
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}

}
