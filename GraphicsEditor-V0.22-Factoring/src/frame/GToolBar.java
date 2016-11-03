package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GConstants.EToolBarButton;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	// association	
	private GDrawingPanel drawingPanel;
	public void setDrawingPanel(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public GToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		
		for(EToolBarButton eToolBarButton: EToolBarButton.values()){
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eToolBarButton.getIconName()));
			button.setSelectedIcon(new ImageIcon(eToolBarButton.getSelectedIconNames()));
			this.add(button);
			
			buttonGroup.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBarButton.toString());
		}
	}
	
	public void initialize() {
		JRadioButton button = (JRadioButton) this.getComponentAtIndex(EToolBarButton.rectangle.ordinal());
		button.doClick();
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			drawingPanel.setESelectedTool(
//			EToolBarButton.valueOf(e.getActionCommand()));
			
			drawingPanel.setSelectedShape(
					EToolBarButton.valueOf(e.getActionCommand()).getShape());
			
		}
	}
}
