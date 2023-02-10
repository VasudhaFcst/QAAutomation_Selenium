package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class AlertBox implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public AlertBox(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
	
}
public String testing(Properties p,String[]record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	
	try{
		Thread.sleep(2000);
	String alertdata=driver.switchTo().alert().getText();
	if(record[VALUE].trim().equalsIgnoreCase("ok")){
		driver.switchTo().alert().accept();
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Showing data: "+alertdata+". "+record[DESCRIPTION]);
	}
	else if(record[VALUE].trim().equalsIgnoreCase("cancel")){
		driver.switchTo().alert().dismiss();
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Showing data: "+alertdata+". "+record[DESCRIPTION]);
	}
	else{
		driver.switchTo().alert().sendKeys(record[VALUE]);
		driver.switchTo().alert().accept();
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Showing data: "+alertdata+". Entered Text is "+record[VALUE]+". "+record[DESCRIPTION]);
	}
	cond= true;
	obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
	return Information.PASS;
}
catch(Exception e){
	ResultUpdation noe =new ResultUpdation(obj);
	noe.withoutBy(p, record, row, sh, resultRow, Information.FAIL,imp);
	e.printStackTrace();
	return Information.FAIL;
}
}

public String alertText(Properties p,String[]record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	
	try{
		Thread.sleep(2000);
	String alertdata=driver.switchTo().alert().getText();
	VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, alertdata);
	if(record[VALUE].trim().equals(alertdata)){
		driver.switchTo().alert().dismiss();
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Showing data: "+alertdata+". "+record[DESCRIPTION]);
		cond= true;
		obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
		return Information.PASS;
	}
	else{
		driver.switchTo().alert().dismiss();
		obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], "Showing data: "+alertdata+" <br>Expected text is "+record[VALUE]);
		cond= false;
		obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
		return Information.FAIL;
	}
	
}
catch(Exception e){
	ResultUpdation noe =new ResultUpdation(obj);
	noe.withoutBy(p, record, row, sh, resultRow, Information.FAIL,imp);
	e.printStackTrace();
	return Information.FAIL;
}
}
}
