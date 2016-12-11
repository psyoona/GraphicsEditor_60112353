package sycom;

import java.io.Serializable;

public class GSwap implements Serializable{
	private static final long serialVersionUID = 1L;
	private double x1, x2, y1, y2;
	private double tempWidth, tempHeight;
	
	// getter & setter
	public double getX1() {	return x1; }
	public void setX1(double x1) { this.x1 = x1; }
	public double getX2() { return x2; }
	public void setX2(double x2) { this.x2 = x2; }
	public double getY1() { return y1; }
	public void setY1(double y1) { this.y1 = y1; }
	public double getY2() { return y2; }
	public void setY2(double y2) { this.y2 = y2; }
	public double getTempWidth() { return tempWidth; }
	public void setTempWidth(double tempWidth) { this.tempWidth = tempWidth; }
	public double getTempHeight() { return tempHeight; }
	public void setTempHeight(double tempHeight) { this.tempHeight = tempHeight; }

	public static void swapX(GSwap obj){
		double temp;
		temp = obj.x1;
		obj.x1 = obj.x2;
		obj.x2 = temp;
	}
	
	public static void swapY(GSwap obj){
		double temp;
		temp = obj.y1;
		obj.y1 = obj.y2;
		obj.y2 = temp;
	}
}