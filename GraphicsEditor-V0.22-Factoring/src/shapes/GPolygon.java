package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EDrawingType;

public class GPolygon extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Polygon polygon = new Polygon();
	
	int prevX, prevY, nextX, nextY;

	public GPolygon(){
		super(EDrawingType.NP);
		this.setName(GConstants.POLYGON);
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		polygon.addPoint(x, y);
		prevX = x;
		prevY = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		nextX = x;
		nextY = y;
		g2D.drawLine(prevX, prevY, nextX, nextY);
		polygon.addPoint(x, y);
		prevX = nextX;
		prevY = nextY;
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.draw(g2D);
	}

	@Override
	public void draw(Graphics2D g2D) {
		// TODO Auto-generated method stub
		g2D.drawPolygon(polygon);
		
	}
}