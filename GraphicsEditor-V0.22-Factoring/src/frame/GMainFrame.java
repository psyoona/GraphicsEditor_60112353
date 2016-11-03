package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GConstants;
import constants.GConstants.EMainFrame;

// MainFrame specialize JFrame
public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GMyMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;

	public GMainFrame() {
		// attribute initialization
		this.setTitle(GConstants.MAINFRAME_TITLE);
		this.setLocation(EMainFrame.X.getValue(), EMainFrame.Y.getValue());
		this.setSize(EMainFrame.W.getValue(), EMainFrame.H.getValue());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Component Creation & Registration
		this.menuBar = new GMyMenuBar();
		this.setJMenuBar(menuBar);
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// Set Association among Components
		this.toolBar.setDrawingPanel(drawingPanel);

		// Component Initialization
		toolBar.initialize();
		menuBar.initialize();
		drawingPanel.initialize();
	}
}
