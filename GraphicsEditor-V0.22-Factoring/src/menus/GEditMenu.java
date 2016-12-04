package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frame.GDrawingPanel;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	// association
	private GDrawingPanel drawingPanel;

	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		for (EEditMenuItem eMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
}
