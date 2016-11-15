package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constants.GConstants;
import constants.GConstants.EDrawingType;

public class GCross extends GShape {
	private Vector<GShape> shapeVector;
	Cursor hourglassCursor;
	Cursor normalCursor;
	JPanel panel;
	public GCursorList cursorList;

	public GCross() {
		super(EDrawingType.CHOICE);
		hourglassCursor = new Cursor(Cursor.MOVE_CURSOR);
		normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		cursorList = new GCursorList();
		
	}

	public void init(Vector<GShape> shapeVector, JPanel panel) {
		this.shapeVector = new Vector<GShape>();
		this.shapeVector = shapeVector;
		this.panel = panel;
	}

//	public void changePointShape(int x, int y, Graphics2D g2D) {
//		for (GShape shape : this.shapeVector) {
//			if (shape.contains(x, y)) {
//				panel.setCursor(cursorList.getRecursiveCursor()); 
//				break;
//			} else {
//				panel.setCursor(normalCursor);
//			}
//		}
//	}
	
	public void changeCursor(int x, int y, Graphics2D g2D){
		for(GShape shape : this.shapeVector){
			GConstants.EAnchors eAnchor = shape.contains(x, y, g2D);
			if(eAnchor != null){
				switch(eAnchor){
				case NN: panel.setCursor(cursorList.getnResizeCursor()); break;
				case NE: panel.setCursor(cursorList.getNeResizeCursor()); break;
				case NW: panel.setCursor(cursorList.getNwResizeCursor()); break;
				case SS: panel.setCursor(cursorList.getsResizeCursor()); break;
				case SW: panel.setCursor(cursorList.getSwResizeCursor());break;
				case SE: panel.setCursor(cursorList.getSeResizeCursor()); break;
				case EE: panel.setCursor(cursorList.geteResizeCursor()); break;
				case WW: panel.setCursor(cursorList.getwResizeCursor()); break;
				case RR: panel.setCursor(cursorList.getRecursiveCursor()); break;
				case MM: panel.setCursor(cursorList.getMoveCursor()); break;
				default: break;
				}
			}else{
				panel.setCursor(cursorList.getDefaultCursor());
			}
		}
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		for (GShape shape : this.shapeVector) {
			if (shape.contains(x, y)) {
				if(shape.bSelected == false){
					shape.clickShape(x, y, g2D);
					shape.bSelected = true;
					//break;
				}
				//break;
			} else {
				if(shape.bSelected == true){
					shape.clickShape(x, y, g2D);
					shape.bSelected = false;
				}
			}
		}
	}
}
