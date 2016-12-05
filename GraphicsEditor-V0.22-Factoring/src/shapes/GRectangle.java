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
		swap.x1 = x;
		swap.y1 = y;
	}
	
	public void setPoint(int x, int y){
		this.px = x;
		this.py = y;
	}
	
	@Override
	public void move(int x, int y) {
		this.rectangle.x += x - px;
		this.rectangle.y += y - py;
		this.setPoint(x, y);
	}
	
	private void changeDraw(Graphics2D g2D){
		swap.x2 = this.rectangle.width + this.rectangle.x;
		swap.y2 = this.rectangle.height + this.rectangle.y;
		
		if (this.rectangle.width < 0 && this.rectangle.height < 0) {
			rectangle.x = rectangle.width + (int)swap.x1;
			rectangle.y = rectangle.height + (int)swap.y1;
			this.rectangle.width = Math.abs(this.rectangle.width);
			this.rectangle.height = Math.abs(this.rectangle.height);

		} else if (this.rectangle.height < 0) {
			rectangle.y = rectangle.height + (int)swap.y1;
			this.rectangle.height = Math.abs(this.rectangle.height);
			
		} else if (this.rectangle.width < 0) {			
			rectangle.x = rectangle.width + (int)swap.x1;
			this.rectangle.width = Math.abs(this.rectangle.width);
		}
		
		// draw
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
					(x - (int)this.swap.x1, y - (int)this.swap.y1));
			return;
		}
		switch (this.getCurrentEAnchor()) {
		case NN:
			swap.y1 = this.rectangle.y;
			this.rectangle.y = y;
			this.rectangle.height += swap.y1 - y;
			break;
		case NE:
			swap.y1 = this.rectangle.y;
			this.rectangle.y = y;
			this.rectangle.height += swap.y1 - y;
			this.rectangle.width = x - rectangle.x;
			break;
		case NW:
			swap.x1 = this.rectangle.x;
			swap.y1 = this.rectangle.y;
			this.rectangle.x = x;
			this.rectangle.y = y;
			this.rectangle.height += swap.y1 - y;
			this.rectangle.width += swap.x1 - x;
			break;
		case SS:
			this.rectangle.height = y - rectangle.y;
			break;
		case SE:
			this.rectangle.setSize(new Dimension
					(x - rectangle.x, y - rectangle.y));
			break;
		case SW:
			swap.x1 = this.rectangle.x;
			this.rectangle.x = x;
			this.rectangle.width += swap.x1 - x;
			this.rectangle.height = y - rectangle.y;
			break;
		case EE:
			this.rectangle.width = x - rectangle.x;
			break;
		case WW:
			swap.x1 = this.rectangle.x;
			this.rectangle.x = x;
			this.rectangle.width += swap.x1-x;
			break;
		case RR:
			break;
		default:
			break;
		}
		this.setPoint(x, y);
	}

}
