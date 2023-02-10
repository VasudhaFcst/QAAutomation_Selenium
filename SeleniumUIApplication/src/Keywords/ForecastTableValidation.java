package Keywords;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;

public class ForecastTableValidation implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;

	public ForecastTableValidation(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
	}

	public String testing(Properties p, String[] record, int rowNum, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			String comparison = "", failure = "";
			Set<String> keys = RECORDS.keySet();
			String[] rows = record[VALUE].split(";");
			for (int i = 0; i < rows.length; i++) {
				String[] validationWith = rows[i].split("=");
				HashMap<String, Long> source = RECORDS.get(validationWith[0].trim());
				String[] parameters = validationWith[1].split(",");
				HashMap<String, Long> duplicate = new HashMap<String, Long>();
				for (int j = 0; j < parameters.length; j++) {
					for (String key : keys) {
						if (key.endsWith(parameters[j].trim())) {
							HashMap<String, Long> dest = RECORDS.get(parameters[j].trim());
							dest.forEach((k, v) -> duplicate.merge(k, v, Long::sum));
						}
					}
				}
				if (source.equals(duplicate)) {
					cond = true;
					comparison = comparison + validationWith[0] + " is compared with " + validationWith[1] + "<br>";
					break;
				}
				else {
					failure = failure + validationWith[0] + " is not compared with " + validationWith[1] + "<br>";
				}
			}
			if (cond) {
				Information.VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "true");
				obj.getExcelResult().setData(cond, rowNum, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\r\n Output: " + record[EXPECTED_COLUMN] + 
						"<br>" + comparison);
				return Information.PASS;
			}
			else {
				Information.VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
				obj.getExcelResult().setData(cond, rowNum, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\r\n Output: " + record[EXPECTED_COLUMN] + 
						"<br>" + failure);
				return Information.FAIL;
			}
		} catch (Exception e) {
			ResultUpdation noe = new ResultUpdation(obj);
			noe.testing(p, record, rowNum, sh, resultRow,
					Information.ERROR + ": Driver didn't find on " + record[OBJECTNAME] + " because of " + e, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			e.printStackTrace();
			return Information.ERROR;
		}
	}

	public boolean isPresent(String ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 0, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
