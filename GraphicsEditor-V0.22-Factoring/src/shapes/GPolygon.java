package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;

import constants.GConstants.EDrawingType;

public class GPolygon extends GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Polygon polygon;
	
	private int prevX, prevY, nextX, nextY;
	private Color lineColor;
	private Color fillColor;
	private boolean check;
	private void setCheck(boolean check){ this.check = check; }
	private boolean getCheck(){ return check; }
	
	// Constructor
	public GPolygon(){
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
		this.lineColor = Color.BLACK;
		this.fillColor = Color.WHITE;
		setCheck(false);
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		polygon.addPoint(x, y);
		startX = x;
		startY = y;
		// 폴리 라인을 그리기 위한 prev x, y 좌표
		prevX = x;
		prevY = y;
	}
	
	@Override
	public void addPoint(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
			nextX = x;
			nextY = y;
			g2D.drawLine(prevX, prevY, nextX, nextY);
			polygon.addPoint(x, y);
			prevX = nextX;
			prevY = nextY;
			return;
		}
	}
	
	@Override
	public void resize(int x, int y, Graphics2D g2D) {
		if (this.getCurrentEAnchor() == null) {
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
		default:
			break;		
		}
		this.setPoint(x, y);
		polygon.invalidate();
	}
	
	@Override
	public void finish(int x, int y, Graphics2D g2d) { }

	public void draw(Graphics2D g2D) {
		if(getCheck()){
			g2D.setColor(fillColor);
			g2D.fillPolygon(this.polygon);
		}
		g2D.setColor(lineColor);
		g2D.drawPolygon(polygon);
		if (getbSelected() == true) {
			this.getAnchors().draw(g2D, getShape().getBounds());
		}
	}
	
	@Override
	public void move(int x, int y) {
		for(int i=0; i<polygon.npoints; i++){
			this.polygon.xpoints[i] += x - this.px;
			this.polygon.ypoints[i] += y - this.py;
		}
		this.setPoint(x, y);
		polygon.invalidate();
	}
	
	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2D) {
		this.lineColor = lineColor;
		draw(g2D);
	}

	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2D) {
		this.fillColor = fillColor;
		setCheck(true);
		draw(g2D);
	}

	@Override
	public void activateAnchor(Graphics2D g2D) {
		this.getAnchors().draw(g2D, getShape().getBounds());
		this.setbSelected(true);
	}
	@Override
	public void rotate(int x, int y) {
		System.out.println("rotate");
		
	}
	@Override
	public void deleteAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}