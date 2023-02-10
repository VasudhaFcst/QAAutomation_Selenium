package Keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class FinancialView implements Information{

	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	String displayResult = "";
	
	public FinancialView(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
		
	}

	public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			
			Double expectedValueDouble = null;
			String[] elements = record[VALUE].split(",");
			String v1=elements[0].trim();
			String v2=elements[1].trim();
			String v3=elements[2].trim();
			String valueCheckString=elements[3].trim();
			System.out.println("IS it cal/check  :"+valueCheckString);
			Double value1=TABLE_DATA.get(v1);
			Double value2=TABLE_DATA.get(v2);
			Double value3=TABLE_DATA.get(v3);
			if (valueCheckString.equalsIgnoreCase("cal")) {
				System.out.println("entered to cal");
				System.out.println("value3:" + value3);
				expectedValueDouble = (value1) - (value2);
				System.out.println("expected value:" + expectedValueDouble);
//		   String s2=Double.toString(expectedValueDouble);
//		    String s1=Double.toString(value3);
				// if(expectedValueDouble.compareTo(value3))
				if (Math.ceil(expectedValueDouble) == Math.ceil(value3)
						|| Math.floor(expectedValueDouble) == Math.floor(value3)) {
					System.out.println("Both values are valid");
					cond = true;
					// displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey();
					displayResult = displayResult + v1 + "-" + v2 + " value as per the calculation:"
							+ expectedValueDouble + "<br>" + v3 + "value as per the UI:" + value3 + "<br>";
				} else {
					displayResult = displayResult + v1 + "-" + v2
							+ "value as per the calculation:\"<br><span style='color:red'>" + expectedValueDouble
							+ "<br>" + v3 + "value as per the UI:" + value3 + "<br>";
					System.out.println("Both values are Invalid");
				}
				if (cond) {
					System.out.println("Success");

					obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
					obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
							"Description: " + record[DESCRIPTION] + "\n" + displayResult);
				} else {
					obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
					obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
							"Description: " + record[DESCRIPTION] + "\nPlease check below rows. " + displayResult);
				}

				
			}else {
				if (valueCheckString.equalsIgnoreCase("check")) {
					System.out.println("entered check");
				if(value1==0 && value2==0 && value3==0) {
					cond= true;
					ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
					obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
					obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
							"Description: " + record[DESCRIPTION] + "\n Values are present");
					return Information.PASS;
					}
				else {
					ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
					obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
					obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
							"Description: " + record[DESCRIPTION] + "\n Values are not present");
					return Information.FAIL;
				}
					}
			}
			return Information.PASS;
			
         }
			
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Invalid data:Calculation is wrong "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}
public String PercentCM(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			Double expectedValueDouble = null;
			String[] elements = record[VALUE].split(",");
			String v1=elements[0].trim();
			String v2=elements[1].trim();
			String v3=elements[2].trim();
			Double value1=TABLE_DATA.get(v1);
			Double value2=TABLE_DATA.get(v2);
			Double value3=TABLE_DATA.get(v3);
			System.out.println("value1:"+value1);
			System.out.println("value2:"+value2);
			System.out.println("value3:"+value3);	
			expectedValueDouble=(value1/value2)*100;
		    System.out.println("expected value:"+expectedValueDouble);
//		   String s2=Double.toString(expectedValueDouble);
//		    String s1=Double.toString(value3);
		   // if(expectedValueDouble.compareTo(value3))
		    if(Math.ceil(expectedValueDouble) == Math.ceil(value3) || Math.floor(expectedValueDouble) == Math.floor(value3)) {
		    	System.out.println("Both values are valid");
		    	cond = true;
		    	//displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey();
		    	displayResult= displayResult + v1+"/"+v2+" value as per the calculation:"+expectedValueDouble+"<br>"+v3+ "value as per the UI:" +value3+"<br>";
		    }else {
		    	displayResult= displayResult + v1+"/"+v2+"value as per the calculation:\"<br><span style='color:red'>"+expectedValueDouble+"<br>"+v3+ "value as per the UI:" +value3+"<br>";
		    	System.out.println("Both values are Invalid");
		    }
		    if (cond) {
				System.out.println("Success");

				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n" + displayResult);
			} else {
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\nPlease check below rows. " + displayResult);
			}

		    return Information.PASS;
			
         }
		
			
		catch(Exception ne){
			ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Invalid data:Calculation is wrong "+record[OBJECTNAME]
					+" because of "+ne,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
			return Information.FAIL;
		}
}
}