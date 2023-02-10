package Application;

import java.awt.Component;

import javax.swing.JPanel;

public class ComponentsEnable {

	public void enablePanel(JPanel p,Boolean con) {
		for(Component c: p.getComponents()) {
			c.setEnabled(con);
		}
		
	}
	
	public void visible(Component c, Boolean con) {
		c.setVisible(con);
	}
	
	public void enable(Component c, Boolean con) {
		c.setEnabled(con);
	}
}
