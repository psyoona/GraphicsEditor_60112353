package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GDrawer extends GTransformer {
	private Vector<GShape> shapeVector;
	
	public GDrawer(GShape shape) {
		super(shape);
	}
	public void initTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().setOrigin(x, y, g2D);
		this.getShape().draw(g2D);
	}
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().draw(g2D);
		this.getShape().resize(x, y, g2D);
		this.getShape().draw(g2D);
	}
	public void finishTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().finish(x, y, g2D);
	}
	public void continueTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().addPoint(x, y, g2D);
	}
	@Override
	public void getAllShapes(Vector<GShape> shapeVector) {
		this.shapeVector = shapeVector;
	}
}
