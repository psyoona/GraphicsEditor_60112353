package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape {
	// attributes
	protected boolean bSelected;
	private Vector<GShape> shapeVector;
	public Vector<GShape> getShapeVector() { return shapeVector; }
	public void setShapeVector(Vector<GShape> shapeVector) { this.shapeVector = shapeVector; }
	
	private EDrawingType eDrawingType;
	public EDrawingType geteDrawingType() {	return eDrawingType; }
	protected int startX, startY;
	
	// components
	private Shape shape;
	
	public Shape getShape() { return shape;	}
	public void setShape(Shape shape) { this.shape = shape; }

	private GAnchors anchors;	
	public GAnchors getAnchors() { return anchors; }
	public void setAnchors(GAnchors anchors) { this.anchors = anchors; }
	
	public GShape(EDrawingType eDrawingType){
		this.bSelected = false;
		this.eDrawingType = eDrawingType;
		this.anchors = new GAnchors();
	}
	
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
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
	
	public EAnchors contains(int x, int y, Graphics2D g2D) {
		for (int i=0; i<EAnchors.values().length-1; i++) {
			if(anchors.get(i).x-10 <= x && anchors.get(i).x+10 >= x &&
					anchors.get(i).y-10 <= y && anchors.get(i).y+10 >= y){
				GConstants.EAnchors eAnchor = EAnchors.values()[i];
				return eAnchor; 
			} else if(shape.contains(x, y)){
				return GConstants.EAnchors.MM;
			} // if end
		} // for end
		return null;
	}
	
	public boolean contains(int x, int y) {
		return shape.contains(x, y);
	}
	
	public Rectangle getBounds(){
		return this.shape.getBounds();
	}
	
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);	
	abstract public void draw(Graphics2D g2d);	
	abstract public void init(Vector<GShape> shapeVector, JPanel panel);
	abstract public void changeCursor(int x, int y, Graphics2D g2D);
	abstract public void clickShape(int x, int y, Graphics2D g2D);
}
