package Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class RelationshipMap  implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	
public RelationshipMap(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		System.out.println("entered relationship map");
		//String[] elements = record[VALUE];
		WebElement data=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		String AccountRelationShip=data.getText();
		//String recordValue = elements[0].trim();
		int AccountRelationShipScore=Integer.parseInt(AccountRelationShip);
		System.out.println("value:"+AccountRelationShipScore);
		List<WebElement> allFieldsdata = ELEMENTS_LIST.get(record[VALUE].trim());
			
			System.out.println(allFieldsdata.size());
			  ArrayList<Integer> values = new ArrayList<Integer>();
			  for (WebElement element:allFieldsdata)
			  {
				  if(!element.getText().trim().equals("0"))
				  values.add(Integer.parseInt(element.getText()));
				  System.out.println("All Fiels data:"+values);
				  }
			 
			int result= (int) Math.ceil(values.stream().mapToInt(Integer::intValue).average().getAsDouble());
			System.out.println("Average Result:"+result);
		//System.out.println("All Fiels data:"+values.get(0));
			if(AccountRelationShipScore==result) {
				System.out.println("Both values are valid");
			cond = true;
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, String.valueOf(cond));
			obj.getExcelResult().setData(cond,row,sh,resultRow,PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
			}
			else {
				System.out.println("Both values are Invalid");
				cond = true;
				VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, String.valueOf(cond));
				obj.getExcelResult().setData(cond,row,sh,resultRow,PASS,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: Both values are Invalid");
			}
			return Information.PASS;
		
		}
		catch(Exception ne){
			
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			ne.printStackTrace();
			return Information.FAIL;
			}
}
public String actionBasedOnTheName(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{// Clikcing the element based on the value/name passed on the excel sheet
		
		//Eg: when we pass name  as test, it select particular and click on the exclude button for that
		System.out.println("entered relationship map");
		//String[] elements = record[VALUE];
		WebElement data=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		//String AccountRelationShip=data.getText();
		System.out.println(data);
		data.click();
			/*
			 * List<WebElement> allFieldsdata = ELEMENTS_LIST.get(record[VALUE].trim());
			 * 
			 * System.out.println(allFieldsdata.size()); ArrayList<Integer> values = new
			 * ArrayList<Integer>(); for (WebElement element:allFieldsdata) {
			 * 
			 * System.out.println("All Fiels data:"+element.getText()); }
			 */
		//System.out.println("All Fiels data:"+values.get(0));
			
			return Information.PASS;
		
		}
		catch(Exception ne){
			
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			ne.printStackTrace();
			return Information.FAIL;
			}
}

}


