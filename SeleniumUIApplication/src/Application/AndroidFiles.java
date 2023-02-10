package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AndroidFiles {
//	ConsoleOutput co;
	JPanel main,folder,verify,details,output1,output2;
	JButton start,back;
	JLabel folderTitle,excelSheet,properties,reports,screenshot,optional;
	JButton eButton,pButton,rButton,sButton;
	JCheckBox check;
	JTextField text;
	JFileChooser file,directory;
	
	JLabel verifyTitle,openExcel,openProp,app,appWarning,device;
	JButton oeButton,opButton,browseApp;
	JRadioButton emulator,real;
	ButtonGroup bg;
	
	JLabel detailsTitle,photo,imageLoad;
	
	JLabel oTitle1,deviceLabel,browserLabel,versionLabel,platformLabel,packageLabel,activityLabel,deviceOutput,browserOutput,versionOutput,
	platformOutput,packageOutput,activityOutput;
	
	JLabel oTitle2,eLabel,pLabel,sceneLabel,rLabel,sLabel,aLabel,appResult,eResult,pResult,sceneResult,rResult,sResult;
	BufferedImage myPicture;
	
public AndroidFiles(JPanel android,AndroidPanel ap) {
//	co=new ConsoleOutput(android);
	main=new JPanel();
	main.setLayout(null);
	main.setBackground(Color.WHITE);
	main.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,false));
//	main.setOpaque(false);
	
	folder=new JPanel();
	folder.setLayout(null);
	folder.setBorder(BorderFactory.createLineBorder(new Color(76,167,228), 1, false));
	folderTitle=new JLabel("FOLDERS & FILES",JLabel.CENTER);
	folderTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
	folderTitle.setBackground(new Color(76,167,228));
	folderTitle.setOpaque(true);
	
	excelSheet=new JLabel("Excel File*");
	excelSheet.setFont(new Font("Calibri", Font.BOLD, 15));
	properties= new JLabel("Properties File*");
	properties.setFont(new Font("Calibri", Font.BOLD, 15));
	reports=new JLabel("Reports Path");
	reports.setFont(new Font("Calibri", Font.BOLD, 15));
	screenshot=new JLabel("Screenshot Path");
	screenshot.setFont(new Font("Calibri", Font.BOLD, 15));
	optional=new JLabel("Testcases Sheet");
	optional.setFont(new Font("Calibri", Font.BOLD, 15));
	
	eButton=new JButton("Browse");
	eButton.setFont(new Font("Calibri", Font.BOLD, 13));
	eButton.setBackground(new Color(220,220,220));
	eButton.setFocusPainted(false);
	pButton=new JButton("Browse");
	pButton.setFont(new Font("Calibri", Font.BOLD, 13));
	pButton.setBackground(new Color(220,220,220));
	pButton.setFocusPainted(false);
	rButton=new JButton("Browse");
	rButton.setFont(new Font("Calibri", Font.BOLD, 13));
	rButton.setBackground(new Color(220,220,220));
	rButton.setFocusPainted(false);
	sButton=new JButton("Browse");
	sButton.setFont(new Font("Calibri", Font.BOLD, 13));
	sButton.setBackground(new Color(220,220,220));
	sButton.setFocusPainted(false);
	
	file=new JFileChooser();
	directory=new JFileChooser();
	
	check=new JCheckBox();
	text=new JTextField("SCENARIOS");
	text.setFont(new Font("Calibri", Font.BOLD, 13));
	text.setEditable(false);
	text.setToolTipText("Click on checkbox for updating sheet name \n it is default name");
	
	verify=new JPanel();
	verify.setLayout(null);
	verify.setBorder(folder.getBorder());
	verifyTitle=new JLabel("VERIFICATION",JLabel.CENTER);
	verifyTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
	verifyTitle.setBackground(folderTitle.getBackground());
	verifyTitle.setOpaque(true);
	
	openExcel =new JLabel("Excel File");
	openExcel.setFont(new Font("Calibri", Font.BOLD, 15));
	
	openProp=new JLabel("Properties File");
	openProp.setFont(new Font("Calibri", Font.BOLD, 15));
	
	app=new JLabel("App Location");
	app.setFont(new Font("Calibri", Font.BOLD, 15));
	
	appWarning=new JLabel("(If it is not installed in emulator or real device)");
	appWarning.setFont(new Font("Calibri", Font.PLAIN, 10));
	
	device=new JLabel("Select Device Type :");
	device.setFont(openExcel.getFont());
	
	emulator=new JRadioButton("Emulator");
	emulator.setFont(new Font("Calibri", Font.BOLD, 12));
	emulator.setFocusable(false);
	
	real=new JRadioButton("Real Device");
	real.setFont(new Font("Calibri", Font.BOLD, 12));
	real.setFocusable(false);
	
	bg=new ButtonGroup();
	bg.add(emulator);
	bg.add(real);
	
	browseApp=new JButton("Browse");
	browseApp.setFont(new Font("Calibri", Font.BOLD, 13));
	browseApp.setBackground(Color.YELLOW);
	browseApp.setFocusPainted(false);
	
	oeButton=new JButton("Open");
	oeButton.setFont(new Font("Calibri", Font.BOLD, 13));
	oeButton.setBackground(Color.YELLOW);
	oeButton.setFocusPainted(false);
	
	opButton=new JButton("Open");
	opButton.setFont(new Font("Calibri", Font.BOLD, 13));
	opButton.setBackground(Color.YELLOW);
	opButton.setFocusPainted(false);
	
	details=new JPanel();
	details.setLayout(null);
	details.setBorder(folder.getBorder());
	detailsTitle=new JLabel("INSTRUCTIONS",JLabel.CENTER);
	detailsTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
	detailsTitle.setBackground(folderTitle.getBackground());
	detailsTitle.setOpaque(true);
	
	
	start=new JButton("Start");
	start.setBackground(Color.BLUE);
	start.setForeground(Color.BLUE);
	start.setFont(new Font("Narkisim", Font.BOLD, 18));
	start.setFocusPainted(false);
	
	back=new JButton("Back");
	back.setBackground(Color.RED);
	back.setForeground(Color.RED);
	back.setFont(new Font("Narkisim", Font.BOLD, 18));
	back.setFocusPainted(false);
	
	output1=new JPanel();
	output1.setLayout(null);
	output1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
	
	oTitle1=new JLabel("Capabilities Output",JLabel.CENTER);
	oTitle1.setFont(new Font("Narkisim", Font.BOLD, 15));
	oTitle1.setBackground(new Color(0,0,0));
	oTitle1.setForeground(Color.WHITE);
	oTitle1.setOpaque(true);
	
	deviceLabel=new JLabel("Device Name:");
	deviceLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	
	browserLabel=new JLabel("Browser Name:");
	browserLabel.setFont(deviceLabel.getFont());
	
	versionLabel=new JLabel("Version Name:");
	versionLabel.setFont(deviceLabel.getFont());
	
	platformLabel=new JLabel("Platform Name:");
	platformLabel.setFont(deviceLabel.getFont());
	
	packageLabel=new JLabel("Package Name:");
	packageLabel.setFont(deviceLabel.getFont());
	
	activityLabel=new JLabel("Activity Name:");
	activityLabel.setFont(deviceLabel.getFont());
	
	deviceOutput=new JLabel();
	deviceOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	browserOutput=new JLabel();
	browserOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	versionOutput=new JLabel();
	versionOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	platformOutput=new JLabel();
	platformOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	packageOutput=new JLabel();
	packageOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	activityOutput=new JLabel();
	activityOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	output2=new JPanel();
	output2.setLayout(null);
	output2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
	
	oTitle2=new JLabel("Folder & File Paths",JLabel.CENTER);
	oTitle2.setFont(new Font("Narkisim", Font.BOLD, 15));
	oTitle2.setBackground(oTitle1.getBackground());
	oTitle2.setForeground(oTitle1.getForeground());
	oTitle2.setOpaque(true);
	
	eLabel=new JLabel("Excel :");
	eLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	pLabel=new JLabel("Properties :");
	pLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	sceneLabel=new JLabel("Scenarios :");
	sceneLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	rLabel=new JLabel("Reports :");
	rLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	sLabel=new JLabel("Screenshots :");
	sLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	aLabel=new JLabel("App Location :");
	aLabel.setFont(new Font("Calibri", Font.BOLD, 12));
	
	eResult=new JLabel("");
	eResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	pResult=new JLabel("");
	pResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	sceneResult=new JLabel("SCENARIOS");
	sceneResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	rResult=new JLabel("C:/testing/report");
	rResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	sResult=new JLabel("C:/testing/screen");
	sResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	appResult=new JLabel("");
	appResult.setFont(new Font("Calibri", Font.PLAIN, 12));
	
	photo=new JLabel();
	
	imageLoad=new JLabel("Loading...");
	imageLoad.setFont(new Font("Narkisim", Font.BOLD, 10));
	
	folder.add(folderTitle);
	folder.add(excelSheet);
	folder.add(properties);
	folder.add(reports);
	folder.add(optional);
	folder.add(screenshot);
	folder.add(eButton);
	folder.add(pButton);
	folder.add(rButton);
	folder.add(sButton);
	folder.add(check);
	folder.add(text);
	verify.add(verifyTitle);
	verify.add(openExcel);
	verify.add(app);
	verify.add(appWarning);
	verify.add(browseApp);
	verify.add(openProp);
	verify.add(oeButton);
	verify.add(opButton);
	verify.add(device);
	verify.add(emulator);
	verify.add(real);
	details.add(detailsTitle);
	output1.add(oTitle1);
	output1.add(deviceLabel);
	output1.add(deviceOutput);
	output1.add(browserLabel);
	output1.add(browserOutput);
	output1.add(versionLabel);
	output1.add(versionOutput);
	output1.add(platformLabel);
	output1.add(platformOutput);
	output1.add(packageLabel);
	output1.add(packageOutput);
	output1.add(activityLabel);
	output1.add(activityOutput);
	output2.add(oTitle2);
	output2.add(eLabel);
	output2.add(pLabel);
	output2.add(sceneLabel);
	output2.add(rLabel);
	output2.add(sLabel);
	output2.add(aLabel);
	output2.add(eResult);
	output2.add(pResult);
	output2.add(sceneResult);
	output2.add(rResult);
	output2.add(sResult);
	output2.add(appResult);
	main.add(folder);
	main.add(verify);
	main.add(details);
	android.add(main);
	android.add(output1);
	android.add(output2);
	android.add(start);
	android.add(back);
	android.add(photo);
	android.add(imageLoad);
	Instructions in=new Instructions(details);
	
	check.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			if(check.isSelected()) {
				text.setEditable(true);
			}
			else {
				text.setEditable(false);
			}
			
		}
		
	});
	
	text.getDocument().addDocumentListener(new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			text.setBorder(BorderFactory.createLineBorder(null, 0, true));
			sceneResult.setText(text.getText());
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			text.setBorder(BorderFactory.createLineBorder(null, 0, true));
			sceneResult.setText(text.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			text.setBorder(BorderFactory.createLineBorder(null, 0, true));
			sceneResult.setText(text.getText());
		}
		
	});
	
	eButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			eButton.setBorder(BorderFactory.createLineBorder(null, 0, true));
			file.setDialogTitle("Choose Excel file");
			fileBrowsing(eResult,".xlsx");
		}
		
	});
	pButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			pButton.setBorder(BorderFactory.createLineBorder(null, 0, true));
			file.setDialogTitle("Choose Properties file");
			fileBrowsing(pResult,".properties");
		}
		
	});
	
	rButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			directory.setDialogTitle("Choose Reports folder");
			directory(rResult);
		}
		
	});
	
	sButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			directory.setDialogTitle("Choose Screenshot folder");
			directory(sResult);
		}
		
	});
	
	oeButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			OpenConditions oc=new OpenConditions();
			oc.open(eButton, eResult);
		}
		
	});
	
	opButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			OpenConditions oc=new OpenConditions();
			oc.open(pButton, pResult);
		}
		
	});
	
	browseApp.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			browseApp.setBorder(BorderFactory.createLineBorder(null, 0, true));
			file.setDialogTitle("Choose .apk file");
			fileBrowsing(appResult,".apk");
		}
		
	});
	
	emulator.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			if(emulator.isSelected()) {
				in.none.setBounds(0,0,0,0);
				in.emu.setBounds((int)(0.01*details.getWidth()),(int)(0.2*details.getHeight()),(int)(0.99*details.getWidth()),(int)(0.8*details.getHeight()));
				in.emuText.setBounds((int)(0.05*in.emu.getWidth()), 0, (int)(0.95*in.emu.getWidth()), (int)(0.8*in.emu.getHeight()));
				in.mobile.setBounds(0, 0, 0, 0);
				
			}
		}
		
	});
	
	real.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			if(real.isSelected()) {
				in.none.setBounds(0,0,0,0);
				in.mobile.setBounds((int)(0.01*details.getWidth()),(int)(0.2*details.getHeight()),(int)(0.99*details.getWidth()),(int)(0.8*details.getHeight()));
				in.mobileText.setBounds((int)(0.05*in.mobile.getWidth()), 0, (int)(0.95*in.mobile.getWidth()), (int)(0.7*in.mobile.getHeight()));
				in.udid.setBounds((int)(0.05*in.mobile.getWidth()), (int)(0.8*in.mobile.getHeight()), (int)(0.5*in.mobile.getWidth()), (int)(0.1*in.mobile.getHeight()));
				in.udidText.setBounds((int)(0.5*in.mobile.getWidth()), (int)(0.8*in.mobile.getHeight()), (int)(0.40*in.mobile.getWidth()), (int)(0.1*in.mobile.getHeight()));
		
				in.emu.setBounds(0, 0, 0, 0);
			}
		}
		
	});
	
	android.addComponentListener(new ComponentListener() {

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
			main.setBounds((int)(0.1*android.getWidth()), (int)(0.075*android.getHeight()), 
					(int)(0.8*android.getWidth()), (int)(0.5*android.getHeight()));
			start.setBounds((int)(0.40*android.getWidth()), (int)(0.595*android.getHeight()),
					(int)(0.09*android.getWidth()), (int)(0.05*android.getHeight()));
			back.setBounds((int)(0.51*android.getWidth()), (int)(0.595*android.getHeight()),
					(int)(0.09*android.getWidth()), (int)(0.05*android.getHeight()));
			output1.setBounds((int)(0.1*android.getWidth()), (int)(0.67*android.getHeight()),
					(int)(0.39*android.getWidth()), (int)(0.27*android.getHeight()));
			output2.setBounds((int)(0.51*android.getWidth()), (int)(0.67*android.getHeight()),
					(int)(0.39*android.getWidth()), (int)(0.27*android.getHeight()));
			photo.setBounds(0, 0, android.getWidth(), android.getHeight());
			imageLoad.setBounds((int)(0.01*android.getWidth()), (int)(0.9*android.getHeight()),
					(int)(0.1*android.getWidth()), (int)(0.05*android.getHeight()));
					SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
						   @Override
						   protected Void doInBackground() throws Exception {
						    // Simulate doing something useful.
					try {
						myPicture = ImageIO.read(new URL("http://walldiskpaper.com/wp-content/uploads/2014/11/Android-Logo-Background-PC.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						myPicture = ImageIO.read(getClass().getResource("/Images/androidFiles.jpg"));
					}
					Image image=new ImageIcon(myPicture).getImage();
					Image newImage=image.getScaledInstance(photo.getWidth(), photo.getHeight(), Image.SCALE_SMOOTH);
					photo.setIcon(new ImageIcon(newImage));
					 return null;
						   }
						  };
					worker1.execute();
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
	main.addComponentListener(new ComponentListener() {

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
			folder.setBounds((int)(0.02*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
			verify.setBounds((int)(0.35*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
			details.setBounds((int)(0.68*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
			
			verifyTitle.setBounds(0, (int)(0*verify.getHeight()), verify.getWidth(), (int)(0.2*verify.getHeight()));
			detailsTitle.setBounds(0, (int)(0*details.getHeight()), details.getWidth(), (int)(0.2*details.getHeight()));
			oTitle1.setBounds(0, (int)(0*output1.getHeight()), output1.getWidth(), (int)(0.2*output1.getHeight()));
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	folder.addComponentListener(new ComponentListener() {

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
			folderTitle.setBounds(0, (int)(0*folder.getHeight()), folder.getWidth(), (int)(0.2*folder.getHeight()));
			excelSheet.setBounds((int)(0.1*folder.getWidth()), (int)(0.25*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			properties.setBounds((int)(0.1*folder.getWidth()), (int)(0.40*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			optional.setBounds((int)(0.1*folder.getWidth()), (int)(0.55*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			reports.setBounds((int)(0.1*folder.getWidth()), (int)(0.70*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			screenshot.setBounds((int)(0.1*folder.getWidth()), (int)(0.85*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			eButton.setBounds((int)(0.5*folder.getWidth()), (int)(0.25*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			pButton.setBounds((int)(0.5*folder.getWidth()), (int)(0.40*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			check.setBounds((int)(0.5*folder.getWidth()), (int)(0.55*folder.getHeight()), 
					(int)(0.06*folder.getWidth()), (int)(0.075*folder.getHeight()));
			text.setBounds((int)(0.57*folder.getWidth()), (int)(0.55*folder.getHeight()), 
					(int)(0.31*folder.getWidth()), (int)(0.075*folder.getHeight()));
			rButton.setBounds((int)(0.5*folder.getWidth()), (int)(0.70*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
			sButton.setBounds((int)(0.5*folder.getWidth()), (int)(0.85*folder.getHeight()), 
					(int)(0.38*folder.getWidth()), (int)(0.075*folder.getHeight()));
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
			
	});
	
	verify.addComponentListener(new ComponentListener() {

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
			verifyTitle.setBounds(0, (int)(0*verify.getHeight()), verify.getWidth(), (int)(0.2*verify.getHeight()));
			openExcel.setBounds((int)(0.1*verify.getWidth()), (int)(0.25*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			openProp.setBounds((int)(0.1*verify.getWidth()), (int)(0.40*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			oeButton.setBounds((int)(0.5*verify.getWidth()), (int)(0.25*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			opButton.setBounds((int)(0.5*verify.getWidth()), (int)(0.40*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			app.setBounds((int)(0.1*verify.getWidth()), (int)(0.55*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			appWarning.setBounds((int)(0.1*verify.getWidth()), (int)(0.62*verify.getHeight()), 
					(int)(0.6*verify.getWidth()), (int)(0.06*verify.getHeight()));
			browseApp.setBounds((int)(0.5*verify.getWidth()), (int)(0.55*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			device.setBounds((int)(0.1*verify.getWidth()), (int)(0.72*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			emulator.setBounds((int)(0.1*verify.getWidth()), (int)(0.8*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
			real.setBounds((int)(0.5*verify.getWidth()), (int)(0.8*verify.getHeight()), 
					(int)(0.38*verify.getWidth()), (int)(0.075*verify.getHeight()));
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
			
	});
	
	output1.addComponentListener(new ComponentListener() {

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
			oTitle2.setBounds(0, (int)(0*output2.getHeight()), output2.getWidth(), (int)(0.2*output2.getHeight()));
			eLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.25*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			pLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.37*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			sceneLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.49*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			rLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.61*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			sLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.73*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			aLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.85*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.12*output2.getHeight()));
			eResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.25*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
			pResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.37*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
			sceneResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.49*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
			rResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.61*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
			sResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.73*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
			appResult.setBounds((int)(0.25*output2.getWidth()), (int)(0.85*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.12*output2.getHeight()));
		
			deviceLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.25*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.15*output1.getHeight()));
			browserLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.37*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.12*output1.getHeight()));
			versionLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.49*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.12*output1.getHeight()));
			platformLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.61*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.12*output1.getHeight()));
			packageLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.73*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.12*output1.getHeight()));
			activityLabel.setBounds((int)(0.05*output1.getWidth()), (int)(0.85*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.12*output1.getHeight()));
			
			deviceOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.25*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.15*output1.getHeight()));
			browserOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.37*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.12*output1.getHeight()));
			versionOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.49*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.12*output1.getHeight()));
			platformOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.61*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.12*output1.getHeight()));
			packageOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.73*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.12*output1.getHeight()));
			activityOutput.setBounds((int)(0.25*output1.getWidth()), (int)(0.85*output1.getHeight()), (int)(0.5*output1.getWidth()), (int)(0.12*output1.getHeight()));
		
			display(ap.device,deviceOutput);
			display(ap.browser,browserOutput);
			display(ap.version,versionOutput);
			display(ap.platform,platformOutput);
			display(ap.packageName,packageOutput);
			display(ap.activity,activityOutput);
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
			
	});
	
	details.addComponentListener(new ComponentListener() {

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
			
			if(emulator.isSelected()) {
				in.none.setBounds(0,0,0,0);
				in.emu.setBounds((int)(0.01*details.getWidth()),(int)(0.2*details.getHeight()),(int)(0.99*details.getWidth()),(int)(0.8*details.getHeight()));
				in.emuText.setBounds((int)(0.05*in.emu.getWidth()), 0, (int)(0.95*in.emu.getWidth()), (int)(0.8*in.emu.getHeight()));
				in.mobile.setBounds(0, 0, 0, 0);
			}
			else if(real.isSelected()) {
				in.none.setBounds(0,0,0,0);
				in.mobile.setBounds((int)(0.01*details.getWidth()),(int)(0.2*details.getHeight()),(int)(0.99*details.getWidth()),(int)(0.8*details.getHeight()));
				in.mobileText.setBounds((int)(0.05*in.mobile.getWidth()), 0, (int)(0.95*in.mobile.getWidth()), (int)(0.7*in.mobile.getHeight()));
				in.udid.setBounds((int)(0.05*in.mobile.getWidth()), (int)(0.8*in.mobile.getHeight()), (int)(0.5*in.mobile.getWidth()), (int)(0.1*in.mobile.getHeight()));
				in.udidText.setBounds((int)(0.5*in.mobile.getWidth()), (int)(0.8*in.mobile.getHeight()), (int)(0.40*in.mobile.getWidth()), (int)(0.1*in.mobile.getHeight()));
		
				in.emu.setBounds(0, 0, 0, 0);
			}
			else {
				in.none.setBounds((int)(0.01*details.getWidth()),(int)(0.2*details.getHeight()),(int)(0.99*details.getWidth()),(int)(0.8*details.getHeight()));
				in.message.setBounds((int)(0.15*in.none.getWidth()),(int)(0.3*in.none.getHeight()),(int)(0.7*in.none.getWidth()),(int)(0.4*in.none.getHeight()));	
				in.emu.setBounds(0, 0, 0, 0);
				in.mobile.setBounds(0, 0, 0, 0);
			}
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
			
	});
}

public void display(JTextField main,JLabel dest ) {
	if(main.getForeground()!=Color.GRAY) {
		dest.setText(main.getText());
	}
	else {
		dest.setText(null);
	}
}

public void fileBrowsing(JLabel dest, String format) {
	
	int returnValue = file.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = file.getSelectedFile();
      System.out.println(selectedFile.getName());
      String name=selectedFile.getAbsolutePath();
      if(name.substring(name.lastIndexOf(".")).equals(format)) {
    	  dest.setText(selectedFile.getAbsolutePath().replace("\\", "/"));
    	  dest.setForeground(Color.BLACK);
      }
      else {
    	  dest.setText("Please select "+format+" file"); 
    	  dest.setForeground(Color.RED);
      }
      file.setCurrentDirectory(file.getCurrentDirectory());
      System.out.println(dest.getText());
    }
}


public void directory(JLabel dest) {
	directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnValue = directory.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
     dest.setText(directory.getSelectedFile().toString().replace("\\", "/"));
     directory.setCurrentDirectory(directory.getCurrentDirectory());
      System.out.println(dest.getText());
    }
}
}

