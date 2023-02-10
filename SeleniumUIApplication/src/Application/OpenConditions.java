package Application;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OpenConditions {

	public void open(JButton button,JLabel result) {
		if(result.getText().contains("Please select")||result.getText().equals("")) {
			button.setBorder(BorderFactory.createLineBorder(Color.RED, 2, false));
			JOptionPane.showMessageDialog(null, "Please select correct file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				Desktop.getDesktop().open(new File(result.getText()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
