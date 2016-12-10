package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
import constants.GConstants.EMainFrame;
import shapes.GGroup;
import shapes.GShape;
import sycom.GSwap;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	
	// object states
	private enum EState { idle, drawingTP, drawingNP, transforming };
	private EState eState;
	
	// components
	private Stack<Vector<GShape>> redo;
	private Stack<Vector<GShape>> undo;
	private Vector<GShape> groupList;
	private Vector<GShape> shapeVector;
	public void setShapeVector(){ this.shapeVector = new Vector<GShape>(); }
	public void setShapeVector(Vector<GShape> shapeVector){ this.shapeVector = shapeVector; }
	public Vector<GShape> getShapeVector() { return this.shapeVector; }
	private MouseEventHandler mouseEventHandler;
	private boolean bDirty;
	public boolean isDirty() { return bDirty; }
	public void setDirty(boolean bDirty) { this.bDirty = bDirty; }
	
	// associative attributes
	private GShape selectedShape;	
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public GSwap swap;
	Graphics2D g2D;
	JPanel panel;
	
	// working Objects;
	private GShape currentShape;
	private GTransformer currentTransformer;
	
	@SuppressWarnings("unchecked")
	public GDrawingPanel() {
		super();
		// attributes
		this.eState = EState.idle;		
		// components
		this.shapeVector = new Vector<GShape>();	
		this.mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		redo = new Stack<Vector<GShape>>();
		undo = new Stack<Vector<GShape>>();
		groupList = new Vector<GShape>();
		undo.add((Vector<GShape>) shapeVector.clone());
		
		swap = new GSwap();
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(EMainFrame.W.getValue(), EMainFrame.H.getValue()));
		this.add(panel);
		
		// working variables
		this.selectedShape = null;
		this.currentShape = null;
		this.currentTransformer = null;
		this.bDirty = false;
	}
	
	public void initialize() { }

	// 최소화 이후 도형 복원
	public void paint(Graphics g) {
		super.paint(g);
		//setBackground(Color.WHITE);
		for (GShape shape : this.shapeVector) {
			shape.draw((Graphics2D) g);
		}
	}
	
	public void resetSelected() {
		for (GShape shape: this.shapeVector) {
			shape.setbSelected(false);
		}
		this.repaint();
	}
	
	@SuppressWarnings("unchecked")
	public void redo() {
		if(redo.size() != 0) {
			undo.add((Vector<GShape>)shapeVector.clone());
			shapeVector = redo.pop();
			repaint();
		}
	}
	@SuppressWarnings("unchecked")
	public void undo() {
		if(undo.size() != 0) {
			redo.add((Vector<GShape>)shapeVector.clone());
			shapeVector = undo.pop();
			repaint();
		}
	}
	
	private void initTransforming(int x, int y){
		if (this.currentShape == null) {
			this.currentShape= this.selectedShape.clone();
			this.currentTransformer = new GDrawer(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.MM) {
			this.currentTransformer = new GMover(this.currentShape);
		} else if (this.currentShape.getCurrentEAnchor() == EAnchors.RR) {
			this.currentTransformer = new GRotator(this.currentShape);
		} else {			
			this.currentTransformer = new GResizer(this.currentShape);
		}
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		//this.currentTransformer.setGroupList(groupList);
		this.currentTransformer.initTransforming(x, y, g2D);
	}
	
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.keepTransforming(x, y, g2D);
	}
	
	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.continueTransforming(x, y, g2D);
	}
	
	@SuppressWarnings("unchecked")
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTransformer.finishTransforming(x, y, g2D);
		
		if (this.currentTransformer.getClass().getSimpleName().equals("GDrawer")) {
			undo.add((Vector<GShape>) shapeVector.clone());
			redo.clear();
			this.shapeVector.add(this.currentShape);
		}
		
		this.currentShape.setbSelected(true);
		this.repaint();
		this.setDirty(true);
	}
	
	public void group(GGroup group){
//		boolean check = false;
//		for(int i = shapeVector.size(); i > 0; i--){
//			GShape shape = shapeVector.get(i-1);
//			if(shape.getbSelected()){
//				shape.setbSelected(false);
//				group.add(shape);
//				shapeVector.remove(shape);
//				check = true;
//			}
//		}
//		
//		if(check){
//			group.setbSelected(true);
//			shapeVector.add(group);
//		}
//		groupList = group.getGroupList();
//		repaint();
	}
	
	public void ungroup(){
//		Vector<GShape>ungroupList = new Vector<>();
//		for(int i = shapeVector.size(); i > 0; i--){
//			GShape shape = shapeVector.get(i-1);
//			if(shape instanceof GGroup && shape.getbSelected()){
//				for(GShape childShape : this.groupList){
//					childShape.setbSelected(true);
//					ungroupList.add(childShape);
//				}
//				shapeVector.remove(shape);
//			}
//		}
//		shapeVector.addAll(ungroupList);
		repaint();
	}
	
	private GShape onShape(int x, int y){
		g2D = (Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		for (GShape shape: this.shapeVector) {
			EAnchors eAnchor = shape.contains(x, y, g2D);
			if (eAnchor != null)
				return shape;
		}
		return null;
	}
	
	private void changeCursor(GShape shape) {
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		this.setCursor(shape.getCurrentEAnchor().getCursor());
	}

	class MouseEventHandler implements MouseInputListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if (selectedShape.geteDrawingType()==EDrawingType.NP) {
						initTransforming(e.getX(), e.getY());
						eState = EState.drawingNP;
					}
				}
			} else if (eState == EState.drawingNP) {	
				continueTransforming(e.getX(), e.getY());			
			}
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eState == EState.drawingNP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}	
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if (selectedShape.geteDrawingType()==EDrawingType.TP) {
						initTransforming(e.getX(), e.getY());
						eState = EState.drawingTP;
					}
				} else {
					initTransforming(e.getX(), e.getY());
					eState = EState.transforming;
				}
			}	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			} else if (eState == EState.transforming) {
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			} 
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eState == EState.idle) {
				GShape shape = onShape(e.getX(), e.getY());
				changeCursor(shape);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eState == EState.transforming) {
				keepTransforming(e.getX(), e.getY());				
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}

	
}