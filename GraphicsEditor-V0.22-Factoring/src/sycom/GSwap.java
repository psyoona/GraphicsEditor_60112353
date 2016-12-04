package sycom;

import java.io.Serializable;

public class GSwap implements Serializable{
	private static final long serialVersionUID = 1L;
	public double x1, x2, y1, y2;
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