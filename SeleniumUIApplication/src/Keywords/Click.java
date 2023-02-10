
package Keywords;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class Click implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public Click(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
		WebElement clk=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(clk);							// highlighting specific element
		clk.click();
		Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		cond= true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver didn't click on "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}
public String enter(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
		WebElement clk=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		//obj.getJavaScript().highlight(clk);							// highlighting specific element
		clk.sendKeys(Keys.ENTER);
		Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		cond= true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver didn't click on "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}

public String optional(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		WebElement clkop=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(clkop);							// highlighting specific element
		obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE])).click();
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
		}
		catch(Exception ne){
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.SKIP;
		}
}

public String press(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
	WebElement press=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(press);
	cond= obj.getJavaScript().jsClick(press);
	VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
	obj.getJavaScript().waitforloading();
	String testResult = cond? Information.PASS : Information.FAIL;
	ru.updateResult(cond, sh, row, resultRow, testResult, imp, record);
	return testResult;
	}
	catch(Exception e){
		ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		e.printStackTrace();
		return Information.FAIL;
	}
}

public String randomClick(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		Random random=new Random();
		List<WebElement> rclick=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		int randnumb=random.nextInt(rclick.size());
		WebElement webele=rclick.get(randnumb);
		obj.getJavaScript().scroll(webele, record[VALUE]);
		obj.getJavaScript().highlight(webele);

		if(record[VALUE].equalsIgnoreCase("screenshot")) {
			obj.getJavaScript().mark(webele);
			obj.getResultScreenshot().demand(
			      webele,record[STEPNUMBER],"Description: "+record[DESCRIPTION]+" <html><br></html>Output: "+record[EXPECTED_COLUMN],Information.PASS,imp);
			obj.getJavaScript().nomark(webele);
			obj.getJavaScript().js.executeScript("arguments[0].click();", webele);
		}
		else {
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"<html><br></html>Output: "+record[EXPECTED_COLUMN]);
			obj.getJavaScript().js.executeScript("arguments[0].click();", webele);
		}
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
		obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
		return Information.PASS;
	}
	catch(Exception e){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver didn't click on "+record[OBJECTNAME]
				+" because of "+e,imp);
		e.printStackTrace();
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		return Information.FAIL;
	}
}

public String selection(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		String fields=p.getProperty(record[OBJECTNAME]);
		fields=fields.replace("--", record[VALUE]);
		WebElement options=driver.findElement(By.xpath(fields));
		obj.getJavaScript().highlight(options);
		options.click();
		obj.getJavaScript().waitforloading();
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
		obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

public String screen(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
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
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
		}
		else{
			cond= false;
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], record[OBJECTNAME]+" is disabled");
			return Information.FAIL;
		}
		return Information.PASS;
		}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Element didn't find "+record[OBJECTNAME]
					+" because of "+ne,imp);
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			ne.printStackTrace();
			return Information.FAIL;
		}
}
}
