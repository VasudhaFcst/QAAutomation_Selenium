package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UnsupportedLookAndFeelException;

public class CountDown {
	
	JFrame loading=new JFrame();
	JDesktopPane desktop = new JDesktopPane();
	JProgressBar progress;
	JButton button,pause;
	JLabel label,note;
	int wait=0;
	String message="";
	JPanel panel,color;
	Picture pic=new Picture();
	public void circle(int k) {
		wait=k;
	}
	public void message(String s) {
		message=s;
	}
		@SuppressWarnings("serial")
		public void waiting(JFrame frame) throws MalformedURLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
			loading.getContentPane().setBackground(Color.WHITE);
			loading.setBounds((int)(0.3*frame.getWidth()),(int)(0.25*frame.getHeight()),(int)(0.4*frame.getWidth()),(int)(0.5*frame.getHeight()));
			loading.setVisible(true);
			loading.getContentPane().setLayout(null);
			
			panel=new JPanel() {
				@Override
				public void paint(Graphics g) {
					super.paint(g);
					Graphics2D g2=(Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.translate(this.getWidth()/2, this.getHeight()/2);
					g2.rotate(Math.toRadians(270));
					Arc2D.Float arc=new Arc2D.Float(Arc2D.PIE);
					Ellipse2D ellipse=new Ellipse2D.Float(0, 0, 53, 55);
					arc.setFrameFromCenter(new Point(0,0), new Point(60,60));
					ellipse.setFrameFromCenter(new Point(0,0), new Point(53,55));
					
					if(wait<=100) {
						arc.setAngleStart(-1);
					arc.setAngleExtent(-wait*3.6);
					g2.setColor(new Color(255-wait,0,0));
					}
					if(wait>100) {
						arc.setAngleStart(-1);
						arc.setAngleExtent(-(wait-100)*3.6);
						g2.setColor(new Color(0,255-wait,0));
					}
					g2.draw(arc);
					g2.fill(arc);
					g2.setColor(Color.WHITE);
					g2.draw(ellipse);
					g2.fill(ellipse);
					g2.rotate(Math.toRadians(90));
					g2.setColor(Color.BLACK);
					g.setFont(new Font("Verdana",Font.BOLD,13));
					FontMetrics fm=g2.getFontMetrics();
					Rectangle2D rect=fm.getStringBounds("Loading..", g);
//					int x=(0-(int)(rect.getWidth()))/2;
//					int y=(0-(int)(rect.getHeight()))/2+fm.getAscent();
					g2.drawString(message, (int)(-0.5*rect.getWidth()), (int)(-0.5*rect.getHeight()+10));
				}
			};
			color=new JPanel();
			color.setBounds(0, 0, loading.getWidth(), (int)(1*loading.getHeight()));
			color.setLayout(null);
			panel.setBounds(0, (int)(0.3*loading.getHeight()), loading.getWidth(), (int)(0.4*loading.getHeight()));
			panel.setLayout(null);
			panel.setBackground(new Color(230,211,211));
			
			label=new JLabel();
			label.setBounds((int)(0.6*loading.getWidth()), (int)(0*loading.getHeight()), (int)(0.3*loading.getWidth()), (int)(0.3*loading.getHeight()));
			
			button=new JButton("Stop");
			button.setBounds((int)(0.25*loading.getWidth()), (int)(0.8*loading.getHeight()), (int)(0.2*loading.getWidth()), (int)(0.1*loading.getHeight()));
			button.setBackground(Color.RED);
			button.setFocusable(false);
			button.setVisible(true);
			
			pause=new JButton("Pause");
			pause.setBounds((int)(0.55*loading.getWidth()), (int)(0.8*loading.getHeight()), (int)(0.2*loading.getWidth()), (int)(0.1*loading.getHeight()));
			pause.setBackground(Color.BLUE);
			pause.setFocusable(false);
			pause.setVisible(true);
			
			note=new JLabel("Note: If you want to stop execution wait for opens the browser and then click on Stop button",JLabel.CENTER);
			note.setForeground(Color.RED);
			note.setFont(new Font("Calibri",Font.BOLD,10));
			note.setBounds((int)(0.05*loading.getWidth()), (int)(0.83*loading.getHeight()), (int)(0.9*loading.getWidth()), (int)(0.075*loading.getHeight()));
			note.setVisible(true);
			
			loading.setVisible(true);
			loading.add(label);
			loading.add(button);
			loading.add(pause);
			loading.add(panel);
			color.add(note);
			loading.add(color);
			
			
//			desktop.add(loading);
			frame.addComponentListener(new ComponentListener() {

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
				loading.setBounds((int)(0.3*frame.getWidth()),(int)(0.25*frame.getHeight()),(int)(0.4*frame.getWidth()),(int)(0.5*frame.getHeight()));
				}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
				
		});
			
			loading.addComponentListener(new ComponentListener() {

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
					button.setBounds((int)(0.25*loading.getWidth()), (int)(0.7*loading.getHeight()), (int)(0.2*loading.getWidth()), (int)(0.05*loading.getHeight()));
					pause.setBounds((int)(0.55*loading.getWidth()), (int)(0.7*loading.getHeight()), (int)(0.2*loading.getWidth()), (int)(0.05*loading.getHeight()));
					label.setBounds((int)(0.6*loading.getWidth()), (int)(0*loading.getHeight()), (int)(0.3*loading.getWidth()), (int)(0.3*loading.getHeight()));
					panel.setBounds(0, (int)(0.3*loading.getHeight()), loading.getWidth(), (int)(0.4*loading.getHeight()));
					note.setBounds((int)(0.05*loading.getWidth()), (int)(0.83*loading.getHeight()), (int)(0.9*loading.getWidth()), (int)(0.075*loading.getHeight()));
					if(loading.getWidth()!=0)
					label.setIcon(new ImageIcon(pic.getImage(label, getClass().getResource("/Images/testing.png"))));
					else
						label.setIcon(null);
					}

				@Override
				public void componentShown(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
					
			});

			color.setBackground(new Color(230,211,211));
		loading.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loading.setResizable(false);
		}
		
}
