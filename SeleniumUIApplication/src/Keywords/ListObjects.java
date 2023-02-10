package Keywords;

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

public class ListObjects implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public ListObjects(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		String value=record[VALUE];
		By list=obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]);
		String other=value.substring(value.indexOf("=")+1,value.indexOf(","));
		String substring=value.substring(0,value.indexOf("="));
		int size=Integer.parseInt(other);
		if(obj.getVisible().isElementPresent(list)){
			WebElement prodlist = driver.findElement(list);
			List<WebElement> listImages = null;
			listImages=prodlist.findElements(By.tagName(substring));
			int total=listImages.size();
			int images=0;
			String main=value.substring(value.indexOf(",")+1,value.lastIndexOf("="));
			String mn=value.substring(value.lastIndexOf("=")+1,value.lastIndexOf(","));
			int mainNumber=Integer.parseInt(mn);
			if(value.contains(main)){
					listImages=prodlist.findElements(By.tagName(main));
					images=listImages.size();
			}
			if(total/size==images/mainNumber){
			if(value.contains("screen")){
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
				obj.getJavaScript().mark(prodlist);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Number of images are "+total/size, Information.PASS,imp);
				obj.getJavaScript().nomark(prodlist);
			}
			else{
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Number of images are "+total/size);
			}
			return Information.PASS;
			}
			else{
				cond= false;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
				obj.getJavaScript().mark(prodlist);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Number of images are "+total/size+" but showing images are "+images/mainNumber, Information.FAIL,imp);
				obj.getJavaScript().nomark(prodlist);
				return Information.FAIL;
			}
		}
		else{
			cond= true;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], record[OBJECTNAME]+" is disabled");
			return Information.PASS;
		}

		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

public String counting(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		String[] mainObjectName=record[OBJECTNAME].split(";");
		String[] mainObjectType=record[OBJECTTYPE].split(";");
		int countElements=Integer.parseInt(record[VALUE]);
		WebElement parent=driver.findElement(obj.getLocators().getObject(p,mainObjectName[0],mainObjectType[0]));
		obj.getJavaScript().highlight(parent);
		List<WebElement> child=parent.findElements(obj.getLocators().getObject(p,mainObjectName[1], mainObjectType[1]));
		obj.getJavaScript().mark(parent);
		if(countElements==child.size()){
			cond= true;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			obj.getResultScreenshot().demand(record[STEPNUMBER], "Actual Number of images or links are "+child.size()+" \n Actual and expected images or links are matched", Information.PASS,imp);
			obj.getJavaScript().nomark(parent);
			return Information.PASS;
		}
		else{
			cond= false;
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
			obj.getResultScreenshot().demand(record[STEPNUMBER], "Actaul Number of images or links are "+child.size()+" \n But expected images or links are"+countElements, Information.FAIL,imp);
			obj.getJavaScript().nomark(parent);
			return Information.FAIL;
		}
		

		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
