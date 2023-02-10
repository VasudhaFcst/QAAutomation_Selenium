package AdditionalSetup;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Common.Information;
import Running.ScreenshotPage;
import Running.ScreenshotwithURL;
import Running.Timing;
import io.appium.java_client.android.AndroidDriver;

public class ResultScreenshot implements Information{
	WebDriver driver;
	@SuppressWarnings("rawtypes")
	AndroidDriver andy;
	ExtentTest test;
	public ResultScreenshot(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		this.test=test;
	}
	
	@SuppressWarnings("rawtypes")
	public ResultScreenshot(AndroidDriver driver,ExtentTest test) throws Exception{
		this.andy=driver;
		this.test=test;
	}
	
	public String convertToBase64(String image) throws IOException {
		byte[] fileContent = FileUtils.readFileToByteArray(new File(image));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		return "data:image/gif;base64,"+encodedString;
	}
	
	
	
	public void demand(String step,String any,String result,String[] imp,String... s) throws Exception{			// report and screenshot purpose
		
		String img;
		if(imp[PLATFORM].equalsIgnoreCase("Windows")) {
		Visible v=new Visible(driver);
		if(v.isAlertBoxPresent()){
			ScreenshotwithURL scurl=new ScreenshotwithURL(driver, imp[SCREENSHOT]+"\\"+new Timing().timeform());
			img=test.addScreenCapture(convertToBase64(scurl.imagepath));
		}
		else{
		ScreenshotPage sc=new ScreenshotPage(driver, imp[SCREENSHOT]+"\\",new Timing().timeform());
		img=test.addScreenCapture(convertToBase64(sc.imagepath));
		}
		}
		else {
			ScreenshotPage sc=new ScreenshotPage(andy, imp[SCREENSHOT]+"\\"+new Timing().timeform());
			img=test.addScreenCapture(convertToBase64(sc.imagepath));
		}
		switch(result.toUpperCase()){
		case "PASS":
			test.log(LogStatus.PASS, step, any+" The page screenshot: \n"+img );
		break;
		case "FAIL":
			test.log(LogStatus.FAIL, step, any+" The page screenshot: \n"+img );
		break;
		case "WARNING":
			test.log(LogStatus.WARNING, step, any+" The page screenshot: \n"+img );
		break;
		case "INFO":
			test.log(LogStatus.INFO, step, any+" The page screenshot: \n"+img );
		break;
		case "FATAL":
			test.log(LogStatus.FATAL, step, any+" The page screenshot: \n"+img );
		break;
		case "SKIP":
			test.log(LogStatus.SKIP, step, any+" The page screenshot: \n"+img );
		break;
		case "UNKNOWN":
			test.log(LogStatus.UNKNOWN, step, any+" The page screenshot: \n"+img );
		break;
		case "ERROR":
			test.log(LogStatus.ERROR, step, any+" The page screenshot: \n"+img );
		break;
		}
	}
	

	public void demand(WebElement element,String step,String any,String result,String[] imp) throws Exception{			// report and screenshot purpose
			ScreenshotPage sc=new ScreenshotPage(driver, imp[SCREENSHOT]+"\\",new Timing().timeform());
			String img=test.addScreenCapture(convertToBase64(sc.imagepath));
//			File file=new File(sc.imagepath);
//			Point point=element.getLocation();
//			int x=point.getX();
//			int y=point.getY();
//			Dimension dimension=element.getSize();
//			int width=dimension.getWidth();
//			int height=dimension.getHeight();
//			BufferedImage bimage=ImageIO.read(file);
//			BufferedImage subbimage=bimage.getSubimage(x, y, width, height);
//			String sub=imp[5]+"\\element "+new Timing().timeform()+".png";
//			File file1=new File(sub);
//			ImageIO.write(subbimage, "PNG", file1);
//			FileUtils.copyFile(file, new File(sub));
//			String subimg = test.addScreenCapture(sub);
			switch(result.toUpperCase()){
			case "PASS":
				test.log(LogStatus.PASS, step, any+" The page screenshot: \n"+img +"\n");
			break;
			case "FAIL":
				test.log(LogStatus.FAIL, step, any+" The page screenshot: \n"+img );
			break;
			case "WARNING":
				test.log(LogStatus.WARNING, step, any+" The page screenshot: \n"+img);
			break;
			case "INFO":
				test.log(LogStatus.INFO, step, any+" The page screenshot: \n"+img );
			break;
			case "FATAL":
				test.log(LogStatus.FATAL, step, any+" The page screenshot: \n"+img );
			break;
			case "SKIP":
				test.log(LogStatus.SKIP, step, any+" The page screenshot: \n"+img  );
			break;
			case "UNKNOWN":
				test.log(LogStatus.UNKNOWN, step, any+" The page screenshot: \n"+img  );
			break;
			case "ERROR":
				test.log(LogStatus.ERROR, step, any+" The page screenshot: \n"+img );
			break;
			}
	}
}
