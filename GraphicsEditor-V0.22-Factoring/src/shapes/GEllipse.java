package shapes;

import java.awt.Graphics2D;
import java.io.Serializable;

import constants.Constants;
import sycom.Swap;

public class GEllipse extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	Swap swap;
	
	public GEllipse(){
		this.setName(Constants.ELLIPSE);
		swap = new Swap();
	}
	
	public void draw(Graphics2D g2D, int startX, int startY, int width, int height){
		int x2 = width + startX;
		int y2 = height + startY;
		
		if (width <= 0 && height <= 0) {
			swap.x1 = startX;
			swap.x2 = x2;
			swap.y1 = startY;
			swap.y2 = y2;

			Swap.swapX(swap);
			Swap.swapY(swap);

			width = swap.x2 - swap.x1;
			height = swap.y2 - swap.y1;

			g2D.drawOval(swap.x1, swap.y1, width, height);

		} else if (height <= 0) {

			swap.y1 = startY;
			swap.y2 = y2;
			Swap.swapY(swap);
			height = swap.y2 - swap.y1;
			g2D.drawOval(startX, swap.y1, width, height);

		} else if (width <= 0) {
			swap.x1 = startX;
			swap.x2 = x2;
			Swap.swapX(swap);
			width = swap.x2 - swap.x1;
			g2D.drawOval(swap.x1, startY, width, height);

		} else {
			g2D.drawOval(startX, startY, width, height);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}

