package Keywords;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class ElementState implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public ElementState(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			By element = obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			String state = "";
			if (obj.getVisible().isElementPresent(element)) {
				WebElement visible = driver.findElement(element);
				obj.getJavaScript().waitforloading();
				obj.getJavaScript().highlight(visible);
				switch (imp[VALUE].toUpperCase()) {
				case "ENABLED":
					cond = visible.isEnabled();
					state = (cond) ? " is enabled" : "is not enabled";
					break;
				case "DISABLED":
					cond = !visible.isEnabled();
					state = (cond) ? " is disabled" : "is not disabled";
					break;
				case "SELECTED":
					cond = visible.isSelected();
					state = (cond) ? " is selected" : "is not selected";
					break;
				case "DESELECTED":
					cond = !visible.isSelected();
					state = (cond) ? " is deselected" : "is not deselected";
					break;
				case "DISPLAYED":
					cond = visible.isDisplayed();
					state = (cond) ? " is displayed" : "is not displayed";
					break;
				}
			} else {
				if (imp[VALUE].toUpperCase().trim().equals("NOT DISPLAYED")) {
					cond = true;
					state = (cond) ? " is not displayed" : "is displayed";
				} else {
					cond = false;
				}
			}
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, String.valueOf(cond));
			if (cond) {
				ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
				return Information.PASS;
			} else {
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\nOutput: " + record[OBJECTNAME] + state);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				return Information.FAIL;
			}

		} catch (Exception ne) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "error");
			ne.printStackTrace();
			return Information.FAIL;
		}
	}

}
