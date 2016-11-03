package shapes;

import java.awt.Graphics2D;
import java.io.Serializable;

import constants.Constants;

public class GPolygon extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;

	public GPolygon(){
		this.setName(Constants.POLYGON);
	}
	
	public void draw(Graphics2D g2D, int startX, int startY, int width, int height){
		g2D.drawPolygon(xArray, yArray, xArray.length);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}