package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import constants.GConstants.EDrawingType;

public class GGroup extends GShape{
	private static final long serialVersionUID = 1L;
	
	private Vector<GShape> groupList;

	public GGroup(){
		super();
		this.shape = new Rectangle();
		groupList = new Vector<GShape>();
	}
	public GGroup(EDrawingType eDrawingType, Shape shape) {
		super(eDrawingType, shape);
		this.shape = new Rectangle();
		groupList = new Vector<GShape>();
	}

	public Vector<GShape> getGroupList(){
		return groupList;
	}

	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
	}
	
	public void add(GShape newShape){
		groupList.add(0, newShape);
		if(groupList.size() == 1){
			this.shape = newShape.getBounds();
		}else{
			this.shape = this.shape.getBounds().createUnion(
					(Rectangle2D) newShape.getBounds());
		}
	}
	
	public void draw(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		for(GShape shape : groupList){
			shape.draw(g2D);
		}
		if(this.getbSelected()){
			g2D.draw(this.shape);
			this.setbSelected(true);
			super.draw(g2D);
		}
	}
	
	public GShape newClone(){
		return new GGroup();
	}

	@Override
	public void setPoint(int x, int y) {
		
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {}

	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void move(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x - rectangle.x, y- rectangle.y);
	}
	@Override
	public void finish(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
