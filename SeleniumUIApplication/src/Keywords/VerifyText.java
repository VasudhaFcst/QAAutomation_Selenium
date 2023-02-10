package Keywords;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.annotations.Until;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class VerifyText implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public VerifyText(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp) {
		try {
			int count = 0;
			String data = "";
			String[] onames = record[OBJECTNAME].split(";");
			String[] otypes = record[OBJECTTYPE].split(";");
			String text[] = record[VALUE].split(";");
			for (int i = 0; i < onames.length; i++) {
				try {
					WebElement verifyPass = obj.getFluent()
							.fluentWait(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
					obj.getJavaScript().highlight(verifyPass);
					String actual_text = obj.getJavaScript().getInnerText(verifyPass).trim();
					text[i] = text[i].replace("_", " ");
					VALUE_STORAGE.put(onames[i] + VALUE_END, actual_text);
							if (actual_text.equalsIgnoreCase(text[i].trim())) {
						obj.getJavaScript().mark(verifyPass);
						data = data + actual_text + "\n";
						count++;
					} else {
						if (obj.getVisible().isElementPresent(obj.getLocators().getObject(p, onames[i], otypes[i])))
							obj.getJavaScript().mark(verifyPass);
					}
				} catch (Exception vr) {
					vr.printStackTrace();
				}
			}
			if (count == onames.length) {
				cond = true;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + data, Information.PASS,
						imp);
			} else {
				cond = false;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + data, Information.FAIL,
						imp);
			}
			for (int j = 0; j < onames.length; j++) {
				try {
					if (obj.getVisible().isElementPresent(obj.getLocators().getObject(p, onames[j], otypes[j]))) {
						WebElement verify = obj.getFluent()
								.fluentWait(obj.getLocators().getObject(p, onames[j], otypes[j]));
						obj.getJavaScript().nomark(verify);
					}
				} catch (Exception vr) {
					vr.printStackTrace();
				}
			}
			if (cond)
				return Information.PASS;
			else
				return Information.FAIL;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Information.FAIL;
		}
	}

	public String negative(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			By neg = obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]);
			if (obj.getVisible().isElementPresent(neg)) {
				WebElement negative = driver.findElement(neg);
				String negmsg = negative.getText();
				VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
				obj.getExcelResult().setData(false, row, sh, resultRow, Information.FAIL, imp);
				obj.getJavaScript().mark(negative);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + negmsg, Information.FAIL,
						imp);
				obj.getJavaScript().nomark(negative);
				return Information.FAIL;
			} else {
				cond = true;
				VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "true");
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Verification is passed");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			}
		} catch (Exception ne) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}

	public String inputVerify(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			WebElement element = obj.getFluent()
					.fluent(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			String elementText = obj.getJavaScript().getInputText(element);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, elementText);
			if (elementText.equals(record[VALUE])) {
				cond = true;
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						record[VALUE] + " matches with expected value " + elementText);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			} else {
				cond = false;
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						record[VALUE] + " is not matching with expected value " + elementText);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				return Information.FAIL;
			}
		} catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}

	public String dropdownVerify(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			WebElement element = obj.getFluent()
					.fluent(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			Select sel = new Select(element);
			String elementText = sel.getFirstSelectedOption().getText();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, elementText);
			if (elementText.equals(record[VALUE])) {
				cond = true;
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						record[VALUE] + " matches with expected value " + elementText + " in dropdown");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			} else {
				cond = false;
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						record[VALUE] + " is not matching with expected value " + elementText + " in dropdown");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				return Information.FAIL;
			}
		} catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}
	
	/*This keyword is used to verify 2 values after gettingText and stored in variable
	Eg: ModifyRiskscore=StrategicRiskscore---By using GettingText keyword, get the value and stored in ModifyRiskscore and
	StrategicRiskscore and after comparing using this keyword(verifyGettingTextValues) wheather it is equal or not*/
	
public String verifyGettingTextValues(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			
			/*String[] elements = record[VALUE].split(",");
			String v1=elements[0].trim();
			String v2=elements[1].trim();
			System.out.println("value1:"+v1);
			System.out.println("Value2:"+v2);
			ArrayList<String> value1=TEXT_STORAGE.get(v1);
			ArrayList<String> value2=TEXT_STORAGE.get(v2);
			System.out.println("Value1"+value1);
			System.out.println("Value2"+value2);
			if(value1.equals(value2))
				System.out.println("Both are same");
			else
				System.out.println("Both are not same");
		*/
			String[] elements = record[VALUE].split(",");
			String v1=elements[0].trim();
			String v2=elements[1].trim();
			System.out.println("value1:"+v1);
			System.out.println("Value2:"+v2);
			Double value1=TABLE_DATA.get(v1);
			Double value2=TABLE_DATA.get(v2);
			
			String s1=Double.toString(value1).split("\\.")[0];  // i am getting 50.0 as a output so i am Spliting  the double value after the points, so the o/p will get as 50
			System.out.println("String s1 value:"+s1);
			String s2=Double.toString(value2).split("\\.")[0];
			System.out.println("String s2 value:"+s2);
			if(s1.equals(s2)) {
				System.out.println("Both values are valid");
				cond= true;
				ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
				return Information.PASS;
				}
			else {
				ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n Both Values are not valid");
				return Information.FAIL;
			}
		

		//return Information.PASS;
		} 

		catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
			
}
		
} 
//It is used to verify two text that are getting using the GETTING VALUE keyword
public String verifyGettingValue(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{
		
		/*String[] elements = record[VALUE].split(",");
		String v1=elements[0].trim();
		String v2=elements[1].trim();
		String value1=VALUE_STORAGE.get(v1);
		String value2=VALUE_STORAGE.get(v2);
		System.out.println("Value1"+value1);
		System.out.println("Value2"+value2);
		if(value1.equals(value2)) {
			System.out.println("Both values are same");
		    cond= true;
		    ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
		    obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
		    obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
				"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
		    return Information.PASS;
		}
		else {
			ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are not valid");
			return Information.FAIL;
		}*/
		
		 String[] elements = record[VALUE].split(",");
		 String v1=elements[0];
		 String v2=elements[1];
		 List<String> value1=MULTIVALUE_LIST.get(v1);
		 List<String> value2=MULTIVALUE_LIST.get(v2);
		 Collections.sort(value1);
		 Collections.sort(value2);
		 
		 if((value1.size() == value2.size()) && value1.equals(value2)) {
				System.out.println("Both values are same");
			    cond= true;
			    ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
			    obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
			    obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
			    return Information.PASS;
			}
			else {
				System.out.println("Both values are not same");
				ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n Both Values are not valid");
				return Information.FAIL;
			}
		 
	} 

	catch (Exception e) {
		ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
		e.printStackTrace();
		VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
		return Information.FAIL;
		
}
	
} 
public String RiskScore(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	
	try {
		String[] elements = record[VALUE].split(",");
		String StrategicRisk=elements[0].trim();
		String OperationalRisk=elements[1].trim();
		String CommericalRisk=elements[2].trim();
		String overviewriskscore=elements[3].trim();
		//System.out.println("StrategicRisk value:"+StrategicRisk);
		//System.out.println("OperationalRisk Value:"+OperationalRisk);
		//System.out.println("CommericalRisk Value:"+CommericalRisk);
		Double value1=TABLE_DATA.get(StrategicRisk);
		Double value2=TABLE_DATA.get(OperationalRisk);
		Double value3=TABLE_DATA.get(CommericalRisk);
		Double value4=TABLE_DATA.get(overviewriskscore);
		Double result=TABLE_DATA.get(StrategicRisk)+TABLE_DATA.get(OperationalRisk)+TABLE_DATA.get(CommericalRisk);
		double riskscoreResult=Math.round((result/180)*100);
		String riskAssementRiskScore=Double.toString(riskscoreResult).split("\\.")[0];
		System.out.println("Total of 3 values"+riskAssementRiskScore);
		String overviewRisk=Double.toString(value4).split("\\.")[0];
		System.out.println("OverViewRiskScore:"+overviewRisk);
		if(riskAssementRiskScore.equals(overviewRisk)) {
			System.out.println("both values are valid");
			cond= true;
			ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are valid");
			return Information.PASS;
			}
		else {
			System.out.println("Both values are invalid");
			ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n Both Values are not valid");
			return Information.FAIL;
		}
	
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
		e.printStackTrace();
		VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
		return Information.FAIL;
		
	}
	
}
}