package AdditionalSetup;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class PopupWindow {
	WebDriver driver;
public PopupWindow(WebDriver driver){
	this.driver=driver;
}
public void window(int windowNumber ) {
	ArrayList<String> wh=new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(wh.get(windowNumber));
	driver.manage().window().maximize();
	System.out.println(driver.getTitle());
}

}
