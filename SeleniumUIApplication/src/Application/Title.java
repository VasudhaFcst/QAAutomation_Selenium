package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title {
	JPanel panel, imgPanel;
	JButton window=new JButton("Windows");
	JButton android=new JButton("Andriod");
	JButton ios=new JButton("iOS");
	JLabel logo = new JLabel("");
public Title(JPanel titlePanel) {
	Picture pic=new Picture();
	panel=new JPanel();
	imgPanel = new JPanel();
	imgPanel.setLayout(null);
	imgPanel.setBackground(new Color(255,228,225));
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
	android.setBackground(new Color(50,205,50));
	android.setForeground(new Color(50,205,50));
	android.setContentAreaFilled(true);
	ios.setBackground(new Color(255,140,0));
	ios.setForeground(new Color(255,140,0));
	ios.setContentAreaFilled(true);
	window.setFocusPainted(false);
	android.setFocusPainted(false);
	ios.setFocusPainted(false);
	panel.add(window);
	panel.add(android);
	panel.add(ios);
	panel.add(title);
	imgPanel.add(logo);
	imgPanel.setBounds((int) (0.05*titlePanel.getWidth()), (int) (0.05*titlePanel.getHeight()), (int) (0.2*titlePanel.getWidth()), (int) (0.15*titlePanel.getHeight()));
	panel.setBounds((int) (0.35*titlePanel.getWidth()), (int) (0.15*titlePanel.getHeight()), (int) (0.3*titlePanel.getWidth()), (int) (0.7*titlePanel.getHeight()));
	window.setBounds((int) (0.1*panel.getWidth()), (int) (0.2*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
	android.setBounds((int) (0.1*panel.getWidth()), (int) (0.45*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
	ios.setBounds((int) (0.1*panel.getWidth()), (int) (0.7*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
	logo.setBounds(0, 0, imgPanel.getWidth(), imgPanel.getHeight());
	if(imgPanel.getWidth() != 0) {
		logo.setIcon(new ImageIcon(pic.getImage(logo, getClass().getResource("/Images/logo.png"))));
	}
	title.setBounds((int) (0.1*panel.getWidth()), (int) (0*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
	titlePanel.add(panel);
	titlePanel.add(imgPanel);
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
		imgPanel.setBounds((int) (0.05*titlePanel.getWidth()), (int) (0.05*titlePanel.getHeight()), (int) (0.2*titlePanel.getWidth()), (int) (0.15*titlePanel.getHeight()));
		panel.setBounds((int) (0.35*titlePanel.getWidth()), (int) (0.15*titlePanel.getHeight()), (int) (0.3*titlePanel.getWidth()), (int) (0.7*titlePanel.getHeight()));
		window.setBounds((int) (0.1*panel.getWidth()), (int) (0.2*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
		android.setBounds((int) (0.1*panel.getWidth()), (int) (0.45*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
		ios.setBounds((int) (0.1*panel.getWidth()), (int) (0.7*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
		logo.setBounds(0, 0, imgPanel.getWidth(), imgPanel.getHeight());
		title.setBounds((int) (0.1*panel.getWidth()), (int) (0*panel.getHeight()), (int) (0.8*panel.getWidth()), (int) (0.2*panel.getHeight()));
		if(imgPanel.getWidth() != 0) {
			logo.setIcon(new ImageIcon(pic.getImage(logo, getClass().getResource("/Images/logo.png"))));
		}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
});
}
}

