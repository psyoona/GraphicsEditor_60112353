package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;
import frame.GDrawingPanel;

public class GFileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	GDrawingPanel drawingPanel;
	ActionHandler actionHandler;

	public GFileMenu(){
		super(GConstants.FILEMENU_TITLE);
		
		for (EFileMenuItem eMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
			actionHandler = new ActionHandler();
			menuItem.addActionListener(actionHandler);
		}
	}
	
	private void open(){
		  JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		            chooser.getSelectedFile().getName());
		    }
	}
	
	private void save(){
		  JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showSaveDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to save this file: " +
		            chooser.getSelectedFile().getName());
		    }
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("open")){
				open();
			}else if(event.getActionCommand().equals("save")){
				save();
			}
			
		}
		
	}
}