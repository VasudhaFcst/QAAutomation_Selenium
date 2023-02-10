package Keywords;

import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.handlers.text_html;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;
import Running.Final;
//import sun.awt.www.content.image.jpeg;

public class TableValidation implements Information {

	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;
	String displayResult = "";

	public TableValidation(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	
	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {

		try {
			obj.getJavaScript().scroll(
					driver.findElement(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE])), "up");

			int fail = 0;
			String[] elements = record[VALUE].split(",");
			List<WebElement> tableRowsElements = driver
					.findElements(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			System.out.println(tableRowsElements.size());
			List<WebElement> headers = ELEMENTS_LIST.get(elements[0].trim());
			List<WebElement> planningVersions = ELEMENTS_LIST.get(elements[1].trim());
			String reportCondition=elements[2].trim();
			System.out.println("Report Condition(True/False:"+reportCondition);
			List<String> versionValues = new ArrayList<>();
			for (WebElement versionElement : planningVersions) {
				versionValues.add(obj.getJavaScript().getInnerText(versionElement));
			}
			System.out.println("versionValues :: " + versionValues);
			ArrayList<String> headerName = new ArrayList<String>();
			for (WebElement element : headers)
				headerName.add(element.getText());
			ArrayList<Map<String, Double>> month_values = new ArrayList<>();

			for (int i = 1; i <= tableRowsElements.size(); i++) {
				WebElement tableRow = driver.findElement(By.xpath(p.getProperty(record[OBJECTNAME]) + "[" + i + "]"));
				List<WebElement> cells = tableRow.findElements(By.tagName("td"));
				Map<String, Double> months = new LinkedHashMap<String, Double>();
				int count = 0;
				double d = 0.0;
				for (WebElement cell : cells) {
					if (!cell.getAttribute("class").equals("dummyCell")) {
						String valueFromCell;
						if (obj.getJavaScript().getInnerHTML(cell).trim().contains("<input")) {
							WebElement input = cell.findElement(By.tagName("input"));
							valueFromCell = obj.getJavaScript().getInputText(input);
						} else
							valueFromCell = cell.getText().replace(",", "").trim();

						valueFromCell = valueFromCell.replaceAll("%", "");

						// System.out.println("valueFromCell :: "+valueFromCell);
						double convertedValue;
						if (valueFromCell.equals("-") || valueFromCell.equals(" "))
							convertedValue = 0;
						else {
							if (valueFromCell != null && valueFromCell.contains("(")) {
								System.out.println("Before paranthesis removal :: " + valueFromCell);
								valueFromCell = valueFromCell.replaceAll("\\(", "");
								valueFromCell = valueFromCell.replaceAll("\\)", "");
								valueFromCell = "-" + valueFromCell;
								System.out.println("After paranthesis removal :: " + valueFromCell);
							}

							convertedValue = Double.parseDouble(valueFromCell);
						}
						months.put(headerName.get(count), convertedValue);
						count++;
					}
				}
				month_values.add(months);
				System.out.println("Month values:" + month_values);
				int month = 1;
				if (i == 1) {
					month = Integer.parseInt(versionValues.get(0).split("-")[1]);
				} else {
					month = Integer.parseInt(versionValues.get(0).split("-")[1]);
					System.out.println("month is " + month);
				}

				double q1addition = months.get("Jan") + months.get("Feb") + months.get("Mar");
				double q2addition = months.get("Apr") + months.get("May") + months.get("Jun");
				double q3addition = months.get("Jul") + months.get("Aug") + months.get("Sep");
				double q4addition = months.get("Oct") + months.get("Nov") + months.get("Dec");
				double ytd = 0;
				for (int k = 1; k < month; k++) {
					// SimpleDateFormat sdf1 = new SimpleDateFormat("M");
					// SimpleDateFormat sdf2 = new SimpleDateFormat("MMM");
					// System.out.println(sdf2.format(sdf1.parse(String.valueOf(k))));
					ytd = ytd + months.get(headerName.get(k - 1));
				}
				double ltg = 0;
				for (int c = month; c <= 12; c++) {
					ltg = ltg + months.get(headerName.get(c - 1));
				}
				System.out.println("Q1 value:" + q1addition + " -> " + months.get("Q1"));
				System.out.println("Q2 value:" + q2addition + " -> " + months.get("Q2"));
				System.out.println("Q3 value:" + q3addition + " -> " + months.get("Q3"));
				System.out.println("Q4 value:" + q4addition + " -> " + months.get("Q4"));
				System.out.println("YTD value:" + ytd + " ->" + months.get("YTD"));
				System.out.println("LTG value:" + ltg + " ->" + months.get("LTG"));
				double total = q1addition + q2addition + q3addition + q4addition;
				System.out.println("Full year:" + total + " -> " + months.get("Full Year"));
				
				if(reportCondition.equalsIgnoreCase("True")) {
					displayResult = displayResult + "<br>Row-" + i;
	
					fail = fail + validateValues(q1addition, months.get("Q1"), "Q1",reportCondition);
					fail = fail + validateValues(q2addition, months.get("Q2"), "Q2",reportCondition);
					fail = fail + validateValues(q3addition, months.get("Q3"), "Q3",reportCondition);
					fail = fail + validateValues(q4addition, months.get("Q4"), "Q4",reportCondition);
					fail = fail + validateValues(total, months.get("Full Year"), "Full Year",reportCondition);
					fail = fail + validateValues(ytd, months.get("YTD"), "YTD",reportCondition);
					fail = fail + validateValues(ltg, months.get("LTG"), "LTG",reportCondition);
				}
				
			}

			if (fail == 0) {
				cond = true;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n" + displayResult);
			} else {
				cond = false;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);

				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], "Description: " + record[DESCRIPTION]
						+ "\nPlease check below rows. " + fail + " are failed<br>" + displayResult);

			}

			TABLE_VALUES.put(record[OBJECTNAME], month_values);

			return cond ? Information.PASS : Information.FAIL;
		} catch (Exception ne) {

			ne.printStackTrace();
			return Information.FAIL;
		}

	}

	private int validateValues(double actual, double expected, String label, String check) {
		int condition=1;
		try {
			
				System.out.println("entered checking condition loop");
			if(!(Math.floor(actual) == Math.floor(expected) || Math.ceil(actual) == Math.ceil(expected))) {
				//condition =1;
				displayResult = displayResult + "<br><span style='color:red'>"+label+" value:"+actual+" -> "+expected+"</span>";
			}
			else {
				condition =0;
				displayResult = displayResult + "<br>"+label+" value:"+actual+" -> "+expected;
			}
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return condition;
	}
	public String TableData(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {

		try {
			
			List<WebElement> tableRowsElements=driver.findElements(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			for (int i = 1; i <= tableRowsElements.size(); i++) {
				WebElement tableRow = driver.findElement(By.xpath(p.getProperty(record[OBJECTNAME]) + "[" + i + "]"));
				List<WebElement> cells = tableRow.findElements(By.tagName("td"));
				
				Map<String, Double> months = new LinkedHashMap<String, Double>();
				int count = 0;
				double d = 0.0;
				for (WebElement cell : cells) {
					String valueFromCell = cell.getText();
					System.out.println("table Data ===>"+valueFromCell);
						
				}}
		}
		catch (Exception ne) {

			ne.printStackTrace();
			return Information.FAIL;
		}
		return sh;

	}
}
