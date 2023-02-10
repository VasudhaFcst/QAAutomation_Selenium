package Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolder {
	List<String> list=new ArrayList<String>();
	public static void main(String[] args) throws IOException {
		File dir=new File("C:\\testing\\screen\\Screenshots_29-06-2018_15-02-10");
		ZipFolder zf=new ZipFolder();
		zf.zipping(dir);
	}
	
	public void zipping(File dir) {
		try {
			files(dir);
			System.out.println(dir.toString());
			FileOutputStream fos=new FileOutputStream(dir+".zip");
			ZipOutputStream zos=new ZipOutputStream(fos);
			for(String filePath : list) {
				ZipEntry ze=new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
				zos.putNextEntry(ze);
				FileInputStream fis=new FileInputStream(filePath);
				byte[] buffer=new byte[1024];
				int len;
				while((len=fis.read(buffer))>0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void files(File dir) {
		File[] all=dir.listFiles();
		for(File file:all) {
			if(file.isFile())
				list.add(file.getAbsolutePath());
		}
	}

}
