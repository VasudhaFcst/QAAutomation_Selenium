package Keywords;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class Upload implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public Upload(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			Robot robot = new Robot();
			WebElement upload = driver
					.findElement(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			obj.getJavaScript().highlight(upload);
			obj.getFluent().fluentWait(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE])).click();
			StringSelection sel1 = new StringSelection(record[VALUE]);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel1, null);
			System.out.println("selection" + sel1);
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2500);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			cond = true;
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "true");
			obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
			return Information.PASS;
		} catch (Exception ne) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}

}
