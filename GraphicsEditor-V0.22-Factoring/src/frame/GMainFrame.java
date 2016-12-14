package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GConstants;
import constants.GConstants.EMainFrame;

// MainFrame specialize JFrame
public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;

	// constructor
	public GMainFrame() {
		// attribute initialization
		this.setTitle(GConstants.MAINFRAME_TITLE);
		this.setLocation(EMainFrame.X.getValue(), EMainFrame.Y.getValue());
		this.setSize(EMainFrame.W.getValue(), EMainFrame.H.getValue());		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Component Creation & Registration
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// Component Initialization
		toolBar.initialize(this.drawingPanel);
		menuBar.initialize(this.drawingPanel);
		drawingPanel.initialize();
	}
}
