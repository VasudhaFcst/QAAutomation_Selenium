package Running;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Timing {
	public String timeform(){
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
	String timefr=dateFormat.format(date);
	return timefr;
}
public String timeReport(){
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
	//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	String timerep=dateFormat.format(date);
	return timerep;
}
//public String folderName() throws Exception{
//	ReadProp rp=new ReadProp();
//	String name=rp.getPath().getProperty("report");
//	return name;
//}

}
