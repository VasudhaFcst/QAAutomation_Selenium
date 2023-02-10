package Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public interface Information {
String PASS="Pass";
String FAIL="Fail";
String WARNING="Warning";
String INFO="Info";
String SKIP="Skip";
String FATAL="Fatal";
String UNKNOWN="Unknown";
String ERROR="Error";
String WRONG="Wrong Input";

String VALUE_END="-value";

String DRIVER="driver";
String FIREFOX="firefox";
String CHROME="chrome";
String IE="internet explorer";
String CHROME_DRIVER="webdriver.chrome.driver";
String GECKO_DRIVER="webdriver.gecko.driver";
String IE_DRIVER="webdriver.ie.driver";
String CHROME_DRIVER_PATH="chromedriver";

//String PROPERTIES="C:\\testing\\info.properties";
String PROPERTIES_PATH="path";

String EXCEL_PATH="path";
String SCREENSHOT_PATH="screenshotpath";
String REPORTS_PATH="reportspath";
//String SCENARIOS="mainsheet";

String INVALID_INPUT="Invalid Input";
String INVALID_KEYWORD="Invalid Keywords or NULLs are mentioned";
String UNEXPECTED="User is unable to perform operation on that element";
String EXPECTED="Output as expected";
String FRAME_WARNING="Warning: User is unable to move to Frame";
String PARENT_WARNING="Warning: User is unable to move to parent window";
int STEPNUMBER=0;
int KEYWORD=1;
int DESCRIPTION=2;
int OBJECTNAME=3;
int OBJECTTYPE=4;
int VALUE=5;
int EXPECTED_COLUMN=6;
int ACTUAL=7;
int RESULT_COLUMN=8;
int SCENARIOS_COLUMN=3;

int TESTCASENAME=0;
int TESTCASENUMBER=1;
int REQUIRED=2;
int TESTCASERESULT=3;
int NUMBEROFFAILURES=4;
int ACTUALFAILURES=5;

int PLATFORM=0;
int BROWSERNAME=1;
int EXCEL=2;
int PROPERTIES=3;
int EXTENTREPORT=4;
int SCREENSHOT=5;
int BROWSERPATH=6;
int SCENARIOS=7;
int XML=8;

Map<String, WebElement> ELEMENTS=new LinkedHashMap<String, WebElement>();
Map<String, List<WebElement>> ELEMENTS_LIST=new LinkedHashMap<String, List<WebElement>>();
HashMap<String, List<WebElement>> VALUES = new HashMap<String,List<WebElement>>();
Map<String, HashMap<String, Long>> RECORDS = new LinkedHashMap<String, HashMap<String, Long>>();
Map<String, String> VALUE_STORAGE=new HashMap<String, String>();
Map<String, ArrayList<Map<String, Double>>> TABLE_VALUES = new HashMap<>();
Map<String,Double> TABLE_DATA=new HashMap<String, Double>();
List<String> DIVVALUE_STORAGE=new ArrayList<String>();
Map<String, List<String>> MULTIVALUE_LIST=new LinkedHashMap<String, List<String>>();
}
