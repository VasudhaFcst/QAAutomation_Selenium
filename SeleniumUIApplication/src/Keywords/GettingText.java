package Keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class GettingText implements Information{

	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	String displayResult = "";
	
	public GettingText(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			
			WebElement valueText=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			obj.getJavaScript().highlight(valueText);	
			String valueFromCell=valueText.getText();
			System.out.println("text:"+valueFromCell);
			double convertedValue;
			valueFromCell = valueFromCell.replaceAll("\\$", "");
			valueFromCell = valueFromCell.replaceAll("k", "");
			valueFromCell=valueFromCell.replaceAll(",", "");
			valueFromCell=valueFromCell.replaceAll("£", "");
			valueFromCell=valueFromCell.replaceAll("%", "");
			if(valueFromCell.equals("/")) {
			valueFromCell=valueFromCell.split("/")[0];
			}
			
			else {
			if (valueFromCell.equals("-") || valueFromCell.equals(" ")) {
				convertedValue = 0;
			}     
			else {
				if (valueFromCell != null && valueFromCell.contains("(")) {
					System.out.println("Before paranthesis removal :: " + valueFromCell);
					valueFromCell = valueFromCell.replaceAll("\\(", "");
					valueFromCell = valueFromCell.replaceAll("\\)", "");
					valueFromCell = "-" + valueFromCell;
					System.out.println("After paranthesis removal :: " + valueFromCell);
				}
			
			
				// To get Quarter Number
				if (valueFromCell.contains("-")) {
					convertedValue = Double.parseDouble(valueFromCell.split("-")[1].trim());
				}else{
					convertedValue = Double.parseDouble(valueFromCell);
				}
			
				System.out.println("convertedValue :: "+convertedValue);
				System.out.println("Data for the xpath"+valueFromCell);
			}
			
			TABLE_DATA.put(record[OBJECTNAME], convertedValue);
			}
			//System.out.println("Table Data:"+TABLE_DATA);
		return Information.PASS;
		}
		
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}
public String GettingValue(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
	try {
		String resultString = null;
		double valueResult = 0;
		ArrayList LIST = new ArrayList();
		// WebElement
		// valueText=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		// obj.getJavaScript().highlight(valueText);
		List<WebElement> value = driver.findElements(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
		ELEMENTS_LIST.put(record[OBJECTNAME], value);
		System.out.println("size"+value.size());
		ArrayList<String> values = new ArrayList<String>();
		for (WebElement element : value) {

			String[] strArray = (element.getText()).split("\n");
			values.add(strArray[0].toUpperCase());
			// values.add(element.getText());

		}
		MULTIVALUE_LIST.put(record[OBJECTNAME], values);
		System.out.println("All Fiels data:" + values);
		Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		cond= true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
		
	}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Unable to get the text "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
	/*try{
		
		WebElement valueText=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(valueText);	
		String valueFromCell=valueText.getText();
		System.out.println("text:"+valueFromCell);
		VALUE_STORAGE.put(record[OBJECTNAME],valueFromCell );
		cond= true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
				"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
		return Information.PASS;
		
		
	}
	catch(Exception ne){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		return Information.FAIL;
}*/
}
}
