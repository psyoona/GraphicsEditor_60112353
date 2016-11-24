package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape {
	// attributes
	private Vector<GShape> shapeVector;
	private EDrawingType eDrawingType;
	protected boolean bSelected;
	private EAnchors currentEAnchor;
	protected int startX, startY;
	
	// components
	private Shape shape;
	private GAnchors anchors;
	
	// working variables
	private Point p0, p1;
	
	// getters & setters
	public Vector<GShape> getShapeVector() { return shapeVector; }
	public void setShapeVector(Vector<GShape> shapeVector) { this.shapeVector = shapeVector; }
	public EDrawingType geteDrawingType() {	return eDrawingType; }
	public Shape getShape() { return shape;	}
	public void setShape(Shape shape) { this.shape = shape; }
	public GAnchors getAnchors() { return anchors; }
	public void setAnchors(GAnchors anchors) { this.anchors = anchors; }
	public EAnchors getCurrentEAnchor() { return currentEAnchor; }
	
	public Point getP0() {return p0; }
	public void setP0(int x, int y) { this.p0.x = x; this.p0.y = y;}
	public Point getP1() { return p1; }
	public void setP1(int x, int y) { this.p1.x = x; this.p1.y = y; }

	// Constructor
	public GShape(EDrawingType eDrawingType){
		this.bSelected = false;
		this.eDrawingType = eDrawingType;
		this.anchors = new GAnchors();
		this.currentEAnchor = null;
		
		this.p0 = new Point(0, 0);
		this.p1 = new Point(0, 0);
	}
	
	public boolean getbSelected() { return bSelected; }
	public void setbSelected(boolean bSelected) { this.bSelected = bSelected; }
	
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
				this.currentEAnchor = EAnchors.values()[i];
				return this.currentEAnchor;
			} else if(shape.contains(x, y)){
				this.currentEAnchor = EAnchors.MM;
				return this.currentEAnchor;
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
	
	abstract public void initTransforming(int x, int y, Graphics2D g2d);
	abstract public void keepTransforming(int x, int y, Graphics2D g2d);
	abstract public void finishTransforming(int x, int y, Graphics2D g2d);
	
	abstract public void initResizing(int x, int y, Graphics2D g2d);
	abstract public void keepResizing(int x, int y, Graphics2D g2d);
	abstract public void finishResizing(int x, int y, Graphics2D g2d);
}
