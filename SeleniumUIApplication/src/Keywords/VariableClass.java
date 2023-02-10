package Keywords;

import java.util.Properties;
import Common.Information;

public class VariableClass implements Information{
	
	
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{
	String key=record[OBJECTNAME];
	String value=record[VALUE];
	VALUE_STORAGE.put(key, value);
	return Information.PASS;
	}
	catch(Exception ne){
	
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}

}


