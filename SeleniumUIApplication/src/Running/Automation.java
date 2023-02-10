package Running;

import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.Select;
import java.text.ParseException;

public class Automation {
	static String username = "Vasudha Katragunta"; // Your username
	static String authkey = "56bfbcfd-212e-4161-b420-ebe156531995";
	 public static final String URL = "http://" + username + ":" + authkey + "@ondemand.saucelabs.com:80/wd/hub";
	 static int n=0;
public static void main(String[] args) throws MalformedURLException, ParseException {
//	DesiredCapabilities caps = DesiredCapabilities.firefox();
//    caps.setCapability("platform", "Windows 10");
//    caps.setCapability("version", "59");
// 
//
//	RemoteWebDriver driver = new RemoteWebDriver(
//			new URL(URL), caps);
//	driver.get("http://automationpractice.com/index.php");
//	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	driver.findElement(By.xpath(".//a[contains(text(),'Sign in')]")).click();
//	driver.findElement(By.id("email")).sendKeys("katraguntavasudha@gmail.com");
//	driver.findElement(By.id("passwd")).sendKeys("selenium");
//	driver.findElement(By.name("SubmitLogin")).click();
//	
//	driver.findElement(By.id("search_query_top")).sendKeys("Dresses");
//	driver.findElement(By.name("submit_search")).click();
//	
//	driver.findElement(By.xpath(".//ul[@class='product_list grid row']//img[1]")).click();
////	driver.navigate().refresh();
//	driver.findElement(By.xpath(".//*[@id='color_to_pick_list']//li[1]")).click();
//	
//	Select select=new Select(driver.findElement(By.id("group_1")));
//	select.selectByVisibleText("M");
//	
//	driver.quit();
	
	ReadExcelSheet res=new ReadExcelSheet("C:\\testing\\Shopping.xlsx");
	int c=res.count("Sheet1");
	main : for(int i=0;i<=c;i++) {
		String key=res.readData("Sheet1", i, 1);
		if(key.equals("IF")) {
			
			
			if(res.readData("Sheet1", i, 5).equals("Hello1")) {
				for(int j=i+1;j<=c;j++) {
					String key2=res.readData("Sheet1", j, 1);
					if(key2.equals("END IF")) {
						i=j;
						break main;
					}
					else {
						System.out.println(res.readData("Sheet1", j, 1));
					}
				}
			}
			else {
				
				for(int m=i+1;m<c;m++) {
					if(res.readData("Sheet1", m, 1).equals("ELSE")) {
						n=m;
						break;
					}
				}
				for(int k=n+1;k<c;k++) {
					String key2=res.readData("Sheet1", k, 1);
					if(key2.equals("END ELSE")) {
						i=k;
						break;
					}
					else {
						System.out.println(res.readData("Sheet1", k, 1));
					}
				}
			}
		}
		else
		System.out.println(res.readData("Sheet1", i, 1));
	}
}
}
