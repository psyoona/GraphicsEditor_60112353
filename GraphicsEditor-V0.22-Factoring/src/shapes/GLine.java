package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GLine extends GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	private Line2D line;
	private GSwap swap;
	
	private Color lineColor;

	// Constructor
	public GLine() {
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
		swap = new GSwap();
		this.lineColor = Color.BLACK;
		this.fillColor = Color.WHITE;
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		line.setLine(x, y, x, y);
		swap.setX1(x);
		swap.setY1(y);
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2D) { }
	
	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
			this.line.setLine(swap.getX1(), swap.getY1(), x, y);
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			this.line.setLine(line.getX1(), y, line.getX2(), line.getY2());
			break;
		case NE:
			this.line.setLine(line.getX1(), y, x, line.getY2());
			break;
		case NW:
			this.line.setLine(x, y, line.getX2(), line.getY2());
			break;
		case SS:
			this.line.setLine(line.getX1(), line.getY1(), line.getX2(), y);
			break;
		case SE:
			this.line.setLine(line.getX1(), line.getY1(), x, y);
			break;
		case SW:
			this.line.setLine(x, line.getY1(), line.getX2(), y);
			break;
		case EE:
			this.line.setLine(line.getX1(), line.getY1(), x, line.getY2());
			break;
		case WW:
			this.line.setLine(x, line.getY1(), line.getX2(), line.getY2());
			break;
		case RR:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void finish(int x, int y, Graphics2D g2d) { }
	
	public void draw(Graphics2D g2D) {
		g2D.setColor(lineColor);
		g2D.draw(this.line);
		if (this.bSelected == true) {
			activateAnchor(g2D);
		}
	}
	
	@Override
	public void move(int x, int y) {
		this.line.setLine(line.getX1()+x-this.px, 
				line.getY1()+y - this.py, 
				line.getX2()+x - this.px, 
				line.getY2()+y - this.py);
		this.setPoint(x, y);
		
	}
	
	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2D) {
		this.lineColor = lineColor;
		draw(g2D);
	}

	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2D) { }

	@Override
	public void activateAnchor(Graphics2D g2D) {
		this.getAnchors().draw(g2D, getShape().getBounds());
	}
	
	public EAnchors contains(int x, int y, Graphics2D g2D) {
		for (int i=0; i<EAnchors.values().length-1; i++) {
			if(anchors.get(i).x-10 <= x && anchors.get(i).x+10 >= x &&
					anchors.get(i).y-10 <= y && anchors.get(i).y+10 >= y){
				this.currentEAnchor = EAnchors.values()[i];
				return this.currentEAnchor;
			} else if(contains(x, y)){
				this.currentEAnchor = EAnchors.MM;
				return this.currentEAnchor;
			} // if end
		} // for end
	
		return null;
	}

	public boolean contains(int x, int y) {
		int boxX = x - GConstants.LINE_BOX_SIZE / 2;
		int boxY = y - GConstants.LINE_BOX_SIZE / 2;

		int width = GConstants.LINE_BOX_SIZE;
		int height = GConstants.LINE_BOX_SIZE;

		if (line.intersects(boxX, boxY, width, height)) {
			return true;
		}
		return false;
	}

	@Override
	public void rotate(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GShape newClone() {
		// TODO Auto-generated method stub
		return new GLine();
	}
}