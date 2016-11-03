package sycom;

public class Swap {
	public int x1, x2, y1, y2;
	public static void swapX(Swap obj){
		int temp;
		temp = obj.x1;
		obj.x1 = obj.x2;
		obj.x2 = temp;
	}
	
	public static void swapY(Swap obj){
		int temp;
		temp = obj.y1;
		obj.y1 = obj.y2;
		obj.y2 = temp;
	}
}