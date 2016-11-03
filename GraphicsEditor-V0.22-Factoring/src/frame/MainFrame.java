package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.Constants;
import constants.Constants.EMainFrame;

// MainFrame specialize JFrame
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private MyMenuBar menuBar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;
	
	public MainFrame() {
		// attribute initialization
		this.setTitle(Constants.MAINFRAME_TITLE);
		this.setLocation(EMainFrame.X.getValue(), EMainFrame.Y.getValue());
		this.setSize(EMainFrame.W.getValue(), EMainFrame.H.getValue());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Component Creation & Registration
		this.menuBar = new MyMenuBar();
		this.setJMenuBar(menuBar);
		this.toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		this.drawingPanel = new DrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// set Association among components
		toolBar.setDrawingPanel(this.drawingPanel);
		// add Components
		
		
		// associations
		toolBar.setDrawingPanel(drawingPanel);		
	}
}
