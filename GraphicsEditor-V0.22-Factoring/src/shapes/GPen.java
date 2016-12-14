package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GPen extends GShape{
	private static final long serialVersionUID = 1L;
	
	//private Color lineColor;
	private Line2D line;
	private GSwap swap;
	
	public GPen(){
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
		swap = new GSwap();
		//this.lineColor = Color.BLACK;
	}

	@Override
	public void setOrigin(int x, int y, Graphics2D g2d) {
		swap.setX1(x);
		swap.setY1(y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2d) {
		
	}

	@Override
	public void resize(int x, int y, Graphics2D g2d) {
		System.out.println("zz");
		this.px = x;
		this.py = y;
		// TODO Auto-generated method stub
		line.setLine(x, y, px, py);
//		vc.add(d);
		x = px;
		y = py;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(this.line);
	}

	@Override
	public void finish(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
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
	public void rotate(int x, int y) {
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

}
