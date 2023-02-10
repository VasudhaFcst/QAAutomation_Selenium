package Running;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import Common.Information;


public class ReadProp implements Information{
//public Properties getDataProp() throws Exception{

//		File f= new File(PROPERTIES);
//		FileInputStream fis = new FileInputStream(f);
//		Properties p= new Properties();
//		p.load(fis);
//		return p;
//}
public Properties getPath(String path) throws Exception{
//	Properties p=getDataProp();
//	Properties p=new Properties();
//	File f= new File(p.getProperty(PROPERTIES_PATH));
	File f= new File(path);
	FileInputStream fis = new FileInputStream(f);
	Properties p1= new Properties();
	p1.load(fis);
	return p1;
}

}
