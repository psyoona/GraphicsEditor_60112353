package constants;

import java.awt.Cursor;

import shapes.GCross;
import shapes.GCursorList;
import shapes.GEllipse;
import shapes.GHeart;
import shapes.GLine;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;
import shapes.GText;

public class GConstants {
	//JFrame Attributes
	public final static String MAINFRAME_TITLE = "Graphics Editor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	public final static String INFOMENU_TITLE = "Info";
	public final static String COLORMENU_TITLE = "Color";
	
	// 메뉴의 스트링을 처리
	public final static String FILE_SAVEAS = "Save as";
	public final static String EDIT_DEL = "Delete";
	public final static String EDIT_UNDO = "UnDo";
	
	// newItem Alert Message
	public final static String NOTICE = "알림창";
	public final static String SAVEMESSAGE = "변경 내용을 제목 없음에 저장하시겠습니까?";
	public final static String NOTTHING = "그림판에 그려진 도형이 없습니다.";
	public final static String PAINTTITLE = "Graphics Editor";
	
	// Info Class Message
	public final static String MAKER = "만든이";
	public final static String MADE = "Made by : Seyoon Park";
	public final static String CLASSNAME = "Advanced objct-oriented programming";
	public final static String PERIOD = "Operation Period : 2016-09-01 ~ 2016-12-15";
	public final static String OKMSG = "확인";
	
	public int widthSize;
	public int heightSize;
	
	public enum EAnchors {
		NN(new GCursorList().getNResize()), 
		NE(new GCursorList().getNEResize()), 
		NW(new GCursorList().getNWResize()), 
		SS(new GCursorList().getSResize()), 
		SE(new GCursorList().getSEResize()), 
		SW(new GCursorList().getSWResize()), 
		EE(new GCursorList().getEResize()), 
		WW(new GCursorList().getWResize()), 
		RR(new GCursorList().getRecursiveCursor()), 
		MM(new GCursorList().getMoveCursor());
		
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
		TP, NP, transforming, TEXT;
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
		select("Select All"),
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
	
	public static enum EColorMenuItem {
		fillColor("fill Color"),
		lineColor("line Color");
		
		private String color;
		private EColorMenuItem(String color){
			this.color = color;
		}
		
		public String getColor(){ return this.color; }
	}
	
	public static enum EToolBarButton{
		rectangle("rsc/rectangle.jpg", "rsc/rectangleSLT.jpg", new GRectangle()),
		ellipse("rsc/ellipse.jpg", "rsc/ellipseSLT.jpg", new GEllipse()),
		line("rsc/line.jpg", "rsc/lineSLT.jpg", new GLine()),
		polygon("rsc/polygon.jpg", "rsc/polygonSLT.jpg", new GPolygon()),
		heart("rsc/heart.jpg", "rsc/heartSLT.jpg", new GHeart()),
		text("rsc/text.jpg", "rsc/textSLT.jpg", new GText()),
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