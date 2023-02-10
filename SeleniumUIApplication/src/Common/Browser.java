package Common;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import Application.ConsoleOutput;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Browser {
	WebDriver driver=null;
	String browser;
	String browserPath;
	
	public Browser(String browser, String browserPath) {
		this.browser = browser;
		this.browserPath = browserPath;
	}
	
	public void setNullWebDriver() {
		driver = null;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
public WebDriver webBrowser() throws Exception{
//	ReadProp rp= new ReadProp();	// calling ReadProp class
//	Properties prop=rp.getPath();	// store the path which is saved in properties file
//	String browser=prop.getProperty(Information.DRIVER);
	if(driver==null) {
		System.out.println("Selected browser is "+browser);
	if(browser.equalsIgnoreCase(Information.FIREFOX)){
		try {
			FirefoxOptions profile =new FirefoxOptions();
			profile.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.ms-excel,text/x-csv,application/x-msexcel,application/excel,application/x-excel");
			profile.addPreference("browser.helperApps.alwaysAsk.force", false);
			profile.addPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.addPreference("dom.webnotifications.enabled", false);
			System.setProperty(Information.GECKO_DRIVER, browserPath);
			driver=new FirefoxDriver(profile);
			ConsoleOutput.addText("Firefox is opened\n", Color.DARK_GRAY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ConsoleOutput.addText("Firefox is not connected. Please check version and geckodriver\n", Color.RED);
			ConsoleOutput.addText("Error -"+e.toString()+"\n", Color.RED);
			System.out.println("Firefox is not connected. Please check version and geckodriver");
			e.printStackTrace();
		}
	}
	// for chrome browser
	else if(browser.equalsIgnoreCase(Information.CHROME)){
//		System.setProperty(Information.CHROME_DRIVER, prop.getProperty(Information.CHROME_DRIVER_PATH));
		try {
			System.setProperty(Information.CHROME_DRIVER, browserPath);
//			WebDriverManager.chromedriver().setup();
			ChromeOptions co =new ChromeOptions();
			co.addArguments("disable-infobars");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("password_manager_enabled", false); 
			prefs.put("profile.default_content_setting_values.notifications", 2);
			co.setExperimentalOption("prefs", prefs);
			driver=new ChromeDriver(co);
			ConsoleOutput.addText("Chrome is opened\n", Color.DARK_GRAY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ConsoleOutput.addText("Chrome is not connected. Please check version and geckodriver\n", Color.RED);
			ConsoleOutput.addText("Error -"+e.toString()+"\n", Color.RED);
			System.out.println("Chrome is not connected. Please check version and chromedriver");
			e.printStackTrace();
		}
	}
	else if(browser.equalsIgnoreCase(Information.IE)){
		try {
			WebDriverManager.iedriver().setup();
//		System.setProperty(Information.IE_DRIVER, Final.browserPath);
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability("requireWindowFocus", true);  
//		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
//		capabilities.setCapability("ie.ensureCleanSession", true);
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			driver = new InternetExplorerDriver();
			ConsoleOutput.addText("Internet Explorer is opened\n", Color.DARK_GRAY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ConsoleOutput.addText("Internet Explorer is not connected. Please check version and geckodriver\n", Color.RED);
			ConsoleOutput.addText("Error -"+e.toString()+"\n", Color.RED);
			e.printStackTrace();
		}
	}
	else{
		System.out.println("browser is incorrect");
	}
}
	if(driver!=null) {
			driver.manage().window().maximize();	// maximize the window
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		   	Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase();
		    System.out.println(browserName);
		    String os = cap.getPlatform().toString();
		    System.out.println(os);
		    String v = cap.getVersion().toString();
		    System.out.println(v);
		    ConsoleOutput.addText("Browser version is "+v+"\n", Color.BLACK);
	}
	
	return driver;
}
}
