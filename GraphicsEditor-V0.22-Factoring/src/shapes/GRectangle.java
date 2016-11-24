package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GRectangle extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Rectangle rectangle;
	GSwap swap;

	public GRectangle(){
		super(EDrawingType.TP);
		this.rectangle = new Rectangle(0, 0, 0, 0);
		this.setShape(this.rectangle);
		swap = new GSwap();
	}
	
	private void changeDraw(Graphics2D g2D){
		swap.x2 = this.rectangle.width + this.rectangle.x;
		swap.y2 = this.rectangle.height + this.rectangle.y;
		
		if (this.rectangle.width < 0 && this.rectangle.height < 0) {
			rectangle.x = rectangle.width + swap.x1;
			rectangle.y = rectangle.height + swap.y1;
			this.rectangle.width = Math.abs(this.rectangle.width);
			this.rectangle.height = Math.abs(this.rectangle.height);

		} else if (this.rectangle.height < 0) {
			rectangle.y = rectangle.height + swap.y1;
			this.rectangle.height = Math.abs(this.rectangle.height);
			
		} else if (this.rectangle.width < 0) {			
			rectangle.x = rectangle.width + swap.x1;
			this.rectangle.width = Math.abs(this.rectangle.width);
		}
		
		// draw
		g2D.draw(this.rectangle);
	}
	
	public void draw(Graphics2D g2D){
		/*
		 * startX : ���� x��ǥ, startY : ���� y��ǥ
		 */
		changeDraw(g2D);
		if(this.bSelected == true){
			clickShape(0, 0, g2D);
		}
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.rectangle.setLocation(x, y);
		swap.x1 = x;
		swap.y1 = y;
		this.draw(g2D);

	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// erase Shape
		this.draw(g2D);
		this.rectangle.setSize(new Dimension
				(x - this.swap.x1, y - this.swap.y1));
		
		// redraw Shape
		this.draw(g2D);
	}


	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.getAnchors().draw(g2D, getShape().getBounds());
		this.setbSelected(true);
	}

	@Override
	public void init(Vector<GShape> shapeVector, JPanel panel) {
		
	}

	@Override
	public void changeCursor(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, this.getShape().getBounds());
	}

	@Override
	public void initResizing(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void keepResizing(int x, int y, Graphics2D g2D) {
		System.out.println("??");
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		case RR:
			break;
		case MM:
			break;
		}
	}

	@Override
	public void finishResizing(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2D) {
		setP1(x, y);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		//erase shape
		this.draw(g2D);
		this.rectangle.x += x - this.getP1().x;
		this.rectangle.y += y - this.getP1().y;
		
		//redraw Shape
		this.draw(g2D);
		this.setP1(x,y);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2D) {
		
	}
}
