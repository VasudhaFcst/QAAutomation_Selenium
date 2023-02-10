package Application;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import Running.ReadExcelSheet;

public class FinalVerification {
	String port=null;
public boolean verify(WindowPanel wp) {
	boolean cond=false;
	if(!wp.cb.getSelectedItem().toString().equalsIgnoreCase("Select browser")) {
		if(!(wp.exeOutput.getText().equals("")||wp.exeOutput.getText().contains("Please select")||wp.exeOutput.getText().contains("is not"))) {
			ReadExcelSheet res=new ReadExcelSheet(wp.eResult.getText());
			if(!(wp.eResult.getText().contains("Please select")||wp.eResult.getText().equals(""))) {
				if(!(wp.pResult.getText().contains("Please select")||wp.pResult.getText().equals(""))) {
					boolean exists=res.exists(wp.sceneResult.getText());
					if(exists) {
						cond=true;	
						wp.text.setBorder(BorderFactory.createLineBorder(null, 0, true));
						
					}
					else {
						cond=false;
						wp.text.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
						JOptionPane.showMessageDialog(null, "Please mention correct sheet name", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					cond=false;
					wp.pButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
					JOptionPane.showMessageDialog(null, "Please select .properties file", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				cond=false;
				wp.eButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
				JOptionPane.showMessageDialog(null, "Please select .xlsx file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			cond=false;
			wp.pathbutton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			JOptionPane.showMessageDialog(null, "Please browse .exe file for "+wp.cb.getSelectedItem(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	else {
		cond=false;
		wp.cb.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		JOptionPane.showMessageDialog(null, "Please select Browser in browser panel", "Error", JOptionPane.ERROR_MESSAGE);
	}
	return cond;
}

public boolean verify(AndroidPanel ap,String values[]) {
	boolean cond=false;
	if(ap.fileExists(values[0])&&ap.fileExists(values[1])) {
		cond=true;
		if(ap.hostNumber.getForeground()!=Color.GRAY) {
			cond=true;
						if(ip(ap.hostNumber.getText())) {
							cond=true;
						try {
						port=ap.bg.getSelection().getActionCommand();
						cond=true;
						} catch (Exception e) {
						cond=false;
						JOptionPane.showMessageDialog(null, "Please select any one of port types", "Error", JOptionPane.ERROR_MESSAGE);
						return cond;
						}

						if(port.equals("Others")) {
						if(ap.portNumber.getForeground()!=Color.GRAY) {
							cond=true;
						}
						else {
							cond=false;
							JOptionPane.showMessageDialog(null, "Please mention any number in port field", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
		}
						else {
							cond=false;
						}
		}
		else {
			cond=false;
			JOptionPane.showMessageDialog(null, "Please mention IP Address ", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	else {
		cond=false;
		JOptionPane.showMessageDialog(null, "Please check node.exe path and appium.js or main.js path", "Error", JOptionPane.ERROR_MESSAGE);
	}
	return cond;
}

public boolean ip(String ipAddress) {
	try {
		String ip=new String(ipAddress);
		String[] add=ip.split("\\.",-1);
		try {
			if(add[0].length()<=3&&add[1].length()<=3&&add[2].length()<=3&&add[3].length()<=3&&add.length==4) {
				int[] number =new int[4];
				number[0]=Integer.parseInt(add[0]);
				number[1]=Integer.parseInt(add[1]);
				number[2]=Integer.parseInt(add[2]);
				number[3]=Integer.parseInt(add[3]);
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "IP Address syntex error", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} 
		catch(ArrayIndexOutOfBoundsException a) {
			JOptionPane.showMessageDialog(null, "IP Address format is not correct", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Don't use alphabets or blanks in IP address", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "IP address should be in this format '127.0.0.1'", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
}
}
