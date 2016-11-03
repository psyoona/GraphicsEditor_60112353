package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants.EDrawingType;
import constants.GConstants.EMainFrame;
import constants.GConstants.EToolBarButton;
import shapes.GEllipse;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;
import sycom.GSwap;

public class GDrawingPanel extends JPanel {
	private static enum EState {
		idle, drawing
	};

	// attributes
	private static final long serialVersionUID = 1L;

	// working variables
	private Vector<GShape> shapeVector = new Vector<GShape>();
	
	// working Objects;
	private GShape currentShape;

	// associative attributes
	private GShape selectedShape;
	private EState eState;

	public GSwap swap;
	Graphics2D g2D;
	JPanel panel;

	public GDrawingPanel() {
		eState = EState.idle;
		swap = new GSwap();

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(EMainFrame.W.getValue(), EMainFrame.H.getValue()));
		this.add(panel);

		MouseEventHandler mouseEventHandler = new MouseEventHandler();

		this.add(mouseEventHandler);
		this.addMouseListener(mouseEventHandler);
		// 마우스가 움직이는 것을 보여주기 위한 부분
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void setSelectedShape(GShape selectedShape){
		this.selectedShape =  selectedShape;
	}
	
	// 최소화 이후 도형 복원
	public void paint(Graphics g) {
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g);
		}
	}
	
	public void setESelectedTool(EToolBarButton eToolBarButton) {
		
		switch (eToolBarButton) {
		case rectangle:
			this.currentShape = new GRectangle();
			break;
		case ellipse:
			this.currentShape = new GEllipse();
			break;
		case line:
			this.currentShape = new GLine();
			break;
		case polygon:
			this.currentShape = new GPolygon();
			break;
		default:
			break;
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

	class MouseEventHandler extends JComponent implements MouseInputListener, MouseMotionListener {

		private static final long serialVersionUID = 1L;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (selectedShape.geteDrawingType() == EDrawingType.NP) {
				if(eState == EState.idle){
					eState = EState.drawing;
					initDrawing(e.getX(), e.getY());
				}else if(eState == EState.drawing){
					keepDrawing(e.getX(), e.getY());
				}

				if (e.getClickCount() == 2) {
					// 더블클릭 한 경우, 폴리곤 완성
					finishDrawing(e.getX(), e.getY());
					eState = EState.idle;
				}
				
			// 선택된 도형이 NP타입이 아닌 경우
			} else {
				if (eState == EState.idle) {
					eState = EState.drawing;
					initDrawing(e.getX(), e.getY());
				} else if (eState == EState.drawing) {
					// 최종 그림을 완성하는 부분
					finishDrawing(e.getX(), e.getY());
					eState = EState.idle;
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if (selectedShape.geteDrawingType() == EDrawingType.NP) {
				
			// 선택된 도형이 NP타입이 아닌 경우
			} else {
				if (eState == EState.idle) {
					initDrawing(e.getX(), e.getY());
					eState = EState.drawing;
				} else if (eState == EState.drawing) {
					keepDrawing(e.getX(), e.getY());
//					System.out.println("mouse dragged");
				}

			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			if (selectedShape.geteDrawingType() == EDrawingType.NP) {
				
			// 선택된 도형이 NP타입이 아닌 경우
			} else {
				if (eState == EState.drawing) {
//					System.out.println("mouse moved");
					keepDrawing(e.getX(), e.getY());
				}
			}
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
