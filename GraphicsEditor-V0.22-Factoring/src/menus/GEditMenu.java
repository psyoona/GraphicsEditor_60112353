package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frame.GDrawingPanel;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	// association
	private GDrawingPanel drawingPanel;
	
	// components
	ActionHandler actionHandler;

	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		actionHandler = new ActionHandler();
		for (EEditMenuItem eMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	
	private void cut(){
		
	}
	
	private void copy(){
		
	}
	
	private void paste(){
		
	}
	
	private void delete(){
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EEditMenuItem.cut.name())) {
				cut();
			}else if (event.getActionCommand().equals(EEditMenuItem.copy.name())) {
				copy();
			}else if (event.getActionCommand().equals(EEditMenuItem.paste.name())) {
				paste();
			}else if (event.getActionCommand().equals(EEditMenuItem.delete.name())) {
				delete();
			}else if (event.getActionCommand().equals(EEditMenuItem.redo.name())) {
				
			}else if (event.getActionCommand().equals(EEditMenuItem.undo.name())) {
				
			}else if (event.getActionCommand().equals(EEditMenuItem.group.name())) {
				
			}else if (event.getActionCommand().equals(EEditMenuItem.ungroup.name())) {
				
			}
		}
	}
}
