package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EInfoMenuItem;
import frame.GDrawingPanel;

public class GInfoMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	// association
	private GDrawingPanel drawingPanel;

	public GInfoMenu() {
		super(GConstants.INFOMENU_TITLE);

		for (EInfoMenuItem eMenuItem : EInfoMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
}