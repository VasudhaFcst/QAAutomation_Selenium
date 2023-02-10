package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import Common.Information;

public class Default implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Default(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String existing(Properties p,String[] records, int row, String sh, int resultRow,String[] imp) throws Exception{
	obj.getExcelResult().setData(cond,row,sh,resultRow,INVALID_KEYWORD,imp);
	obj.getResultScreenshot().demand(records[STEPNUMBER], "'"+records[KEYWORD]+"' is wrong Keyword. Please write correct keyword", Information.SKIP,imp);
	return Information.SKIP;
//	driver.quit();
//	try {
//		Desktop.getDesktop().open(new File(imp[2]));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	System.exit(0);
}
}
