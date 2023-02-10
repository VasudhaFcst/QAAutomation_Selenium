package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Instructions {
JPanel emu,mobile,none;
JLabel emuText,mobileText,udid;
JTextField udidText;
JTextPane message;
String emuMessage,mobileMessage;
	public Instructions(JPanel instr) {
		emu=new JPanel();
		emu.setLayout(null);
		mobile=new JPanel();
		mobile.setLayout(null);
		none=new JPanel();
		none.setLayout(null);
		emuMessage="<html><div style='text-align: justify;'>"
				+ "1. Open AVD Manager by clicking on " + 
				"<br>   'Browse & Open button' in Appium Server & Managers tab" + 
				"<br><br>2. Create emulator in AVD Manager with\r\n" + 
				"<br>   Capabilities output\r\n" + 
				"<br><br>3. Open Emulator before clicking on start button" + 
				"<br><br>4. Make sure excel, properties file, capabilites,"+
				"<br> emulator should be correct."+
				"<br><br>5. Close excel sheet before start the execution"+
				"</div></html>" ;
		
		mobileMessage="<html><div style='text-align: top;'>" + 
				"1. Enable USB debugging option in mobile" +
				"<br><br>2. Connect mobile to system through USB" + 
				"<br><br>3. Get UDID number by running 'adb devices'" + 
				"<br>in command prompt" + 
				"<br><br>4. Enter UDID number in below text label" + 
				"<br><br>5. Close excel sheet before start the execution" + 
				"</div></html>";
		emuText=new JLabel(emuMessage);
		
		message=new JTextPane();
		message.setText("\nSelect device type \n in \n 'VERIFICATION' tab");
		message.setFont(new Font("Narkisim", Font.ITALIC, 20));
		message.setBorder(BorderFactory.createLineBorder(new Color(76,167,228), 2, true));
		message.setEditable(false);
		message.setEnabled(false);
//		message.setBackground(Color.LIGHT_GRAY);
		
		StyledDocument doc = message.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		mobileText=new JLabel(mobileMessage);
		udid=new JLabel("UDID");
		udid.setFont(new Font("Calibri", Font.BOLD, 15));
		
		udidText=new JTextField();
		instr.add(emu);
		instr.add(mobile);
		instr.add(none);
		emu.add(emuText);
		mobile.add(mobileText);
		mobile.add(udid);
		mobile.add(udidText);
		none.add(message);
		
		instr.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				emu.setBounds((int)(0.01*instr.getWidth()),(int)(0.2*instr.getHeight()),(int)(0.99*instr.getWidth()),(int)(0.8*instr.getHeight()));
				mobile.setBounds((int)(0.01*instr.getWidth()),(int)(0.2*instr.getHeight()),(int)(0.99*instr.getWidth()),(int)(0.8*instr.getHeight()));
				none.setBounds((int)(0.01*instr.getWidth()),(int)(0.2*instr.getHeight()),(int)(0.99*instr.getWidth()),(int)(0.8*instr.getHeight()));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		
	}

}
