package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EColorMenuItem;
import frame.GDrawingPanel;

public class GColorMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	
	// association
	private GDrawingPanel drawingPanel;
	
	// components
	ActionHandler actionHandler;
	
	public GColorMenu() {
		super(GConstants.COLORMENU_TITLE);
		actionHandler = new ActionHandler();
		for (EColorMenuItem eMenuItem : EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getColor());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	
	private void fillColor(){
		Color fillColor = JColorChooser.showDialog(null, "Fill Color Selection", null);
		if(fillColor != null){
			this.drawingPanel.setFillColor(fillColor);
		}
	}
	
	private void lineColor(){
		Color lineColor = JColorChooser.showDialog(null, "Line Color Selection", null);
		if(lineColor != null){
			this.drawingPanel.setLineColor(lineColor);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(EColorMenuItem.fillColor.name())) {
				fillColor();
			}else if (event.getActionCommand().equals(EColorMenuItem.lineColor.name())) {
				lineColor();
				
			}
		}
	}
}
