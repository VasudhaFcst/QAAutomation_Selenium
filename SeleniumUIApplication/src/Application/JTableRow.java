
package Application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


public class JTableRow {

	JPanel tab,input,output,consolePanel;
	JTable table,parameters,resultTable;
	JScrollPane pane,pane2,scroll,outScroll,consoleScroll;
	JLabel uLabel,pLabel,smtpLabel,attachment,out,inputLabel,outputLabel,mailLabel,consoleLabel;
	JTextField mail,username,smtp,port;
	JPasswordField password;
	JComboBox tag;
	JButton btnAdd,btnUpdate,btnDelete,send,compose,ok,home,back;
	JCheckBox excel,html,xml;
	
	JFrame frame;
	String status="close";
	JLabel subLabel,messageLabel,moreLabel,paraName,extent,xmlLink,excelLink,downloads;
	JTextArea message,more;
	JTextField subject,value;
	JTextArea consoleOutput;
	JEditorPane yourEditorPane;
	HTMLEditorKit htmlEditorKit;
	String mailCode;
	
	JLabel pass,passPercent,fail,failPercent,others,othersPercent;
	DefaultTableModel model;
	
	public JTableRow() {
		 
		 table = new JTable(); 
	        Object[] columns = {"S.No","Mail ID","Taggings"};
	        model = new DefaultTableModel() {
	        	@Override
	        	public boolean isCellEditable(int row, int column) {
	        		return false;
	        	}
	        };
	        model.setColumnIdentifiers(columns);
	        
	        table.getTableHeader().setOpaque(false);
	        table.getTableHeader().setBackground(Color.BLACK);
	        table.getTableHeader().setForeground(Color.WHITE);
	        table.setModel(model);
	        
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table.setBackground(Color.WHITE);
	        table.setForeground(Color.black);
	        table.setRowHeight(20);
	        table.setFocusable(false);
	   
	        // create JTextFields
	        pane = new JScrollPane(table);	

	    	more=new JTextArea("Regards");
	}
	
	
	int j=1;
	public void tableRow(JPanel panel,List<Map<String,String>> listOfMaps,Map<String,String> inputResult) {
		float p=0,f=0,o=0;
		 tab=new JPanel();
		 tab.setLayout(null);
		 tab.setBounds((int)(panel.getWidth()*0.1), (int)(panel.getHeight()*0.1), (int)(panel.getWidth()*0.4), (int)(panel.getHeight()*0.5));
		 tab.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 panel.setBackground(new Color(253, 237, 236));
		 
		 input=new JPanel();
		 input.setLayout(null);
		 input.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 input.setBackground(new Color(229, 247, 224));
		 
		 output=new JPanel();
		 output.setLayout(null);
		 output.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		 
		 consolePanel=new JPanel();
		 consolePanel.setLayout(null);
		 consolePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
	      
	        mail = new JTextField();
	        
	        uLabel=new JLabel("Username");
	        
	        pLabel=new JLabel("Password");
	        
	        smtpLabel=new JLabel("SMTP");
	        
	        attachment=new JLabel("Attachments:");
	        
	        username=new JTextField();
	        username.setText("xyz@gmail.com");
	        username.setForeground(Color.GRAY);
	        username.addFocusListener(new Focus(username,username.getText()));
	       
	        password=new JPasswordField();
	        password.setText("Password: xxxxx");
	        password.setForeground(Color.GRAY);
	        password.addFocusListener(new Focus(password,"Password: xxxxx"));
	        password.setEchoChar('*');
	        
	        smtp=new JTextField("Ex: smtp.gmail.com");
	        smtp.setForeground(Color.GRAY);
	        smtp.addFocusListener(new Focus(smtp,smtp.getText()));
	        
	        port=new JTextField("Port (Ex: 465)");
	        port.setForeground(Color.GRAY);
	        port.addFocusListener(new Focus(port,port.getText()));
	        
	        excel=new JCheckBox("Excel Sheet");
	        excel.setFocusable(false);
	        
	        html=new JCheckBox("HTML report");
	        html.setFocusable(false);
	        
	        xml=new JCheckBox("XML report");
	        xml.setFocusable(false);
	        
	        String[] options={"TO","CC","BCC"};
			tag=new JComboBox(options);
			tag.setFocusable(false);
	        // create JButtons
	        btnAdd = new JButton("Add");
	        btnAdd.setFocusable(false);
	        
	        btnDelete = new JButton("Delete");
	        btnDelete.setFocusable(false);
	        
	        btnUpdate = new JButton("Update"); 
	        btnUpdate.setFocusable(false);
	        
	        send=new JButton("Send");
	        send.setFocusable(false);
	        
	        compose=new JButton("Compose");
	        compose.setFocusable(false);
	        
	        out=new JLabel("Status:");
	        
	        Object[] row = new Object[3];
	        // button add row
	        btnAdd.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
//	            	textId.setText(String.valueOf(j));
	            	boolean cond=true;
	            	if(!mail.getText().trim().equals("")) {
	            		for(int i=0;i<table.getRowCount();i++) {
	            			if(model.getValueAt(i, 1).toString().equals(mail.getText())) {
	            				cond=false;
	            				break;
	            			}
	            		}
	            		if(cond) {
	            	row[0]=j;
	                row[1] = mail.getText();
	                row[2] = tag.getSelectedItem();
	                
	                // add row to the model
	                model.addRow(row);
	                mail.setText(" ".trim());
	                int total=table.getRowCount();
	                j=total+1;
	            		}
	            	}
	            }
	        });
	        
	        // button remove row
	        btnDelete.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            
	                // i = the index of the selected row
	            	int[] k=table.getSelectedRows();
	            	for(int p=k.length-1;p>=0;p--) {
	            		System.out.println(k[p]);
	            		if(k[p] >= 0){
		                    // remove a row from jtable
		                    model.removeRow(k[p]);
		                    reArrange(table,model);
		                    int total=table.getRowCount();
			                j=total+1;
		                }
		                else{
		                    System.out.println("Delete Error");
		                }
	            	}
	                
	            }
	        });
	        
	        // get selected row data From table to textfields 
	        table.addMouseListener(new MouseAdapter(){
	        
	        @Override
	        public void mouseClicked(MouseEvent e){
	            
	            // i = the index of the selected row
	            int i = table.getSelectedRow();
	            
	            mail.setText(model.getValueAt(i, 1).toString());
	            tag.setSelectedItem(model.getValueAt(i, 2).toString());
	        }
	        });
	        
	        // button update row
	        btnUpdate.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	             boolean cond=true;
	                // i = the index of the selected row
	                int i = table.getSelectedRow();
	                for(int j=0;j<table.getRowCount()&&j!=i;j++) {
            			if(model.getValueAt(j, 1).toString().equals(mail.getText())) {
            				cond=false;
            			}
            		}
	                if(i >= 0&&cond&&!mail.getText().trim().equals("")) 
	                {
	                   model.setValueAt(mail.getText(), i, 1);
	                   model.setValueAt(tag.getSelectedItem(), i, 2);
	                   mail.setText(" ".trim());
	                }
	                else{
	                    System.out.println("Update Error");
	                }
	            }
	        });
	        
	        resultTable = new JTable(); 
	        resultTable.setRowHeight(30);
	        resultTable.setFocusable(false);
	        
	        Object[] testCases = {"Test Case name","Test Case Number","Result"};
	        DefaultTableModel model1 = new DefaultTableModel() {
	        	@Override
	        	public boolean isCellEditable(int row, int column) {
	        		return false;
	        	}
	        };
	        model1.setColumnIdentifiers(testCases);
	        
//	        Map<String,String> test=new LinkedHashMap<String,String>();
//	        Map<String,String> result=new LinkedHashMap<String,String>();
//	        
//	        test.put("TC-001", "Login");
//	        test.put("TC-002", "Shopping");
//	        test.put("TC-003", "Payment");
//	        test.put("TC-004", "Logout");
//	        
//	        result.put("TC-001", "SKIP");
//	        result.put("TC-002", "PASS");
//	        result.put("TC-003", "FAIL");
//	        result.put("TC-004", "INFO");
//	        List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();
//	        listOfMaps.add(test);
//	        listOfMaps.add(result);
	        
	        Set<String> keyTest=listOfMaps.get(0).keySet();
	        Iterator<String> itr=keyTest.iterator();
	        
	        mailCode="<html><head><style>\r\n" + 
	        		"table, th, td {\r\n" + 
	        		"    border: 1px solid black;\r\n" + 
	        		"    border-collapse: collapse;\r\n" + 
	        		"}\r\n" + 
	        		"</style></head><body>\r\n"+
	        		"<p>Hi,</p>\r\n" +
	        		"<p>This is Automatic mail for Automation script</p><br>"+
	        		"<table style=\"width:100%\">\r\n" + 
	        		"  <tr>\r\n" + 
	        		"    <th>Testcase Name</th>\r\n" + 
	        		"    <th>Testcase Number</th> \r\n" + 
	        		"    <th>Status</th>\r\n" + 
	        		"  </tr>";
	        
	        while(itr.hasNext()) {
	        	String testkey=itr.next().toString();
	        	String name=(String)listOfMaps.get(0).get(testkey);
	        	String sta=(String)listOfMaps.get(1).get(testkey);
	        	System.out.println(testkey+" "+name+" "+sta);
	        	String color="style=\" color:orange\"";
	        	if(sta.equalsIgnoreCase("pass")) {
	        		p++;
	        		color="style=\" color:green\"";
	        	}
	        	else if(sta.equalsIgnoreCase("fail")) {
	        		f++;
	        		color="style=\" color:red\"";
	        	}
	        	else {
	        		o++;
	        	}
	        	model1.addRow(new Object[]{name, testkey,sta});
	        	mailCode=mailCode+"<tr>\r\n" + 
	        			"    <td>"+name+"</td>\r\n" + 
	        			"    <td>"+testkey+"</td>\r\n" + 
	        			"    <td "+color+">"+sta+"</td>\r\n" + 
	        			"  </tr>";
	        	
	        }
	        mailCode=mailCode+"</table><br>";
	        System.out.println(mailCode);
	        
	      
	        frame = new JFrame();
	        frame.setVisible(false);
	        frame.getContentPane().setLayout(null);
	        
	    	subLabel=new JLabel("Subject");
	    	
	    	subject=new JTextField("Automation mail");
	    	
	    	messageLabel=new JLabel("Message:");
	    	
	    	moreLabel=new JLabel("Your comment:");
	    	
	    	message=new JTextArea();
	    	
	    	pane2=new JScrollPane(more);
	    	
	    	yourEditorPane = new JEditorPane();
	    	yourEditorPane.setContentType("text/html");
	    	yourEditorPane.setEditable(false);

	    	//setup HTMLEditorKit 
	    	htmlEditorKit = new HTMLEditorKit();
	    	yourEditorPane.setEditorKit(htmlEditorKit);
	    	
	    	String htmlText = mailCode;
	    	Document doc = yourEditorPane.getDocument();
	    	yourEditorPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    	
	    	try {
	    		htmlEditorKit.insertHTML((HTMLDocument) doc, doc.getLength(), htmlText, 0, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	JScrollPane scrollPane = new JScrollPane(yourEditorPane);
	    	ok=new JButton("OK");
	    	ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.dispose();					
				}
	    		
	    	});
	    	
	    	frame.add(subLabel);
	    	frame.add(subject);
	    	frame.add(messageLabel);
	    	frame.add(pane2);
	    	frame.add(moreLabel);
	    	frame.add(ok);
	    	frame.add(scrollPane);
	        compose.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(status.equals("close")) {
					
			    	try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						e.printStackTrace();
					} 
			    	
			    	frame.setVisible(true);
			    	frame.setBounds((int)(0.25*panel.getWidth()), (int)(0.15*panel.getHeight()),(int)(0.5*panel.getWidth()), (int)(0.7*panel.getHeight()));
			    	
			    	
			    	frame.addComponentListener(new ComponentListener() {

						@Override
						public void componentHidden(ComponentEvent arg0) {
							
						}

						@Override
						public void componentMoved(ComponentEvent arg0) {
							
						}

						@Override
						public void componentResized(ComponentEvent arg0) {
							subLabel.setBounds((int)(0.05*frame.getWidth()), (int)(0.02*frame.getHeight()),(int)(0.25*frame.getWidth()), (int)(0.05*frame.getHeight()));
							subject.setBounds((int)(0.12*frame.getWidth()), (int)(0.02*frame.getHeight()),(int)(0.8*frame.getWidth()), (int)(0.05*frame.getHeight()));
							messageLabel.setBounds((int)(0.05*frame.getWidth()), (int)(0.1*frame.getHeight()),(int)(0.8*frame.getWidth()), (int)(0.05*frame.getHeight()));
							moreLabel.setBounds((int)(0.52*frame.getWidth()), (int)(0.1*frame.getHeight()),(int)(0.8*frame.getWidth()), (int)(0.05*frame.getHeight()));
							scrollPane.setBounds((int)(0.05*frame.getWidth()), (int)(0.15*frame.getHeight()),(int)(0.4*frame.getWidth()), (int)(0.65*frame.getHeight()));
							pane2.setBounds((int)(0.52*frame.getWidth()), (int)(0.15*frame.getHeight()),(int)(0.4*frame.getWidth()), (int)(0.65*frame.getHeight()));
							ok.setBounds((int)(0.38*frame.getWidth()), (int)(0.85*frame.getHeight()),(int)(0.22*frame.getWidth()), (int)(0.05*frame.getHeight()));
						}

						@Override
						public void componentShown(ComponentEvent arg0) {
							
						}
			    		
			    	});
			    	frame.addWindowListener(new WindowListener() {

						@Override
						public void windowActivated(WindowEvent arg0) {
							
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							status="close";
						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							status="close";
						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							
						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							
						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							
						}

						@Override
						public void windowOpened(WindowEvent arg0) {
							status="open";
						}
			    		
			    	});
					
				}
				}
	        	
	        });
	        
	        send.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Map<String,String> users=new HashMap<String,String>();
					String[] lines=more.getText().split("\\n");
					String words="";
					for(int t=0;t<lines.length;t++) {
						words=words+lines[t]+"<br>";
					}
					System.out.println(words);
					String text=mailCode+" <p> "+words+" </p></body></html>";
					System.out.println(text);
					
					if(condition()) {
						
					for(int i=0;i<table.getRowCount();i++) {
						users.put(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString());
						
					}
					System.out.println(users);
					List<String> attachments=new ArrayList<String>();
					if(table.getRowCount()!=0) {
						if(excel.isSelected()) {
							attachments.add((new ArrayList<String>(inputResult.values())).get(3));
						}
						if(html.isSelected()) {
							attachments.add((new ArrayList<String>(inputResult.values())).get(6));
							File file=new File((new ArrayList<String>(inputResult.values())).get(8));
							if(file.listFiles().length!=0) {
							attachments.add((new ArrayList<String>(inputResult.values())).get(8)+".zip");
							}
						}
						if(xml.isSelected()) {
							attachments.add((new ArrayList<String>(inputResult.values())).get(7));
						}
				SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
					@Override
				protected Void doInBackground() throws Exception {
					SendMail sm=new SendMail();
					out.setText("Status: In-progress. We will update you status soon");
					out.setForeground(Color.BLUE);
					boolean delivery=sm.mail(users, username.getText(), String.valueOf(password.getPassword()), smtp.getText(), port.getText(), subject.getText(), text,attachments);
					if(delivery) {
						  Date dNow = new Date( );
					      SimpleDateFormat ft =new SimpleDateFormat ("E dd-MM-yyyy 'at' hh:mm:ss a ");

						out.setText("Status: Mail sent successfully on "+ft.format(dNow));
						out.setForeground(Color.GREEN);
					}else {
						out.setText("Status: Mail didn't send. Please check your credentials and other options");
						out.setForeground(Color.RED);
					}
					return null;
					}
				};
			worker1.execute();
					System.out.println(yourEditorPane.getText());
					}
					
				}
				}
	        });
	        
	        inputLabel=new JLabel("<html>I<br>N<br>P<br>U<br>T</html>");
	        inputLabel.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
	        
	        outputLabel=new JLabel("<html>O<br>U<br>T<br>P<br>U<br>T</html>");
	        outputLabel.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
	        
	        mailLabel=new JLabel("<html>M<br>A<br>I<br>L</html>");
	        mailLabel.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
	        
	        consoleLabel=new JLabel("<html>C<br>O<br>N<br>S<br>O<br>L<br>E</html>");
	        consoleLabel.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
	        
	        parameters = new JTable(); 
	       
	        Object[] inputcolumns = {"Input Parameters","Values"};
	        DefaultTableModel inputmodel = new DefaultTableModel() {
	        	@Override
	        	public boolean isCellEditable(int row, int column) {
	        		return false;
	        	}
	        };
	        inputmodel.setColumnIdentifiers(inputcolumns);
	        inputmodel.setRowCount(0);
	        for (Map.Entry<?,?> entry : inputResult.entrySet()) {
	            inputmodel.addRow(new Object[] { entry.getKey(), entry.getValue() });
	        }
	      	        
	        parameters.setModel(inputmodel);
	        parameters.getTableHeader().setOpaque(false);
	        parameters.getTableHeader().setBackground(Color.BLACK);
	        parameters.getTableHeader().setForeground(Color.WHITE);
	        
	        parameters.setRowHeight(30);
	        parameters.setFocusable(false);
	   
	        // create JTextFields
	        scroll = new JScrollPane(parameters);
	        scroll.setBackground(Color.BLACK);
	        
	        paraName=new JLabel(" ");
	        
	        value=new JTextField(" ");
	        value.setBorder(null);
	        value.setBackground(input.getBackground());
	        value.setEditable(false);
	        
	        JScrollPane jsp=new JScrollPane(value);
	        jsp.setBorder(null);
	        jsp.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 5));
	        jsp.setBackground(input.getBackground());
	        
	        downloads=new JLabel("Reports  :");
	        downloads.setFont(new Font("Calibri",Font.BOLD,15));
	        
	        extent=new JLabel("<HTML><U>Extent Report</U></HTML>");
	        extent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        extent.setForeground(Color.BLUE);
	        Font font=new Font("Calibri",Font.BOLD,12);
	        extent.setFont(font);
	        
	        xmlLink=new JLabel("<HTML><U>XML Report</U></HTML>");
	        xmlLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        xmlLink.setForeground(Color.BLUE);
	        xmlLink.setFont(font);
	        
	        excelLink=new JLabel("<HTML><U>Excel Sheet</U></HTML>");
	        excelLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        excelLink.setForeground(Color.BLUE);
	        excelLink.setFont(font);
	        
	        parameters.addMouseListener(new MouseAdapter(){
		        
		        @Override
		        public void mouseClicked(MouseEvent e){
		            
		            // i = the index of the selected row
		            int i = parameters.getSelectedRow();
		            
		            paraName.setText(inputmodel.getValueAt(i, 0).toString()+"  :");
		            value.setText(inputmodel.getValueAt(i, 1).toString());
		        }
		        });
	        
	        extent.addMouseListener(new LinkClick(inputmodel.getValueAt(6, 1).toString()));
	        xmlLink.addMouseListener(new LinkClick(inputmodel.getValueAt(7, 1).toString()));
	        excelLink.addMouseListener(new LinkClick(inputmodel.getValueAt(3, 1).toString()));
	        
	        pass=new JLabel("Pass");
	        pass.setFont(new Font("Algerian", Font.BOLD, 12));
	        pass.setForeground(new Color(47, 148, 17));
	        
	        passPercent=new JLabel("Pass : "+String.valueOf((float)((p/(p+f+o))*100))+"%");
	        passPercent.setFont(pass.getFont());
	        passPercent.setForeground(pass.getForeground());
	        
	        fail=new JLabel("Fail");
	        fail.setFont(pass.getFont());
	        fail.setForeground(Color.RED);
	        
	        failPercent=new JLabel("Fail : "+String.valueOf((float)((f/(p+f+o))*100))+"%");
	        failPercent.setFont(pass.getFont());
	        failPercent.setForeground(fail.getForeground());
	        
	        others=new JLabel("Others");
	        others.setFont(pass.getFont());
	        others.setForeground(Color.BLUE);
	        
	        othersPercent=new JLabel("Others : "+String.valueOf((float)((o/(p+f+o))*100))+"%");
	        othersPercent.setFont(pass.getFont());
	        othersPercent.setForeground(others.getForeground());
	        
	        resultTable.setBorder(null);
	        resultTable.getTableHeader().setOpaque(false);
	        resultTable.getTableHeader().setBackground(Color.BLACK);
	        resultTable.getTableHeader().setForeground(Color.WHITE);
	        this.resultTable.setModel(model1);
	        
	        outScroll=new JScrollPane(this.resultTable);
	        outScroll.setBorder(BorderFactory.createEmptyBorder());
	        
	        output.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	        
	        consoleOutput=new JTextArea();
	        consoleOutput.setText("vasudha");
//	        Console wOut=new Console(consoleOutput);
//	        wOut.time.start();
	        consoleScroll=new JScrollPane(consoleOutput);
	        
	        home=new JButton("Home");
	        back=new JButton("Back");
	        
	        tab.add(pane);
	        tab.add(mail);
	        tab.add(tag);
	        tab.add(btnAdd);
	        tab.add(btnUpdate);
	        tab.add(btnDelete);
	        tab.add(username);
	        tab.add(password);
	        tab.add(uLabel);
	        tab.add(pLabel);
	        tab.add(smtpLabel);
	        tab.add(smtp);
	        tab.add(port);
	        tab.add(attachment);
	        tab.add(excel);
	        tab.add(html);
	        tab.add(xml);
	        tab.add(send);
	        tab.add(compose);
	        tab.add(out);
	        panel.add(tab);
	        input.add(downloads);
	        input.add(extent);
	        input.add(xmlLink);
	        input.add(excelLink);
	        input.add(scroll);
	        input.add(paraName);
	        input.add(jsp);
	        output.add(outScroll);
	        output.add(passPercent);
	        output.add(failPercent);
	        output.add(othersPercent);
	        consolePanel.add(consoleScroll);
	        panel.add(inputLabel);
	        panel.add(outputLabel);
	        panel.add(consoleLabel);
	        panel.add(mailLabel);
	        panel.add(input);
	        panel.add(output);
	        panel.add(consolePanel);
	        panel.add(home);
	        panel.add(back);
		panel.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				 System.out.println("Table");
				 
				tab.setBounds((int)(panel.getWidth()*0.5), (int)(panel.getHeight()*0.05), (int)(panel.getWidth()*0.425), (int)(panel.getHeight()*0.5));
				input.setBounds((int)(panel.getWidth()*0.05), (int)(panel.getHeight()*0.05), (int)(panel.getWidth()*0.425), (int)(panel.getHeight()*0.5));
				output.setBounds((int)(panel.getWidth()*0.05), (int)(panel.getHeight()*0.6), (int)(panel.getWidth()*0.425), (int)(panel.getHeight()*0.3));
				consolePanel.setBounds((int)(panel.getWidth()*0.5), (int)(panel.getHeight()*0.6), (int)(panel.getWidth()*0.425), (int)(panel.getHeight()*0.25));
				home.setBounds((int)(panel.getWidth()*0.6), (int)(panel.getHeight()*0.87), (int)(panel.getWidth()*0.075), (int)(panel.getHeight()*0.025));
				back.setBounds((int)(panel.getWidth()*0.74), (int)(panel.getHeight()*0.87), (int)(panel.getWidth()*0.075), (int)(panel.getHeight()*0.025));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				
			}
			
		});
	       tab.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				System.out.println("Going");
				pane.setBounds(0, 0, tab.getWidth(), (int)(tab.getHeight()*0.5));
				scroll.setBounds(0, 0, input.getWidth(), (int)(input.getHeight()*0.7));
				mail.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.52), (int)(tab.getWidth()*0.35), (int)(panel.getHeight()*0.025));
		        tag.setBounds((int)(tab.getWidth()*0.41), (int)(tab.getHeight()*0.52), (int)(tab.getWidth()*0.105), (int)(panel.getHeight()*0.025));
		        btnAdd.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.6), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        btnDelete.setBounds((int)(tab.getWidth()*0.21), (int)(tab.getHeight()*0.6), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        btnUpdate.setBounds((int)(tab.getWidth()*0.37), (int)(tab.getHeight()*0.6), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        uLabel.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.68), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        pLabel.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.76), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        smtpLabel.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.84), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        username.setBounds((int)(tab.getWidth()*0.15), (int)(tab.getHeight()*0.68), (int)(tab.getWidth()*0.37), (int)(panel.getHeight()*0.025));
		        password.setBounds((int)(tab.getWidth()*0.15), (int)(tab.getHeight()*0.76), (int)(tab.getWidth()*0.37), (int)(panel.getHeight()*0.025));
		        smtp.setBounds((int)(tab.getWidth()*0.15), (int)(tab.getHeight()*0.84), (int)(tab.getWidth()*0.2), (int)(panel.getHeight()*0.025));
		        port.setBounds((int)(tab.getWidth()*0.37), (int)(tab.getHeight()*0.84), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        attachment.setBounds((int)(tab.getWidth()*0.6), (int)(tab.getHeight()*0.52), (int)(tab.getWidth()*0.2), (int)(panel.getHeight()*0.025));
		        excel.setBounds((int)(tab.getWidth()*0.6), (int)(tab.getHeight()*0.6), (int)(tab.getWidth()*0.2), (int)(panel.getHeight()*0.025));
		        html.setBounds((int)(tab.getWidth()*0.6), (int)(tab.getHeight()*0.68), (int)(tab.getWidth()*0.2), (int)(panel.getHeight()*0.025));
		        xml.setBounds((int)(tab.getWidth()*0.6), (int)(tab.getHeight()*0.76), (int)(tab.getWidth()*0.2), (int)(panel.getHeight()*0.025));
		        compose.setBounds((int)(tab.getWidth()*0.6), (int)(tab.getHeight()*0.84), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        send.setBounds((int)(tab.getWidth()*0.8), (int)(tab.getHeight()*0.84), (int)(tab.getWidth()*0.15), (int)(panel.getHeight()*0.025));
		        out.setBounds((int)(tab.getWidth()*0.05), (int)(tab.getHeight()*0.91), (int)(tab.getWidth()*0.8), (int)(panel.getHeight()*0.025));
		        
		        paraName.setBounds((int)(input.getWidth()*0.05), (int)(input.getHeight()*0.9), (int)(input.getWidth()*0.4), (int)(input.getHeight()*0.04));
//		        paraName.setBounds((int)(input.getWidth()*0.3), (int)(input.getHeight()*0.75), (int)(input.getWidth()*0.4), (int)(input.getHeight()*0.04));
//		        valueLabel.setBounds((int)(input.getWidth()*0.05), (int)(input.getHeight()*0.85), (int)(input.getWidth()*0.4), (int)(input.getHeight()*0.04));
		        jsp.setBounds((int)(input.getWidth()*0.3), (int)(input.getHeight()*0.875), (int)(input.getWidth()*0.65), (int)(input.getHeight()*0.09));
		        downloads.setBounds((int)(input.getWidth()*0.05), (int)(input.getHeight()*0.75), (int)(input.getWidth()*0.2), (int)(input.getHeight()*0.11));
		        excelLink.setBounds((int)(input.getWidth()*0.3), (int)(input.getHeight()*0.75), (int)(input.getWidth()*0.2), (int)(input.getHeight()*0.1));
		        extent.setBounds((int)(input.getWidth()*0.55), (int)(input.getHeight()*0.75), (int)(input.getWidth()*0.2), (int)(input.getHeight()*0.1));
		        xmlLink.setBounds((int)(input.getWidth()*0.8), (int)(input.getHeight()*0.75), (int)(input.getWidth()*0.2), (int)(input.getHeight()*0.1));
		        inputLabel.setBounds((int)(panel.getWidth()*0.02), (int)(panel.getHeight()*0.0), (int)(panel.getWidth()*0.1), (int)(panel.getHeight()*0.55));
		        outputLabel.setBounds((int)(panel.getWidth()*0.02), (int)(panel.getHeight()*0.5), (int)(panel.getWidth()*0.1), (int)(panel.getHeight()*0.5));
		        mailLabel.setBounds((int)(panel.getWidth()*0.93), (int)(panel.getHeight()*0.0), (int)(panel.getWidth()*0.1), (int)(panel.getHeight()*0.55));
		        consoleLabel.setBounds((int)(panel.getWidth()*0.93), (int)(panel.getHeight()*0.5), (int)(panel.getWidth()*0.1), (int)(panel.getHeight()*0.5));
		        //		        pass.setBounds((int)(output.getWidth()*0.05), (int)(output.getHeight()*0.2), (int)(output.getWidth()*0.3), (int)(output.getHeight()*0.15));
		        passPercent.setBounds((int)(output.getWidth()*0.05), (int)(output.getHeight()*0.85), (int)(output.getWidth()*0.2), (int)(output.getHeight()*0.1));
//		        fail.setBounds((int)(output.getWidth()*0.05), (int)(output.getHeight()*0.5), (int)(output.getWidth()*0.3), (int)(output.getHeight()*0.15));
		        failPercent.setBounds((int)(output.getWidth()*0.4), (int)(output.getHeight()*0.85), (int)(output.getWidth()*0.2), (int)(output.getHeight()*0.1));
//		        others.setBounds((int)(output.getWidth()*0.05), (int)(output.getHeight()*0.8), (int)(output.getWidth()*0.3), (int)(output.getHeight()*0.15));
		        othersPercent.setBounds((int)(output.getWidth()*0.75), (int)(output.getHeight()*0.85), (int)(output.getWidth()*0.2), (int)(output.getHeight()*0.1));
		        outScroll.setBounds(1, 1, (int)(output.getWidth()-2), (int)(output.getHeight()*0.8));
		        
		        consoleScroll.setBounds(0, 0, consolePanel.getWidth(), consolePanel.getHeight());
		        TableColumnModel columnModel = table.getColumnModel();
		        columnModel.getColumn(0).setPreferredWidth((int)(tab.getWidth()*0.1));
		        columnModel.getColumn(1).setPreferredWidth((int)(tab.getWidth()*0.75));
		        columnModel.getColumn(2).setPreferredWidth((int)(tab.getWidth()*0.15));
		        
		        TableColumnModel columnModel2 = parameters.getColumnModel();
		        columnModel2.getColumn(0).setPreferredWidth((int)(input.getWidth()*0.3));
		        columnModel2.getColumn(1).setPreferredWidth((int)(input.getWidth()*0.7));
		        
		        TableColumnModel columnModel3 = resultTable.getColumnModel();
		        columnModel3.getColumn(0).setPreferredWidth((int)(tab.getWidth()*0.4));
		        columnModel3.getColumn(1).setPreferredWidth((int)(tab.getWidth()*0.4));
		        columnModel3.getColumn(2).setPreferredWidth((int)(tab.getWidth()*0.2));
		       
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				
			}
	    	   
	       });
	}
	public void reArrange(JTable table,DefaultTableModel model ) {
		int count=table.getRowCount();
		for(int i=0;i<count;i++) {
			model.setValueAt(i+1, i, 0);
		}
	}
	
	public boolean condition() {
		Boolean condition=false;
		if(username.getForeground()!=Color.GRAY && !username.getText().trim().equals("")) {
			if(password.getForeground()!=Color.GRAY&&!String.valueOf(password.getPassword()).trim().equals("")) {
				if(smtp.getForeground()!=Color.GRAY&&!smtp.getText().trim().equals("")) {
					if(port.getForeground()!=Color.GRAY&&!port.getText().trim().equals("")) {
						if(table.getRowCount()!=0) {
						condition=true;
						}
						else {
							condition=false;
							out.setText("Status: Didn't mention receivers");
							out.setForeground(Color.RED);
						}
					}
					else {
						condition=false;
						out.setText("Status: Port is not mentioned");
						out.setForeground(Color.RED);
					}
				}
				else {
					condition=false;
					out.setText("Status: SMTP is not mentioned");
					out.setForeground(Color.RED);
				}
			}
			else {
				condition=false;
				out.setText("Status: Password is not mentioned");
				out.setForeground(Color.RED);
			}
		}else {
			condition=false;
			out.setText("Status: Username is not mentioned");
			out.setForeground(Color.RED);
		}
		return condition;
	}
	
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        
        // create JFrame and JTable
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String console=getClass().getResource("/PropertiesBase/out.txt").toString();
			    	PrintStream out = new PrintStream(new File(console.substring(console.indexOf(":")+1)));
					System.setOut(out);
					System.out.println(new File(console.substring(console.indexOf(":")+1)).lastModified());
					Date date=new Date();
				    System.out.println(date.getTime());
					JFrame frame = new JFrame();
			        JPanel pan=new JPanel();
			        pan.setBackground(Color.WHITE);
			        pan.setLayout(null);
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			    	frame.setVisible(true);
			    	frame.setSize((int)(frame.getWidth()), (int)(0.999*frame.getHeight()));
			    	frame.getContentPane().setLayout(null);
			    	pan.setBounds(0, 0, 0,0);
			    	JButton but=new JButton("Click");
			    	but.setBounds(10, 10, 10, 10);
			        frame.getContentPane().add(pan);
			        frame.getContentPane().add(but);
			        JTableRow jt=new JTableRow();
//			       jt.tableRow(pan);
			      but.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						pan.setBounds(0, 0, (int)(frame.getWidth()), (int)(0.999*frame.getHeight()));
					}
			    	  
			      });
			        frame.setVisible(true);
			        frame.setResizable(false);
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//        
    }
}

class LinkClick implements MouseListener
{
	String value;
	public LinkClick(String value) {
		this.value=value;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(value.endsWith(".html")||value.endsWith(".xml")) {
			try {
				Desktop.getDesktop().browse(new File(value).toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(value.endsWith(".xlsx")||value.endsWith(".xls")||value.endsWith(".ods")) {
			try {
				Desktop.getDesktop().open(new File(value));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

