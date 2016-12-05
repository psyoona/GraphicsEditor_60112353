package constants;

import java.awt.Cursor;

import shapes.GCross;
import shapes.GEllipse;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;

public class GConstants {
	//JFrame Attributes
	public final static String MAINFRAME_TITLE = "Graphics Editor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	public final static String INFOMENU_TITLE = "Info";
	
	// 메뉴의 스트링을 처리
	public final static String FILE_SAVEAS = "Save as";
	public final static String EDIT_DEL = "Delete";
	public final static String EDIT_UNDO = "UnDo";
	
	public enum EAnchors {
		NN(new Cursor(Cursor.N_RESIZE_CURSOR)), 
		NE(new Cursor(Cursor.NE_RESIZE_CURSOR)), 
		NW(new Cursor(Cursor.NW_RESIZE_CURSOR)), 
		SS(new Cursor(Cursor.S_RESIZE_CURSOR)), 
		SE(new Cursor(Cursor.SE_RESIZE_CURSOR)), 
		SW(new Cursor(Cursor.SW_RESIZE_CURSOR)), 
		EE(new Cursor(Cursor.E_RESIZE_CURSOR)), 
		WW(new Cursor(Cursor.W_RESIZE_CURSOR)), 
		RR(new Cursor(Cursor.HAND_CURSOR)), 
		MM(new Cursor(Cursor.MOVE_CURSOR));
		
		private Cursor cursor;
		private EAnchors(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() { return this.cursor; }
	};
	
	// Line size
	public static final int LINE_BOX_SIZE = 2;
	
	public static enum EMainFrame{
		X(100), Y(100),	W(400),	H(600);
		
		private int value;
		private EMainFrame(int value){
			this.value = value;
		}
		
		public int getValue(){ return this.value; }
	}
	
	public static enum EDrawingType {
		TP, NP, CHOICE;
	}
	
	public static enum EFileMenuItem {
		newItem("New"), 
		open("Open"), 
		close("Close"), 
		save("Save"), 
		saveAs(FILE_SAVEAS), 
		print("Print"),
		exit("Exit");
		
		private String text;
		private EFileMenuItem(String text){	this.text = text; }
		
		public String getText(){ return this.text; }
	}
	
	public static enum EEditMenuItem {
		cut("Cut"),
		copy("Copy"),
		paste("Paste"),
		delete("Delete"),
		redo("ReDo"),
		undo(EDIT_UNDO),
		group("Group"),
		ungroup("Ungroup");
		
		private String text;
		private EEditMenuItem(String text){	this.text = text; }
		
		public String getText(){ return this.text; }
	}
	
	public static enum EInfoMenuItem {
		attribute("Attribute"),
		info("Info");
		
		private String text;
		private EInfoMenuItem(String text){
			this.text = text;
		}
		
		public String getText(){ return this.text; }
	}
	
	public static enum EToolBarButton{
		rectangle("rsc/rectangle.jpg", "rsc/rectangleSLT.jpg", new GRectangle()),
		ellipse("rsc/ellipse.jpg", "rsc/ellipseSLT.jpg", new GEllipse()),
		line("rsc/line.jpg", "rsc/lineSLT.jpg", new GLine()),
		polygon("rsc/polygon.jpg", "rsc/polygonSLT.jpg", new GPolygon()),
		cross("rsc/cross.jpg", "rsc/crossSLT.jpg", new GCross());
		
		private String iconName;
		private String selectedIconName;
		private GShape shape;
		
		private EToolBarButton(String iconName, String selectedIconName, 
				GShape shape){
			this.iconName = iconName;
			this.selectedIconName = selectedIconName;
			this.shape = shape;
		}
		
		public String getIconName(){ return this.iconName; }
		public String getSelectedIconNames(){ return this.selectedIconName; }
		public GShape getShape() { return this.shape; }
	}
}