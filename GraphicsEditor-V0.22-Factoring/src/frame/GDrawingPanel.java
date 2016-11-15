package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants.EDrawingType;
import constants.GConstants.EMainFrame;
import shapes.GShape;
import sycom.GSwap;

public class GDrawingPanel extends JPanel {
	private enum EState {
		idleTP, idleNP, drawingTP, drawingNP, move
	};

	// attributes
	private static final long serialVersionUID = 1L;

	// working variables
	private Vector<GShape> shapeVector = new Vector<GShape>();;

	// working Objects;
	private GShape currentShape;

	// associative attributes
	private GShape selectedShape;
	private EState eState;

	public GSwap swap;
	Graphics2D g2D;
	JPanel panel;
	

	
	Cursor hourglassCursor;
	Cursor normalCursor;

	public GDrawingPanel() {
		//shapeVector = new Vector<GShape>();
		hourglassCursor = new Cursor(Cursor.MOVE_CURSOR);
		normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
 
		swap = new GSwap();

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(EMainFrame.W.getValue(), EMainFrame.H.getValue()));
		
		this.add(panel);
		// repaint error 방지
		this.setOpaque(true);

		MouseEventHandler mouseEventHandler = new MouseEventHandler();

		// this.add(mouseEventHandler);
		this.addMouseListener(mouseEventHandler);
		// 마우스가 움직이는 것을 보여주기 위한 부분
		this.addMouseMotionListener(mouseEventHandler);
	}

	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
		switch (this.selectedShape.geteDrawingType()) {
			case TP: eState = EState.idleTP;
			break;
			case NP: eState = EState.idleNP;
			break;
			case CHOICE: eState = EState.move; 
			break;
		}
	}

	// 최소화 이후 도형 복원
	public void paint(Graphics g) {
		for (GShape shape : this.shapeVector) {
			shape.draw((Graphics2D) g);
		}
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.selectedShape.clone();
		g2D = (Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		this.currentShape.initDrawing(x, y, g2D);
	}

	private void keepDrawing(int x, int y) {
		g2D = (Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		this.currentShape.keepDrawing(x, y, g2D);
	}

	private void finishDrawing(int x, int y) {
		g2D = (Graphics2D) getGraphics();
		if (selectedShape.geteDrawingType() != EDrawingType.NP) {
			g2D.setXORMode(getBackground());
		}
		this.currentShape.finishDrawing(x, y, g2D);
		this.shapeVector.add(this.currentShape);
	}

	private void changePointShape(int x, int y) {
		try{
			g2D = (Graphics2D) getGraphics();
			this.selectedShape.init(shapeVector, panel);
			this.selectedShape.changeCursor(x, y, g2D);
		}catch(NullPointerException ne){
			
		}
	}
	
	private void clickShape(int x, int y){
		try{
			g2D = (Graphics2D) getGraphics();
			g2D.setXORMode(getBackground());
			this.selectedShape.clickShape(x, y, g2D);
		}catch(NullPointerException ne){
			
		}
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
			if (eState == EState.idleNP) {
				initDrawing(e.getX(), e.getY());
				eState = EState.drawingNP;
			} else if (eState == EState.drawingNP) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eState == EState.drawingNP) {
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleNP;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idleTP) {
				initDrawing(e.getX(), e.getY());
				eState = EState.drawingTP;
			}else if(eState == EState.move){
				clickShape(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP) {
				//keepDrawing(e.getX(), e.getY());
			} else if (eState == EState.move) {
				changePointShape(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepDrawing(e.getX(), e.getY());
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