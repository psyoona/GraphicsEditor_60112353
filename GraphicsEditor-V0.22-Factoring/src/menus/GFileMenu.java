package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;
import frame.GDrawingPanel;
import shapes.GShape;

public class GFileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	private File file;
	
	// association
	private GDrawingPanel drawingPanel;
	
	// components
	ActionHandler actionHandler;

	public GFileMenu(){
		super(GConstants.FILEMENU_TITLE);
		actionHandler = new ActionHandler();
		file = null;
		for (EFileMenuItem eMenuItem : EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void inputStream() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			drawingPanel.setShapeVector((Vector<GShape>)inputStream.readObject());
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	private void outputStream() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapeVector());
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize(GDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public int showOpenDialog(){
		JFileChooser fileChooser = new JFileChooser(new File("."));
		int result;
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("GraphicEditor", "gps");
		fileChooser.setFileFilter(filter);
		result = fileChooser.showOpenDialog(null);
		file = fileChooser.getSelectedFile();
		return result;
	}
	
	public int showSaveDialog(){
		JFileChooser fileChooser = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("GraphicEditor", "gps");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(null);
		File renameFile = fileChooser.getSelectedFile();
		String extension = ".gps";
		if(renameFile != null) {
			if(renameFile.getName().contains(extension))
				file = new File(renameFile.getName());
			else
				file = new File(renameFile.getName() + extension);
		}
		return result;	
	}
	
	private void open(){
		int result;
		saveOrNot();
		result = showOpenDialog();
		if(result == JOptionPane.OK_OPTION){
			inputStream();
			drawingPanel.repaint();
		}else{
			
		}
	}
	
	private void save(){
		int result = JOptionPane.OK_OPTION;
		if(drawingPanel.isDirty()) {
			if(file == null) {
				result = showSaveDialog();
			}
			if(result == JOptionPane.OK_OPTION) {
				drawingPanel.setDirty(false);
				outputStream();
			}
		}
	}
	
	private void saveAs(){
		int result = showSaveDialog();
		if(result == JOptionPane.OK_OPTION){
			outputStream();
		}
	}
	
	private void saveOrNot(){
		int result = 0;
		if(drawingPanel.isDirty()){
			result = JOptionPane.showConfirmDialog(null, GConstants.confirmMessage);
			if(result == JOptionPane.OK_OPTION){
				this.showSaveDialog();
				this.drawingPanel.setDirty(false);
				this.outputStream();
			}
		}
	}
	
	private void newItem(){
		int result;
		result = JOptionPane.showConfirmDialog(null, GConstants.confirmMessage, GConstants.paintTitle, 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		// 예 : 0 아니오 : 1 취소 : 2
		if(result == 0){
			this.save();
		}else if(result == 1){
			this.drawingPanel.setShapeVector();
			this.drawingPanel.repaint();
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
			System.exit(1);
		}
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EFileMenuItem.newItem.name())) {
				newItem();
			}else if (event.getActionCommand().equals(EFileMenuItem.open.name())) {
				open();
			}else if (event.getActionCommand().equals(EFileMenuItem.save.name())) {
				save();
			}else if (event.getActionCommand().equals(EFileMenuItem.saveAs.name())) {
				saveAs();
			}else if (event.getActionCommand().equals(EFileMenuItem.exit.name())) {
				exit();
			}else if (event.getActionCommand().equals(EFileMenuItem.close.name())) {
				exit();
			}
		}
	}
}