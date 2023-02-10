package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Resizing {
		static int r=0;
		JFrame titlePanel=new JFrame();
			public Resizing() throws MalformedURLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
				JPanel panel;
				titlePanel.getContentPane().setBackground(Color.PINK);
				titlePanel.setExtendedState(JFrame.MAXIMIZED_BOTH);
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				titlePanel.setVisible(true);
				titlePanel.getContentPane().setLayout(null);
				panel=new JPanel();
				JButton window=new JButton("Windows");
				JButton android=new JButton("Andriod");
				JButton ios=new JButton("iOS");
				panel.setBackground(new Color(255, 255, 255));
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,false));
				panel.setLayout(null);
				
				JLabel title=new JLabel("SELECT PLATFORM",JLabel.CENTER);
				title.setFont(new Font("Andalus", Font.BOLD, 30));
				window.setFont(new Font("Narkisim", Font.BOLD, 20));
				android.setFont(new Font("Narkisim", Font.BOLD, 20));
				ios.setFont(new Font("Narkisim", Font.BOLD, 20));
				window.setBackground(new Color(65,105,225));
				window.setForeground(new Color(65,105,225));
				window.setContentAreaFilled(true);
				
				window.setHorizontalTextPosition(AbstractButton.CENTER);
				window.setVerticalTextPosition(AbstractButton.BOTTOM);
				android.setBackground(new Color(50,205,50));
				android.setForeground(new Color(50,205,50));
				android.setContentAreaFilled(true);
				
				android.setHorizontalTextPosition(AbstractButton.CENTER);
				android.setVerticalTextPosition(AbstractButton.BOTTOM);
				ios.setBackground(new Color(255,140,0));
				ios.setForeground(new Color(255,140,0));
				ios.setContentAreaFilled(true);
				
				ios.setHorizontalTextPosition(AbstractButton.CENTER);
				ios.setVerticalTextPosition(AbstractButton.BOTTOM);
				ios.setFocusPainted(false);
				android.setFocusPainted(false);
				ios.setFocusPainted(false);
				panel.add(window);
				panel.add(android);
				panel.add(ios);
				panel.add(title);
				panel.setBounds((int) (0.1*titlePanel.getWidth()), (int) (0.25*titlePanel.getHeight()), (int) (0.8*titlePanel.getWidth()), (int) (0.5*titlePanel.getHeight()));
				window.setBounds((int)(0.02*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
				android.setBounds((int)(0.35*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
				ios.setBounds((int)(0.68*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
				
				title.setBounds((int) (0.1*panel.getWidth()), (int) (0*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
			if(panel.getWidth()!=0) {
				Image img = ImageIO.read(getClass().getResource("/Images/windows.png"));
				Image img1 = ImageIO.read(getClass().getResource("/Images/chrome.png"));
				Image img2 = ImageIO.read(getClass().getResource("/Images/ios.png"));
				window.setIcon(new ImageIcon(img));
				android.setIcon(new ImageIcon(img1));
				ios.setIcon(new ImageIcon(img2));
			}
			else {
				window.setIcon(null);
				android.setIcon(null);
				ios.setIcon(null);
			}
				titlePanel.add(panel);
			titlePanel.addComponentListener(new ComponentListener() {

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
					panel.setBounds((int) (0.1*titlePanel.getWidth()), (int) (0.25*titlePanel.getHeight()), (int) (0.8*titlePanel.getWidth()), (int) (0.5*titlePanel.getHeight()));
					window.setBounds((int)(0.02*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
					android.setBounds((int)(0.35*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
					ios.setBounds((int)(0.68*panel.getWidth()), (int)(0.1*panel.getHeight()), (int)(0.3*panel.getWidth()),(int)(0.8*panel.getHeight()));
					
					title.setBounds((int) (0.1*panel.getWidth()), (int) (0*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
				
				}

				@Override
				public void componentShown(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
					
			});
			titlePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
//			public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, InterruptedException {
//				JFrame frame=new JFrame();
//				frame.getContentPane().setBackground(Color.WHITE);
//				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				
//				frame.setVisible(true);
//				frame.getContentPane().setLayout(null);
//				CountDown cd=new CountDown();
//				cd.waiting(frame);
//				
//						new Thread(new Runnable() {
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								for(int i=0;i<=200;i++) {
//									cd.circle(i);
//									cd.panel.repaint();
//									if(i==200) {
//										i=0;
//									}
//									if(r==1) {
//										cd.message("Closing...");
//									}
//									else {
//										cd.message("Loading...");
//									}
//									try {
//										Thread.sleep(4);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//								}
//							}
//							
//						}).start();
//						
//						cd.button.addActionListener(new ActionListener() {
//
//							@Override
//							public void actionPerformed(ActionEvent arg0) {
//								// TODO Auto-generated method stub
//								r=1;
//							}
//							
//						});
//						frame.setContentPane(cd.desktop);
//						cd.desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
//						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				
//								
//			}
	}


