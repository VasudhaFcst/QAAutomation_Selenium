package Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ReadWriteProperties {
	Properties props;
	FileInputStream fis;
	File f;
	public ReadWriteProperties(String path) throws Exception {
		f= new File(path);
		fis = new FileInputStream(f);
		props= new Properties();
		props.load(fis);
	}

	public void setData(String value,String key) throws Exception {
		FileOutputStream out = new FileOutputStream(f);
		props.setProperty(value, key);
		props.store(out, null);
		out.close();
	}
	
	public String getData(String value) {
		String data=props.getProperty(value);
		return data;
	}
	
}
