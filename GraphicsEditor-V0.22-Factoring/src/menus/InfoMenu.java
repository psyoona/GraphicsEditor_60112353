package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants;
import constants.Constants.EInfoMenuItem;

public class InfoMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public InfoMenu() {
		super(Constants.INFOMENU_TITLE);

		for (EInfoMenuItem eMenuItem : EInfoMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}
}