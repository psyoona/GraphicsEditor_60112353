package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import constants.GConstants.EDrawingType;

public class GHeart extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;

	private Rectangle rectangle;
	public GHeart(){
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	
	@Override
	public void setOrigin(int x, int y) {
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2d) {
		
	}

	@Override
	public void resize(int x, int y, Graphics2D g2d) {
		
	}

	@Override
	public void move(int x, int y) {
		
	}

	@Override
	public void finish(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
