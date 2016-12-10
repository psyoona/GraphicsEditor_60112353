package shapes;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public class GCursorList{
	private Cursor recursiveCursor;
	Toolkit tk;
	
	private Cursor crossHairCursor;
	private Cursor defaultCursor;
	private Cursor handCursor;
	private Cursor moveCursor;
	private Cursor EResize;
	private Cursor NResize;
	private Cursor NWResize;
	private Cursor SResize;
	private Cursor NEResize;
	private Cursor SEResize;
	private Cursor SWResize;
	private Cursor WResize;
	private Cursor textCursor;
	private Cursor waitCursor;
	
	public Cursor getRecursiveCursor() { return recursiveCursor; }

	public Cursor getCrossHairCursor() { return crossHairCursor; }
	public Cursor getDefaultCursor() { return defaultCursor; }
	public Cursor getHandCursor() { return handCursor; }
	public Cursor getMoveCursor() { return moveCursor; }
	public Cursor getEResize() { return EResize; }
	public Cursor getNResize() { return NResize; }
	public Cursor getNWResize() { return NWResize; }
	public Cursor getSResize() { return SResize; }
	public Cursor getNEResize() { return NEResize; }
	public Cursor getSEResize() { return SEResize; }
	public Cursor getSWResize() { return SWResize; }
	public Cursor getWResize() { return WResize; }
	public Cursor getTextCursor() { return textCursor; }
	public Cursor getWaitCursor() { return waitCursor; }
	
	public GCursorList(){
		crossHairCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		EResize = new Cursor(Cursor.E_RESIZE_CURSOR);
		handCursor = new Cursor(Cursor.HAND_CURSOR);
		moveCursor = new Cursor(Cursor.MOVE_CURSOR);
		NResize = new Cursor(Cursor.N_RESIZE_CURSOR);
		NEResize = new Cursor(Cursor.NE_RESIZE_CURSOR);
		NWResize = new Cursor(Cursor.NW_RESIZE_CURSOR);
		SResize = new Cursor(Cursor.S_RESIZE_CURSOR);
		SEResize = new Cursor(Cursor.SE_RESIZE_CURSOR);
		SWResize = new Cursor(Cursor.SW_RESIZE_CURSOR);
		textCursor = new Cursor(Cursor.TEXT_CURSOR);
		WResize = new Cursor(Cursor.W_RESIZE_CURSOR);
		waitCursor = new Cursor(Cursor.WAIT_CURSOR);
		
		tk = Toolkit.getDefaultToolkit();
		recursiveCursor = tk.createCustomCursor(tk.createImage("rsc/cursor.jpg"), new Point(), null);
		
	}
}
