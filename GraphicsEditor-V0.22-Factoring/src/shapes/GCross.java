package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;

public class GCross extends GShape {
	private Vector<GShape> shapeVector;
	Cursor hourglassCursor;
	Cursor normalCursor;
	JPanel panel;

	public GCross() {
		super(EDrawingType.CHOICE);
		hourglassCursor = new Cursor(Cursor.MOVE_CURSOR);
		normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
	}

	public void init(Vector<GShape> shapeVector, JPanel panel) {
		this.shapeVector = new Vector<GShape>();
		this.shapeVector = shapeVector;
		this.panel = panel;
	}

	public void changePointShape(int x, int y, Graphics2D g2D) {
		for (GShape shape : this.shapeVector) {
			if (shape.contains(x, y)) {
				panel.setCursor(hourglassCursor);
				break;
			} else {
				panel.setCursor(normalCursor);
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
	
	public void delPoint(int x, int y, Graphics2D g2D){
		// bSelected가 true이면 모두 false로 바꾼다.
		for(GShape shape : this.shapeVector){
			if(shape.bSelected == true){
				shape.clickShape(x, y, g2D);
				shape.bSelected = false;
			}
		}
	}
	
	public void activatePoint(int x, int y, Graphics2D g2D){
		for(GShape shape : this.shapeVector){
			if(shape.contains(x, y)){
				shape.clickShape(x, y, g2D);
				shape.bSelected = true;
				break;
			}
		}
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		delPoint(x, y, g2D);
		activatePoint(x, y, g2D);
		
//		for (GShape shape : this.shapeVector) {
//			if (shape.contains(x, y)) {
//				if(shape.bSelected == false){
//					shape.clickShape(x, y, g2D);
//					shape.bSelected = true;
//					//break;
//				}
//				//break;
//			} else {
//				if(shape.bSelected == true){
//					shape.clickShape(x, y, g2D);
//					shape.bSelected = false;
//				}
//			}
//		}
	}
}
