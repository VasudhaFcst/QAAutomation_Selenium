package AdditionalSetup;

import Common.Information;
import Running.WriteExcelSheet;

public class Excelresult implements Information {
	public void setData(boolean condition,int j, String sheetindex,int resultRow,String result,String[] excel ){ // for writing into excel sheet
//		String s= p.getProperty(Information.EXCEL_PATH);
		String s= excel[EXCEL];
		WriteExcelSheet wes = new WriteExcelSheet(s);
		
		
	if(condition){
		System.out.println("Row:  "+j);
		wes.writeData(sheetindex,j,Information.RESULT_COLUMN,Information.PASS,condition);
		wes.writeData(sheetindex,j,Information.ACTUAL,EXPECTED,condition);
	}
		else{
			try {
				String scenarios=excel[SCENARIOS];
//						new ReadProp().getPath().getProperty(Information.SCENARIOS);
				if(result.startsWith(Information.WARNING)){
					wes.writeData(sheetindex,j,Information.RESULT_COLUMN,Information.WARNING,condition);
					wes.writeData(sheetindex,j,Information.ACTUAL,result,condition);
					wes.writeData(scenarios,resultRow,Information.SCENARIOS_COLUMN,Information.FAIL,condition);
				}
				else if(result.startsWith(Information.INFO))
				{
						wes.writeData(sheetindex,j,Information.RESULT_COLUMN,Information.INFO,condition);
						wes.writeData(sheetindex,j,Information.ACTUAL,result,condition);
				}
				else if(result.contains("Keyword")){
					wes.writeData(sheetindex,j,Information.RESULT_COLUMN,SKIP,condition);
					wes.writeData(sheetindex,j,Information.ACTUAL,INVALID_KEYWORD,condition);
					wes.writeData(scenarios,resultRow,Information.SCENARIOS_COLUMN,SKIP,condition);
				}
				else if(result.equalsIgnoreCase(Information.FAIL)){
					wes.writeData(sheetindex,j,Information.RESULT_COLUMN,Information.FAIL,condition);
					wes.writeData(sheetindex,j,Information.ACTUAL,UNEXPECTED,condition);
					wes.writeData(scenarios,resultRow,Information.SCENARIOS_COLUMN,Information.FAIL,condition);
						}
				else if(result.startsWith(Information.FAIL)||result.startsWith(Information.ERROR)){
			wes.writeData(sheetindex,j,Information.RESULT_COLUMN,Information.FAIL,condition);
			wes.writeData(sheetindex,j,Information.ACTUAL,result,condition);
			wes.writeData(scenarios,resultRow,Information.SCENARIOS_COLUMN,Information.FAIL,condition);
				}
			//wes.closeResources();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
