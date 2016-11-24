package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GEllipse extends GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	GSwap swap;
	Ellipse2D.Double ellipse;

	public GEllipse() {
		super(EDrawingType.TP);
		swap = new GSwap();
		ellipse = new Ellipse2D.Double(0, 0, 0, 0);
		setShape(this.ellipse);
	}

	private void changeDraw(Graphics2D g2D) {
		swap.x2 = (int) (this.ellipse.width + this.ellipse.x);
		swap.y2 = (int) (this.ellipse.height + this.ellipse.y);

		if (this.ellipse.width < 0 && this.ellipse.height < 0) {
			ellipse.x = ellipse.width + swap.x1;
			ellipse.y = ellipse.height + swap.y1;
			this.ellipse.width = Math.abs(this.ellipse.width);
			this.ellipse.height = Math.abs(this.ellipse.height);
			g2D.draw(this.ellipse);

		} else if (this.ellipse.height < 0) {
			ellipse.y = ellipse.height + swap.y1;
			this.ellipse.height = Math.abs(this.ellipse.height);
			g2D.draw(this.ellipse);

		} else if (this.ellipse.width < 0) {
			ellipse.x = ellipse.width + swap.x1;
			this.ellipse.width = Math.abs(this.ellipse.width);
			g2D.draw(this.ellipse);

		} else {
			g2D.draw(this.ellipse);
		}
	}

	public void draw(Graphics2D g2D) {
		changeDraw(g2D);
		if(this.bSelected == true){
			clickShape(0, 0, g2D);
		}
		// this.getAnchors().draw(g2D, this.shape.getBounds());
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.ellipse.setFrame(x, y, 0, 0);
		swap.x1 = x;
		swap.y1 = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.draw(g2D);
		this.ellipse.width = x - this.swap.x1;
		this.ellipse.height = y - this.swap.y1;
		this.draw(g2D);
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.getAnchors().draw(g2D, getShape().getBounds());
		this.bSelected = true;
	}

	@Override
	public void changeCursor(int x, int y, Graphics2D g2D) {

	}

	@Override
	public void init(Vector<GShape> shapeVector, JPanel panel) {

	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, getShape().getBounds());
	}

	@Override
	public void initResizing(int x, int y, Graphics2D g2D) {
		this.setP1(x, y);
		draw(g2D);
	}

	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {

	}

	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {

	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2D) {
		setP1(x, y);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		// swap 변수들은 temp의 기능만을 함
		// 그 외에 별도의 기능은 없음

		// erase shape
		this.draw(g2D);

		switch (this.getCurrentEAnchor()) {
		case NN:
			swap.y1 = this.ellipse.getY();
			this.ellipse.y = y;
			this.ellipse.height += swap.y1 - y;
			break;
		case NE:
			swap.y1 = this.ellipse.y;
			this.ellipse.y = y;
			this.ellipse.height += swap.y1 - y;
			this.ellipse.width = x - ellipse.x;
			break;
		case NW:
			swap.x1 = this.ellipse.x;
			swap.y1 = this.ellipse.y;
			this.ellipse.x = x;
			this.ellipse.y = y;
			this.ellipse.height += swap.y1 - y;
			this.ellipse.width += swap.x1 - x;
			break;
		case SS:
			this.ellipse.height = y - ellipse.y;
			break;
		case SE:
			this.ellipse.width = x - this.swap.x1;
			this.ellipse.height = y - this.swap.y1;
			break;
		case SW:
			swap.x1 = this.ellipse.x;
			this.ellipse.x = x;
			this.ellipse.width += swap.x1 - x;
			this.ellipse.height = y - ellipse.y;
			break;
		case EE:
			this.ellipse.width = x - ellipse.x;
			break;
		case WW:
			swap.x1 = this.ellipse.x;
			this.ellipse.x = x;
			this.ellipse.width += swap.x1 - x;
			break;
		case RR:
			break;
		case MM:
			this.ellipse.x += x - this.getP1().x;
			this.ellipse.y += y - this.getP1().y;
			break;
		}
		this.draw(g2D);
		this.setP1(x, y);

	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}
}
