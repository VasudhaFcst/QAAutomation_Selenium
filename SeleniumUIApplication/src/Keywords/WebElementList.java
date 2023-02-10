package Keywords;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

public class WebElementList implements Information {
	
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	static Map<String, Integer> headerValueColNum;
	static List<List<String>> allRowsData;
	
public WebElementList(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj= new Objects(driver, test);
	ru = new ResultUpdation(obj);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try { // Getting first row data in a fixed/static table
		System.out.println(record[OBJECTNAME]);
		System.out.println(record[OBJECTTYPE]);
		// List<WebElement>
		// eiElement=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		WebElement tableRow = driver.findElement(By.xpath(p.getProperty(record[OBJECTNAME])));
		List<WebElement> headerCells = tableRow.findElements(By.tagName("th"));
		headerValueColNum = new LinkedHashMap<>();
		for(int i = 0; i < headerCells.size(); i++) {
			String columnData = headerCells.get(i).getText();
				
			//String[] splitHeaders = columnData.split("Column Actions",0);
			String[] splitHeaders = columnData.split("\\r?\\n|\\r");
			if(splitHeaders != null) {
				columnData = splitHeaders[0];
			}
			headerValueColNum.put(columnData, i);
		}
		System.out.println("Header Details =====> " + headerValueColNum);
		cond = true;
		ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		return Information.PASS;
	}
	catch(Exception ne){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}
public String variableTable(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{ // Getting data from the variable table----like adding new rows
		int i,j;
	List<WebElement> cells = null;
	System.out.println(record[OBJECTNAME]);
	System.out.println(record[OBJECTTYPE]);
	List<WebElement> eiElement=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	int rowSize=eiElement.size();
	System.out.println("Our Table Row Size========>:"+rowSize);
	allRowsData = new ArrayList<>();
	for(i=2;i<=rowSize;i++) {
		List<String> singleRowData = new ArrayList<>();
		for(j=1;j<headerValueColNum.size();j++) {
		WebElement tableRow = driver.findElement(By.xpath(p.getProperty(record[OBJECTNAME]) + "[" + i + "]"+"/td"+"[" + j + "]"));
		//WebElement tableRow = driver.findElement(By.xpath(p.getProperty(record[OBJECTNAME]) + "[" + i + "]"));
		 //cells = tableRow.findElements(By.tagName("td"));
		 String rowValue =tableRow.getText();
		 singleRowData.add(rowValue);
		}
		allRowsData.add(singleRowData);
		System.out.println("Single Row Data ===> "+singleRowData);
	}
	System.out.println("All Rows Data ===> "+allRowsData);
	//System.out.println(ELEMENTS_LIST.get(record[OBJECTNAME]).get(i-1).getText());
	cond= true;
	ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
	return Information.PASS;
	}
	catch(Exception ne){
		ru.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}
//This method is used to sum a column i.e., we get the headers initially and later will get the count of particular header and
//will map corresponding header to data and will sum that.
//Note: which column we have to sum, that column header name need to pass as a argument(value) in excel
	public String summingAmountColumn(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		try {
		//String element = record[VALUE];
		String[] elements = record[VALUE].split(",");
		String v1=elements[0].trim();
		String v2=elements[1].trim();
		System.out.println("===============>"+v1);
		System.out.println("===============>"+v2);
		int amountColNum = headerValueColNum.get(v1);
		List<String> totalCommitValueFromUI=MULTIVALUE_LIST.get(v2);
		
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		
		String uiValue = totalCommitValueFromUI.get(0);
		
		uiValue = uiValue.replace(",", "").trim().replace("$","");
		if(uiValue.endsWith("K")) {
			uiValue = uiValue.replace("K", "");
			double uiDoubleValue = Double.parseDouble(uiValue);
			uiDoubleValue = uiDoubleValue * 1000;
			uiValue = df.format(uiDoubleValue);
		}
		
		String amountData;
		double totalAmout = 0;
		
		for(List<String> rowData : allRowsData) {
			amountData = rowData.get(amountColNum-1).replace(",", "").trim().replace("$","");
			totalAmout = totalAmout + Double.parseDouble(amountData);
			System.out.println("Table Amount = "+amountData+" Total Amount ="+totalAmout);
		}
		System.out.println("Total Amount Before Rounding =====> "+totalAmout);
		
		String totalSum = df.format(totalAmout);
		System.out.println("Total Amount After Rounding =====> "+totalSum);

		VALUE_STORAGE.put(record[OBJECTNAME], totalSum);
		System.out.println("All Fiels data:" + totalSum);
		
		if(totalSum != null && totalSum.equals(uiValue)) {
			System.out.println("Both are equal "+ "Toatal Sum = "+totalSum+" UI Value = "+uiValue);
			cond= true;
			ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
			return Information.PASS;
		}else {
			System.out.println("Both are not equal "+ "Toatal Sum = "+totalSum+" UI Value = "+uiValue);
			ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are not valid");
			return Information.FAIL;
		}

		
	}
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
}
}

