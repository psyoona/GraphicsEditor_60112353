package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GLine extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	Line2D line;
	
	GSwap swap;
	
	private int x2, y2;

	public GLine(){
		super(EDrawingType.TP);
		line = new Line2D.Double();
		setShape(this.line);
		swap = new GSwap();
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
		g2D.draw(this.line);
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.line.setLine(x, y, x, y);
		swap.x1 = x;
		swap.y1 = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.draw(g2D);
		this.line.setLine(swap.x1, swap.y1, x, y);
		this.draw(g2D);
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(Vector<GShape> shapeVector, JPanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePointShape(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.getAnchors().draw(g2D, getShape().getBounds());
	}
}