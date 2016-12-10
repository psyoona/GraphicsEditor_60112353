package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GShape;

public class GRotator extends GTransformer {
	public GRotator(GShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	private double computeRotationAngle(Point startP, Point previousP, Point currentP) {
		double startAngle = Math.toDegrees(
				Math.atan2(startP.getX()-previousP.getX(), startP.getY()-previousP.getY()));
		double endAngle = Math.toDegrees(
				Math.atan2(startP.getX()-currentP.getX(), startP.getY()-currentP.getY()));
		double angle = startAngle-endAngle;
		if (angle<0) angle += 360;
		return angle;
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2D) {
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.getShape().draw(g2D);
		this.getShape().resize(x, y, g2D);
		this.getShape().draw(g2D);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub

	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub

	}

}
