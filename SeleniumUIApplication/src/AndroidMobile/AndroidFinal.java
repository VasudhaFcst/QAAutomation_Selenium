package AndroidMobile;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import Common.Information;
import Running.Extents;
import Running.ReadExcelSheet;
import Running.ReadProp;
import Running.WriteExcelSheet;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidFinal implements Information{
@SuppressWarnings("rawtypes")
AndroidDriver driver;

@SuppressWarnings({ "rawtypes", "unchecked" })
public void testing(String parameters[]) throws Exception {
	File normal,secret;
	String[] testcases,result;
	int i=0,r=0;
	ReadProp rp= new ReadProp();	// calling ReadProp class
	Properties prop=rp.getPath(parameters[9]);	// store the path which is saved in properties file
	Extents ext=new Extents(parameters[11]);
	DesiredCapabilities cap=new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, parameters[1]);
	cap.setCapability(MobileCapabilityType.BROWSER_NAME, parameters[2]);
	cap.setCapability(MobileCapabilityType.VERSION, parameters[3]);
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, parameters[4]);
	cap.setCapability("appPackage", parameters[5]);
	cap.setCapability("appActivity", parameters[6]);
	
	
	driver=new AndroidDriver(new URL(parameters[7]),cap);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	String Efile=parameters[8];
	normal=new File(Efile);
	secret=new File(normal.getParent()+"/"+"secret");
	FileUtils.copyFileToDirectory(normal.getAbsoluteFile(), secret.getAbsoluteFile(), false);
	ReadExcelSheet er=new ReadExcelSheet(Efile);	//calling excel sheet
	
	String scenarios=parameters[10];	//calling scenarios worksheet
	System.out.println(scenarios);
	System.out.println("count: "+er.count(scenarios));
	er.removeCells(scenarios,3);	// removing result data in scenarios worksheet
	for(int j=2;j<=er.count(scenarios);j++){					//for loop starts
		if(er.readData(scenarios, j, 2).equals("Y")){			// verifying whether Y is present or not in scenarios sheet
			r++;
			er.removeCells(er.readData(scenarios, j, 0),Information.ACTUAL);	
			er.removeCells(er.readData(scenarios, j, 0),Information.RESULT_COLUMN);			// removing result data in current testcase
		}		
	}															// for loop ends
	testcases=new String[r];
	result=new String[r];
	r=0;
main:	for(int k=1;k<=er.count(scenarios);k++){					// for loop-1 starts
		String required=er.readData(scenarios, k, 2);
		String tc=er.readData(scenarios, k, 1);					// saving respective testcase number
		if(required.equals("Y")){								// verifying whether Y is present or not in scenarios sheet
			String sh=er.readData(scenarios, k, 0);				// calling respective testcase sheet
			System.out.println(sh);
	ext.test=ext.er1.startTest(tc+" -- "+sh);							// report starts for this testcase
//	Element tcTag=xml.create(sh, "TestcaseNumber", tc, xml.testcases);
	testcases[r]=sh;
	int count= er.count(sh);									// number of rows in testcase
	CallKeywords ck= new CallKeywords(driver,ext.test);
	WriteExcelSheet wes = new WriteExcelSheet(Efile);	//write data into excel sheet
	wes.writeData(scenarios,k,Information.SCENARIOS_COLUMN,Information.PASS,true);					// Pass is typed in excel sheet
	result[r]=PASS;
	for(i=2;i<=count;i++){										// for loop-2 starts
		Thread.sleep(500);
		String column[]=new String[10];
		column[0]=er.readData(sh, i, 0);
		column[1]=er.readData(sh, i, 1).toUpperCase().trim();
		column[2]=er.readData(sh, i, 2);
		column[3]=er.readData(sh, i, 3);
		column[4]=er.readData(sh, i, 4);
		column[5]=er.readData(sh, i, 5);
		column[6]=er.readData(sh, i, 6);
//		Element step=xml.create("Teststep", "Stepnumber", column[0], tcTag);
		
		if(mobileExists(driver)) {
//			String stepResult=
			ck.operation(prop, column, i,sh,k,parameters);
		if(ext.test.getRunStatus().toString().equalsIgnoreCase(FAIL)){
			result[r]=FAIL;
		}
		else if(ext.test.getRunStatus().toString().equalsIgnoreCase(SKIP)){
			result[r]=SKIP;
		}
		
		if(er.readData(sh, i, 1).equalsIgnoreCase("GOTOURL")){		// verifying title name
			String title_name=driver.getTitle();
			String value="Problem loading page";
			if(title_name.equals(value.trim())){
				wes.writeData(scenarios,k,Information.SCENARIOS_COLUMN,Information.FAIL,false);
				wes.writeData(sh,i,Information.RESULT_COLUMN,Information.FAIL,false);
				result[r]=FAIL;
				break;
			}
			else
				System.out.println("URL is succussfully running");	
		}
		}
		else {
			ext.endExtent();
			break main;
		}
	}															 // for loop-2 ends
	r++;
		}
		else if(!(required.equals("")||required.equals("empty"))){		// for entering wrong input data
			String sh=er.readData(scenarios, k, 0);
			System.out.println(sh);
			System.out.println(required);
			WriteExcelSheet we = new WriteExcelSheet(Efile);
			we.removeCellvalue(scenarios, k, Information.SCENARIOS_COLUMN);
			we.writeData(scenarios, k, Information.SCENARIOS_COLUMN, UNKNOWN, false);
		}
	ext.endExtent();												// ending report for current testcase
	}															// for loop-1 ends
	System.out.println("Test execution is completed");
	driver.quit();												// closing browser
}

public boolean mobileExists(AndroidDriver<MobileElement> wd) {
	try {
		wd.getTitle();
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
//public static void main(String[] args) {
//	builder = new AppiumServiceBuilder();
//	builder
//	.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//	.withAppiumJS(new File("C:\\Users\\miracle\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//	.withIPAddress("127.0.0.1")
//	.usingAnyFreePort();
//	//Start the server with the builder
//	service = AppiumDriverLocalService.buildService(builder);
//	service.start();
//	
//	System.out.println(service.isRunning()+"  "+service.getUrl().toString());
//	
//	service.stop();
//	
//	System.out.println(service.isRunning());
//}
}
