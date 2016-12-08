package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EInfoMenuItem;
import frame.GDrawingPanel;

public class GInfoMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;

	// components
	ActionHandler actionHandler;
	
	public GInfoMenu() {
		super(GConstants.INFOMENU_TITLE);
		actionHandler = new ActionHandler();
		for (EInfoMenuItem eMenuItem : EInfoMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	private void attribute(){
		
	}
	
	private void info(){
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EInfoMenuItem.attribute.name())) {
				attribute();
			}else if (event.getActionCommand().equals(EInfoMenuItem.info.name())) {
				info();
			}
		}
	}
}