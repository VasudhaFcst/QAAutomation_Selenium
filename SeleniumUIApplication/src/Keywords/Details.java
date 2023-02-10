package Keywords;

import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class Details implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Details(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		WebElement details=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(details);
		Point point= details.getLocation();
		int x=point.getX();
		int y=point.getY();
		Dimension dimension=details.getSize();
		int height=dimension.getHeight();
		int width=dimension.getWidth();
		String info="Information: Element is in (x,y) position: ("+x+","+y+")\n"+"Height of the Element:"+height+"\nWidth of the Element:"+width;
		cond=false;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, info);
		obj.getExtentTest().log(LogStatus.INFO, Information.INFO.toUpperCase(), info);
		obj.getExcelResult().setData(cond,row,sh,resultRow,info,imp);
		return Information.INFO;
		}
		catch(Exception ne){
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
