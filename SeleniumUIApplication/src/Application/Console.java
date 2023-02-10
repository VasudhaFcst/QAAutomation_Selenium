package Application;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class Console {
//	Timer time;
public Console(JTextArea area) {
//	time=new Timer(1000, new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
			try {
				String console=getClass().getResource("/PropertiesBase/out.txt").toString();
				File f=new File(console.substring(console.indexOf(":")+1));
				
//				System.out.println(new Date().getTime()-f.lastModified());
				
				FileInputStream input=new FileInputStream(f);
				DataInputStream dis=new DataInputStream(input);
				@SuppressWarnings("resource")
				BufferedReader br=new BufferedReader(new InputStreamReader(dis));
				area.setText("");
				String output="";
				
				while((output=br.readLine())!=null) {
					
					area.append(output+"\n");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//		}
		
//	});	
}
}
