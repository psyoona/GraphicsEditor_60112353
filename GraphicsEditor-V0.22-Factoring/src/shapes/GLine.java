package shapes;

import java.awt.Graphics2D;
import java.io.Serializable;

import constants.Constants;

public class GLine extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	private int x2, y2;

	public GLine(){
		this.setName(Constants.LINE);
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
	
	public void draw(Graphics2D g2D, int startX, int startY, int width, int height){
		setX2(getX() + getW());
		setY2(getY() + getH());
		g2D.drawLine(this.getX(), this.getY(), this.getX2(), this.getY2());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}