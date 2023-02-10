package AdditionalSetup;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;


public class Objects {
	WebDriver driver = null;
	ExtentTest test;
	Fluent flu;
	JavaScript js;
	Locators l;
	ResultScreenshot rs;
	Visible v;
	PopupWindow pw;
	private Excelresult exr;
	public Objects(WebDriver driver) {
		this.driver = driver;
	}
	
	public Objects(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}
	
	public ExtentTest getExtentTest() {
		return test;
	}
	public Fluent getFluent() {
		return new Fluent(driver);
	}
	public JavaScript getJavaScript() {
		return new JavaScript(driver);
	}
	public Excelresult getExcelResult() {
		if(exr==null) {
			exr=new Excelresult();
		}
		return exr;
	}
	public Locators getLocators() {
		return new Locators();
	}
	public ResultScreenshot getResultScreenshot() throws Exception {
		return new ResultScreenshot(driver, test);
	}
	public Visible getVisible() {
		return new Visible(driver);
	}

	public PopupWindow getPopupWindow() {
		return new PopupWindow(driver);
	}
}
