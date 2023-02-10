package Keywords;

import java.awt.Color;

import javax.swing.text.BadLocationException;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import Application.ConsoleOutput;
import Common.Information;

public class Print implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;

	public Print(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
	}

	public String getValue(String object[]) throws BadLocationException {
		String[] printValues = object[VALUE].split("[+]");
		for (String key : printValues) {
			System.out.println(key);
			if (VALUE_STORAGE.containsKey(key.trim())) {
				ConsoleOutput.addText(VALUE_STORAGE.get(key.trim()), Color.BLUE);
				obj.getExtentTest().log(LogStatus.INFO, object[STEPNUMBER],
						"Description: " + object[DESCRIPTION] + "\nOutput: " + VALUE_STORAGE.get(key.trim()));

			} else {
				ConsoleOutput.addText(key.trim().replace("\"", ""), Color.BLACK);
			}
		}
		ConsoleOutput.addText("\n", Color.BLACK);

		return INFO;
	}

}
