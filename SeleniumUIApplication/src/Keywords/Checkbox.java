package Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class Checkbox implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public Checkbox(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			WebElement checkbox = driver
					.findElement(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			obj.getJavaScript().highlight(checkbox);
			if (!checkbox.isSelected()) { // for select the checkbox
				checkbox.click();
				obj.getJavaScript().waitforloading();
			} else if (record[VALUE].equalsIgnoreCase("uncheck") && checkbox.isSelected()) { // for uncheck checkbox
				checkbox.click();
				obj.getJavaScript().waitforloading();
			}
			cond = true;
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, String.valueOf(cond));
			obj.getExcelResult().setData(cond,row,sh,resultRow,PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
			return Information.PASS;
		} catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			e.printStackTrace();
			return Information.FAIL;
		}
	}
	public String SelectORDeSelect(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			String[] elements = record[VALUE].split(",");
			String checkboxName = elements[0].trim();
			String Checking = elements[1].trim();
			cond = false;
			String data = "";
			WebElement checkbox = driver
					.findElement(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			obj.getJavaScript().highlight(checkbox);
			if(Checking.equalsIgnoreCase("Check")) {
				cond = checkbox.isSelected();
				System.out.println("Checkbox status:"+cond);
				data = (cond)? checkboxName +" is Selected": checkboxName +" is not Selected";
				System.out.println("Checkbox status:"+data);
			}
			else if(Checking.equalsIgnoreCase("Uncheck")) {
				cond = !checkbox.isSelected();
				data = (cond)? checkboxName +" is not Selected": checkboxName +" is Selected";
				System.out.println("Checkbox status:"+data);
			}
			else {
				data = "Invalid data. Mention Check or Uncheck in Value column as second parameter";
				System.out.println("Checkbox status:"+data);
			}
			
			String resultStatus = (cond)? PASS : FAIL;
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, String.valueOf(cond));
			obj.getExcelResult().setData(cond,row,sh,resultRow,resultStatus,imp);
			if(cond)
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+data);
			else 
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Failed reason: " + data, Information.FAIL,imp);
			return resultStatus;
		}	
		catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			e.printStackTrace();
			return Information.FAIL;
		}

	
	}
}
