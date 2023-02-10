package Application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import AdditionalSetup.ExecutionPart;
import AdditionalSetup.SelectExecutionOption;
import AndroidMobile.AppiumServer;
import Common.Information;
import Running.Final;
import Running.Timing;

public class TitlePage implements Information{
	int r=0;
	JFrame frame = new JFrame();
	String params[]=new String[6];
	String imp[]=new String[9];
	static List<Map<String,String>> list;
	public static String pauseStatus="Pause";
	public static String stopStatus="Start";
	AppiumServer as;
	public TitlePage() throws Exception {
//		Desktop.getDesktop().open(new File("C:\\testing\\Kohler.xlsx"));
	frame.getContentPane().setBackground(Color.WHITE);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	frame.setVisible(true);
	frame.setLocation(0, 0);
	frame.setSize((int)(frame.getWidth()), (int)(0.999*frame.getHeight()));
	frame.getContentPane().setLayout(null);
	JPanel aPanel=new JPanel();
	JPanel tPanel=new JPanel();
	JPanel wPanel=new JPanel();
	JPanel aFiles=new JPanel();
	JPanel oPanel=new JPanel();
	JPanel iPanel=new JPanel();
	JPanel iPhone=new JPanel();
	JPanel mPanel=new JPanel();
	tPanel.setLayout(null);
	wPanel.setLayout(null);
	aFiles.setLayout(null);
	oPanel.setLayout(null);
	iPanel.setLayout(null);
	iPhone.setLayout(null);
	mPanel.setLayout(null);
	aFiles.setSize(0, 0);
	wPanel.setSize(0,0);
	oPanel.setSize(0, 0);
	iPanel.setSize(0,0);
	iPhone.setSize(0,0);
	mPanel.setSize(0, 0);
	aPanel.setLayout(null);
	tPanel.setBackground(new Color(255,228,225));
	wPanel.setBackground(new Color(135,206,250));
	aPanel.setBackground(new Color(152,251,152));
	aFiles.setBackground(aPanel.getBackground());
	iPanel.setBackground(new Color(192,192,192));
	iPhone.setBackground(new Color(192,192,192));
	mPanel.setBackground(new Color(192,192,192));
	aPanel.setSize(0,0);
	JMenuBar menu;
	JMenu help;
	JMenuItem item1,item2;
	
	menu=new JMenuBar();
	help=new JMenu("Help");
	item1 =new JMenuItem("How to process");
	item2 =new JMenuItem("About Automation Application");
	help.add(item1);
	help.add(item2);
	menu.add(help);
	frame.setJMenuBar(menu);
	menu.setVisible(false);

//	int width=(int) (0.25*frame.getWidth());
	int height=(int) (0.15*frame.getHeight());

	System.out.println(frame.getWidth()+"  "+height);
	tPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
	JLabel lab=new JLabel();
	lab.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
	tPanel.add(lab);
	Title title=new Title(tPanel);
	WindowPanel win=new WindowPanel(wPanel);
	AndroidPanel and=new AndroidPanel(aPanel);
	AndroidFiles af=new AndroidFiles(aFiles,and);
	JTableRow jtr=new JTableRow();
	ComponentsEnable ce=new ComponentsEnable();
	IDevices id=new IDevices(iPanel);
	Mac mac=new Mac(mPanel);
//	Console wOut=new Console(win.cow.area);
//	Console aOut=new Console(and.co.area);
//	Console afOut=new Console(af.co.area);
	
	frame.getContentPane().add(tPanel);
	frame.getContentPane().add(wPanel);
	frame.getContentPane().add(aPanel);
	frame.getContentPane().add(aFiles);
	frame.getContentPane().add(oPanel);
	frame.getContentPane().add(iPanel);
	frame.getContentPane().add(iPhone);
	frame.getContentPane().add(mPanel);
//	frame.getContentPane().setBackground(new Color(255,99,71));
	
	title.window.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tPanel.setSize(0, 0);
			wPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//			wOut.time.start();
//			aOut.time.stop();
//			afOut.time.stop();
			}
	});
	
	title.android.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tPanel.setSize(0, 0);
			aPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//			wOut.time.stop();
//			aOut.time.start();
//			afOut.time.start();
			}
	});
	
	title.ios.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tPanel.setSize(0,0);
			iPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
		}
		
	});
	
	id.system.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			iPanel.setSize(0,0);
			mPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
		}
		
	});
	
	id.back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			iPanel.setSize(0,0);
			tPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
		}
		
	});
	
	win.back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			wPanel.setSize(0, 0);
			tPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
			
		}
		
	});
	
	and.back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			aPanel.setSize(0, 0);
			tPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
			
		}
		
	});
	
	af.back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			aFiles.setSize(0, 0);
			aPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
			
		}
		
	});
	
	mac.back.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mPanel.setSize(0, 0);
			iPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
			
		}
		
	});
	
	win.output.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(list!=null) {
				Map<String,String> parameters=new LinkedHashMap<String,String>();
				parameters.put(" Platform", imp[PLATFORM]);
				parameters.put(" Browser Name", imp[BROWSERNAME]);
				parameters.put(" Driver Path", imp[BROWSERPATH]);
				parameters.put(" Excel Sheet", imp[EXCEL]);
				parameters.put(" Properties File", imp[PROPERTIES]);
				parameters.put(" Scenarios Name", imp[SCENARIOS]);
				parameters.put(" Extent Report", imp[EXTENTREPORT]);
				parameters.put(" XML Report", imp[XML]);
				parameters.put(" Screenshot Folder", imp[SCREENSHOT]);
			jtr.tableRow(oPanel,list,parameters);
			wPanel.setSize(0, 0);
			oPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			jtr.back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					oPanel.setSize(0, 0);
					wPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
					jtr.frame.dispose();
				}
				
			});
			
			jtr.home.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					oPanel.setSize(0, 0);
					tPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
					jtr.frame.dispose();
				}
				
			});
		}
			else {
				JOptionPane.showMessageDialog(null, "No testcases are executed", "Info", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	});
	
	
	
	win.start.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			wOut.time.start();
			stopStatus="Start";
			pauseStatus="Pause";
			imp[PLATFORM]=title.window.getText();
			imp[BROWSERNAME]=win.cb.getSelectedItem().toString();
			imp[EXCEL]=win.eResult.getText();
			imp[PROPERTIES]=win.pResult.getText();
			imp[EXTENTREPORT]=win.rResult.getText();
			imp[SCREENSHOT]=win.sResult.getText();
			imp[BROWSERPATH]=win.exeOutput.getText();
			imp[SCENARIOS]=win.sceneResult.getText();
			imp[XML]=win.rResult.getText();
			FinalVerification fv=new FinalVerification();
			SelectExecutionOption.setSelectOption(ExecutionPart.GUI);
			boolean condition=fv.verify(win);
			
			try {
				if(condition) {
					
					CountDown cd=new CountDown();
					Final f=new Final();
					int a=JOptionPane.showConfirmDialog(frame,"Are you sure? Please close excel sheet before click on 'YES'");  
					if(a==JOptionPane.YES_OPTION){  
						String time=new Timing().timeReport();
						imp[EXTENTREPORT]=directoryCreated(imp[EXTENTREPORT], "HTML_reports")+"/"+time+".html";
						imp[XML]=directoryCreated(imp[XML], "XML_reports")+"/"+time+".xml";
						imp[SCREENSHOT]=directoryCreated(imp[SCREENSHOT], "Screenshots_"+time);
						ce.enablePanel(win.browser, false);
						ce.enablePanel(win.folder, false);
						ce.enablePanel(win.verify, false);
						win.start.setEnabled(false);
						win.output.setEnabled(false);
						list=null;
						cd.waiting(frame);
						r=0;
						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
							   @Override
							   protected Void doInBackground() throws Exception {
							    // Simulate doing something useful.
								   new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											for(int i=0;i<=200;i++) {
												cd.circle(i);
												cd.panel.repaint();
												if(i==200) {
													i=0;
												}
												if(r==1) {
													cd.message("Closing...");
												}
												else {
													cd.message("Loading...");
												}
												try {
													Thread.sleep(10);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
										
									}).start();
							    return null;
							   }
							  };
							  
							  worker.execute();
						SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
							   @Override
							   protected Void doInBackground() throws Exception {
							    // Simulate doing something useful.
									list=f.testing(imp);
									cd.loading.dispose();
									ce.enablePanel(win.browser, true);
									ce.enablePanel(win.folder, true);
									ce.enablePanel(win.verify, true);
									win.start.setEnabled(true);
									win.output.setEnabled(true);
//									wOut.time.stop();
							    return null;
							   }
							  };
						worker1.execute();
						
						cd.button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								stopStatus="Stop";
								boolean closed=f.stop();
								if(closed) {
								r=1;
								ce.enablePanel(win.browser, true);
								ce.enablePanel(win.folder, true);
								ce.enablePanel(win.verify, true);
								win.start.setEnabled(true);
								win.output.setEnabled(true);
								}
								
							}
							
						});
						SwingWorker<Void, Void> worker2 = new SwingWorker<Void, Void>() {
							   @Override
							   protected Void doInBackground() throws Exception {
						cd.pause.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								if(cd.pause.getText().equals("Pause")) {
									cd.pause.setText("Resume");
									pauseStatus="Resume";
								}else {
									cd.pause.setText("Pause");
									pauseStatus="Pause";
								}
							}
							
						});
						 return null;
							   }
							  };
						worker2.execute();
					} 
					else {
						win.progress.setVisible(false);
						win.start.setEnabled(true);
					}
					System.out.println("Success...");
				}
				for(int i=0;i<imp.length;i++) {
					System.out.println(imp[i]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	
	and.next.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			aPanel.setSize(0, 0);
			aFiles.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//			System.out.println("Appium URL is "+as.getURL());
		}
		
	});
	
	and.server.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			params[0]=and.nodePath.getText();
			params[1]=and.appiumPath.getText();
			params[2]=and.hostNumber.getText();
			FinalVerification fv=new FinalVerification();
			boolean condition=fv.verify(and, params);
			if(condition) {
				params[3]=and.bg.getSelection().getActionCommand();
				params[4]=and.portNumber.getText();
				
				SwingWorker<Void, Void> serverstart = new SwingWorker<Void, Void>() {
					   @Override
					   protected Void doInBackground() throws Exception {
					    // Simulate doing something useful.
						  as=new AppiumServer();
							as.start(params);
							params[5]=as.getURL();
							System.out.println(as.getURL());
							if(as.isRunning()) {
								ce.visible(and.server, false);
								ce.visible(and.stopserver, true);
								ce.enable(and.nodeButton, false);
								ce.enable(and.appiumButton, false);
								ce.enable(and.hostNumber, false);
								ce.enable(and.normal, false);
								ce.enable(and.free, false);
								ce.enable(and.other, false);
								ce.enable(and.next, true);
								if(and.free.isSelected()) {
									String url=as.getURL();
									and.portNumber.setText(url.substring(url.lastIndexOf(":")+1, url.lastIndexOf("/wd")));
								}
								if(and.other.isSelected()) {
								ce.enable(and.portNumber, false);
								}
								JOptionPane.showMessageDialog(null, as.getURL(), "Info", JOptionPane.PLAIN_MESSAGE);
							}
							System.out.println("Success");
					    return null;
					   }
					  };
					  
				serverstart.execute();
			}
		
		}
		
	});
	
	and.stopserver.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			as.stop();
			ce.visible(and.server, true);
			ce.visible(and.stopserver, false);
			ce.enable(and.nodeButton, true);
			ce.enable(and.appiumButton, true);
			ce.enable(and.hostNumber, true);
			ce.enable(and.normal, true);
			ce.enable(and.free, true);
			ce.enable(and.next, false);
			if(and.free.isSelected()) {
				and.portNumber.setText("  ");
			}
			ce.enable(and.other, true);
			if(and.other.isSelected()) {
				ce.enable(and.portNumber, true);
				}
		}
	});
	frame.addComponentListener(new ComponentListener() {
		
		@Override
		public void componentResized(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			if(tPanel.getWidth()!=0) {
			tPanel.setBounds(0,0, (int) (1*frame.getWidth()), (int) (1*frame.getHeight()));
			}
		}

		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	});
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public String directoryCreated(String path,String newFolder) {
		path=path+"/"+newFolder;
		File f=new File(path);
		f.mkdir();
		return path;
	}
	
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					String result=getClass().getResource("/PropertiesBase/out.txt").toString();
//					System.out.println(result.substring(result.indexOf("file:/")+6));
//					PrintStream out = new PrintStream(new File(result.substring(result.indexOf(":")+1)));
//					System.setOut(out);
					TitlePage window = new TitlePage();
					System.out.println(window.frame.getWidth()+"  ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		Properties p=new Properties();
//		File f=new File("C:\\testing\\ESS.properties");
//		FileInputStream fis=new FileInputStream(f);
//		p.load(fis);
//		
////		Set map=p.keySet();
//		TreeSet<Object> hs=new TreeSet<Object>(p.keySet());
//		
//		Iterator<Object> i=hs.iterator();
//
//		LinkedHashSet<String> ts=new LinkedHashSet<String>();
//		while(i.hasNext()){   
//            String key = (String)i.next(); 
//            if(key.startsWith("path_")) {
//            ts.add(p.getProperty(key));
//            System.out.println(key+":"+p.getProperty(key));   
//            }
//             }
//		
//		Iterator<String> it=ts.iterator();
//		while(it.hasNext()){   
//            String key = (String)it.next(); 
//           
//            System.out.println(key);   
//            }
		
		
	}
}

