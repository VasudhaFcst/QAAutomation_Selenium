package AdditionalSetup;

import java.util.Properties;

import org.openqa.selenium.By;


public class Locators {
	
	public By getObject(Properties p,String objectName, String objectType) throws Exception {	
//		Properties p=rp.getPath();	
		if(objectType.equalsIgnoreCase("ID")){
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINK")){
			return By.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASS")){
			return By.className(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")){
			return By.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CSS")){
			return By.cssSelector(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")){
			return By.name(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")){
			return By.tagName(p.getProperty(objectName));
		}
		return null;
	}
	
	
}
