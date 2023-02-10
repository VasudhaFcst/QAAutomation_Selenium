package AndroidMobile;

import java.util.Properties;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileLocators {
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	@SuppressWarnings("rawtypes")
	public MobileLocators(AndroidDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getLocator(Properties p, String objectName, String objectType) {
		WebElement me=null;
		if(objectType.equalsIgnoreCase("ID")){
			me=driver.findElementById(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("LINK")){
			me=driver.findElementByLinkText(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("PARTIAL LINK")){
			me=driver.findElementByPartialLinkText(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("CLASS")){
			me=driver.findElementByClassName(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("XPATH")){
			me=driver.findElementByXPath(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("CSS")){
			me=driver.findElementByCssSelector(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("NAME")){
			me=driver.findElementByName(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")){
			me=driver.findElementByTagName(p.getProperty(objectName));
			return me;
		}
		else if(objectType.equalsIgnoreCase("ACCESSIBILITY")){
			me=driver.findElementByAccessibilityId(p.getProperty(objectName));
			return me;
		}
		return null;
	}
	
	public MobileBy getObject(Properties p,String objectName, String objectType) throws Exception {	
		if(objectType.equalsIgnoreCase("ID")){
			return (MobileBy) MobileBy.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("LINK")){
			return (MobileBy) MobileBy.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("PARTIAL LINK")){
			return (MobileBy) MobileBy.partialLinkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASS")){
			return (MobileBy) MobileBy.className(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")){
			return (MobileBy) MobileBy.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CSS")){
			return (MobileBy) MobileBy.cssSelector(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("NAME")){
			return (MobileBy) MobileBy.name(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")){
			return (MobileBy) MobileBy.tagName(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("ACCESSIBILITY")){
			return (MobileBy) MobileBy.AccessibilityId(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("UI TEXT")||objectType.equalsIgnoreCase("UI_TEXT")){
			return (MobileBy) MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().text(\""+p.getProperty(objectName)+"\"))");
		}
		else if(objectType.equalsIgnoreCase("UI ID")||objectType.equalsIgnoreCase("UI_ID")){
			return (MobileBy) MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().resourceId(\""+p.getProperty(objectName)+"\"))");
		}
		else if(objectType.equalsIgnoreCase("UI DESC")||objectType.equalsIgnoreCase("UI_DESC")){
			return (MobileBy) MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().description(\""+p.getProperty(objectName)+"\"))");
		}
		else if(objectType.equalsIgnoreCase("UI PACKAGE")||objectType.equalsIgnoreCase("UI_PACKAGE")){
			return (MobileBy) MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().packageName(\""+p.getProperty(objectName)+"\"))");
		}
		return null;
	}
	
	public WebElement getElement(Properties p,String objectName, String objectType)  {
		try {
			WebElement we=driver.findElement(getObject(p, objectName, objectType));
			return we;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
