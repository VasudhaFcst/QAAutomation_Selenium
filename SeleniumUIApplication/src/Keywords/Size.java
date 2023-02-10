package Keywords;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class Size implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Size(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		if(!(record[OBJECTTYPE].equals("") || record[OBJECTTYPE].equals("empty"))){
			record[OBJECTNAME]=p.getProperty(record[OBJECTNAME]);
		String multi[]=record[OBJECTNAME].split("--");
		if(record[OBJECTNAME].contains("select")){
		record[OBJECTNAME]=multi[0]+record[OBJECTTYPE]+multi[1];
		System.out.println(record[OBJECTTYPE]);
		By selector=By.xpath(record[OBJECTNAME]);
		if(obj.getVisible().isElementPresent(selector)){
		WebElement selection=driver.findElement(By.xpath(record[OBJECTNAME]));
		obj.getJavaScript().highlight(selection);
		Select s1=new Select(selection);
		List<WebElement> size=s1.getOptions();
		System.out.println("Total Options are: "+size.size());
		System.out.println(size.get(0).getText()+" "+size.get(2).getText());
		for(int d=0;d<=size.size();d++){
			Random rand=new Random();
			int num=rand.nextInt(size.size());
			if(!(size.get(num).getText().equalsIgnoreCase("Select"))){
				s1.selectByVisibleText(size.get(num).getText());
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Size is selected - "+size.get(num).getText());
				break;
			}
			else{
				d--;
			}
		}
		}
		}
		else{
			String sizes[]=record[VALUE].split(",");
			for(int rep=0;rep<sizes.length;rep++){
			record[OBJECTNAME]=multi[0]+sizes[rep]+multi[1];
			By cloths=By.xpath(record[OBJECTNAME]);
			if(obj.getVisible().isElementPresent(cloths)){
				WebElement clothsize=driver.findElement(cloths);
				if(clothsize.isEnabled()){
					obj.getJavaScript().highlight(clothsize);
					clothsize.click();
					cond= true;
					obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
					obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Size is selected - "+sizes[rep]);
					break;
				}
			}
			}
		}
		}
		return Information.PASS;
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}
