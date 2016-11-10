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
		// �ʱ� ��ǥ 2��
		startX = x;
		startY = y;
		
		// ���� ������ �׸��� ���� prev x, y ��ǥ
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
		
		// �ʱ���ǥ�� ���� ���� Ŭ���Ǿ����� üũ
		checkFinish(x, y, g2D);
	}
	
	private void checkFinish(int x, int y, Graphics2D g2D){
		// �ʱ���ǥ�� ���� ���� Ŭ���Ǿ����� üũ
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