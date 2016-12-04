package frame;

import javax.swing.JMenuBar;

import menus.GEditMenu;
import menus.GFileMenu;
import menus.GInfoMenu;

public class GMyMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	// components
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GInfoMenu infoMenu;
	
	// association
	private GDrawingPanel drawingPanel;

	public GMyMenuBar(){
		fileMenu = new GFileMenu();
		this.add(fileMenu);
		
		editMenu = new GEditMenu();
		this.add(editMenu);
		
		infoMenu = new GInfoMenu();
		this.add(infoMenu);	
	}

	public void initialize(GDrawingPanel drawingPanel) {
		// TODO Auto-generated method stub
		this.drawingPanel = drawingPanel;
		this.fileMenu.initialize(this.drawingPanel);
		this.editMenu.initialize(this.drawingPanel);
		this.infoMenu.initialize(this.drawingPanel);
	}
}