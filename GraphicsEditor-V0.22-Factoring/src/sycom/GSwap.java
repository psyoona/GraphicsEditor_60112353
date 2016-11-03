package sycom;

public class GSwap {
	public int x1, x2, y1, y2;
	public static void swapX(GSwap obj){
		int temp;
		temp = obj.x1;
		obj.x1 = obj.x2;
		obj.x2 = temp;
	}
	
	public static void swapY(GSwap obj){
		int temp;
		temp = obj.y1;
		obj.y1 = obj.y2;
		obj.y2 = temp;
	}
}