package shapes;

import java.awt.Graphics2D;

import constants.GConstants.EDrawingType;

abstract public class GShape {
	private EDrawingType eDrawingType;
	protected int x, y, width, height;
	protected int startX, startY, finX, finY;
	protected int[] xArray, yArray;
	protected String name;
	
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	abstract public void draw(Graphics2D g2d);

	public EDrawingType geteDrawingType() {	return eDrawingType;}
	
	public GShape(EDrawingType eDrawingType){
		this.eDrawingType = eDrawingType;
	}
	
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
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
