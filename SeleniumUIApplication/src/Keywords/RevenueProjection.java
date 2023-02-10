package Keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.graphbuilder.math.func.SumFunction;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class RevenueProjection implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public RevenueProjection(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
	}

public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		
		List<WebElement> tableRowsElements = driver
				.findElements(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
		System.out.println("table size"+tableRowsElements.size());
		for (int i = 1; i <= tableRowsElements.size(); i++) {
			List<WebElement> tableRow = driver.findElements(By.xpath(p.getProperty(record[OBJECTNAME])));
			//System.out.println("Table rows:"+data);
			ArrayList<String> HeaderDetails = new ArrayList<String>();
			for (WebElement element:tableRow)
			HeaderDetails.add(element.getText());
			System.out.println("ArrayListData"+HeaderDetails);
		}
		/*String[] elements = record[VALUE].split(",");
		Map<String, ArrayList<String>> months = new LinkedHashMap<String, ArrayList<String>>();
		List<WebElement> Headers = ELEMENTS_LIST.get(elements[0].trim());
		 ArrayList<String> HeaderDetails = new ArrayList<String>();
		  for (WebElement element:Headers)
			  HeaderDetails.add(element.getText());
		  System.out.println("Headers:"+HeaderDetails);
		
		List<WebElement> row1 = ELEMENTS_LIST.get(elements[1].trim());
		List<WebElement> row2 = ELEMENTS_LIST.get(elements[2].trim());
		//List<WebElement> row3 = ELEMENTS_LIST.get(elements[3].trim());
		String condition=elements[4].trim();
		  ArrayList<String> value1 = new ArrayList<String>();//Getting Row 1 data
		  for (WebElement element:row1) 
			  value1.add(element.getText().replaceAll("%", "").trim());
			  int count = 0;
		  System.out.println("C data:"+value1);
		  
		  ArrayList<String> value2 = new ArrayList<String>();// Getting Row 2 data
		  for (WebElement element:row2)
			  value2.add(element.getText().replaceAll("%", "").trim());
		  System.out.println("Row 2 data:"+value2);
		
		 
		  List<Integer> result = new ArrayList<Integer>();
		  ArrayList<String> finalArrayList1 = new ArrayList<String>();
		  
		  
		  try {
			  if(condition.equalsIgnoreCase("Add")) {
				  int sizeValue2=value2.size();
				  System.out.println("Row2 Size:"+sizeValue2);
				  for (int i = 1; i <value2.size(); i++) {
				 result.add(Integer.parseInt(value1.get(i).trim()) + Integer.parseInt(value2.get(i).trim()));
				 
			  
				  } 
			  }
			  else {
				  if(condition.equalsIgnoreCase("PERC")) {
					  int sizeValue1=value1.size();
					  System.out.println("Row2 Size:"+sizeValue1);
					  for (int i = 0; i <=value1.size(); i++) {
							 result.add(((Integer.parseInt(value1.get(i).trim()) + Integer.parseInt(value2.get(i).trim())))/2);
							
				  }
			  }
			  }
			  String i=value1.get(2);
			  System.out.println("Getting second value:"+i);
			  String finalArrayList = result.toString();
			  //finalArrayList.add(result.toString());
			//System.out.println("Row3 Final values:"+Finalvalue3);
			  System.out.println("result Values:"+finalArrayList);
			  //System.out.println(value3.equals(finalArrayList));
				
				 * for(int i=0;i<=result.size();i++) { if(value3.equals(result))
				 * System.out.println("Both are valid"); }
				 
			
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  */
	     return Information.PASS;
	     
}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return Information.FAIL;
	}
	
	}

	public String calculatingColumns(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {

			String[] elements = record[VALUE].split(",");

			List<WebElement> tableRowsElements = driver
					.findElements(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			int size = tableRowsElements.size();
			System.out.println("Size of the rows" + tableRowsElements);
			for (int i = 2; i < tableRowsElements.size() - 1; i++) {
				List<WebElement> tableRow = driver
						.findElements(By.xpath(p.getProperty(record[OBJECTNAME]) + "[" + i + "]"));
				int columnSize = tableRow.size();
				System.out.println("column size:" + columnSize);
				for (WebElement cell : tableRow) {
					String valueFromCell = cell.getText();
					System.out.println(valueFromCell);

				}

			}

			return Information.PASS;
		} catch (Exception e) {
			// TODO: handle exception
			return Information.FAIL;
		}
	}
}
