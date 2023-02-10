package Application;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {
	JMenuBar menu;
	JMenu help;
	JMenuItem item1,item2;
public void menus(JFrame frame) {
	menu=new JMenuBar();
	help=new JMenu("Help");
	item1 =new JMenuItem("How to process");
	item2 =new JMenuItem("About Automation Application");
	help.add(item1);
	help.add(item2);
	menu.add(help);
	frame.setJMenuBar(menu);
}
}
