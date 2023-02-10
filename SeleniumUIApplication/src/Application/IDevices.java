package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class IDevices {
	JButton phone,system,back;
	JPanel devices,backPanel;
	JLabel photo,title,imageLoad;
	BufferedImage myPicture=null;
	public IDevices(JPanel panel) {
		devices=new JPanel();
		devices.setLayout(null);
		devices.setBounds((int) (0.35*panel.getWidth()), (int) (0.15*panel.getHeight()), (int) (0.3*panel.getWidth()), (int) (0.55*panel.getHeight()));
		devices.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		devices.setBackground(new Color(255,255,255));
		
		backPanel=new JPanel();
		backPanel.setLayout(null);
		backPanel.setBounds((int) (0.45*panel.getWidth()), (int) (0.75*panel.getHeight()), (int) (0.1*panel.getWidth()), (int) (0.05*panel.getHeight()));
		
		title=new JLabel("SELECT DEVICE",JLabel.CENTER);
		title.setFont(new Font("Andalus", Font.BOLD, 30));
		
		phone=new JButton("iPhone");
		phone.setBackground(new Color(128,0,0));
		phone.setForeground(new Color(128,0,0));
		phone.setFont(new Font("Narkisim", Font.BOLD, 20));
		phone.setFocusable(false);
		system=new JButton("Mac System");
		system.setBackground(new Color(0,0,0));
		system.setForeground(new Color(0,0,0));
		system.setFont(new Font("Narkisim", Font.BOLD, 20));
		system.setFocusable(false);
		back=new JButton("Back");
		back.setBackground(Color.RED);
		back.setForeground(Color.RED);
		back.setFont(new Font("Narkisim", Font.BOLD, 15));
		back.setFocusable(false);
		photo=new JLabel();
		
		imageLoad=new JLabel("Loading...");
		imageLoad.setFont(new Font("Narkisim", Font.BOLD, 10));
		
		devices.add(phone);
		devices.add(system);
		devices.add(title);
		backPanel.add(back);
		panel.add(devices);
		panel.add(backPanel);
		panel.add(photo);
		panel.add(imageLoad);
		panel.addComponentListener(new ComponentListener() {

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
				devices.setBounds((int) (0.35*panel.getWidth()), (int) (0.15*panel.getHeight()), (int) (0.3*panel.getWidth()), (int) (0.55*panel.getHeight()));
				backPanel.setBounds((int) (0.45*panel.getWidth()), (int) (0.75*panel.getHeight()), (int) (0.1*panel.getWidth()), (int) (0.05*panel.getHeight()));
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		devices.addComponentListener(new ComponentListener() {

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
				phone.setBounds((int) (0.1*devices.getWidth()), (int) (0.25*devices.getHeight()), (int) (0.8*devices.getWidth()), (int) (0.25*devices.getHeight()));
				system.setBounds((int) (0.1*devices.getWidth()), (int) (0.6*devices.getHeight()), (int) (0.8*devices.getWidth()), (int) (0.25*devices.getHeight()));
				title.setBounds((int) (0.1*devices.getWidth()), (int) (0.075*devices.getHeight()), (int) (0.8*devices.getWidth()), (int) (0.1*devices.getHeight()));
				photo.setBounds(0, 0, panel.getWidth(), panel.getHeight());
				imageLoad.setBounds((int)(0.01*panel.getWidth()), (int)(0.9*panel.getHeight()),
						(int)(0.1*panel.getWidth()), (int)(0.05*panel.getHeight()));
				SwingWorker<Void, Void> worker1 = new SwingWorker<Void, Void>() {
					   @Override
					   protected Void doInBackground() throws Exception {
					    // Simulate doing something useful.
				try {
					myPicture = ImageIO.read(new URL("https://macgui.com/upload/gallery/f_6/user_6121/regular/upload_3324.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					myPicture = ImageIO.read(getClass().getResource("/Images/apple.jpg"));
//					e.printStackTrace();
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
		
		backPanel.addComponentListener(new ComponentListener() {

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
				back.setBounds(0, 0, backPanel.getWidth(), backPanel.getHeight());
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
