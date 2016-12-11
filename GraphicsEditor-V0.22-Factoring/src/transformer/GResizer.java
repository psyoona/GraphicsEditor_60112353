package transformer;

import java.awt.Graphics2D;

import shapes.GShape;

public class GResizer extends GTransformer {

	public GResizer(GShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().setPoint(x, y);
		this.getShape().draw(g2D);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().draw(g2D);
		this.getShape().resize(x, y, g2D);
		this.getShape().draw(g2D);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().finish(x, y, g2D);
	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2D) {

	}
}