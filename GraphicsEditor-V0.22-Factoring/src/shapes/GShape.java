package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.Vector;

import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	// attributes
	private Vector<GShape> shapeVector;
	private EDrawingType eDrawingType;
	protected boolean bSelected;
	protected EAnchors currentEAnchor;
	protected int startX, startY;
	
	// components
	protected Shape shape;
	protected GAnchors anchors;
	protected AffineTransform affineTransform;
	
	// working variables
	protected int px, py;
	
	// getters & setters
	public Vector<GShape> getShapeVector() { return shapeVector; }
	public void setShapeVector(Vector<GShape> shapeVector) { this.shapeVector = shapeVector; }
	public EDrawingType geteDrawingType() {	return eDrawingType; }
	public Shape getShape() { return shape;	}
	public void setShape(Shape shape) { this.shape = shape; }
	public GAnchors getAnchors() { return anchors; }
	public void setAnchors(GAnchors anchors) { this.anchors = anchors; }
	public EAnchors getCurrentEAnchor() { return currentEAnchor; }
	
	// Constructor
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.bSelected = false;
		this.currentEAnchor = null;
		
		this.shape = shape;
		this.anchors = new GAnchors();
		
		this.px = this.py = 0;
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
	
	
	// methods
	public void draw(Graphics2D g2d) {
		g2d.draw(this.shape);
		if (this.bSelected) {
			this.anchors.draw(g2d, this.shape.getBounds());
		}
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
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y); 
	abstract public void resize(int x, int y, Graphics2D g2D);
	abstract public void move(int x, int y);
	
	public void clickShape(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}	
}
