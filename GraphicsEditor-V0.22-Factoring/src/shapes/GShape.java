package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;

abstract public class GShape {
	protected Shape shape;
	protected boolean clickmode;
	
	private EDrawingType eDrawingType;
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	
	protected int x, y, width, height;
	protected int startX, startY, finX, finY;
	protected int[] xArray, yArray;
	protected String name;
	
	private GAnchors anchors;	
	public GAnchors getAnchors() { return anchors; }
	public void setAnchors(GAnchors anchors) { this.anchors = anchors; }
	
	public GShape(EDrawingType eDrawingType){
		this.clickmode = false;
		this.eDrawingType = eDrawingType;
		this.anchors = new GAnchors();
	}
	
	
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);	
	abstract public void draw(Graphics2D g2d);
	
	abstract public void init(Vector<GShape> shapeVector, JPanel panel);
	abstract public void changePointShape(int x, int y, Graphics2D g2D);
	abstract public void clickShape(int x, int y, Graphics2D g2D);
	
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean contains(int x, int y) {		
		return shape.contains(x, y);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	

	public int[] getyArray() {
		return yArray;
	}

	public void setyArray(int[] yArray) {
		this.yArray = yArray;
	}

	public int[] getxArray() {
		return xArray;
	}

	public void setxArray(int[] xArray) {
		this.xArray = xArray;
	}
}
