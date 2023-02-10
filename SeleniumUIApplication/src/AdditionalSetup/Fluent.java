package AdditionalSetup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import io.appium.java_client.android.AndroidDriver;

public class Fluent {
WebDriver driver;
@SuppressWarnings("rawtypes")
AndroidDriver and;
public Fluent(WebDriver driver){
	this.driver=driver;
}

@SuppressWarnings("rawtypes")
public Fluent(AndroidDriver driver){
	this.and=driver;
}

public WebElement fluentWait(final By locator){
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(10, TimeUnit.SECONDS)
        .pollingEvery(1000, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .ignoring(WebDriverException.class)
        .ignoring(ElementNotVisibleException.class);

    try {
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		        public WebElement apply(WebDriver driver) {
		            return driver.findElement(locator);
		        }
		    }
		);
		return foo;
	} catch (Exception e) {
		return null;
	}
}

public WebElement fluentWait(final By locator,int time){
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(time, TimeUnit.MILLISECONDS)
        .pollingEvery(1000, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .ignoring(WebDriverException.class)
        .ignoring(ElementNotVisibleException.class);

    try {
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		        public WebElement apply(WebDriver driver) {
		            return driver.findElement(locator);
		        }
		    }
		);
		return foo;
	} catch (Exception e) {
		return null;
	}
}

@SuppressWarnings("rawtypes")
public WebElement fluent(final By locator){
    Wait<AndroidDriver> wait = new FluentWait<AndroidDriver>(and)
        .withTimeout(10, TimeUnit.SECONDS)
        .pollingEvery(1000, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .ignoring(WebDriverException.class)
        .ignoring(ElementNotVisibleException.class);

    WebElement foo = wait.until(new Function<AndroidDriver, WebElement>() {
            public WebElement apply(AndroidDriver driver) {
                return driver.findElement(locator);
            }
        }
    );
    return foo;
}

@SuppressWarnings("rawtypes")
public WebElement fluent(final By locator,int time){
    Wait<AndroidDriver> wait = new FluentWait<AndroidDriver>(and)
        .withTimeout(time, TimeUnit.SECONDS)
        .pollingEvery(1000, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .ignoring(WebDriverException.class)
        .ignoring(ElementNotVisibleException.class);

    WebElement foo = wait.until(new Function<AndroidDriver, WebElement>() {
            public WebElement apply(AndroidDriver driver) {
                return driver.findElement(locator);
            }
        }
    );
    return foo;
}

}
