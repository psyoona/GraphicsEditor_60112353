package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import constants.GConstants.EDrawingType;

public class GCross extends GShape {
	private static final long serialVersionUID = 1L;
	JPanel panel;
	public GCursorList cursorList;
	
	private Rectangle rectangle;
	
	public GCross() {
		super(EDrawingType.transforming, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}

//	public void init(Vector<GShape> shapeVector, JPanel panel) {
//		this.shapeVector = new Vector<GShape>();
//		this.shapeVector = shapeVector;
//		this.panel = panel;
//	}
//	
//	public void changeCursor(int x, int y, Graphics2D g2D){
//		for(GShape shape : this.shapeVector){
//			EAnchors eAnchor = shape.contains(x, y, g2D);
//			if(eAnchor != null){
//				switch(eAnchor){
//				case NN: panel.setCursor(cursorList.getNResize()); break;
//				case NE: panel.setCursor(cursorList.getNEResize()); break;
//				case NW: panel.setCursor(cursorList.getNWResize()); break;
//				case SS: panel.setCursor(cursorList.getSResize()); break;
//				case SW: panel.setCursor(cursorList.getSWResize());break;
//				case SE: panel.setCursor(cursorList.getSEResize()); break;
//				case EE: panel.setCursor(cursorList.getEResize()); break;
//				case WW: panel.setCursor(cursorList.getWResize()); break;
//				case RR: panel.setCursor(cursorList.getRecursiveCursor()); break;
//				case MM: panel.setCursor(cursorList.getMoveCursor()); break;
//				default: break;
//				}
//								
//				if(shape.bSelected == true || eAnchor == EAnchors.MM){
//					// 도형이 겹치는 경우에
//					// 선택된 도형에 대해서 아이콘 변경이 실행될 수 있도록 해준다.
//					return ;
//				}
//			}else{
//				panel.setCursor(cursorList.getDefaultCursor());
//			}
//		}
//	}
//
//		
//	public void delPoint(int x, int y, Graphics2D g2D){
//		// bSelected가 true이면 모두 false로 바꾼다.
//		for(GShape shape : this.shapeVector){
//			if(shape.bSelected == true){
//				shape.clickShape(x, y, g2D);
//				shape.bSelected = false;
//			}
//		}
//	}
//	
//	public void activatePoint(int x, int y, Graphics2D g2D){
//		for(GShape shape : this.shapeVector){
//			if(shape.contains(x, y)){
//				shape.clickShape(x, y, g2D);				
//				shape.bSelected = true;
//				break;
//			}
//		}
//	}
//
//	
//	public void clickShape(int x, int y, Graphics2D g2D) {
//		delPoint(x, y, g2D);
//		activatePoint(x, y, g2D);
//		panel.setCursor(cursorList.getMoveCursor());
//	}

	public void initResizing(int x, int y, Graphics2D g2D) {
		
	}

	public void keepResizing(int x, int y, Graphics2D g2D) {
		
	}

	public void finishResizing(int x, int y, Graphics2D g2D) {
		
	}

	public void initTransforming(int x, int y, Graphics2D g2D) {
		
	}

	public void keepTransforming(int x, int y, Graphics2D g2D) {
		
	}

	public void finishTransforming(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		
	}

	@Override
	public void setPoint(int x, int y) {
		
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {
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
