package Running;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AdditionalSetup.ExecutionPart;
import AdditionalSetup.SelectExecutionOption;
import Common.Information;

public class ExecutionTest implements Information {

	String[] basicParams = new String[10];
	
	@BeforeSuite
	public void initial() {
		SelectExecutionOption.setSelectOption(ExecutionPart.TESTNG);
	}
	
	@BeforeTest
	@Parameters({"browser", "driverPath", "excel", "reportName"})
	public void setup(String browserName, String driverPath, String excelName, String reportName) throws IOException {
		basicParams[PLATFORM]= "Windows";
		basicParams[BROWSERNAME]= browserName;
		System.out.println("Browser name "+basicParams[BROWSERNAME]);
		basicParams[EXCEL]= excelName;
		File f = new File(excelName);
		basicParams[PROPERTIES]= excelName.replace(".xlsx", ".properties");
		String time=new Timing().timeReport();
		System.out.println(f.getParent());
		new File(System.getProperty("user.dir")+"/reports/HTML_Reports").mkdirs();
		new File(System.getProperty("user.dir")+"/reports/XML_Reports").mkdirs();
		new File(System.getProperty("user.dir")+"/Screenshot_"+time).mkdirs();
		basicParams[EXTENTREPORT]= System.getProperty("user.dir")+"/reports/HTML_Reports/"+browserName+reportName+".html";
		basicParams[SCREENSHOT]= System.getProperty("user.dir")+"/"+browserName+"Screenshot_"+time;
		basicParams[BROWSERPATH]= driverPath;
		basicParams[SCENARIOS]= "SCENARIOS";
		basicParams[XML]= System.getProperty("user.dir")+"/reports/XML_Reports/"+browserName+reportName+".xml";;
	}
	
	@Test
	public void testcases() throws Exception {
		Final f = new Final();
		System.out.println(Arrays.asList(basicParams));
		f.testing(basicParams);
		System.out.println(basicParams[EXTENTREPORT]);
	}
}
