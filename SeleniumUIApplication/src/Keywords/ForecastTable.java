package Keywords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class ForecastTable implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public ForecastTable(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int rowNum, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			List<WebElement> columnNames = driver.findElements(
					By.xpath("//table[@class='slds-table slds-table--bordered slds-table--cell-buffer']/tr[2]/th"));
			ArrayList<String> columns = new ArrayList<String>();
			for (WebElement ele : columnNames)
				columns.add(ele.getText());

			List<WebElement> accountName = driver.findElements(By.xpath("//tr[@title='Account Name']"));
			int rowNumber = 0, dup = 0;
			for (WebElement name : accountName) {
				WebElement rowButton = name
						.findElement(By.xpath("th//button/lightning-primitive-icon/*[name()='svg']"));
				WebElement cellName = name.findElement(By.tagName("p"));
				String buttonStatus = rowButton.getAttribute("data-key");
				if (cellName.getText().trim().equals(record[VALUE])) {
					if (buttonStatus.equals("chevronright"))
						rowButton.click();
				} else {
					if (buttonStatus.equals("chevrondown"))
						rowButton.click();
				}
				rowNumber++;
			}
			String common = p.getProperty(record[OBJECTNAME]); 
			// "//table[@class='slds-table slds-table--bordered slds-table--cell-buffer']/tr[not(@class='slds-text-title--caps')]";
			String header = "";
			while (true) {
				Thread.sleep(500);
				WebElement row = driver.findElement(By.xpath(common + "[" + rowNumber + "]"));
				obj.getJavaScript().highlight(row);
				List<WebElement> cells = row.findElements(By.tagName("th"));
				String account = "";
				for (int i = 0; i < cells.size(); i++) {
					if (i == 0) {
						account = cells.get(i).getText().trim();
						System.out.println(account);
						String button = common + "[" + rowNumber + "]" + "/th//button/lightning-primitive-icon/*[name()='svg']";
						if (isPresent(button)) {
							WebElement rowButton = driver.findElement(By.xpath(button));
							String attribute = rowButton.getAttribute("data-key");
							if (attribute.equals("chevronright"))
								rowButton.click();
							header = account + "/";
							RECORDS.put(account, new HashMap<String, Long>());
						} else {
							account = header + account;
							RECORDS.put(account, new HashMap<String, Long>());
						}
						if (account.endsWith("Conservative (iii)")) {
							dup = rowNumber;
							System.out.println("Row Number " + rowNumber);
						} else if (account.endsWith("Committed (iv)")) {
							rowNumber = dup + 1;
						}
					} else {
						String cellValue = "";
						cellValue = (String) obj.getJavaScript().js.executeScript("return arguments[0].innerText;", cells.get(i));
						if (cellValue.equals(""))
							cellValue = String.valueOf(obj.getJavaScript().js.executeScript("return arguments[0].value;", cells.get(i).findElement(By.tagName("lightning-input"))));
//						System.out.println("cell value"+ cellValue);
						cellValue = cellValue.replace("$", "").replace(",", "");
						if (cellValue.contains(".") && !cellValue.matches("^[a-zA-Z]*$")) {
							cellValue = String.valueOf(Math.round(Double.parseDouble(cellValue)));
						}
						long rate = Long.parseLong(cellValue);
						RECORDS.get(account).put(columns.get(i - 1), rate);
					}
				}
				System.out.println(RECORDS);
				rowNumber++;
				try {
					if (driver.findElement(By.xpath(common + "[" + rowNumber + "]")).getAttribute("title")
							.equals("Account Number") || !isPresent(common + "[" + rowNumber + "]")) {
						break;
					}
				} catch (Exception e) {
					break;
				}
			}
			cond = true;
			Information.VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "true");
			ru.updateResult(cond, sh, rowNum, resultRow, PASS, imp, record);
			return Information.PASS;
		} catch (Exception e) {
			ru.testing(p, record, rowNum, sh, resultRow,
					Information.FAIL + ": Driver didn't find on " + record[OBJECTNAME] + " because of " + e, imp);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, ERROR);
			e.printStackTrace();
			return Information.FAIL;
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
