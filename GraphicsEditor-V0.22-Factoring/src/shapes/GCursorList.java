package shapes;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public class GCursorList{
	private Cursor recursiveCursor;
	Toolkit tk;
	
	private Cursor crossHairCursor;
	private Cursor defaultCursor;
	private Cursor eResizeCursor;
	private Cursor handCursor;
	private Cursor moveCursor;
	private Cursor nResizeCursor;
	private Cursor nwResizeCursor;
	private Cursor sResizeCursor;
	private Cursor neResizeCursor;
	private Cursor seResizeCursor;
	private Cursor swResizeCursor;
	private Cursor textCursor;
	private Cursor wResizeCursor;
	private Cursor waitCursor;
	
	public Cursor getRecursiveCursor() { return recursiveCursor; }

	public Cursor getCrossHairCursor() { return crossHairCursor; }
	public Cursor getDefaultCursor() { return defaultCursor; }
	public Cursor geteResizeCursor() { return eResizeCursor; }
	public Cursor getHandCursor() { return handCursor; }
	public Cursor getMoveCursor() { return moveCursor; }
	public Cursor getnResizeCursor() { return nResizeCursor; }
	public Cursor getNwResizeCursor() { return nwResizeCursor; }
	public Cursor getsResizeCursor() { return sResizeCursor; }
	public Cursor getNeResizeCursor() { return neResizeCursor; }
	public Cursor getSeResizeCursor() { return seResizeCursor; }
	public Cursor getSwResizeCursor() { return swResizeCursor; }
	public Cursor getTextCursor() { return textCursor; }
	public Cursor getwResizeCursor() { return wResizeCursor; }
	public Cursor getWaitCursor() { return waitCursor; }
	
	public GCursorList(){
		crossHairCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		eResizeCursor = new Cursor(Cursor.E_RESIZE_CURSOR);
		handCursor = new Cursor(Cursor.HAND_CURSOR);
		moveCursor = new Cursor(Cursor.MOVE_CURSOR);
		nResizeCursor = new Cursor(Cursor.N_RESIZE_CURSOR);
		neResizeCursor = new Cursor(Cursor.NE_RESIZE_CURSOR);
		nwResizeCursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
		sResizeCursor = new Cursor(Cursor.S_RESIZE_CURSOR);
		seResizeCursor = new Cursor(Cursor.SE_RESIZE_CURSOR);
		swResizeCursor = new Cursor(Cursor.SW_RESIZE_CURSOR);
		textCursor = new Cursor(Cursor.TEXT_CURSOR);
		wResizeCursor = new Cursor(Cursor.W_RESIZE_CURSOR);
		waitCursor = new Cursor(Cursor.WAIT_CURSOR);
		
		tk = Toolkit.getDefaultToolkit();
		recursiveCursor = tk.createCustomCursor(tk.createImage("rsc/cursor.jpg"), new Point(), null);
		
	}
}
