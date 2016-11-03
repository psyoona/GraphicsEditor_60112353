package shapes;

import java.awt.Graphics2D;

import constants.Constants;

abstract public class GShape {
	protected int x, y, w, h;
	protected int[] xArray, yArray;
	protected String name;
	
	abstract public String getName();

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setDimension(int w, int h) {
		this.w = w;
		this.h = h;
	}
	

	public int[] getyArray() {
		return yArray;
	}

	public void setyArray(int[] yArray) {
		this.yArray = yArray;
	}

	public int[] getxArray() {
		return xArray;
	}

	public void setxArray(int[] xArray) {
		this.xArray = xArray;
	}
	
	// 그리는 과정을 보여줄 메소드
	public void draw(Graphics2D g2D, int startX, int startY, int width, int height) {
		// TODO Auto-generated method stub
		GShape figure;
		try {
			if(this.getName().equals(Constants.RECTANGLE)){
				figure = new GRectangle();
				figure.draw(g2D, startX, startY, width, height);
			}else if(this.getName().equals(Constants.ELLIPSE)){
				figure = new GEllipse();
				figure.draw(g2D, startX, startY, width, height);
			}else if(this.getName().equals(Constants.LINE)){
				figure = new GLine();
				figure.draw(g2D, startX, startY, width, height);
			}else if(this.getName().equals(Constants.POLYGON)){
				figure = new GPolygon();
				figure.draw(g2D, startX, startY, width, height);
			}else{
				
			}
			
		}catch(NullPointerException Ne){
			System.out.println("Null Pointer Exception");
		}catch(StackOverflowError se){
			System.out.println("Stack over Flow");
		}catch(Exception e){
			System.out.println("Exception e");
		}
		
	}
}
