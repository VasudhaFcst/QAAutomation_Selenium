package Running;
import Common.Information;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Extents implements Information{
	public ExtentReports er1;
	public ExtentTest test,parent;
	public String folder;
	ReadProp rp= new ReadProp();
	File f;
	// for saving reports
	public Extents(String path) throws Exception{
//	folder=rp.getPath().getProperty(REPORTS_PATH)+"\\"+new Timing().timeReport()+".html";
	folder=path;
//			+"\\HTML_reports";
//	
//	f=new File(path);
//	if (f.mkdir()){
//        System.out.println("File is created!");
//      }else{
//        System.out.println("File already exists.");
//      }
//	folder=folder+"\\"+new Timing().timeReport()+".html";
	er1= new ExtentReports(folder,true);
	information();
	}
	protected void information(){
		er1.addSystemInfo("Automation Tool","Selenium");
		er1.addSystemInfo("Framework","Keyword Driven framework");
		er1.addSystemInfo("Created by","Vasudha katragunta");
	}
	
	public void endExtent(){
		// end test
        er1.endTest(test);
        
        // calling flush writes everything to the log file
        er1.flush();
	}

	protected void endParentExtent(){
		// end test
        er1.endTest(parent);
        
        // calling flush writes everything to the log file
        er1.flush();
	}

}
