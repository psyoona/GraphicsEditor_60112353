package shapes;

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

	public GRectangle(){
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
		swap = new GSwap();
	}
	
	public void setOrigin(int x, int y){
		this.rectangle.setLocation(x, y);
		swap.setX1(x);
		swap.setY1(y);
	}
	
	public void setPoint(int x, int y){
		this.px = x;
		this.py = y;
	}
	
	@Override
	public void move(int x, int y) {
		this.rectangle.x += x - this.px;
		this.rectangle.y += y - this.py;
		this.setPoint(x, y);
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
		g2D.draw(this.rectangle);
	}
	
	public void draw(Graphics2D g2D){
		changeDraw(g2D);
		if(getbSelected() == true){
			clickShape(0, 0, g2D);
		}
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, this.getShape().getBounds());
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {
		
	}

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
}
