package frame;

import javax.swing.JMenuBar;

import menus.EditMenu;
import menus.FileMenu;
import menus.InfoMenu;

public class MyMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;

	public MyMenuBar(){
		FileMenu fileMenu = new FileMenu();
		EditMenu editMenu = new EditMenu();
		InfoMenu infoMenu = new InfoMenu();
		
		this.add(fileMenu);
		this.add(editMenu);
		this.add(infoMenu);	
	}
}