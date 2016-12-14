package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import constants.GConstants.EDrawingType;

public class GHeart extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;

	private Rectangle rectangle;
	private int x, y, width, height;
	
	public GHeart(){
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	
	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
		this.x = x;
		this.y = y;
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {
	}

	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		width = x - this.x;
		height = y - this.y;
		//System.out.println("resize : " + width + ", " + height);
	}

	@Override
	public void move(int x, int y) {
		
	}

	@Override
	public void finish(int x, int y, Graphics2D g2D) {
		System.out.println("finish");
		int[] triangleX = {
	            x - 2*width/18,
	            x + width + 2*width/18,
	            (x - 2*width/18 + x + width + 2*width/18)/2};
	    int[] triangleY = { 
	            y + height - 2*height/3, 
	            y + height - 2*height/3, 
	            y + height };
	    g2D.fillOval(
	            x - width/12,
	            y, 
	            width/2 + width/6, 
	            height/2); 
	    g2D.fillOval(
	            x + width/2 - width/12,
	            y,
	            width/2 + width/6,
	            height/2);
	    g2D.fillPolygon(triangleX, triangleY, triangleX.length);
	}

	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GShape newClone() {
		// TODO Auto-generated method stub
		return null;
	}
}
