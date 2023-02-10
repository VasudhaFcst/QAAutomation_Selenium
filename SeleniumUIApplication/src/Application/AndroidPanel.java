package Application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UnsupportedLookAndFeelException;

public class AndroidPanel {
	JPanel parent,main,common;
	JButton server,next,back,stopserver;
	JLabel title,photo,imageLoad;
	
	JLabel deviceLabel,browserLabel,versionLabel,platformLabel,packageLabel,activityLabel;
	JTextField device,browser,version,platform,packageName,activity;	
	
	JLabel appTitle,sdkLabel,avgLabel,avdWarning,host,port,node,appium,warn;
	JButton sdkButton,avgButton,nodeButton,appiumButton;
	JTextField sdkPath,avdPath,nodePath,appiumPath,hostNumber,portNumber;
	JRadioButton normal,free,other;
	ButtonGroup bg;
	
	JFileChooser manage,js,nodeexe;
	BufferedImage myPicture;
//	ConsoleOutput co;
	
	public AndroidPanel(JPanel androidPanel) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//		co=new ConsoleOutput(androidPanel);
		
		parent=new JPanel();
		parent.setLayout(null);
		parent.setBackground(Color.WHITE);
//		parent.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,false));
		parent.setOpaque(false);
		
		main=new JPanel();
//		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		main.setBounds((int)(0.05*androidPanel.getWidth()), (int)(0.1*androidPanel.getHeight()), 
				(int)(0.35*androidPanel.getWidth()), (int)(0.5*androidPanel.getHeight()));
		main.setLayout(null);
		main.setBackground(new Color(230,241,249));
		main.setBorder(BorderFactory.createLineBorder(new Color(76,167,228),2,false));
		
		title=new JLabel("CAPABILITIES",JLabel.CENTER);
		title.setFont(new Font("Narkisim", Font.BOLD, 20));
		title.setBackground(new Color(76,167,228));
		title.setForeground(Color.BLACK);
		title.setOpaque(true);
		
		deviceLabel=new JLabel("Device Name");
		deviceLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		
		browserLabel=new JLabel("Browser Name");
		browserLabel.setFont(deviceLabel.getFont());
		
		versionLabel=new JLabel("Version Name");
		versionLabel.setFont(deviceLabel.getFont());
		
		platformLabel=new JLabel("Platform Name");
		platformLabel.setFont(deviceLabel.getFont());
		
		packageLabel=new JLabel("App Package Name");
		packageLabel.setFont(deviceLabel.getFont());
		
		activityLabel=new JLabel("App Activity Name");
		activityLabel.setFont(deviceLabel.getFont());
		
		device=new JTextField("Eg: Rose");
		device.setFont(new Font("Calibri", Font.PLAIN, 13));
		device.setForeground(Color.GRAY);
		device.addFocusListener(new Focus(device,device.getText()));
		
		browser=new JTextField("Eg: Andriod or Chrome");
		browser.setFont(device.getFont());
		browser.setForeground(Color.GRAY);
		browser.addFocusListener(new Focus(browser,browser.getText()));
		
		version=new JTextField("Eg: 5.11");
		version.setFont(device.getFont());
		version.setForeground(Color.GRAY);
		version.addFocusListener(new Focus(version,version.getText()));
		
		platform=new JTextField("Eg: Android");
		platform.setFont(device.getFont());
		platform.setForeground(Color.GRAY);
		platform.addFocusListener(new Focus(platform,platform.getText()));
		
		packageName=new JTextField("Eg: com.abc");
		packageName.setFont(device.getFont());
		packageName.setForeground(Color.GRAY);
		packageName.addFocusListener(new Focus(packageName,packageName.getText()));
		
		activity=new JTextField("Eg: com.abc.activity.RedirectActivity");
		activity.setFont(device.getFont());
		activity.setForeground(Color.GRAY);
		activity.addFocusListener(new Focus(activity,activity.getText()));
		
		common=new JPanel();
		common.setLayout(null);
		common.setBackground(new Color(230,241,249));
		common.setBorder(BorderFactory.createLineBorder(new Color(76,167,228),2,false));
		
		appTitle=new JLabel("APPIUM SERVER & MANAGERS",JLabel.CENTER);
		appTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
		appTitle.setBackground(new Color(76,167,228));
		appTitle.setForeground(Color.BLACK);
		appTitle.setOpaque(true);
		
		manage=new JFileChooser();
		js=new JFileChooser();
		nodeexe=new JFileChooser();
		
		sdkLabel =new JLabel("SDK Manager");
		sdkLabel.setFont(deviceLabel.getFont());
		
		avgLabel =new JLabel("AVD Manager");
		avgLabel.setFont(deviceLabel.getFont());
		
		avdWarning=new JLabel("(No needed for real devices)");
		avdWarning.setFont(new Font("Calibri", Font.PLAIN, 10));
		
		host =new JLabel("IP Address");
		host.setFont(deviceLabel.getFont());
		
		port =new JLabel("Port");
		port.setFont(deviceLabel.getFont());
		
		node =new JLabel("Node.js");
		node.setFont(deviceLabel.getFont());
		
		appium =new JLabel("Appium.js");
		appium.setFont(deviceLabel.getFont());
		
		sdkButton=new JButton("Browse & Open");
		sdkButton.setFont(new Font("Calibri", Font.BOLD, 13));
		sdkButton.setBackground(new Color(220,220,220));
		sdkButton.setFocusPainted(false);
		
		avgButton=new JButton("Browse & Open");
		avgButton.setFont(sdkButton.getFont());
		avgButton.setBackground(new Color(220,220,220));
		avgButton.setFocusPainted(false);
		
		nodeButton=new JButton("Browse");
		nodeButton.setFont(sdkButton.getFont());
		nodeButton.setBackground(new Color(220,220,220));
		nodeButton.setFocusPainted(false);
		
		appiumButton=new JButton("Browse");
		appiumButton.setFont(sdkButton.getFont());
		appiumButton.setBackground(new Color(220,220,220));
		appiumButton.setFocusPainted(false);
		
		sdkPath=new JTextField("");
		sdkPath.setFont(device.getFont());
		sdkPath.setBackground(common.getBackground());
		sdkPath.setBorder(BorderFactory.createEmptyBorder());
		sdkPath.setEditable(false);
		
		avdPath=new JTextField("");
		avdPath.setFont(device.getFont());
		avdPath.setBackground(common.getBackground());
		avdPath.setBorder(BorderFactory.createEmptyBorder());
		avdPath.setEditable(false);
		
		nodePath=new JTextField("");
		nodePath.setFont(device.getFont());
		nodePath.setText("C:/Program Files/nodejs/node.exe");
		nodePath.setEditable(false);
		nodePath.setBackground(common.getBackground());
		nodePath.setBorder(BorderFactory.createEmptyBorder());
		nodePath.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		appiumPath=new JTextField("");
		appiumPath.setFont(device.getFont());
		appiumPath.setText("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js");
		appiumPath.setEditable(false);
		appiumPath.setBackground(common.getBackground());
		appiumPath.setBorder(BorderFactory.createEmptyBorder());
		appiumPath.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		hostNumber=new JTextField("Eg: 127.0.0.1");
		hostNumber.setFont(device.getFont());
		hostNumber.setForeground(Color.GRAY);
		hostNumber.addFocusListener(new Focus(hostNumber,hostNumber.getText()));
		
		warn=new JLabel("Default IP address is 127.0.0.1");
		warn.setFont(new Font("Calibri", Font.BOLD, 11));
		warn.setForeground(Color.RED);
		
		normal=new JRadioButton("Default");
		normal.setActionCommand("Default");
		normal.setFont(new Font("Calibri", Font.BOLD, 12));
		normal.setFocusable(false);
		normal.setBackground(new Color(135,206,250));
		
		free=new JRadioButton("Any Port");
		free.setActionCommand("Any Port");
		free.setFont(new Font("Calibri", Font.BOLD, 12));
		free.setFocusable(false);
		free.setBackground(new Color(135,206,250));
		
		other=new JRadioButton("Others");
		other.setActionCommand("Others");
		other.setFont(new Font("Calibri", Font.BOLD, 12));
		other.setFocusable(false);
		other.setBackground(new Color(135,206,250));
		
		bg=new ButtonGroup();
		bg.add(normal);
		bg.add(free);
		bg.add(other);
		
		portNumber=new JTextField("");
		portNumber.setFont(device.getFont());
		portNumber.setEnabled(false);
		
		server=new JButton("Start Server");
		server.setBackground(new Color(255,140,0));
		server.setForeground(new Color(255,140,0));
		server.setFont(new Font("Narkisim", Font.BOLD, 18));
		server.setFocusPainted(false);
		
		stopserver=new JButton("Stop Server");
		stopserver.setBackground(Color.RED);
		stopserver.setForeground(Color.RED);
		stopserver.setFont(new Font("Narkisim", Font.BOLD, 18));
		stopserver.setFocusPainted(false);
		stopserver.setVisible(false);
		
		next=new JButton("Next");
		next.setBackground(Color.BLACK);
		next.setForeground(Color.BLACK);
		next.setFont(new Font("Narkisim", Font.BOLD, 18));
		next.setFocusPainted(false);
		next.setEnabled(false);
		
		back=new JButton("Back");
		back.setBackground(Color.RED);
		back.setForeground(Color.RED);
		back.setFont(new Font("Narkisim", Font.BOLD, 18));
		back.setFocusPainted(false);
		
		photo=new JLabel();
		
		imageLoad=new JLabel("Loading...");
		imageLoad.setFont(new Font("Narkisim", Font.BOLD, 10));
		
		main.add(title);
		main.add(deviceLabel);
		main.add(browserLabel);
		main.add(versionLabel);
		main.add(platformLabel);
		main.add(packageLabel);
		main.add(activityLabel);
		main.add(device);
		main.add(browser);
		main.add(version);
		main.add(platform);
		main.add(packageName);
		main.add(activity);
		common.add(appTitle);
		common.add(sdkLabel);
		common.add(avgLabel);
		common.add(avdWarning);
		common.add(host);
		common.add(port);
		common.add(node);
		common.add(appium);
		common.add(sdkButton);
		common.add(avgButton);
		common.add(nodeButton);
		common.add(appiumButton);
		common.add(sdkPath);
		common.add(avdPath);
		common.add(nodePath);
		common.add(appiumPath);
		common.add(hostNumber);
		common.add(warn);
		common.add(normal);
		common.add(free);
		common.add(other);
		common.add(portNumber);
		parent.add(main);
		parent.add(common);
		androidPanel.add(parent);
		androidPanel.add(server);
		androidPanel.add(next);
		androidPanel.add(back);
		androidPanel.add(stopserver);
		androidPanel.add(photo);
		androidPanel.add(imageLoad);
		
		sdkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				managers(sdkPath,".exe");
			}
			
		});
		
		avgButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				managers(avdPath,".exe");
			}
			
		});
		
		nodeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				node(nodePath,"node.exe");
			}
			
		});
		
		appiumButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				appium(appiumPath,"appium.js");
			}
			
		});
		
		normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				portNumber.setText("4723");
				portNumber.setEnabled(false);
			}
			
		});
		
		free.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				portNumber.setText(" ");
				portNumber.setEnabled(false);
			}
			
		});
		
		other.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				portNumber.setText("Eg: 1822");
				portNumber.setEnabled(true);
				portNumber.setForeground(Color.GRAY);
				portNumber.addFocusListener(new Focus(portNumber,portNumber.getText()));
			}
			
		});
	
		androidPanel.addComponentListener(new ComponentListener() {

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
				
				parent.setBounds((int)(0.05*androidPanel.getWidth()), (int)(0.1*androidPanel.getHeight()),
						(int)(0.9*androidPanel.getWidth()), (int)(0.65*androidPanel.getHeight()));
				server.setBounds((int)(0.3*androidPanel.getWidth()), (int)(0.8*androidPanel.getHeight()),
						(int)(0.12*androidPanel.getWidth()), (int)(0.05*androidPanel.getHeight()));
				next.setBounds((int)(0.43*androidPanel.getWidth()), (int)(0.8*androidPanel.getHeight()),
						(int)(0.12*androidPanel.getWidth()), (int)(0.05*androidPanel.getHeight()));
				back.setBounds((int)(0.56*androidPanel.getWidth()), (int)(0.8*androidPanel.getHeight()),
						(int)(0.12*androidPanel.getWidth()), (int)(0.05*androidPanel.getHeight()));
				stopserver.setBounds((int)(0.3*androidPanel.getWidth()), (int)(0.8*androidPanel.getHeight()),
						(int)(0.12*androidPanel.getWidth()), (int)(0.05*androidPanel.getHeight()));
				photo.setBounds(0, 0, androidPanel.getWidth(), androidPanel.getHeight());
				imageLoad.setBounds((int)(0.01*androidPanel.getWidth()), (int)(0.9*androidPanel.getHeight()),
						(int)(0.1*androidPanel.getWidth()), (int)(0.05*androidPanel.getHeight()));
				SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
					   @Override
					   protected Void doInBackground() throws Exception {
					    // Simulate doing something useful.
				try {
					myPicture = ImageIO.read(new URL("https://avante.biz/wp-content/uploads/Wallpapers-HD-For-Android-Mobile/Wallpapers-HD-For-Android-Mobile-001.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					myPicture = ImageIO.read(getClass().getResource("/Images/androidPanel.jpg"));
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
		
		
		
		parent.addComponentListener(new ComponentListener() {

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
				main.setBounds((int)(0.05*parent.getWidth()), (int)(0.1*parent.getHeight()), 
						(int)(0.35*parent.getWidth()), (int)(0.8*parent.getHeight()));
				common.setBounds((int)(0.45*parent.getWidth()), (int)(0.1*parent.getHeight()), 
						(int)(0.5*parent.getWidth()), (int)(0.8*parent.getHeight()));
				
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
				title.setBounds((int)(0*main.getWidth()), (int)(0*main.getHeight()), 
						(int)(1*main.getWidth()), (int)(0.15*main.getHeight()));
				deviceLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.2*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				browserLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.33*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				versionLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.46*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				platformLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.59*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				packageLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.72*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				activityLabel.setBounds((int)(0.1*main.getWidth()), (int)(0.85*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				
				device.setBounds((int)(0.5*main.getWidth()), (int)(0.2*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				browser.setBounds((int)(0.5*main.getWidth()), (int)(0.33*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				version.setBounds((int)(0.5*main.getWidth()), (int)(0.46*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				platform.setBounds((int)(0.5*main.getWidth()), (int)(0.59*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				packageName.setBounds((int)(0.5*main.getWidth()), (int)(0.72*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
				activity.setBounds((int)(0.5*main.getWidth()), (int)(0.85*main.getHeight()), (int)(0.4*main.getWidth()), (int)(0.06*main.getHeight()));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
		});
		
		common.addComponentListener(new ComponentListener() {

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
				appTitle.setBounds(0, 0, common.getWidth(), (int)(0.15*common.getHeight()));
				sdkLabel.setBounds((int)(0.05*common.getWidth()), (int)(0.2*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				avgLabel.setBounds((int)(0.05*common.getWidth()), (int)(0.33*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				avdWarning.setBounds((int)(0.05*common.getWidth()), (int)(0.38*common.getHeight()), (int)(0.4*common.getWidth()), (int)(0.04*common.getHeight()));
				node.setBounds((int)(0.05*common.getWidth()), (int)(0.46*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				appium.setBounds((int)(0.05*common.getWidth()), (int)(0.59*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				host.setBounds((int)(0.05*common.getWidth()), (int)(0.72*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				port.setBounds((int)(0.05*common.getWidth()), (int)(0.85*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				
				sdkButton.setBounds((int)(0.25*common.getWidth()), (int)(0.2*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				avgButton.setBounds((int)(0.25*common.getWidth()), (int)(0.33*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				nodeButton.setBounds((int)(0.25*common.getWidth()), (int)(0.46*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				appiumButton.setBounds((int)(0.25*common.getWidth()), (int)(0.59*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
			
				sdkPath.setBounds((int)(0.5*common.getWidth()), (int)(0.2*common.getHeight()), (int)(0.45*common.getWidth()), (int)(0.06*common.getHeight()));
				avdPath.setBounds((int)(0.5*common.getWidth()), (int)(0.33*common.getHeight()), (int)(0.45*common.getWidth()), (int)(0.06*common.getHeight()));
				nodePath.setBounds((int)(0.5*common.getWidth()), (int)(0.46*common.getHeight()), (int)(0.45*common.getWidth()), (int)(0.06*common.getHeight()));
				appiumPath.setBounds((int)(0.5*common.getWidth()), (int)(0.59*common.getHeight()), (int)(0.45*common.getWidth()), (int)(0.06*common.getHeight()));
				hostNumber.setBounds((int)(0.25*common.getWidth()), (int)(0.72*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
				warn.setBounds((int)(0.5*common.getWidth()), (int)(0.72*common.getHeight()), (int)(0.45*common.getWidth()), (int)(0.06*common.getHeight()));
				normal.setBounds((int)(0.25*common.getWidth()), (int)(0.85*common.getHeight()), (int)(0.15*common.getWidth()), (int)(0.06*common.getHeight()));
				free.setBounds((int)(0.4*common.getWidth()), (int)(0.85*common.getHeight()), (int)(0.15*common.getWidth()), (int)(0.06*common.getHeight()));
				other.setBounds((int)(0.55*common.getWidth()), (int)(0.85*common.getHeight()), (int)(0.15*common.getWidth()), (int)(0.06*common.getHeight()));
				
				portNumber.setBounds((int)(0.75*common.getWidth()), (int)(0.85*common.getHeight()), (int)(0.2*common.getWidth()), (int)(0.06*common.getHeight()));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	}
	
	public boolean fileExists(String name) {
		File file=new File(name);
		if(file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void managers(JTextField text,String output) {
		if(text.getText().equals("") || !fileExists(text.getText())) {
			int returnValue = manage.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = manage.getSelectedFile();
	          System.out.println(selectedFile.getName());
	          String name=selectedFile.getAbsolutePath();
	          System.out.println(name);
	          if(name.substring(name.lastIndexOf(".")).equals(output)) {
	        	 text.setText(name.replace("\\", "/"));
	        	 text.setForeground(Color.BLACK);
	          }
	          else {
	        	 text.setText("Please select "+output+" file");
	        	 text.setForeground(Color.RED);
	          }
	          
	        }
	        manage.setCurrentDirectory(manage.getCurrentDirectory());
			}
			else {
				System.out.println("its opened");
			}
	}
	
	public void node(JTextField text,String output) {
		int returnValue = nodeexe.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = nodeexe.getSelectedFile();
          System.out.println(selectedFile.getName());
          String name=selectedFile.getAbsolutePath();
          System.out.println(name.substring(name.lastIndexOf("\\")));
        
          if(name.substring(name.lastIndexOf("\\")+1).equals(output)) {
        	 text.setText(name.replace("\\", "/"));
        	 text.setForeground(Color.BLACK);
          }
          else {
        	 text.setText("Please select "+output+" file");
        	 text.setForeground(Color.RED);
          }
        }
        nodeexe.setCurrentDirectory(nodeexe.getCurrentDirectory());
	}


public void appium(JTextField text,String output) {
	int returnValue = js.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = js.getSelectedFile();
      System.out.println(selectedFile.getName());
      String name=selectedFile.getAbsolutePath();
      System.out.println(name.substring(name.lastIndexOf("\\")));
      
     
          if(name.substring(name.lastIndexOf("\\")+1).equals(output)||name.substring(name.lastIndexOf("\\")+1).equals("main.js")) {
        	 text.setText(name.replace("\\", "/"));
        	 text.setForeground(Color.BLACK);
          }
          else {
        	 text.setText("Please select "+output+" or main.js file");
        	 text.setForeground(Color.RED);
          }
               
    }
    js.setCurrentDirectory(js.getCurrentDirectory());
}
}

class Focus implements FocusListener{
	JTextField textfield;
	String message;
	
	Focus(JTextField text,String value){
		textfield=text;
		message=value;
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		 if (textfield.getText().equals(message)) {
	            textfield.setText("");
	            textfield.setForeground(Color.BLACK);
	        }
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		 if (textfield.getText().isEmpty()) {
			 textfield.setForeground(Color.GRAY);
			 textfield.setText(message);
	        }
	}
	
}