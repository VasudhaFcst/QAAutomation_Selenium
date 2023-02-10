package Application;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyFilesAndPaths {
public static void main(String[] args) throws IOException {
	File f=new File("C:\\testing\\Kohler.xlsx");
	File copy=new File(f.getParent()+"/"+"secret");
	FileUtils.copyFileToDirectory(f.getAbsoluteFile(), copy.getAbsoluteFile(),false);
	System.out.println(f.getAbsolutePath());
	System.out.println(copy.getAbsoluteFile());
	System.out.println(f.length());
}
}
