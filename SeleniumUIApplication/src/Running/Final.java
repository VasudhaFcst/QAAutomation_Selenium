package Running;

import static org.testng.Assert.assertNotNull;

import java.awt.Color;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Element;

import Application.ConsoleOutput;
import Application.TitlePage;
import Application.ZipFolder;
import Common.Browser;
import Common.Information;



public class Final implements Information{
//	static WebDriver driver;
	String browser;
	String browserPath;
	Browser browserObject;
	String driverName;
	public static Properties prop=null;
	public static GettingData gd=null;
	static String username = "vasudha katragunta"; // Your username
	static String authkey = "56bfbcfd-212e-4161-b420-ebe156531995";
	public static final String URL = "http://" + username + ":" + authkey + "@ondemand.saucelabs.com:80/wd/hub";
	public Final() throws Exception{
			
	}
//	public static void main(String[] args) throws Exception {
//		new Final().testing();
//	}

public List<Map<String,String>> testing(String[] imp) throws Exception{
	
	// for open the browser
	// for firefox browser
	browser=imp[BROWSERNAME];
	System.out.println("Browser name in Final:"+browser);
	browserPath=imp[BROWSERPATH];
	browserObject = new Browser(browser, browserPath);
	browserObject.setNullWebDriver();
	ReadProp rp= new ReadProp();	// calling ReadProp class
	prop=rp.getPath(imp[PROPERTIES]);	// store the path which is saved in properties file
	Extents ext=new Extents(imp[EXTENTREPORT]);		// store the path for extent report file
	XMLCreation xml=new XMLCreation(imp[XML]);		// store the path for xml file
	File normal,secret;
	Map<String,String> testscripts=new LinkedHashMap<String,String>(); 	// this map for combination of testcase number and test case name
	Map<String,String> testResults=new LinkedHashMap<String,String>();	// this map for combination of testcase number and result
		int i=0;
//		DesiredCapabilities caps = DesiredCapabilities.firefox();
//	    caps.setCapability("platform", "Windows 10");
//	    caps.setCapability("version", "59");
//	    driver = new RemoteWebDriver(
//				new URL(URL), caps);
		String Efile=imp[EXCEL];	//store the path for excel sheet
		WriteExcelSheet we = null; //01-07-2022
		Efile = Efile.replaceAll("/", Matcher.quoteReplacement(File.separator));
		System.out.println("Path for excel sheet"+Efile);
	try{
		normal=new File(Efile);						//call the file
		secret=new File(normal.getParent()+"/"+"secret");	//create secret directory
		secret.mkdir();
		FileUtils.copyFileToDirectory(normal.getAbsoluteFile(), secret.getAbsoluteFile(), false); // copy the file from normal to secret folder
		ReadExcelSheet er=new ReadExcelSheet(Efile);	//calling excel sheet
		
		String scenarios=imp[SCENARIOS];	//calling scenarios worksheet
		System.out.println(scenarios);
		System.out.println("count: "+er.count(scenarios));
		er.removeCells(scenarios,TESTCASERESULT);	// removing result data in scenarios worksheet
		er.removeCells(scenarios,ACTUALFAILURES);	// removing result data in number of errors in scenarios
		ConsoleOutput.addText("===============================================\n",Color.BLACK);
		ConsoleOutput.addText("Test Execution is started\n",Color.BLACK);
		ConsoleOutput.addText("===============================================\n",Color.BLACK);
		
		we = new WriteExcelSheet(Efile);//01-07-2022
		
		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for(int j=2;j<=er.count(scenarios);j++){					//for loop starts
						if(er.readData(scenarios, j, REQUIRED).equals("Y")){			// verifying whether Y is present or not in scenarios sheet
								er.removeCells(er.readData(scenarios, j, TESTCASENAME),Information.ACTUAL);	
								er.removeCells(er.readData(scenarios, j, TESTCASENAME),Information.RESULT_COLUMN);			// removing result data in current testcase
						}		
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}															// for loop ends
			}
			
		});
		thread.start();		
		
main:	for(int k=1;k<=er.count(scenarios);k++){					// for loop-1 starts.. this is for number of sheets
			String required=er.readData(scenarios, k, REQUIRED);	// Required column for 'Y'
			String tc=er.readData(scenarios, k, TESTCASENUMBER);	// saving respective testcase number
			if(required.equals("Y")){								// verifying whether Y is present or not in scenarios sheet
				String sh=er.readData(scenarios, k, TESTCASENAME);	// calling respective testcase sheet
				ConsoleOutput.addText("--------------------------------------------------------------------------------\n",Color.GRAY);
				ConsoleOutput.addText(sh+" testcase is started. TESTCASE ID is "+tc+"\n", Color.BLACK);
				System.out.println(sh);
		ext.test=ext.er1.startTest(tc+" -- "+sh);					// report starts for this testcase
		Element tcTag=xml.create(sh.replace(" ", "_"), "TestcaseNumber", tc, xml.testcases); //creating tag for individual testcase
		int count= er.count(sh);									// number of rows in testcase
		
		int minFail;
		if(!er.readData(scenarios, k, NUMBEROFFAILURES).trim().equalsIgnoreCase("")) { 	//condition for number of failures mentioned in sheet
			minFail=Integer.parseInt(er.readData(scenarios, k, NUMBEROFFAILURES));
		}																			// if ends
		else {																		// else starts
			minFail=count;															//if nothing is mentioned, minFail will be max of test case
		}																			// else ends
		int actualFail=0,dummy=2;
		boolean started=false;
		for(i=2;i<=count;i++){										// for loop-2 starts
			if(TitlePage.pauseStatus.equals("Pause")) {				// pauseStatus should be 'Pause'
			System.out.println(TitlePage.pauseStatus);
			dummy=i;												// store i value in dummy variable
			Thread.sleep(500);
			String column[]=new String[10];							// store each column data in column[] string for better use
			column[STEPNUMBER]=er.readData(sh, i, STEPNUMBER);
			column[KEYWORD]=er.readData(sh, i, KEYWORD).toUpperCase().trim();
			column[DESCRIPTION]=er.readData(sh, i, DESCRIPTION);
			column[OBJECTNAME]=er.readData(sh, i, OBJECTNAME);
			column[OBJECTTYPE]=er.readData(sh, i, OBJECTTYPE);
			column[VALUE]=er.readData(sh, i, VALUE);
			column[EXPECTED_COLUMN]=er.readData(sh, i, EXPECTED_COLUMN);
			Element step=xml.create("Teststep", "Stepnumber", column[STEPNUMBER], tcTag); //create tags for test steps
			xml.xml("Keyword", column[KEYWORD], step);
			xml.xml("Description", column[DESCRIPTION], step);
			xml.xml("ObjectName", column[OBJECTNAME], step);
			xml.xml("Locator", column[OBJECTTYPE], step);
			xml.xml("Value", column[VALUE], step);
			xml.xml("Expected", column[EXPECTED_COLUMN], step);
			
			if(browserExists(browserObject.getWebDriver())||column[KEYWORD].toUpperCase().equals("OPEN BROWSER")) { // Browser must be open or Keyword should be 'OPEN BROWSER'
				gd= new GettingData(browserObject, ext.test);					// calling getting data report with extent test
				String stepResult=gd.operation(prop, column, i,sh,k,imp);	// Call operation method for execution and return result
				//WriteExcelSheet wes1 = new WriteExcelSheet(Efile);			// Write result in excel sheet//01-07-2022
				System.out.println("Excel Sheet"+we);
				if(stepResult.equalsIgnoreCase(FAIL)) {						// if stepResult is fail
					actualFail++;											// actualFail is incremented to 1
					we.writeData(scenarios,k,ACTUALFAILURES,String.valueOf(actualFail),false);	//written no. of failures in excel sheet
					if(minFail<actualFail) {								// if minfail is less than actual fail
						xml.xml("Result", FAIL, tcTag);						// Result is Fail in specific testcase in xml
						break;												// Then break the for loop - 2
					}														// if closes for mail fail condition
				} else if (actualFail==0) {									// else if actual fails is '0'
					we.writeData(scenarios,k,ACTUALFAILURES,String.valueOf(actualFail),true);
				}
				xml.xml("StepResult", stepResult, step);					// Step result in xml
				if(!column[KEYWORD].toUpperCase().equals("OPEN BROWSER")) 	// if keyword is not open browser then started is true
				started=true;												//Start is incremented to 1
				//wes1.closeResources(); 	//Vasu Updated
			
			}																// if closes for browser or keyword must be existed
			else {															// else starts for browser is closed forcefully
				ext.endExtent();											// extent test is closed
				//WriteExcelSheet we1 = new WriteExcelSheet(Efile);			//write data into excel sheet//01-07-2022
				testscripts.put(tc, sh);									//testscripts is created with comb of testcase number & sheet name
				if(actualFail==0) {											// if actual fail is '0'						
					String status;
					if(!started) {
						we.writeData(scenarios,k,Information.SCENARIOS_COLUMN,UNKNOWN,false);	//if status is false then result is unknown
						status=UNKNOWN;
					}else {
						we.writeData(scenarios,k,Information.SCENARIOS_COLUMN,PASS,true);		// else result is pass in excel sheet
						status=PASS;
					}
					testResults.put(tc, status);												// it stores combination of testcase no. & result
					xml.xml("Result", status, tcTag);											// pass/unknown is reflected in xml
				}															// if ends
				else {														// else actual fail is not '0'
				we.writeData(scenarios,k,Information.SCENARIOS_COLUMN,FAIL,false); // fail in sheet
				testResults.put(tc, FAIL);											// it stores combination of testcase no. & result
				xml.xml("Result", FAIL, tcTag);										// pass/unknown is reflected in xml
				}															// else ends
					if(TitlePage.stopStatus.equals("Stop")) {				// if stop status is stop
						ConsoleOutput.addText("===============================================\n",Color.BLACK);
						ConsoleOutput.addText("You stopped execution\n",Color.BLACK);
						ConsoleOutput.addText("===============================================\n",Color.black);
						
						//we1.closeResources(); //Vasu Updated
						break main;											// break whole testcases execution
					}else {													//else
						
						//we1.closeResources(); //Vasu Updated
						break;												// break current test case execution
					}
//				break main;
			
			}												// else ends
			}else {															// else pause status is Resume
				i=dummy;													// i is dummy value until pause status is Pause
				if(TitlePage.stopStatus.equals("Stop")) {
					ext.endExtent();
					ConsoleOutput.addText("===============================================\n",Color.BLACK);
					ConsoleOutput.addText("You stopped execution\n",Color.BLACK);
					ConsoleOutput.addText("===============================================\n",Color.black);
					break main;
				}
				System.out.print("");
				
			}
		}															 // for loop-2 ends
		System.out.println("Started number "+ started);
		testscripts.put(tc, sh);
		
		if(actualFail==0) {
			if(started) {
			//WriteExcelSheet wes = new WriteExcelSheet(Efile);	//write data into excel sheet//01-07-2022
			we.writeData(scenarios,k,Information.SCENARIOS_COLUMN,Information.PASS,true);					// Pass is typed in excel sheet
			xml.xml("Result", PASS, tcTag);
			testResults.put(tc, PASS);
			//wes.closeResources();//Vasu Updated
			}
		}
		else {
			testResults.put(tc, FAIL);
			xml.xml("Result", FAIL, tcTag);
		}
		if(testResults.get(tc).equalsIgnoreCase(PASS)) {
			ConsoleOutput.addText(sh+"("+tc+") result is ", Color.BLACK);
			ConsoleOutput.addText(testResults.get(tc)+"\n", Color.GREEN);
		}
		else if(testResults.get(tc).equalsIgnoreCase(FAIL)) {
			ConsoleOutput.addText(sh+"("+tc+") result is ", Color.BLACK);
			ConsoleOutput.addText(testResults.get(tc)+"\n", Color.RED);
		}
		else {
			ConsoleOutput.addText(sh+"("+tc+") result is ", Color.BLACK);
			ConsoleOutput.addText(testResults.get(tc)+"\n", Color.DARK_GRAY);
		}
		ConsoleOutput.addText("--------------------------------------------------------------------------------\n",Color.GRAY);
			}
			else if(!(required.equals("")||required.equals(" "))){		// for entering wrong input data
				String sh=er.readData(scenarios, k, TESTCASENAME);
				System.out.println(sh);
				System.out.println(required);
				//WriteExcelSheet we = new WriteExcelSheet(Efile); //01-07-2022
				we.removeCellvalue(scenarios, k, Information.SCENARIOS_COLUMN);
				we.writeData(scenarios, k, Information.SCENARIOS_COLUMN, WRONG, false);
				//we.closeResources(); //Vasu Updated
			}
		ext.endExtent();												// ending report for current testcase
		}															// for loop-1 ends
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		list.add(testscripts);
		list.add(testResults);
		xml.f.setReadOnly();
		System.out.println("Test execution is completed");
		ConsoleOutput.addText("===============================================\n",Color.BLACK);
		ConsoleOutput.addText("Test execution is completed\n",Color.BLACK);
		ConsoleOutput.addText("===============================================\n",Color.black);
//		driver.quit();												// closing browser
		//er.closeResources();//Vasu Updated
		return list;
	} catch (Exception e) {
		ConsoleOutput.addText(e.toString()+"\n",Color.RED);
		e.printStackTrace();
		return null;
	}
	finally{
		ZipFolder zf=new ZipFolder();
		File file=new File(imp[5]);
		if(file.listFiles().length!=0) {
			System.out.println(imp[5]);
		zf.zipping(new File(imp[5]));
		}
	}	
}

public boolean browserExists(WebDriver wd) {
	try {
		wd.getTitle();
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Browser is not existed");
		return false;
	}
}

public boolean stop() {
	try {
		browserObject.getWebDriver().quit();
		return true;
	} catch (Exception e) {
		return false;
	}
}
}
	