package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import AdditionalSetup.Objects;

public class Waiting {
	WebDriver driver;
	Objects obj;
	public Waiting(WebDriver driver){
		this.driver=driver;
		obj = new Objects(driver);
	}
public void sleep(String value) throws Exception{
	float changeToFloat=Float.parseFloat(value);
	int changeToInt=(int) changeToFloat;
	Thread.sleep(changeToInt);
}

public void rest(Properties p,String objectName,String objectType,String value) throws Exception{
	obj.getFluent().fluentWait(obj.getLocators().getObject(p,objectName, objectType), Integer.parseInt(value));
}
}
