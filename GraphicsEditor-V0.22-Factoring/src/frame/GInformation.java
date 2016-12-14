package frame;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import constants.GConstants;

public class GInformation extends JFrame{
	private static final long serialVersionUID = 1L;

	public GInformation(GDrawingPanel drawingPanel, String title, boolean boo){
		this.setTitle(title);
		setLayout(new GridLayout(4,1));
		setLocation(400,300);
		Label label1 = new Label(GConstants.CLASSNAME);
		Label label2 = new Label(GConstants.PERIOD);
		Label label3 = new Label(GConstants.MADE);
		Button button = new Button(GConstants.OKMSG);
		
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
