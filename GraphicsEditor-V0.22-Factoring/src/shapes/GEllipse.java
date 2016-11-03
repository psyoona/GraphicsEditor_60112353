package shapes;

import java.awt.Graphics2D;
import java.io.Serializable;

import constants.GConstants;
import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GEllipse extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	GSwap swap;
	
	public GEllipse(){
		super(EDrawingType.TP);
		this.setName(GConstants.ELLIPSE);
		swap = new GSwap();
	}
	
	public void draw(Graphics2D g2D){
		int x2 = width + startX;
		int y2 = height + startY;
		
		if (width <= 0 && height <= 0) {
			swap.x1 = startX;
			swap.x2 = x2;
			swap.y1 = startY;
			swap.y2 = y2;

			GSwap.swapX(swap);
			GSwap.swapY(swap);

			width = swap.x2 - swap.x1;
			height = swap.y2 - swap.y1;

			g2D.drawOval(swap.x1, swap.y1, width, height);

		} else if (height <= 0) {

			swap.y1 = startY;
			swap.y2 = y2;
			GSwap.swapY(swap);
			height = swap.y2 - swap.y1;
			g2D.drawOval(startX, swap.y1, width, height);

		} else if (width <= 0) {
			swap.x1 = startX;
			swap.x2 = x2;
			GSwap.swapX(swap);
			width = swap.x2 - swap.x1;
			g2D.drawOval(swap.x1, startY, width, height);

		} else {
			g2D.drawOval(startX, startY, width, height);
		}
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		startX = x;
		startY = y;
		finX = x;
		finY = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.width = finX - startX;
		this.height = finY - startY;
		this.draw(g2D);
		this.width = x - startX;
		this.height = y - startY;
		this.draw(g2D);
		finX = x;
		finY = y;
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
//		setWidth(x - startX);
//		setHeight(y - startY);
	}
}

