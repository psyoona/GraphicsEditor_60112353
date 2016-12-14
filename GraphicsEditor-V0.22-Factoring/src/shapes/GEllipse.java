package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GEllipse extends GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	GSwap swap;
	Ellipse2D.Double ellipse;
	private Color lineColor;
	private Color fillColor;
	private boolean check;
	private void setCheck(boolean check){ this.check = check; }
	private boolean getCheck(){ return check; }

	// Constructor
	public GEllipse() {
		super(EDrawingType.TP, new Ellipse2D.Double(0,0,0,0));
		swap = new GSwap();
		this.ellipse = (Ellipse2D.Double)this.getShape();
		this.lineColor = Color.BLACK;
		this.fillColor = Color.WHITE;
		setCheck(false);
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		this.ellipse.setFrame(x, y, 0, 0);
		swap.setX1(x);
		swap.setY1(y);
	}
	
	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {}
	
	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
			this.ellipse.width = x - this.swap.getX1();
			this.ellipse.height = y - this.swap.getY1();
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			if(y > swap.getTempHeight()-1){ return ; }
			swap.setY1(this.ellipse.getY());
			this.ellipse.y = y;
			this.ellipse.height += swap.getY1() - y;
			break;
		case NE:
			if(x < ellipse.x || y > swap.getTempHeight()-1){ return ; }
			swap.setY1(this.ellipse.y);
			this.ellipse.y = y;
			this.ellipse.height += swap.getY1() - y;
			this.ellipse.width = x - ellipse.x;
			break;
		case NW:
			if(x > swap.getTempWidth() || y > swap.getTempHeight()){ return ; }
			swap.setX1(this.ellipse.x);
			swap.setY1(this.ellipse.y);
			this.ellipse.x = x;
			this.ellipse.y = y;
			this.ellipse.height += swap.getY1() - y;
			this.ellipse.width += swap.getX1() - x;
			break;
		case SS:
			if(y < ellipse.getY()) { return ; }
			this.ellipse.height = y - ellipse.y;
			break;
		case SE:
			if(x < ellipse.x || y < ellipse.y){ return ; }
			this.ellipse.width = x - this.swap.getX1();
			this.ellipse.height = y - this.swap.getY1();
			break;
		case SW:
			if(x > swap.getTempWidth() || y < ellipse.getY()){ return ; }
			swap.setX1(this.ellipse.x);
			this.ellipse.x = x;
			this.ellipse.width += swap.getX1() - x;
			this.ellipse.height = y - ellipse.y;
			break;
		case EE:
			if( x < ellipse.x ){ return ; }
			this.ellipse.width = x - ellipse.x;
			break;
		case WW:
			if( x > swap.getTempWidth() ){ return ; }
			swap.setX1(this.ellipse.x);
			this.ellipse.x = x;
			this.ellipse.width += swap.getX1() - x;
			break;
		default:
			break;
		}
		this.setPoint(x, y);
	}
	
	@Override
	public void finish(int x, int y, Graphics2D g2d) {
		swap.setTempHeight(ellipse.getY() + ellipse.getHeight());
		swap.setTempWidth(ellipse.getX() + ellipse.getWidth());
	}
	
	public void draw(Graphics2D g2D) {
		changeDraw(g2D);
		if(this.bSelected == true){
			activateAnchor(g2D);
		}
	}

	private void changeDraw(Graphics2D g2D) {
		swap.setX2((int) (this.ellipse.width + this.ellipse.x));
		swap.setY2((int) (this.ellipse.height + this.ellipse.y));

		if (this.ellipse.width < 0 && this.ellipse.height < 0) {
			ellipse.x = ellipse.width + swap.getX1();
			ellipse.y = ellipse.height + swap.getY1();
			this.ellipse.width = Math.abs(this.ellipse.width);
			this.ellipse.height = Math.abs(this.ellipse.height);
		} else if (this.ellipse.height < 0) {
			ellipse.y = ellipse.height + swap.getY1();
			this.ellipse.height = Math.abs(this.ellipse.height);
		} else if (this.ellipse.width < 0) {
			ellipse.x = ellipse.width + swap.getX1();
			this.ellipse.width = Math.abs(this.ellipse.width);
		} 
		if(getCheck()){
			g2D.setColor(fillColor);
			g2D.fillOval((int)ellipse.x, (int)ellipse.y, (int)ellipse.width, (int)ellipse.height);
		}
		g2D.setColor(lineColor);
		g2D.drawOval((int)ellipse.x, (int)ellipse.y, (int)ellipse.width, (int)ellipse.height);
	}

	@Override
	public void move(int x, int y) {
		this.ellipse.x += x - this.px;
		this.ellipse.y += y - this.py;
		this.setPoint(x, y);
		swap.setX1(ellipse.x);
		swap.setY1(ellipse.y);
	}
	
	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2D) {
		this.lineColor = lineColor;
		draw(g2D);
	}

	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2D) {
		this.fillColor = fillColor;
		setCheck(true);
		draw(g2D);
	}

	@Override
	public void activateAnchor(Graphics2D g2D) {
		this.getAnchors().draw(g2D, getShape().getBounds());
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
		return new GEllipse();
	}
}
