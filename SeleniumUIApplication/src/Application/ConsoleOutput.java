package Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import AdditionalSetup.ExecutionPart;
import AdditionalSetup.SelectExecutionOption;

public class ConsoleOutput {
	Boolean isClicked=false;
	JToggleButton output;
	JPanel oPanel;
	
	JLabel oTitle;
	JPanel scrollpanel;
	public static JTextPane area;
	JScrollPane scroll;
	
@SuppressWarnings("unchecked")
public ConsoleOutput(JPanel platform) {
	
	output=new JToggleButton("Console");
	output.setBorder(null);
	output.setForeground(Color.WHITE);
	
	oPanel=new JPanel();
	oPanel.setLayout(null);
	oPanel.setBackground(Color.WHITE);
	oPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,false));
	
	oTitle=new JLabel("CONSOLE",JLabel.CENTER);
	oTitle.setFont(new Font("Narkisim", Font.BOLD, 20));
	oTitle.setForeground(Color.WHITE);
	oTitle.setBackground(Color.BLACK);
	oTitle.setOpaque(true);
	
	scrollpanel=new JPanel();
	
	area=new JTextPane();
	area.setEditable(false);
	area.setDragEnabled(true);
	scroll = new JScrollPane(area);  
	  
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	@SuppressWarnings("rawtypes")
	Map attributes = new Font("Calibri", Font.BOLD, 13).getAttributes();
	attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	output.setFont(new Font("Calibri", Font.BOLD, 13).deriveFont(attributes));
	output.setFocusable(false);
	
	oPanel.add(oTitle);
	scrollpanel.add(scroll);
	oPanel.add(scrollpanel);
	platform.add(output);
	platform.add(oPanel);
	
	output.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			if(output.isSelected()) {
				oPanel.setBounds((int)(0.55*platform.getWidth()), (int)(0.06*platform.getHeight()),
						(int)(0.4*platform.getWidth()), (int)(0.4*platform.getHeight()));
			}
			else {
				oPanel.setBounds((int)(0.65*platform.getWidth()), (int)(0.1*platform.getHeight()),
						(int)(0*platform.getWidth()), (int)(0*platform.getHeight()));
			}
		}
		
	});
	
	oPanel.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isClicked=true;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isClicked=true;
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isClicked=false;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isClicked=true;	
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isClicked=true;
		}
		
	});
	platform.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(output.isSelected() && !isClicked) {
				output.doClick();
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
		
	});
	
	oPanel.addComponentListener(new ComponentListener() {

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
			oTitle.setBounds((int)(0*oPanel.getWidth()), (int)(0*oPanel.getHeight()),
					(int)(oPanel.getWidth()), (int)(0.2*oPanel.getHeight()));
			scrollpanel.setBounds((int)(0.01*oPanel.getWidth()), (int)(0.21*oPanel.getHeight()),
					(int)(0.98*oPanel.getWidth()), (int)(0.78*oPanel.getHeight()));
			area.setBounds((int)(0.01*oPanel.getWidth()), (int)(0.21*oPanel.getHeight()),
					(int)(0.98*oPanel.getWidth()), (int)(0.78*oPanel.getHeight()));
			scroll.setBounds((int)(0*scrollpanel.getWidth()), (int)(0*scrollpanel.getHeight()),
					(int)(1*scrollpanel.getWidth()), (int)(1*scrollpanel.getHeight()));
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	oPanel.addMouseMotionListener(new MouseMotionAdapter() {

        @Override
        public void mouseDragged(MouseEvent me) {
            me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
            oPanel.setLocation(me.getX(), me.getY());
        }

    });
	
	platform.addComponentListener(new ComponentListener() {

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
			output.setBounds((int)(0.89*platform.getWidth()), (int)(0.02*platform.getHeight()),
					(int)(0.06*platform.getWidth()), (int)(0.04*platform.getHeight()));
			
			
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
			
	});
	
	
}

public static void addText(String text, Color color) throws BadLocationException {
	if(SelectExecutionOption.getSelectOption() == ExecutionPart.GUI) {
	StyledDocument doc = area.getStyledDocument();

//    Style style = area.addStyle("I'm a Style", null);
    SimpleAttributeSet attributes = new SimpleAttributeSet();
    StyleConstants.setForeground(attributes, color);
    StyleConstants.setFontSize(attributes, 14);
    StyleConstants.setSpaceAbove(attributes, 20);
    StyleConstants.setLeftIndent(attributes, 10);
    StyleConstants.setLineSpacing(attributes, 60);
    
    if(color == Color.BLACK) {
        attributes.addAttribute(StyleConstants.CharacterConstants.Bold, false);
    }
    else {
        attributes.addAttribute(StyleConstants.CharacterConstants.Bold, true);
    }
    doc.insertString(doc.getLength(), text, attributes);
}
}
}
