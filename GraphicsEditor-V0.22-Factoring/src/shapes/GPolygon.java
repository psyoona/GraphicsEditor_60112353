package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;

import constants.GConstants.EDrawingType;
import sycom.GQuick;

public class GPolygon extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Polygon polygon;
	
	private int prevX, prevY, nextX, nextY;
	private GQuick quick;
	private int[] xArray;
	private int[] yArray;

	public GPolygon(){
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
		quick = new GQuick();
	}
	
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		this.draw(g2D);
		
		this.getAnchors().draw(g2D, getShape().getBounds());
		this.setbSelected(true);
		
		// 최종 그리기를 하면 정렬을 한다.
		xArray = new int[polygon.npoints];
		yArray = new int[polygon.npoints];
		for(int i=0; i<polygon.npoints; i++){
			xArray[i] = polygon.xpoints[i];
			yArray[i] = polygon.ypoints[i];
		}
		quick.sort(xArray, 0, xArray.length-1);
		quick.sort(yArray, 0, yArray.length-1);
	}

	public void draw(Graphics2D g2D) {
		g2D.drawPolygon(polygon);
		if (getbSelected() == true) {
			this.getAnchors().draw(g2D, getShape().getBounds());
		}
	}

	@Override
	public void clickShape(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, getShape().getBounds());
		this.setbSelected(true);
	}

	public void finishTransforming(int x, int y, Graphics2D g2D) {
		this.polygon.invalidate();
	}

	@Override
	public void setOrigin(int x, int y) {
		// TODO Auto-generated method stub
		polygon.addPoint(x, y);

		startX = x;
		startY = y;
		
		// 폴리 라인을 그리기 위한 prev x, y 좌표
		prevX = x;
		prevY = y;
		
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
	public void resize(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
			nextX = x;
			nextY = y;
			g2D.drawLine(prevX, prevY, nextX, nextY);
			polygon.addPoint(x, y);
			prevX = nextX;
			prevY = nextY;
			
			return;
		}
		
		int temp, temp2;
		
		int minX, minY, maxX, maxY;
		int minIndexX, minIndexY, maxIndexX, maxIndexY;
		minX = polygon.xpoints[0];
		minY = polygon.ypoints[0];
		maxX = polygon.xpoints[0];
		maxY = polygon.ypoints[0];
		
		minIndexX = 0;
		minIndexY = 0;
		maxIndexX = 0;
		maxIndexY = 0;
		
		for(int i = 0; i < polygon.npoints; i++){
			if(minX > polygon.xpoints[i]){
				minX = polygon.xpoints[i];
				minIndexX = i;
			}
			if(minY > polygon.ypoints[i]){
				minY = polygon.ypoints[i];
				minIndexY = i;
			}
			if(maxX < polygon.xpoints[i]){
				maxX = polygon.xpoints[i];
				maxIndexX = i;
			}
			if(maxY < polygon.ypoints[i]){
				maxY = polygon.ypoints[i];
				maxIndexY = i;
			}
		}
		
		switch (this.getCurrentEAnchor()) {
		case NN:
			temp = polygon.ypoints[minIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == maxIndexY){
					
				}else{
					polygon.ypoints[i] += y - temp;
				}
			}
			break;
		case NE:
			temp = polygon.xpoints[maxIndexX];
			temp2 = polygon.ypoints[minIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == minIndexX){
					polygon.ypoints[i] += y - temp2;
					
				}else if ( i == maxIndexY ){
					polygon.xpoints[i] += x - temp;
				}else{
					polygon.xpoints[i] += x - temp;
					polygon.ypoints[i] += y - temp2;
				}
			}
			break;
		case NW:
			temp = polygon.xpoints[minIndexX];
			temp2 = polygon.ypoints[minIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == maxIndexX){
					polygon.ypoints[i] += y - temp2;
					
				}else if ( i == maxIndexY ){
					polygon.xpoints[i] += x - temp;
				}else{
					polygon.xpoints[i] += x - temp;
					polygon.ypoints[i] += y - temp2;
				}
			}
			
			break;
		case SS:
			temp = polygon.ypoints[maxIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == minIndexY){
					
				}else{
					polygon.ypoints[i] += y - temp;
				}
			}
			break;
		case SE:
			temp = polygon.xpoints[maxIndexX];
			temp2 = polygon.ypoints[maxIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == minIndexX){
					polygon.ypoints[i] += y - temp2;
					
				}else if ( i == minIndexY ){
					polygon.xpoints[i] += x - temp;
				}else{
					polygon.xpoints[i] += x - temp;
					polygon.ypoints[i] += y - temp2;
				}
			}
			break;
		case SW:
			temp = polygon.xpoints[minIndexX];
			temp2 = polygon.ypoints[maxIndexY];
			for(int i=0; i<polygon.npoints; i++){
				if(i == maxIndexX){
					polygon.ypoints[i] += y - temp2;
					
				}else if ( i == minIndexY ){
					polygon.xpoints[i] += x - temp;
				}else{
					polygon.xpoints[i] += x - temp;
					polygon.ypoints[i] += y - temp2;
				}
			}
			break;
		case EE:
			temp = polygon.xpoints[maxIndexX];
			for(int i=0; i<polygon.npoints; i++){
				if(i == minIndexX){
					
				}else {
					polygon.xpoints[i] += x - temp;
				}
			}
			break;
		case WW:
			temp = polygon.xpoints[minIndexX];
			for(int i=0; i<polygon.npoints; i++){
				if(i == maxIndexX){
					
				}else{
					polygon.xpoints[i] += x- temp;
				}
			}
			break;
		case RR:
			break;
		case MM:
			for(int i=0; i<polygon.npoints; i++){
//				this.polygon.xpoints[i] += x - this.getP1().x;
//				this.polygon.ypoints[i] += y - this.getP1().y;
			}
			break;
		}
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		for(int i=0; i<polygon.npoints; i++){
			this.polygon.xpoints[i] += x - this.px;
			this.polygon.ypoints[i] += y - this.py;
		}
	}
}