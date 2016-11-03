package constants;

public class Constants {
	//JFrame Attributes
	public final static String MAINFRAME_TITLE = "Graphics Editor";
	public final static String FILEMENU_TITLE = "File";
	public final static String EDITMENU_TITLE = "Edit";
	public final static String INFOMENU_TITLE = "Info";
	
	// 메뉴의 스트링을 처리
	public final static String FILE_SAVEAS = "Save as";
	public final static String EDIT_DEL = "Delete";
	public final static String EDIT_UNDO = "UnDo";
	
	public final static String RECTANGLE = "Rectangle";
	public final static String ELLIPSE = "Ellipse";
	public final static String LINE = "Line";
	public final static String POLYGON = "Polygon";
	
	// 방법2 : Enumalation Type
	// 과제 : 스캐너를 해봐라?
	public static enum EMainFrame{
		X(100),
		Y(100),
		W(400),
		H(600);
		
		// 클래스의 형태
		private int value;
		private EMainFrame(int value){
			this.value = value;
		}
		
		public int getValue(){ return this.value; }
		// 클래스의 형태 끝
		
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
		private EFileMenuItem(String text){
			this.text = text;
		}
		
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
		private EEditMenuItem(String text){
			this.text = text;
		}
		
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
		rectangle("rsc/rectangle.jpg", "rsc/rectangleSLT.jpg", RECTANGLE),
		ellipse("rsc/ellipse.jpg", "rsc/ellipseSLT.jpg", ELLIPSE),
		line("rsc/line.jpg", "rsc/lineSLT.jpg", LINE),
		polygon("rsc/polygon.jpg", "rsc/polygonSLT.jpg", POLYGON);
		
		private String iconName;
		private String selectedIconName;
		
		private EToolBarButton(String iconName, String selectedIconName, String name){
			this.iconName = iconName;
			this.selectedIconName = selectedIconName;
		}
		
		public String getIconName(){
			return this.iconName;
		}
		
		public String getSelectedIconNames(){
			return this.selectedIconName;
		}
	}
}