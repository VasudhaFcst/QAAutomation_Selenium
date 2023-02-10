package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Mac {
	JPanel main,browser,folder,verify,output1,output2;
	JButton start,back,output;
	JLabel title,type,path,launching;
	JRadioButton browserpath,info;
	JProgressBar progress;
	JButton pathbutton;
	@SuppressWarnings("rawtypes")
	JComboBox cb;
	JFileChooser exeBrowse;
	
	JLabel folderTitle,excelSheet,properties,reports,screenshot,optional,photo,imageLoad;
	JButton eButton,pButton,rButton,sButton;
	JCheckBox check;
	JTextField text;
	JFileChooser file,directory;
	
	JLabel verifyTitle,openExcel,openProp,urlLabel,image,warning;
	JButton oeButton,opButton,ok;
	JTextField google;
	
	JLabel oTitle1,platform,web,exeName,platformImage,browserImage,exeOutput;
	
	JLabel oTitle2,eLabel,pLabel,sceneLabel,rLabel,sLabel,eResult,pResult,sceneResult,rResult,sResult;
	BufferedImage myPicture;
	ButtonGroup radios;
//	ConsoleOutput cow;
//	ReadWriteProperties rwp;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Mac(JPanel macPanel) throws Exception {
//		cow=new ConsoleOutput(macPanel);
//		rwp=new ReadWriteProperties(getClass().getResourceAsStream("\\PropertiesBase\\windows.properties").toString());
		main=new JPanel();
//		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		main.setBounds((int)(0.1*macPanel.getWidth()), (int)(0.1*macPanel.getHeight()), 
				(int)(0.8*macPanel.getWidth()), (int)(0.5*macPanel.getHeight()));
		main.setLayout(null);
		main.setBackground(Color.WHITE);
		main.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,false));
		
		Picture pic=new Picture();
		browser=new JPanel();
		browser.setLayout(null);
		browser.setBorder(BorderFactory.createLineBorder(new Color(119,136,153), 1, false));
		title=new JLabel("BROWSER",JLabel.CENTER);
		title.setFont(new Font("Narkisim", Font.BOLD, 20));
		title.setBackground(new Color(119,136,153));
		title.setOpaque(true);
		
		type=new JLabel("Browser");
		type.setFont(new Font("Constantia", Font.BOLD, 18));
		
		String[] options={"Select browser","Chrome","Firefox","Safari"};
		cb=new JComboBox(options);
		cb.setFont(new Font("Constantia", Font.BOLD, 15));
		cb.setFocusable(false);
		
		browserpath=new JRadioButton("");
		browserpath.setFont(new Font("Calibri", Font.BOLD, 13));
		browserpath.setVisible(false);
		browserpath.setFocusable(false);
		
		pathbutton=new JButton("Browse");
		pathbutton.setFont(new Font("Calibri", Font.BOLD, 15));
		pathbutton.setBackground(new Color(220,220,220));
		pathbutton.setFocusPainted(false);
		pathbutton.setVisible(false);
		pathbutton.setEnabled(false);
		
		info=new JRadioButton("");
		info.setFont(new Font("Calibri", Font.BOLD, 13));
		info.setVisible(false);
		info.setFocusable(false);
		
		radios=new ButtonGroup();
		radios.add(browserpath);
		radios.add(info);
		
		exeBrowse=new JFileChooser();	
		
		folder=new JPanel();
		folder.setLayout(null);
		folder.setBorder(BorderFactory.createLineBorder(title.getBackground(), 1, false));
		folderTitle=new JLabel("FOLDERS & FILES",JLabel.CENTER);
		folderTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
		folderTitle.setBackground(title.getBackground());
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
		check=new JCheckBox();
		text=new JTextField("SCENARIOS");
		text.setFont(new Font("Calibri", Font.BOLD, 13));
		text.setEditable(false);
		text.setToolTipText("Click on checkbox for updating sheet name \n it is default name");
		
		file=new JFileChooser();
		directory=new JFileChooser();		
		
		verify=new JPanel();
		verify.setLayout(null);
		verify.setBorder(BorderFactory.createLineBorder(title.getBackground(), 1, false));
		
		verifyTitle=new JLabel("VERIFICATION",JLabel.CENTER);
		verifyTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
		verifyTitle.setBackground(title.getBackground());
		verifyTitle.setOpaque(true);
		
		openExcel =new JLabel("Excel File");
		openExcel.setFont(new Font("Calibri", Font.BOLD, 15));
		
		openProp=new JLabel("Properties File");
		openProp.setFont(new Font("Calibri", Font.BOLD, 15));
		
		urlLabel=new JLabel("Check your URL or Application");
		urlLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		
		image=new JLabel("");	
		
		google =new JTextField("Example: www.abc.com");
		google.setFont(new Font("Calibri", Font.PLAIN, 13));
		google.setForeground(Color.GRAY);
		
		oeButton=new JButton("Open");
		oeButton.setFont(new Font("Calibri", Font.BOLD, 13));
		oeButton.setBackground(new Color(135,206,235));
		oeButton.setFocusPainted(false);
		
		opButton=new JButton("Open");
		opButton.setFont(new Font("Calibri", Font.BOLD, 13));
		opButton.setBackground(new Color(135,206,235));
		opButton.setFocusPainted(false);
		
		ok=new JButton("GO");
		ok.setFont(new Font("Calibri", Font.BOLD, 10));
		ok.setBackground(Color.CYAN);
		ok.setFocusPainted(false);
		
		warning=new JLabel("<html><div style='text-align: center;'>Note: Please make sure that files,"
				+ "<br>sheet name and URL should be correct"
				+"<br>before click on start button",JLabel.CENTER);
		warning.setFont(new Font("Calibri", Font.BOLD, 10));
		warning.setForeground(Color.RED);
		
		start=new JButton("Start");
		start.setBackground(Color.GREEN);
		start.setForeground(new Color(0,100,0));
		start.setFont(new Font("Narkisim", Font.BOLD, 18));
		start.setFocusPainted(false);
		back=new JButton("Back");
		back.setBackground(Color.RED);
		back.setForeground(Color.RED);
		back.setFont(new Font("Narkisim", Font.BOLD, 18));
		back.setFocusPainted(false);
		output=new JButton("Output");
		output.setBackground(Color.BLACK);
		output.setFont(new Font("Narkisim", Font.BOLD, 18));
		output.setFocusPainted(false);
		
		launching=new JLabel("Launching Browser in ",JLabel.CENTER);
		launching.setFont(new Font("Calibri", Font.BOLD, 10));
		launching.setVisible(false);
		
		progress=new JProgressBar();
		progress.setIndeterminate(true);
		progress.setVisible(false);
		
		output1=new JPanel();
		output1.setLayout(null);
		
		oTitle1=new JLabel("Platform & Browser Output",JLabel.CENTER);
		oTitle1.setFont(new Font("Narkisim", Font.BOLD, 15));
		oTitle1.setForeground(Color.WHITE);
		oTitle1.setBackground(new Color(105,105,105));
		oTitle1.setOpaque(true);
		
		platform=new JLabel("Platform");
		platform.setFont(new Font("Calibri", Font.BOLD, 12));
		
		web=new JLabel("Browser");
		web.setFont(new Font("Calibri", Font.BOLD, 12));
		
		browserImage=new JLabel("");
		platformImage=new JLabel("");
		
		exeName=new JLabel("");
		exeName.setFont(new Font("Calibri", Font.BOLD, 12));
		
		exeOutput=new JLabel("");
		exeOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		output2=new JPanel();
		output2.setLayout(null);

		oTitle2=new JLabel("Folder & File Paths",JLabel.CENTER);
		oTitle2.setFont(new Font("Narkisim", Font.BOLD, 15));
		oTitle2.setForeground(Color.WHITE);
		oTitle2.setBackground(new Color(105,105,105));
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
		
		eResult=new JLabel("");
		eResult.setFont(new Font("Calibri", Font.PLAIN, 12));
		pResult=new JLabel("");
		pResult.setFont(new Font("Calibri", Font.PLAIN, 12));
		sceneResult=new JLabel("SCENARIOS");
		sceneResult.setFont(new Font("Calibri", Font.PLAIN, 12));
		rResult=new JLabel("user/local/reports");
		rResult.setFont(new Font("Calibri", Font.PLAIN, 12));
		sResult=new JLabel("user/local/screen");
		sResult.setFont(new Font("Calibri", Font.PLAIN, 12));
		
		photo=new JLabel();
		
		imageLoad=new JLabel("Loading...");
		imageLoad.setFont(new Font("Narkisim", Font.BOLD, 10));
		
		browser.add(title);
		browser.add(type);
		browser.add(cb);
		browser.add(pathbutton);
		browser.add(browserpath);
		browser.add(info);
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
		verify.add(openProp);
		verify.add(oeButton);
		verify.add(opButton);
		verify.add(urlLabel);
		verify.add(image);
		verify.add(google);
		verify.add(ok);
		verify.add(warning);
		output1.add(oTitle1);
		output1.add(platform);
		output1.add(web);
		output1.add(browserImage);
		output1.add(platformImage);
		output1.add(exeName);
		output1.add(exeOutput);
		output2.add(oTitle2);
		output2.add(eLabel);
		output2.add(pLabel);
		output2.add(sceneLabel);
		output2.add(rLabel);
		output2.add(sLabel);
		output2.add(eResult);
		output2.add(pResult);
		output2.add(sceneResult);
		output2.add(rResult);
		output2.add(sResult);
		main.add(browser);
		main.add(folder);
		main.add(verify);
		macPanel.add(main);
		macPanel.add(start);
		macPanel.add(back);
		macPanel.add(output);
		macPanel.add(progress);
		macPanel.add(output1);
		macPanel.add(output2);
		macPanel.add(photo);
		macPanel.add(imageLoad);
		
		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String result=cb.getSelectedItem().toString();
				browserpath.setText("");
				exeOutput.setText("");
				cb.setBorder(BorderFactory.createLineBorder(null, 0, true));
				pathbutton.setBorder(BorderFactory.createLineBorder(null, 0, true));
				if(!result.contains("Select")) {
					browserpath.setSelected(true);
					if(result.equalsIgnoreCase("firefox")) {
					
					browserpath.setText("Select Firefox or Gecko Driver file");
					info.setText("<html>Placed Gecko driver file in usr/local/bin folder</html>");
					browserpath.setVisible(true);
					info.setVisible(true);
					if(output1.getWidth()!=0) {
						browserImage.setIcon(new ImageIcon(pic.getImage(browserImage, getClass().getResource("/Images/firefox.jpg"))));
						}
						else {
							browserImage.setIcon(null);
							
						}
					exeName.setText("Gecko Driver file location ");
				
					}
					else if(result.equalsIgnoreCase("chrome")) {
						
						browserpath.setText("Select Chrome Driver file");
						info.setText("<html>Placed Chrome driver file in usr/local/bin folder</html>");
						browserpath.setVisible(true);
						info.setVisible(true);

						if(output1.getWidth()!=0) {
							browserImage.setIcon(new ImageIcon(pic.getImage(browserImage, getClass().getResource("/Images/chrome.png"))));
							}
							else {
								browserImage.setIcon(null);
								
							}
						exeName.setText("Chrome Driver file location");
						}
					else if(result.equalsIgnoreCase("safari")) {
						
						browserpath.setText("Select Safari Driver file");
						info.setText("<html>Placed Safari driver file in usr/local/bin folder</html>");
						browserpath.setVisible(true);
						info.setVisible(true);

						if(output1.getWidth()!=0) {
							browserImage.setIcon(new ImageIcon(pic.getImage(browserImage, getClass().getResource("/Images/safari.png"))));
							}
							else {
								browserImage.setIcon(null);
							}
						exeName.setText("Safari Driver file location");
						}
				
					pathbutton.setVisible(true);
			
			}
			else {
				
				pathbutton.setVisible(false);
				browserpath.setText("");
				browserpath.setVisible(false);
				info.setVisible(false);
				if(output1.getWidth()!=0) {
					browserImage.setIcon(new ImageIcon(pic.getImage(browserImage, getClass().getResource("/Images/invalid.png"))));
					}
					else {
						browserImage.setIcon(null);
						
					}
				exeName.setText("");
			}	
			}
			
		});
		
		browserpath.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(browserpath.isSelected()) {
					pathbutton.setEnabled(true);
				}
				else {
					pathbutton.setEnabled(false);
					exeOutput.setText("");
				}
				
			}
			
		});
		
		info.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(info.isSelected()) {
					pathbutton.setEnabled(false);
					File f;
					if(cb.getSelectedItem().toString().equals("Chrome"))
							f=new File("user/local/bin/chromedriver");
						else if(cb.getSelectedItem().toString().equals("Firefox"))
							f=new File("user/local/bin/geckodriver");
						else if(cb.getSelectedItem().toString().equals("Safari"))
							f=new File("user/local/bin/SafariDriver.safariextz");
						else
							f=null;
					
					if(f.exists()) {
						exeOutput.setText("");
						exeOutput.setForeground(Color.BLACK);
					}
					else {
						exeOutput.setText(cb.getSelectedItem().toString()+ " driver doesn't exist in user/local/bin folder");
						exeOutput.setForeground(Color.RED);
					}
				}
				else {
					exeOutput.setText("");
				}
				
			}
			
		});
		
		pathbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pathbutton.setBorder(BorderFactory.createLineBorder(null, 0, true));
				try {
					String browse;
					if(cb.getSelectedItem().toString().equals("Chrome"))
					browse="/chromedriver";
					else if(cb.getSelectedItem().toString().equals("Firefox"))
						browse="/geckodriver";
					else if(cb.getSelectedItem().toString().equals("Safari"))
						browse="/SafariDriver.safariextz";
					else
						browse=null;
					chooser(cb,exeBrowse,browse);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
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
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String browserName=cb.getSelectedItem().toString();
				try {
					if(!browserName.equalsIgnoreCase("Select browser")) {
						if(browserName.equalsIgnoreCase("firefox"))
					Runtime.getRuntime().exec(new String[]{"cmd", "/c","open -a firefox "+google.getText()});
						else if(browserName.equalsIgnoreCase("chrome"))
							Runtime.getRuntime().exec(new String[]{"cmd", "/c","open -a  chrome "+google.getText()});
						else if(browserName.equalsIgnoreCase("safari"))
							Runtime.getRuntime().exec(new String[]{"cmd", "/c","open -a safari "+google.getText()});
					
					}
					else {
						cb.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
						JOptionPane.showMessageDialog(null, "Please select any browser in Browser Panel", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		google.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				 if (google.getText().equals("Example: www.abc.com")) {
			            google.setText("");
			            google.setForeground(Color.BLACK);
			        }
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				 if (google.getText().isEmpty()) {
			            google.setForeground(Color.GRAY);
			            google.setText("Example: www.abc.com");
			        }
			}
			
		});
		
		google.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyChar()==KeyEvent.VK_ENTER) {
					ok.doClick();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		macPanel.addComponentListener(new ComponentListener() {

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
				main.setBounds((int)(0.1*macPanel.getWidth()), (int)(0.1*macPanel.getHeight()), 
						(int)(0.8*macPanel.getWidth()), (int)(0.5*macPanel.getHeight()));
				browser.setBounds((int)(0.02*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
				folder.setBounds((int)(0.35*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
				verify.setBounds((int)(0.68*main.getWidth()), (int)(0.1*main.getHeight()), (int)(0.3*main.getWidth()),(int)(0.8*main.getHeight()));
				
				start.setBounds((int)(0.35*macPanel.getWidth()), (int)(0.62*macPanel.getHeight()),
						(int)(0.09*macPanel.getWidth()), (int)(0.05*macPanel.getHeight()));
				back.setBounds((int)(0.45*macPanel.getWidth()), (int)(0.62*macPanel.getHeight()),
						(int)(0.09*macPanel.getWidth()), (int)(0.05*macPanel.getHeight()));
				output.setBounds((int)(0.55*macPanel.getWidth()), (int)(0.62*macPanel.getHeight()),
						(int)(0.09*macPanel.getWidth()), (int)(0.05*macPanel.getHeight()));
				progress.setBounds((int)(0.40*macPanel.getWidth()), (int)(0.67*macPanel.getHeight()),
						(int)(0.2*macPanel.getWidth()), (int)(0.05*macPanel.getHeight()));
				output1.setBounds((int)(0.2*macPanel.getWidth()), (int)(0.72*macPanel.getHeight()),
						(int)(0.29*macPanel.getWidth()), (int)(0.2*macPanel.getHeight()));
				output2.setBounds((int)(0.51*macPanel.getWidth()), (int)(0.72*macPanel.getHeight()),
						(int)(0.29*macPanel.getWidth()), (int)(0.2*macPanel.getHeight()));
				photo.setBounds(0, 0, macPanel.getWidth(), macPanel.getHeight());
				imageLoad.setBounds((int)(0.01*macPanel.getWidth()), (int)(0.9*macPanel.getHeight()),
						(int)(0.1*macPanel.getWidth()), (int)(0.05*macPanel.getHeight()));
				SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
					   @Override
					   protected Void doInBackground() throws Exception {
					    // Simulate doing something useful.
				try {
					myPicture = ImageIO.read(new URL("https://macgui.com/upload/gallery/f_6/user_6121/regular/upload_3324.jpg"));
					} catch (IOException e) {
								// TODO Auto-generated catch block
					myPicture = ImageIO.read(getClass().getResource("/Images/apple.jpg"));
//								e.printStackTrace();
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
		
		
		browser.addComponentListener(new ComponentListener() {

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
				title.setBounds(0, (int)(0*browser.getHeight()), browser.getWidth(), (int)(0.2*browser.getHeight()));
				type.setBounds((int)(0.1*browser.getWidth()), (int)(0.25*browser.getHeight()), 
						(int)(0.38*browser.getWidth()), (int)(0.1*browser.getHeight()));
				cb.setBounds((int)(0.42*browser.getWidth()), (int)(0.25*browser.getHeight()), 
						(int)(0.5*browser.getWidth()), (int)(0.1*browser.getHeight()));
				browserpath.setBounds((int)(0.1*browser.getWidth()), (int)(0.45*browser.getHeight()), 
						(int)(0.8*browser.getWidth()), (int)(0.1*browser.getHeight()));
				info.setBounds((int)(0.1*browser.getWidth()), (int)(0.7*browser.getHeight()), 
						(int)(0.8*browser.getWidth()), (int)(0.1*browser.getHeight()));
				pathbutton.setBounds((int)(0.3*browser.getWidth()), (int)(0.55*browser.getHeight()), 
						(int)(0.4*browser.getWidth()), (int)(0.1*browser.getHeight()));
				
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
				urlLabel.setBounds((int)(0.1*verify.getWidth()), (int)(0.55*verify.getHeight()), 
						(int)(0.70*verify.getWidth()), (int)(0.075*verify.getHeight()));
				image.setBounds((int)(0.1*verify.getWidth()), (int)(0.65*verify.getHeight()), 
						(int)(0.075*verify.getWidth()), (int)(0.075*verify.getHeight()));
				if(verify.getWidth()!=0) {
				image.setIcon(new ImageIcon(pic.getImage(image, getClass().getResource("/Images/google.png"))));
				}
				else {
					image.setIcon(null);
				}
				google.setBounds((int)(0.180*verify.getWidth()), (int)(0.65*verify.getHeight()), 
						(int)(0.54*verify.getWidth()), (int)(0.075*verify.getHeight()));
				ok.setBounds((int)(0.71*verify.getWidth()), (int)(0.65*verify.getHeight()), 
						(int)(0.15*verify.getWidth()), (int)(0.075*verify.getHeight()));
				warning.setBounds((int)(0.1*verify.getWidth()), (int)(0.75*verify.getHeight()), 
						(int)(0.8*verify.getWidth()), (int)(0.2*verify.getHeight()));
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
				oTitle1.setBounds(0, (int)(0*output1.getHeight()), output1.getWidth(), (int)(0.2*output1.getHeight()));
				platform.setBounds((int)(0.05*output1.getWidth()), (int)(0.25*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.2*output1.getHeight()));
				web.setBounds((int)(0.05*output1.getWidth()), (int)(0.45*output1.getHeight()), (int)(0.3*output1.getWidth()), (int)(0.2*output1.getHeight()));
				browserImage.setBounds((int)(0.31*output1.getWidth()), (int)(0.47*output1.getHeight()), (int)(0.075*output1.getWidth()), (int)(0.15*output1.getHeight()));
				platformImage.setBounds((int)(0.3*output1.getWidth()), (int)(0.25*output1.getHeight()), (int)(0.1*output1.getWidth()), (int)(0.2*output1.getHeight()));
				if(output1.getWidth()!=0) {
					
					platformImage.setIcon(new ImageIcon(pic.getImage(platformImage, getClass().getResource("/Images/mac.png"))));
					}
					else {
						platformImage.setIcon(null);
					}
				exeName.setBounds((int)(0.05*output1.getWidth()), (int)(0.65*output1.getHeight()), (int)(0.8*output1.getWidth()), (int)(0.2*output1.getHeight()));
				exeOutput.setBounds((int)(0.05*output1.getWidth()), (int)(0.85*output1.getHeight()), (int)(0.8*output1.getWidth()), (int)(0.1*output1.getHeight()));
				oTitle2.setBounds(0, (int)(0*output1.getHeight()), output1.getWidth(), (int)(0.2*output1.getHeight()));
				eLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.25*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.15*output2.getHeight()));
				pLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.40*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.15*output2.getHeight()));
				sceneLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.55*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.15*output2.getHeight()));
				rLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.70*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.15*output2.getHeight()));
				sLabel.setBounds((int)(0.05*output2.getWidth()), (int)(0.85*output2.getHeight()), (int)(0.3*output2.getWidth()), (int)(0.15*output2.getHeight()));
				eResult.setBounds((int)(0.22*output2.getWidth()), (int)(0.25*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.15*output2.getHeight()));
				pResult.setBounds((int)(0.22*output2.getWidth()), (int)(0.40*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.15*output2.getHeight()));
				sceneResult.setBounds((int)(0.22*output2.getWidth()), (int)(0.55*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.15*output2.getHeight()));
				rResult.setBounds((int)(0.22*output2.getWidth()), (int)(0.70*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.15*output2.getHeight()));
				sResult.setBounds((int)(0.22*output2.getWidth()), (int)(0.85*output2.getHeight()), (int)(0.7*output2.getWidth()), (int)(0.15*output2.getHeight()));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
		});
	}
	
	public boolean driverExeFiles(String browser, String exeFile) {
		if(browser.equalsIgnoreCase("Firefox") && exeFile.contains("geckodriver")) {
			return true;
		}
		else if(browser.equalsIgnoreCase("Chrome") && exeFile.contains("chromedriver")) {
			return true;
		}
		else if(browser.equalsIgnoreCase("Safari") && exeFile.contains("SafariDriver")) {
			return true;
		}
		
			return false;
		
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
	
	@SuppressWarnings("rawtypes")
	public void chooser(JComboBox cb,JFileChooser fc,String ext) throws Exception {
		fc.setDialogTitle("Choose "+cb.getSelectedItem().toString()+" driver file");
		
		int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fc.getSelectedFile();
          System.out.println(selectedFile.getName());
          String name=selectedFile.getAbsolutePath();
          if(name.substring(name.lastIndexOf("/")).equals(ext)) {
        	  if(driverExeFiles(cb.getSelectedItem().toString(),selectedFile.getName())) {
        	  exeOutput.setText(selectedFile.getAbsolutePath().replace("\\", "\\\\"));
        	  exeOutput.setForeground(Color.BLACK);
        	  }
        	  else {
        		  exeOutput.setText(selectedFile.getName()+" is not for "+cb.getSelectedItem().toString()); 
        		  exeOutput.setForeground(Color.RED);
        	  }
          }
          else {
        	  exeOutput.setText("Please select .exe file"); 
        	  exeOutput.setForeground(Color.RED);
          }
          
          System.out.println(exeOutput.getText());
        }
        fc.setCurrentDirectory(fc.getCurrentDirectory());
//        rwp.setData("browserpath", );
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
