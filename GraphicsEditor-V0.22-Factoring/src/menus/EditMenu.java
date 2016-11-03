package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants;
import constants.Constants.EEditMenuItem;

public class EditMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public EditMenu() {
		super(Constants.EDITMENU_TITLE);
		for (EEditMenuItem eMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}
}
