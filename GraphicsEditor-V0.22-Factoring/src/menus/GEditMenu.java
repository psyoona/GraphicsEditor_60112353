package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frame.GDrawingPanel;
import shapes.GGroup;
import shapes.GShape;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	// association
	private GDrawingPanel drawingPanel;
	
	// components
	ActionHandler actionHandler;
	private Vector<GShape> temps;
	private Vector<GShape> saves;

	public GEditMenu() {
		super(GConstants.EDITMENU_TITLE);
		actionHandler = new ActionHandler();
		temps = new Vector<GShape>();
		saves = new Vector<GShape>();
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
		saves.clear();
		for(GShape shape : this.drawingPanel.getShapeVector()){
			temps.add(shape);
		}
		for(GShape shape : temps) {
			if(shape.getbSelected()) {
				try {
					saves.add((GShape)shape.deepClone());
					this.drawingPanel.getShapeVector().remove(shape);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	      }
	      drawingPanel.repaint();
	}
	
	private void copy(){
		saves.clear();
		for(GShape s : this.drawingPanel.getShapeVector()){
			if(s.getbSelected()) {
				try {
					saves.add((GShape)s.deepClone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void paste(){
		drawingPanel.resetSelected();
		for(GShape s : saves) {
			try {
				s.setbSelected(true);
				this.drawingPanel.getShapeVector().add((GShape)s.deepClone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		drawingPanel.repaint();
	}
	
	private void delete(){
		for(GShape shape : this.drawingPanel.getShapeVector()){
			temps.add(shape);
		}
		for(GShape shape : temps) {
			if(shape.getbSelected()) {
				this.drawingPanel.getShapeVector().remove(shape);
			}
		}
	    drawingPanel.repaint();
	}
	
	private void redo(){
		drawingPanel.redo();
	}
	
	private void undo(){
		if(checkSize() == true){ return ; }
		drawingPanel.undo();
	}
	
	private void select(){
		if(checkSize() == true){ return ; }
		
		for(int i = 0; i < drawingPanel.getShapeVector().size(); i++){
			drawingPanel.getShapeVector().get(i).setbSelected(true);
		}
	}
	
	private void group(){
		this.drawingPanel.group(new GGroup());
	}
	
	private void ungroup(){
		this.drawingPanel.ungroup();
	}
	
	private boolean checkSize(){
		if(drawingPanel.getShapeVector().size() == 0){
			JOptionPane.showMessageDialog(drawingPanel, GConstants.NOTTHING, GConstants.NOTICE, 0);
			return true;
		}
		return false;
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
				redo();
			}else if (event.getActionCommand().equals(EEditMenuItem.undo.name())) {
				undo();
			}else if (event.getActionCommand().equals(EEditMenuItem.select.name())) {
				select();
			}else if (event.getActionCommand().equals(EEditMenuItem.group.name())) {
				group();
			}else if (event.getActionCommand().equals(EEditMenuItem.ungroup.name())) {
				ungroup();
			}
			drawingPanel.repaint();
		}
	}
}
