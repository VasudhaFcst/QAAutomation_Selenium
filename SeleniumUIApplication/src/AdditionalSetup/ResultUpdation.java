package AdditionalSetup;

import java.util.Properties;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Common.Information;
import io.appium.java_client.android.AndroidDriver;

public class ResultUpdation implements Information{
	@SuppressWarnings("rawtypes")
	AndroidDriver and;
	boolean cond=false;
	Objects obj;
	
	public ResultUpdation(Objects obj) throws Exception{
		this.obj = obj;
	}
	
	@SuppressWarnings("rawtypes")
	public ResultUpdation(AndroidDriver driver,ExtentTest test) throws Exception{
		this.and=driver;
	}
	
	public LogStatus status(String result) {
		switch(result.toUpperCase()) {
		case "PASS":
			return LogStatus.PASS;
		case "FAIL":
			return LogStatus.FAIL;
		case "UNKNOWN":
			return LogStatus.UNKNOWN;
		case "SKIP":
			return LogStatus.SKIP;
		case "WARNING":
			return LogStatus.WARNING;
		case "INFO":
			return LogStatus.INFO;
		case "FATAL":
			return LogStatus.FATAL;
		case "ERROR":
			return LogStatus.ERROR;
		}
		return null;
	}
	
	public void updateResult(boolean condition, String sheetName, int row, int resultRow, String result, String imp[], String record[]) {
		obj.getExcelResult().setData(condition,row,sheetName,resultRow,result,imp);
		obj.getExtentTest().log(status(result), record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\n<html><br></html>Output "+record[EXPECTED_COLUMN]);		
	}
	
	public void testing(Properties p,String[] record,int row, String sh, int resultRow,String status, String[] imp ) throws Exception{
		
			cond=false;
			obj.getExcelResult().setData(cond,row,sh,resultRow, status,imp);
			try {
				By by=obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]);
				if(obj.getVisible().isElementPresent(by)){
				obj.getJavaScript().mark(obj.getWebDriver().findElement(by));
				obj.getResultScreenshot().demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				obj.getJavaScript().nomark(obj.getWebDriver().findElement(by));
				}
				else{
					obj.getResultScreenshot().demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				}
			} catch (Exception e) {
				obj.getResultScreenshot().demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				e.printStackTrace();
			}
		}
	
	public void withoutBy(Properties p,String[] record,int row, String sh, int resultRow,String result,String[] imp ){
		try{
		cond=false;
		obj.getExcelResult().setData(cond,row,sh,resultRow, result,imp);
		obj.getResultScreenshot().demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
}
