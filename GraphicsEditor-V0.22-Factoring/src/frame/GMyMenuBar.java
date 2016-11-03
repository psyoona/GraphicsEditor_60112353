package frame;

import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;
import menus.GInfoMenu;

public class GMyMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;

	public GMyMenuBar(){
		GFileMenu fileMenu = new GFileMenu();
		GEditMenu editMenu = new GEditMenu();
		GInfoMenu infoMenu = new GInfoMenu();
		
		this.add(fileMenu);
		this.add(editMenu);
		this.add(infoMenu);	
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}