package Keywords;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class OpportunityManagement implements Information{

	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;
	
	List<String> ownerList = new ArrayList();

	public OpportunityManagement(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp) {
		try {
             
			
			List<WebElement> value = driver.findElements(By.xpath(".//*[@class='btnClass']"));
			int valueSize=value.size();
			System.out.println("value1"+value.get(0));
			System.out.println("value1"+value.get(1));
			System.out.println("value:"+value);
			System.out.println("size"+valueSize);
			ownerList.add(value.get(0).getText());
			System.out.println("Before  RecursiveDataOwnerDetails()");
			RecursiveDataOwnerDetails(value, record, p);
			
			System.out.println("Final Owners List ===> "+ownerList);
			
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Information.FAIL;
			}
		return sh;
}

	public void RecursiveDataOwnerDetails(List<WebElement> value, String[] record, Properties p) throws Exception
    {
        // terminate condition
        if (value.size() == 1) {
				return;
        }
		
        for(int i=2; i <= value.size(); i++) {
        	ownerList.add(value.get(--i).getText());
        	++i;
        	//WebElement xPathValue = value.get(1);
        	WebElement ValueClick = driver.findElement(By.xpath("//*[@id=\"brandBand_2\"]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[1]/div/div/div[2]/a"));
    		ValueClick.click();
    		List<WebElement> value1 = driver.findElements(By.xpath(".//*[@class='btnClass']"));
    		if(value1.size() > 1) {
    			System.out.println("Sub Owners List => "+ownerList);
    			RecursiveDataOwnerDetails(value1, record, p);
    		}
        }
		
    }

}