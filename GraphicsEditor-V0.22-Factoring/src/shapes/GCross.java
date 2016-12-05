package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

public class GCross extends GShape {
	private static final long serialVersionUID = 1L;
	private Vector<GShape> shapeVector;
	JPanel panel;
	public GCursorList cursorList;

	public GCross() {
		super(EDrawingType.CHOICE, new Rectangle(0, 0, 0, 0));
		cursorList = new GCursorList();
	}

	public void init(Vector<GShape> shapeVector, JPanel panel) {
		this.shapeVector = new Vector<GShape>();
		this.shapeVector = shapeVector;
		this.panel = panel;
	}
	
	public void changeCursor(int x, int y, Graphics2D g2D){
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y, g2D);
			if(eAnchor != null){
				switch(eAnchor){
				case NN: panel.setCursor(cursorList.getnResizeCursor()); break;
				case NE: panel.setCursor(cursorList.getNeResizeCursor()); break;
				case NW: panel.setCursor(cursorList.getNwResizeCursor()); break;
				case SS: panel.setCursor(cursorList.getsResizeCursor()); break;
				case SW: panel.setCursor(cursorList.getSwResizeCursor());break;
				case SE: panel.setCursor(cursorList.getSeResizeCursor()); break;
				case EE: panel.setCursor(cursorList.geteResizeCursor()); break;
				case WW: panel.setCursor(cursorList.getwResizeCursor()); break;
				case RR: panel.setCursor(cursorList.getRecursiveCursor()); break;
				case MM: panel.setCursor(cursorList.getMoveCursor()); break;
				default: break;
				}
								
				if(shape.bSelected == true || eAnchor == EAnchors.MM){
					// 도형이 겹치는 경우에
					// 선택된 도형에 대해서 아이콘 변경이 실행될 수 있도록 해준다.
					return ;
				}
			}else{
				panel.setCursor(cursorList.getDefaultCursor());
			}
		}
	}

		
	public void delPoint(int x, int y, Graphics2D g2D){
		// bSelected가 true이면 모두 false로 바꾼다.
		for(GShape shape : this.shapeVector){
			if(shape.bSelected == true){
				shape.clickShape(x, y, g2D);
				shape.bSelected = false;
			}
		}
	}
	
	public void activatePoint(int x, int y, Graphics2D g2D){
		for(GShape shape : this.shapeVector){
			if(shape.contains(x, y)){
				shape.clickShape(x, y, g2D);				
				shape.bSelected = true;
				break;
			}
		}
	}

	
	public void clickShape(int x, int y, Graphics2D g2D) {
		delPoint(x, y, g2D);
		activatePoint(x, y, g2D);
		panel.setCursor(cursorList.getMoveCursor());
	}

	public void initResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public void keepResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public void finishResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public void initTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int x, int y,  Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
