package shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GRectangle extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Rectangle rectangle;
	GSwap swap;
	
	private Color lineColor;
	private Color fillColor;
	private boolean check;
	private void setCheck(boolean check){ this.check = check; }
	private boolean getCheck(){ return check; }

	// Constructor
	public GRectangle(){
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
		swap = new GSwap();
		this.lineColor = Color.BLACK;
		this.fillColor = Color.WHITE;
		setCheck(false);
	}
	
	@Override
	public void setPoint(int x, int y){
		this.px = x;
		this.py = y;
	}
	
	public void setOrigin(int x, int y, Graphics2D g2D){
		this.rectangle.setLocation(x, y);
		swap.setX1(x);
		swap.setY1(y);
	}
	
	@Override
	public void addPoint(int x, int y, Graphics2D g2D) { }
	
	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
			this.rectangle.setSize(new Dimension
					(x - (int)this.swap.getX1(), y - (int)this.swap.getY1()));
			return;
		}else {
			
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			if(y > swap.getTempHeight()-1){ return ; }
			swap.setY1(this.rectangle.y);
			this.rectangle.y = y;
			this.rectangle.height += swap.getY1() - y;
			break;
		case NE:
			if(x < rectangle.x || y > swap.getTempHeight()-1){ return ; }
			swap.setY1(this.rectangle.y);
			this.rectangle.y = y;
			this.rectangle.height += swap.getY1() - y;
			this.rectangle.width = x - rectangle.x;
			break;
		case NW:
			if(x > swap.getTempWidth() || y > swap.getTempHeight()){ return ; }
			swap.setX1(this.rectangle.x);
			swap.setY1(this.rectangle.y);
			this.rectangle.x = x;
			this.rectangle.y = y;
			this.rectangle.height += swap.getY1() - y;
			this.rectangle.width += swap.getX1() - x;
			break;
		case SS:
			if(y < rectangle.getY()) { return ; }
			this.rectangle.height = y - rectangle.y;
			break;
		case SE:
			if(x < rectangle.x || y < rectangle.y){ return ; }
			this.rectangle.setSize(new Dimension
					(x - (int)this.swap.getX1(), y - (int)this.swap.getY1()));
			break;
		case SW:
			if(x > swap.getTempWidth() || y < rectangle.getY()){ return ; }
			swap.setX1(this.rectangle.x);
			this.rectangle.x = x;
			this.rectangle.width += swap.getX1() - x;
			this.rectangle.height = y - rectangle.y;
			break;
		case EE:
			if( x < rectangle.x ){ return ; }
			this.rectangle.width = x - rectangle.x;
			break;
		case WW:
			if( x > swap.getTempWidth() ){ return ; }
			swap.setX1(this.rectangle.x);
			this.rectangle.x = x;
			this.rectangle.width += swap.getX1() - x;
			break;
		case RR:
			//g2D.rotate();
			break;
		default:
			break;
		}
		this.setPoint(x, y);
	}

	@Override
	public void finish(int x, int y, Graphics2D g2D) {
		swap.setTempHeight(rectangle.getY() + rectangle.getHeight());
		swap.setTempWidth(rectangle.getX() + rectangle.getWidth());
	}
	
	public void draw(Graphics2D g2D){
		changeDraw(g2D);
		if(getbSelected() == true){
			clickShape(0, 0, g2D);
		}
	}
	
	private void changeDraw(Graphics2D g2D){
		swap.setX2(this.rectangle.width + this.rectangle.x);
		swap.setY2(this.rectangle.height + this.rectangle.y);
		if (this.rectangle.width < 0 && this.rectangle.height < 0) {
			rectangle.x = rectangle.width + (int)swap.getX1();
			rectangle.y = rectangle.height + (int)swap.getY1();
			this.rectangle.width = Math.abs(this.rectangle.width);
			this.rectangle.height = Math.abs(this.rectangle.height);
		} else if (this.rectangle.height < 0) {
			rectangle.y = rectangle.height + (int)swap.getY1();
			this.rectangle.height = Math.abs(this.rectangle.height);
		} else if (this.rectangle.width < 0) {			
			rectangle.x = rectangle.width + (int)swap.getX1();
			this.rectangle.width = Math.abs(this.rectangle.width);
		}
		if(getCheck()){
			g2D.setColor(fillColor);
			g2D.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		g2D.setColor(lineColor);
		g2D.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	@Override
	public void move(int x, int y) {
		this.rectangle.x += x - this.px;
		this.rectangle.y += y - this.py;
		this.setPoint(x, y);
		swap.setX1(rectangle.x);
		swap.setY1(rectangle.y);
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
	public void clickShape(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, this.getShape().getBounds());
	}
}
