package Example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Small {
	public Small() throws InterruptedException {
		JFrame f= new JFrame();
		f.setSize(250,250);
		f.setVisible(true);
		f.getContentPane().setLayout(null);
		JButton b1,b2,b3;
		b1=new JButton("Pause");
		b1.setBounds(5, 5, 100, 20);
		b2=new JButton("Stop");
		b2.setBounds(5, 30, 100, 20);
		b3=new JButton("Resume");
		b3.setBounds(5, 55, 100, 20);
		f.getContentPane().add(b1);
		f.getContentPane().add(b2);
		f.getContentPane().add(b3);
		ThreadConcept c=new ThreadConcept();
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				c.waiting();
				System.out.println(c.status);
			}
			
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				c.resume();
			}
			
		});
		c.concept();
		c.t.start();
	}
public static void main(String[] args) throws InterruptedException {
		new Small();
			
	
}
}
