package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;

public class GPolygon extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Polygon polygon;
	
	int prevX, prevY, nextX, nextY;

	public GPolygon(){
		super(EDrawingType.NP);
		this.polygon = new Polygon();
		setShape(this.polygon);
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		polygon.addPoint(x, y);
		// 초기 좌표 2개
		startX = x;
		startY = y;
		
		// 폴리 라인을 그리기 위한 prev x, y 좌표
		prevX = x;
		prevY = y;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		nextX = x;
		nextY = y;
		g2D.drawLine(prevX, prevY, nextX, nextY);
		polygon.addPoint(x, y);
		prevX = nextX;
		prevY = nextY;
		
		// 초기좌표와 같은 점이 클릭되었는지 체크
		checkFinish(x, y, g2D);
	}
	
	private void checkFinish(int x, int y, Graphics2D g2D){
		// 초기좌표와 같은 점이 클릭되었는지 체크
		if(x == startX && y == startY){
			finishDrawing(x, y, g2D);
		}
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.draw(g2D);
	}

	@Override
	public void draw(Graphics2D g2D) {
		// TODO Auto-generated method stub
		g2D.drawPolygon(polygon);
	}

	@Override
	public void init(Vector<GShape> shapeVector, JPanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeCursor(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.getAnchors().draw(g2D, getShape().getBounds());
	}
}