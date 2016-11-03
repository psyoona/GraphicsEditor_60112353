package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants;
import constants.Constants.EFileMenuItem;

public class FileMenu extends JMenu{
	private static final long serialVersionUID = 1L;

	public FileMenu(){
		super(Constants.FILEMENU_TITLE);
		for (EFileMenuItem eMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}
	}
}