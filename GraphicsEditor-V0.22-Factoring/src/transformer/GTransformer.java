package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

import shapes.GShape;

abstract public class GTransformer {
	private GShape shape;
	public void setShape(GShape shape) {this.shape = shape;}
	protected GShape getShape() { return this.shape; }
	protected Vector<GShape> groupList;
	protected Point prevPoint;
	protected void setPrevPoint(Point p){
		prevPoint.x = p.x;
		prevPoint.y = p.y;
	}
	protected Point getPrevPoint(){
		return prevPoint;
	}
	
	public GTransformer(GShape shape) {
		this.shape = shape;
		prevPoint = new Point(0, 0);
	}
	public void setGroupList(Vector<GShape> groupList) {this.groupList = groupList;}
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
	public abstract void getAllShapes(Vector<GShape> shapes);
}