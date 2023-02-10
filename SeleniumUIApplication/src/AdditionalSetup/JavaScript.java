package AdditionalSetup;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScript {
WebDriver driver;
public JavascriptExecutor js;
public JavaScript(WebDriver driver){
	this.driver=driver;
	js = (JavascriptExecutor) driver;
}
public void highlight(WebElement verifyPass) throws Exception {		//highlighting element
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red');",verifyPass);
	Thread.sleep(500);
	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');",verifyPass);
}
public void mark(WebElement element1) throws Exception{				// mark the element
	js.executeScript("arguments[0].setAttribute('style','border: solid 2px red');",element1);
}

public void nomark(WebElement element1) throws Exception{			//unmark the element
	js.executeScript("arguments[0].setAttribute('style','border: none');",element1);
}

public void scroll(WebElement element1, String value){
	if(value.equalsIgnoreCase("bottom"))
	js.executeScript("arguments[0].scrollIntoView(false);",element1);
	else
		js.executeScript("arguments[0].scrollIntoView(true);",element1);
}

public void waitforloading(){
	ExpectedCondition<Boolean> expectation = (driver) -> {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                };
            
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(expectation);
}

public boolean jsClick(WebElement element) {
	try {
		js.executeScript("arguments[0].click();", element);
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

public boolean jsWrite(WebElement element, String value) {
	try {
		js.executeScript("arguments[0].value='"+value+"';", element);
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

public String getInputText(WebElement element) {
	String text = (String) js.executeScript("return arguments[0].value;", element);
	return text;
}

public String getInnerText(WebElement element) {
	String text = (String) js.executeScript("return arguments[0].innerText;", element);
	return text;
}

public String getInnerHTML(WebElement element) {
	String text = (String) js.executeScript("return arguments[0].innerHTML;", element);
	return text;
}

}
