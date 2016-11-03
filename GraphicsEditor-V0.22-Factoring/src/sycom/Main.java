package sycom;

import frame.MainFrame;

public class Main {
	public static void main(String[] args){
		// object name & body created & bound
		MainFrame mainFrame = new MainFrame();
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}
}