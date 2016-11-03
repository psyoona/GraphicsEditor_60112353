package shapes;

import java.awt.Graphics2D;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EDrawingType;

public class GLine extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	private int x2, y2;

	public GLine(){
		super(EDrawingType.TP);
		this.setName(GConstants.LINE);
	}
		
	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public void draw(Graphics2D g2D){
		setX2(width+startX);
		setY2(height+startY);
		g2D.drawLine(startX, startY, this.getX2(), this.getY2());
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		startX = x;
		startY = y;
		finX = x;
		finY = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.width = finX - startX;
		this.height = finY - startY;
		this.draw(g2D);
		this.width = x - startX;
		this.height = y - startY;
		this.draw(g2D);
		finX = x;
		finY = y;
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		setWidth(x - startX);
		setHeight(y - startY);
		
	}
}