package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;
import frame.GDrawingPanel;

public class GFileMenu extends JMenu{
	private static final long serialVersionUID = 1L;

	// association
	private GDrawingPanel drawingPanel;
	
	// components
	ActionHandler actionHandler;

	public GFileMenu(){
		super(GConstants.FILEMENU_TITLE);
		actionHandler = new ActionHandler();
		for (EFileMenuItem eMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	
	public void initialize(GDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public String showDialog(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	return chooser.getSelectedFile().getName();
	    }
	    return null;	
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
		String fileName = showDialog();
		ObjectOutputStream outputStream;
		if (fileName == null) {
			return;
		}
		try {
			File file = new File(fileName);
			outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapeVector());
			outputStream.close();
		} catch( NullPointerException ne){
			System.out.println("File Save Null Pointer Exception!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void newItem(){
		int result;
		result = JOptionPane.showConfirmDialog(null, GConstants.confirmMessage, GConstants.paintTitle, 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		// 예 : 0 아니오 : 1 취소 : 2
		if(result == 0){
			save();
		}else if(result == 1){
			drawingPanel.setShapeVector();
			drawingPanel.repaint();
		}else{
			
		}
	}
	
	private void exit(){
		int result;
		result = JOptionPane.showConfirmDialog(null, GConstants.confirmMessage, GConstants.paintTitle, 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		// 예 : 0 아니오 : 1 취소 : 2
		if(result == 0){
			save();
		}else if(result == 1){
			System.exit(0);
		}
		
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EFileMenuItem.open.name())) {
				open();
			}else if (event.getActionCommand().equals(EFileMenuItem.save.name())) {
				save();
			}else if (event.getActionCommand().equals(EFileMenuItem.newItem.name())) {
				newItem();
			}else if (event.getActionCommand().equals(EFileMenuItem.exit.name())) {
				exit();
			}
		}
	}
}