package Running;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import Common.Browser;
import Common.Information;


public class GettingData implements Information{
ExtentTest test;
Browser browserObject;
KeywordObjects keywordObjects;
WebDriver driver;


public GettingData(Browser browserObject, ExtentTest test) throws Exception{
	this.test=test;
	this.driver=driver;
	this.browserObject =browserObject;
	keywordObjects = new KeywordObjects(browserObject, test);
}

public String operation(Properties p,String[] column,int row, String sh, int resultRow,String[] imp) {
	try{
		
	switch(column[KEYWORD].toUpperCase()){
	case "OPEN BROWSER":
		try {
			browserObject.webBrowser();
			return Information.PASS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Information.FAIL;
		}
				
	case "CLOSE BROWSER":
		browserObject.getWebDriver().quit();
		browserObject.setNullWebDriver();
		return Information.PASS;
		
	case "ESCAPE":
		try {
		Robot r = new Robot();
		r.setAutoDelay(2000);
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
		return Information.PASS;
		}catch(Exception e) {
			return Information.FAIL;
		}
		
	case "REFRESHING":
		try {
			
			//driver.navigate().refresh();
		driver.get(driver.getCurrentUrl());
		System.out.println("Refreshed");
		return Information.PASS;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Information.FAIL;
		}
		
	case "GOTOURL":										// for run the url
		return keywordObjects.getGoToUrl().testing(p,column,row,sh,resultRow,imp);
		
	case "WRITETEXT":									// for typing in application
		return keywordObjects.getWriteText().testing(p, column, row, sh, resultRow, imp);
		
	case "CLICK":										// for clicking on any element
		return keywordObjects.getClick().testing(p, column, row, sh, resultRow, imp);
		
	case "ENTER":										// for clicking on any element
		return keywordObjects.getClick().enter(p, column, row, sh, resultRow, imp);
				
	case "CLEAR":										//for clearing the data
		return keywordObjects.getClear().testing(p, column, row, sh, resultRow, imp);
		
//This is normal click operation but result won't update if fails. If element is present then it will click or else it will skip. This result will never effect on overall result		
	case "OPTIONAL": 
		return keywordObjects.getClick().optional(p, column, row, sh, resultRow, imp);
				
	case "PRESS"://Sometimes CLICK keyword won't work because Selenium don't identify element so we can use PRESS instead of CLICK
		return keywordObjects.getClick().press(p, column, row, sh, resultRow, imp);
		
	case "RANDOM CLICK":  
		return keywordObjects.getClick().randomClick(p, column, row, sh, resultRow, imp);
	
	case "CHECKBOX":	//Select or unselect checkbox							
		return keywordObjects.getCheckBox().testing(p, column, row, sh, resultRow, imp);
		
	case "POPUP":									// for going to other window
		return keywordObjects.getPopup().switching(p, column, row, sh, resultRow, imp);
		
	case "UNPOPUP":									// for close the current window
		return keywordObjects.getPopup().switchingback(p, column, row, sh, resultRow, imp);
		
	case "DROPDOWN":									// select option from drop down
		return keywordObjects.getDropdown().testing(p, column, row, sh, resultRow, imp);
		
	case "OPTION FIELDS":
		return keywordObjects.getClick().selection(p, column, row, sh, resultRow, imp);
		
	case "TEXT":
	case "CALENDAR":
		return keywordObjects.getWriteText().texting(p, column, row, sh, resultRow, imp);
		
	case "REPEAT":
		return keywordObjects.getRepeat().testing(p, column, row, sh, resultRow, imp);
		
	case "SIZE":
		return keywordObjects.getSize().testing(p, column, row, sh, resultRow, imp);
		
	case "ALERT":									// for accept, close or write anything in alert boxes 
		return keywordObjects.getAlertBox().testing(p, column, row, sh, resultRow, imp);
		
	case "VERIFY TEXT":								// for verifying text in application
		return keywordObjects.getVerifyText().testing(p, column, row, sh, resultRow, imp);
		
	case "NEGATIVE VERIFY":
		return keywordObjects.getVerifyText().negative(p, column, row, sh, resultRow, imp);
		
	case "VERIFYING GEETINGTEXT VALUES":
		return keywordObjects.getVerifyText().verifyGettingTextValues(p, column, row, sh, resultRow, imp);
		
	case "VERIFYING GETTING VALUE":
		return keywordObjects.getVerifyText().verifyGettingValue(p, column, row, sh, resultRow, imp);	
		
	case "RISKSCORE":
		return keywordObjects.getVerifyText().RiskScore(p, column, row, sh, resultRow, imp);
		
	case "MOUSEHOVER":										// for mousehover on element
		return keywordObjects.getMouseHover().testing(p, column, row, sh, resultRow, imp);
		
	case "SCROLL":											// scroll to particular element
		return keywordObjects.getScrolling().testing(p, column, row, sh, resultRow, imp);
		
	case "SLEEP":											// sleep for sometime
		keywordObjects.getWaiting().sleep(column[VALUE]);
		return "";
		
	case "WAIT":
		keywordObjects.getWaiting().rest(p,column[OBJECTNAME],column[OBJECTTYPE], column[VALUE]);
		return "";
	
	case "SCREEN ENABLE":	
	case "SCREENSHOT":									// take the screenshot before clicking on element
		return keywordObjects.getScreenEnable().testing(p, column, row, sh, resultRow, imp);
		
	case "ELEMENT STATE":										
		return keywordObjects.getelementState().testing(p, column, row, sh, resultRow, imp);
		
	case "LIST":											// counting and verifying number of images in one page
		return keywordObjects.getListObjects().testing(p, column, row, sh, resultRow, imp);
		
	case "COUNT":
		return keywordObjects.getListObjects().counting(p, column, row, sh, resultRow, imp);
		
	case "SCROLL END":								// scroll to end of the page for number of times
		return keywordObjects.getScrolling().end(column);
		
	case "COPY TEXT":
		return keywordObjects.getCopyAndPaste().testing(p, column, row, sh, resultRow, imp);
		
	case "PASTE":
		return keywordObjects.getCopyAndPaste().paste(p, column, row, sh, resultRow, imp);
		
	case "FRAME":									// for frame purpose
		return keywordObjects.getFrames().testing(p, column, row, sh, resultRow, imp);
		
	case "MAIN":									// coming from frame
		return keywordObjects.getFrames().window(p, column, row, sh, resultRow, imp);
		
	case "DETAILS":
		return keywordObjects.getDetails().testing(p, column, row, sh, resultRow, imp);
		
	case "MENU":
		return keywordObjects.getMenu().testing(p, column, row, sh, resultRow, imp);
		
	case "UPLOAD":									// upload a file
		return keywordObjects.getUpload().testing(p, column, row, sh, resultRow, imp);
		
	case "DOWNLOAD":							// download a file
		return keywordObjects.getDownload().testing(p, column, row, sh, resultRow, imp);
		
	case "PRINT":
		return keywordObjects.getPrint().getValue(column); 
		
	case "TAB":							// for new, shift or close, open with new tab
		return keywordObjects.getTabs().testing(p, column, row, sh, resultRow, imp);
		
	case "VERIFY INPUT TEXT":
		return keywordObjects.getVerifyText().inputVerify(p, column, row, sh, resultRow, imp);
		
	case "VERIFY DROPDOWN":
		return keywordObjects.getVerifyText().dropdownVerify(p, column, row, sh, resultRow, imp);
		
	case "DROPDOWN OPTIONS":
		return keywordObjects.getDropdown().dropdownOptions(p, column, row, sh, resultRow, imp);
		
	case "FORECAST TABLE":
		return keywordObjects.getForecastTable().testing(p, column, row, sh, resultRow, imp);
		
	case "FORECAST TABLE VALIDATION":
		return keywordObjects.getForecastTableValidation().testing(p, column, row, sh, resultRow, imp);
		
	case "WEBELEMENT":
		return keywordObjects.getWebElementKeyword().testing(p, column, row, sh, resultRow, imp);
		
	case "WEBELEMENTS":
		return keywordObjects.getWebElementList().testing(p, column, row, sh, resultRow, imp);
		
	case "VARIABLETABLEDATA":
		return keywordObjects.getWebElementList().variableTable(p, column, row, sh, resultRow, imp);
		
	case "VARIABLE":
		return keywordObjects.getVariable().testing(p, column, row, sh, resultRow, imp);
		
	case "TABLE VALIDATION": // USED TO GET TABLE DATA FOR DN AND PERFORMING THE TABLE CALCULATIONS IN IT
		return keywordObjects.getTableValidation().testing(p, column, row, sh, resultRow, imp);		
		
	case "TABLE DATA":// USED TO GET JUST THE TABLE DATA AND STORED
		return keywordObjects.getTableValidation().TableData(p, column, row, sh, resultRow, imp);
		
	case "MULTIPLETABLE VALIDATION":
		return keywordObjects.getMultipleTableValidation().testing(p, column, row, sh, resultRow, imp);	
		
	case "YTD WEIGHTED":
		return keywordObjects.getMultipleTableValidation().ytdWeighted(p, column, row, sh, resultRow, imp);	
		
	case "SOURINGMIX FOR LTG":
		return keywordObjects.getMultipleTableValidation().SouringMixForLTG(p, column, row, sh, resultRow, imp);	
	
	case "CHECKBOXES ISSELECT":
		return keywordObjects.getCheckBox().SelectORDeSelect(p, column, row, sh, resultRow, imp);
		
	case "VERSION AND YEAR CHANGE":
		return keywordObjects.getMultipleTableValidation().VersionAndYearChange(p, column, row, sh, resultRow, imp);
	
	case "FINANCIAL VIEW":
		return keywordObjects.getFinancialView().testing(p, column, row, sh, resultRow, imp);
		
	case "PERCENTCM":
		return keywordObjects.getFinancialView().PercentCM(p, column, row, sh, resultRow, imp);
		
	case "GETTING TEXT":
		return keywordObjects.getGettingText().testing(p, column, row, sh, resultRow, imp);
	
	case "GETTING VALUE":
		return keywordObjects.getGettingText().GettingValue(p, column, row, sh, resultRow, imp);
		
	case "RELATIONSHIP MAP":
		return keywordObjects.getRelationshipMap().testing(p, column, row, sh, resultRow, imp);
		
	case "PERFORMING ACTION BASED ON NAME"://clicking the particular element based on the value/name passed on the excel.
		return keywordObjects.getRelationshipMap().actionBasedOnTheName(p, column, row, sh, resultRow, imp);
		
	case "REVENUE PROJECTION":
	     return keywordObjects.getRevenueProjection().testing(p, column, row, sh, resultRow, imp);
	case "CALULATING COLUMNS":
		return keywordObjects.getRevenueProjection().calculatingColumns(p, column, row, sh, resultRow, imp);
		
	case "OPPORTUNITY MANAGEMENT":
		return keywordObjects.getOpportunityManagement().testing(p, column, row, sh, resultRow, imp);
		
	case "SUMMING AMOUNT COLUMN":// USED TO SUM A ROW FROM THE TABLE
		return keywordObjects.getWebElementList().summingAmountColumn(p, column, row, sh, resultRow, imp);
		
	default:
			return keywordObjects.getDef().existing(p,column, row, sh, resultRow,imp);
			
	}
	}
	catch(Exception e){
		
		e.printStackTrace();
		return Information.FAIL;
	}
}

public Browser getBrowserObject() {
	return browserObject;
}

public void setBrowserObject(Browser browserObject) {
	this.browserObject = browserObject;
}


}
