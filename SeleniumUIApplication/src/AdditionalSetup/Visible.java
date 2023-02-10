package AdditionalSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

public class Visible {
	WebDriver driver;
	@SuppressWarnings("rawtypes")
	AndroidDriver andy;
public Visible(WebDriver driver){
	this.driver=driver;
}
@SuppressWarnings("rawtypes")
public Visible(AndroidDriver driver){
	this.andy=driver;
}
public boolean isAlertBoxPresent(){
	try{
		driver.switchTo().alert();
		return true;
	}
	catch(Exception na){
		
		return false;
	}
}
public boolean isElementPresent(By by) {
    try {
      driver.findElement(by).isDisplayed();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
public boolean hideKeyboard() {
	try {
		andy.hideKeyboard();
		return true;
	} catch (Exception e) {
		return false;
	}
}

}
