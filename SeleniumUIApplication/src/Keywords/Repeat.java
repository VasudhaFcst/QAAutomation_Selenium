package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class Repeat implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Repeat(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
			
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		int number=Integer.parseInt(record[VALUE]);
		WebElement records=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		for(int k=1;k<=number;k++){
			if(records.isDisplayed() && records.isEnabled()){
				obj.getJavaScript().highlight(records);
				records.click();
				cond=true;
			}
			else{
				break;
			}
		}
			if(cond){
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"record[DESCRIPTION]: "+record[DESCRIPTION]+" --- Output: "+record[EXPECTED_COLUMN]);
			}
			else{
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"record[DESCRIPTION]: "+record[DESCRIPTION]+" --- Output: WebElement is in disable state");
			}
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			return Information.PASS;
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
