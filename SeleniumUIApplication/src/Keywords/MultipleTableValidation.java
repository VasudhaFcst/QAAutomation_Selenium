package Keywords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.apache.xpath.internal.operations.Gt;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class MultipleTableValidation implements Information{
	
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	ResultUpdation ru;
	String displayResult = "";
	
	public MultipleTableValidation(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			int fail=0;
			String[] elements = record[VALUE].split(",");
			Double comparedValueDouble = null;
			Double expectedValueDouble = null;
			String displayResult = "";
			
			List<String> compareListNameStrings = new ArrayList<String>();
			compareListNameStrings.add("Q1");
			compareListNameStrings.add("Q2");
			compareListNameStrings.add("Q3");
			compareListNameStrings.add("Q4");
			compareListNameStrings.add("YTD");
			compareListNameStrings.add("LTG");
			compareListNameStrings.add("Full Year");
			
			
			String typeofContMargin = elements[2].trim();
			System.out.println("Type of cont.Margin:"+typeofContMargin);
			int i;
			if(typeofContMargin.equalsIgnoreCase("Dollar")) {
				System.out.println("Entered Contribution margin$");
						    ArrayList<Map<String, Double>> Revenue = TABLE_VALUES.get(elements[0]);
							ArrayList<Map<String, Double>> AllocatedCost = TABLE_VALUES.get(elements[1]);			
							ArrayList<Map<String, Double>> ContributionMargin = TABLE_VALUES.get(elements[2]);
							System.out.println("Revenu:"+Revenue);
							System.out.println("AllocatedCost:"+AllocatedCost);
							System.out.println("ContributionMargin:"+ContributionMargin);
							for(i=0;i<Revenue.size();i++) {
								Map<String, Double> hm1 = Revenue.get(i);
								Map<String, Double> hm2 = AllocatedCost.get(i);
								Map<String, Double> hm3 = ContributionMargin.get(i);
				for (Map.Entry<String, Double> set : hm1.entrySet()) {
					System.out.println("entered for loop");
				    comparedValueDouble = set.getValue() - hm2.get(set.getKey());
				    System.out.println("Revenue-AllocatedCost:"+comparedValueDouble);
				    expectedValueDouble = hm3.get(set.getKey());
				    System.out.println("contributionMargin($)"+expectedValueDouble);

				    
				    if(compareListNameStrings.contains(set.getKey())){
				    	System.out.println("Result:"+displayResult);
						    if(Math.ceil(comparedValueDouble) == Math.ceil(expectedValueDouble) || Math.floor(comparedValueDouble) == Math.floor(expectedValueDouble)) {
						    	cond = true;
						    	//displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey();
						    	displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey()+" value:"+"<br>Revenue-AllocatedCost:"+comparedValueDouble+"--->contributionMargin($) as per appliation" +expectedValueDouble +"<br>";
						    }else {
						    cond = false;
						   //displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey();
						   displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey()+" value:"+"<br><span style='color:red'>Revenue-AllocatedCost:"+comparedValueDouble+"--->contributionMargin($)</span> as per appliation" +expectedValueDouble +"<br>";
						}
				    }
				    
				}
			}
			
							if (cond) {
								System.out.println("Success");

								obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
								obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
										"Description: " + record[DESCRIPTION] + "\n" + displayResult);
							} else {
								obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
								obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
										"Description: " + record[DESCRIPTION] + "\nPlease check below rows. " + displayResult);
							}
			}
			else {
				if(typeofContMargin.equalsIgnoreCase("percentss")) {		
					ArrayList<Map<String, Double>> ContributionMargin$ = TABLE_VALUES.get(elements[0]);
					ArrayList<Map<String, Double>> Revenue = TABLE_VALUES.get(elements[1]);			
					ArrayList<Map<String, Double>> ContributionMargin = TABLE_VALUES.get(elements[2]);
					System.out.println("ContributionMargin$:"+ContributionMargin$);
					System.out.println("Revenue:"+Revenue);
					System.out.println("ContributionMargin:"+ContributionMargin);	
					
					
					for(i=0;i<Revenue.size();i++) {
						System.out.println("entered to ContributionMargin% ");
						Map<String, Double> hm1 = ContributionMargin$.get(i);
						Map<String, Double> hm2 = Revenue.get(i);
						Map<String, Double> hm3 = ContributionMargin.get(i);
						for (Map.Entry<String, Double> set : hm1.entrySet()) {
							double contributionMargin = (hm2.get(set.getKey()) == 0.0)? 1 : hm2.get(set.getKey());
						    comparedValueDouble =(set.getValue() / contributionMargin)*100;
						    System.out.println("ContributionMargin$/Revenue:"+comparedValueDouble);
						    expectedValueDouble = hm3.get(set.getKey());
						    System.out.println("contributionMargin(%)"+expectedValueDouble);
						    
						    if(compareListNameStrings.contains(set.getKey())){
						    	System.out.println("Result:"+displayResult);
								    if(Math.ceil(comparedValueDouble) == Math.ceil(expectedValueDouble) ||  Math.floor(comparedValueDouble) == Math.floor(expectedValueDouble)) {
								    	cond = true;
								    	displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey()+" value:"+"<br>contributionMargin($)/Revenue:"+comparedValueDouble+"--->contributionMargin(%) as per appliation" +expectedValueDouble +"<br>";
								    }else {
								    cond = false;
								    displayResult= displayResult + "Row-"+i+"<br>"+ set.getKey()+" value:"+"<br><span style='color:red'>contributionMargin(%)/Revenue:"+comparedValueDouble+"--->contributionMargin($)</span> as per appliation" +expectedValueDouble +"<br>";
								}
						    }
						}
						
						 

					} 
				
					if (cond) {
						System.out.println("Success");

						obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
						obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
								"Description: " + record[DESCRIPTION] + "\n" + displayResult);
					} else {
						obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
						obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
								"Description: " + record[DESCRIPTION] + "\nPlease check below rows. " + displayResult);
					}	
						
			}
				
			}
			
			
				    return cond ? Information.PASS: Information.FAIL;
		}
		
		catch(Exception ne){
			
			
			ne.printStackTrace();
			return Information.FAIL;
	}
}


	public String ytdWeighted(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		try {
			WebElement ytd=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			//obj.getJavaScript().highlight(ytd);	
			String ytdValueString=ytd.getText();
			Double ytdValue=Double.valueOf(ytdValueString);
			//int ytdValue = Integer.parseInt(ytdValueString);
			System.out.println("YTD Weighted value:"+ytdValueString);
			String[] elements = record[VALUE].split(",");
			ArrayList<Map<String, Double>> AllocatedCost = TABLE_VALUES.get(elements[0]);
			ArrayList<Map<String, Double>> Volume = TABLE_VALUES.get(elements[1]);
			System.out.println("Volume:"+Volume);
			System.out.println("AllocatedCost:"+AllocatedCost);
			
		//	for(int i=0;i<Volume.size();i++) {
				Map<String, Double> hm1 = Volume.get(0);
				Map<String, Double> hm2 = AllocatedCost.get(0);
			
				Double AllocatedCostYTD=hm2.get("YTD");
				Double VolumeYTD=hm1.get("YTD");
				Double ytdweightedvalue=AllocatedCostYTD/VolumeYTD;
				System.out.println("YTD Weighted:"+ytdweightedvalue);
				displayResult = displayResult + "YTD Weighted vlaue in application-------->:" +ytdValue+ "<Br>"
				        + "YTD Weighted value as per calculation------------>:" +ytdweightedvalue;
		
				if(Math.ceil(ytdValue) == Math.ceil(ytdweightedvalue) ||  Math.floor(ytdValue) == Math.floor(ytdweightedvalue)) {
			System.out.println("Success");
			cond = true;
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\n" + displayResult);
	    }else {
	    cond = false;
		obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
		obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
				"Description: " + record[DESCRIPTION] + "\nPlease check below . " + displayResult);
	}
			
			return cond ? Information.PASS: Information.FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return Information.FAIL;
			// TODO: handle exception
		}
}
	public String SouringMixForLTG(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		try {
			
			WebElement SourcingMixForLTG=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
			//obj.getJavaScript().highlight(SourcingMixForLTG);	
			String SourcingMixForLTGString=SourcingMixForLTG.getText();
			//double SourcingMixForLTGValue=Double.valueOf(SourcingMixForLTGString);
			//int SourcingMixForLTGValue = Integer.parseInt(SourcingMixForLTGString);
			System.out.println("SourcingMixForLTGString value:"+SourcingMixForLTGString);
			String[] elements = record[VALUE].split(",");
            int months=1; 
            List<WebElement> planningVersions = ELEMENTS_LIST.get(elements[0].trim());
			String versionValue = obj.getJavaScript().getInnerText(planningVersions.get(0));
			SimpleDateFormat sdf1 = new SimpleDateFormat("M"); 
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM");
			System.out.println("versionValues :: "+versionValue);
			ArrayList<Map<String, Double>> allocatedCost = TABLE_VALUES.get(elements[1]);
			String typeOfMethod =  elements[2].trim();
			System.out.println("typeOfMethod :: "+typeOfMethod);
			String monthOfCalcuation = versionValue.split("-")[1];
			System.out.println("monthOfCalcuation :: "+monthOfCalcuation);
			int currentMonth = Integer.parseInt(monthOfCalcuation);
			System.out.println("currentMonth :: "+currentMonth);
			
			Double expectedCount = 0.0;
			// U load actual count
			Double actualCount =0.0;
			if(typeOfMethod.equalsIgnoreCase("LTM")) {
					for(int i=1;i<currentMonth; i++) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(0).get(monthString);
					}
					for(int i= currentMonth; i<=12;i++) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(3).get(monthString);
					}
				System.out.println("Actual Count :: "+actualCount);
			}
			if(typeOfMethod.equalsIgnoreCase("YTD")) {
				for(int i=1;i<currentMonth; i++) {
					String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
					actualCount = actualCount+allocatedCost.get(0).get(monthString);
				}
				System.out.println("Actual Count :: "+actualCount);
			}
			if(typeOfMethod.equalsIgnoreCase("L3M")) {
				
				//int checkCounter =Integer.parseInt(sdf1.format(new Date()));
				int checkCounter =3;
				if(currentMonth>3) {
					for(int i=currentMonth-3;i<currentMonth; i++) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(0).get(monthString);
					}
				}else {
					for(int i=currentMonth-1; i>0 ;i--) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(0).get(monthString);
						checkCounter--;
					}
					for(int i=12; checkCounter>0 ;i--) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(3).get(monthString);
						checkCounter--;
					}
				}
				
				System.out.println("Actual Count :: "+actualCount);
			}
			
			if(typeOfMethod.equalsIgnoreCase("L6M")) {
				//int checkCounter =Integer.parseInt(sdf1.format(new Date()));
				System.out.println("In L6M Calcualtion  :: ");
				int checkCounter =6;
				if(currentMonth>6) {
					for(int i=currentMonth-6;i<currentMonth; i++) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(0).get(monthString);
					}
				}else {
					for(int i=currentMonth-1; i>0 ;i--) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(0).get(monthString);
						checkCounter--;
					}
					System.out.println("L6m  Count afeter first loop "+actualCount);
					System.out.println("L6m  Check counter : "+checkCounter);
					for(int i=12; checkCounter>0 ;i--) {
						String monthString =sdf2.format(sdf1.parse(String.valueOf(i)));
						actualCount = actualCount+allocatedCost.get(3).get(monthString);
						checkCounter--;
					}
				}
				
				System.out.println("Actual Count :: "+actualCount);
			}
			 displayResult = displayResult + "SouringMixForLTGvlaue in application-------->:" +SourcingMixForLTGString+ "<Br>"
					        + "SourcingMixLTGvalue as per calculation------------>:" +actualCount;
			
			if(SourcingMixForLTGString.equals(actualCount)) {
				System.out.println("Success");
				cond = true;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						"Description: " + record[DESCRIPTION] + "\n" + displayResult);
		    }else {
		    cond = false;
			obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
					"Description: " + record[DESCRIPTION] + "\nPlease check below . " + displayResult);
		}
				
			
			 
			return cond ? Information.PASS: Information.FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return Information.FAIL;
		
		}
	}

public String VersionAndYearChange(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
		
		try{
			int fail=0;
			String[] elements = record[VALUE].split(",");
			Double valueBeforeChange = null;
			Double valueAfterChange = null;
			String displayResult = "";
			
			List<String> compareListNameStrings = new ArrayList<String>();
			compareListNameStrings.add("Q1");
			compareListNameStrings.add("Q2");
			compareListNameStrings.add("Q3");
			compareListNameStrings.add("Q4");
			compareListNameStrings.add("YTD");
			compareListNameStrings.add("LTG");
			compareListNameStrings.add("Full Year");

			int i;
						
						    ArrayList<Map<String, Double>> DefaultValue = TABLE_VALUES.get(elements[0]);
							ArrayList<Map<String, Double>> AfterChange = TABLE_VALUES.get(elements[1]);			
							System.out.println("Data before changing the value:"+DefaultValue);
							System.out.println("Data after changing the value:"+AfterChange);
							for(i=0;i<DefaultValue.size();i++) {
								Map<String, Double> hm1 = DefaultValue.get(i);
								Map<String, Double> hm2 = AfterChange.get(i);
				for (Map.Entry<String, Double> set : hm1.entrySet()) {
				    valueBeforeChange=hm1.get(set.getKey());
				    valueAfterChange=hm2.get(set.getKey());
				    if(compareListNameStrings.contains(set.getKey())){
				    	System.out.println("Result:"+displayResult);
						    if(!(Math.ceil(valueBeforeChange) == Math.ceil(valueAfterChange) || Math.floor(valueBeforeChange) == Math.floor(valueAfterChange)))
						    {
						    	Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
								cond= true;
								ru.updateResult(cond, sh, row, resultRow, PASS, imp, record);
								return Information.PASS;
								}
						    else {
						    	Information.VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
								cond= false;
								ru.updateResult(cond, sh, row, resultRow, FAIL, imp, record);
								return Information.FAIL;
						    }
				    }
				}}
							
				return cond ? Information.PASS: Information.FAIL;				
		
		                 }	catch(Exception ne){
									ru.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver didn't click on "+record[OBJECTNAME]
											+" because of "+ne,imp);
									ne.printStackTrace();
									VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
									return Information.FAIL;
								}
						}
				    
		}			    
						    	
		