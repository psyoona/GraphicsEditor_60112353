package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EEditMenuItem;
import frame.GDrawingPanel;
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
