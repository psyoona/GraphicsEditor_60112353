package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants.EMainFrame;
import constants.Constants.EToolBarButton;
import shapes.GEllipse;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;
import sycom.Swap;

public class DrawingPanel extends JPanel {
	private static enum EState {
		idle, drawing
	};

	// attributes
	private static final long serialVersionUID = 1L;

	// working variables
	private Vector<Integer> xPoints, yPoints;
	private int[] xArray, yArray;
	private int startX, startY, finX, finY;
	public GShape figure;
	private Vector<GShape> figureVector = new Vector<GShape>();

	// associateive attributes
	public enum ESelectedTool {
		rectangle, ellipse, line, polygon
	};

	public ESelectedTool eSelectedTool;
	private EState eState = EState.idle;

	public Swap swap;
	Graphics2D g2D;

	public DrawingPanel() {
		swap = new Swap();
		xPoints = new Vector<Integer>();
		yPoints = new Vector<Integer>();

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(EMainFrame.W.getValue(), EMainFrame.H.getValue()));
		this.add(panel);

		MouseEventHandler mouseEventHandler = new MouseEventHandler();

		this.add(mouseEventHandler);
		this.addMouseListener(mouseEventHandler);
		// 마우스가 움직이는 것을 보여주기 위한 부분
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void setESelectedTool(EToolBarButton eToolBarButton) {
		
		switch (eToolBarButton) {
		case rectangle:
			eSelectedTool = ESelectedTool.rectangle;
			this.figure = new GRectangle();
			break;
		case ellipse:
			eSelectedTool = ESelectedTool.ellipse;
			this.figure = new GEllipse();
			break;
		case line:
			eSelectedTool = ESelectedTool.line;
			this.figure = new GLine();
			break;
		case polygon:
			eSelectedTool = ESelectedTool.polygon;
			this.figure = new GPolygon();
			break;
		default:
			break;
		}
	}
	
	private void initDrawing(int x, int y) {
		startX = x;
		startY = y;
		finX = x;
		finY = y;
//		figure.initDrawing(x, y, g2D);
	}

	private void keepDrawing(int x, int y) {
		draw(startX, startY, finX - startX, finY - startY);
		draw(startX, startY, x - startX, y - startY);

		finX = x;
		finY = y;
		
//		g2D = (Graphics2D) getGraphics();
//		g2D.setXORMode(getBackground());
//		figure.keepDrawing(x, y, g2D);
	}

	private void finishDrawing(int x, int y) {
		if (eState == EState.idle) {
			draw(startX, startY, x - startX, y - startY);

		}

		figure.setX(startX);
		figure.setY(startY);
		figure.setW(x - startX);
		figure.setH(y - startY);
		figureVector.add(figure);
	}

	private void drawPolyLine(int x, int y) {
		// TODO Auto-generated method stub
		finX = x;
		finY = y;

		// 중간 중간 그림 그리는 부분
		g2D = (Graphics2D) getGraphics();
		g2D.drawLine(startX, startY, finX, finY);

		startX = finX;
		startY = finY;
	}

	private void draw(int startX, int startY, int x2, int y2) {
		/*
		 * x1 : 시작 x좌표, y1 : 시작 y좌표 x2 : 끝 x좌표 - 시작 x좌표, y2 : 끝 y좌표 - 시작 y좌표
		 */
		g2D = (Graphics2D) getGraphics();
		x2 += startX;
		y2 += startY;
		int width = x2 - startX;
		int height = y2 - startY;

		// 저장된 정보를 그리는 곳
		for (int i = 0; i < figureVector.size(); ++i) {
			GShape figure = (GShape) figureVector.elementAt(i);
			figure.draw(g2D, figure.getX(), figure.getY(), figure.getW(), figure.getH());
		}

		if (this.eSelectedTool == ESelectedTool.rectangle) {
			g2D.setXORMode(getBackground());
			figure = new GRectangle();
			figure.draw(g2D, startX, startY, width, height);

		} else if (this.eSelectedTool == ESelectedTool.ellipse) {
			g2D.setXORMode(getBackground());
			figure = new GEllipse();
			figure.draw(g2D, startX, startY, width, height);

		} else if (this.eSelectedTool == ESelectedTool.line) {
			g2D.setXORMode(getBackground());
			figure = new GLine();
			g2D.drawLine(startX, startY, x2, y2);

		} else if (this.eSelectedTool == ESelectedTool.polygon) {
			figure = new GPolygon();
			xArray = new int[xPoints.size()];
			yArray = new int[yPoints.size()];

			for (int i = 0; i < xPoints.size(); i++) {
				xArray[i] = xPoints.get(i);
				yArray[i] = yPoints.get(i);
			}

			g2D.drawPolygon(xArray, yArray, xArray.length);

			figure.setxArray(xArray);
			figure.setyArray(yArray);
			figureVector.add(figure);

			xPoints.clear();
			yPoints.clear();

			eState = EState.idle;
		}
	}

	class MouseEventHandler extends JComponent implements MouseInputListener, MouseMotionListener {

		private static final long serialVersionUID = 1L;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (eSelectedTool == ESelectedTool.polygon) {
				 if(xPoints.contains(e.getX()) && yPoints.contains(e.getY())){
				 // 이전에 클릭한 적이 있는 점
				
				 }else{
					 xPoints.add(e.getX());
					 yPoints.add(e.getY());
				
				 }
				if (e.getClickCount() == 2) {
					// 더블클릭 한 경우, 폴리곤 완성
					draw(startX, startY, finX, finY);
				}
			} else {
				if (eState == EState.idle) {
					eState = EState.drawing;
					initDrawing(e.getX(), e.getY());
				} else if (eState == EState.drawing) {
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
			if (eSelectedTool == ESelectedTool.polygon) {
				if (eState == EState.idle) {
					eState = EState.drawing;
					initDrawing(e.getX(), e.getY());
				} else if (eState == EState.drawing) {
					drawPolyLine(e.getX(), e.getY());
				}
			} else {
				// if(eState == EState.drawing){
				// eState = EState.idle;
				//
				// }
			}
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
			if (eSelectedTool == ESelectedTool.polygon) {
				finX = e.getX();
				finY = e.getY();
			} else {
				if (eState == EState.idle) {
					initDrawing(e.getX(), e.getY());
					eState = EState.drawing;
				} else if (eState == EState.drawing) {
					keepDrawing(e.getX(), e.getY());
				}

			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			if (eSelectedTool == ESelectedTool.polygon) {

			} else {
				if (eState == EState.drawing) {
					keepDrawing(e.getX(), e.getY());
				}
			}
		}

	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
