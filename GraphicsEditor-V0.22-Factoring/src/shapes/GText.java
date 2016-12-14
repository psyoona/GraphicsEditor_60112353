package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.GConstants.EDrawingType;
import sycom.GSwap;

public class GText extends GShape implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private GSwap swap;
	private JPanel panel;
	private JTextField textField;
	
	// getter & setter
	public JPanel getPanel() { return panel; }
	public void setPanel(JPanel panel) { this.panel = panel; }
	public JTextField getTextField() { return textField; }
	public void setTextField(JTextField textField) { this.textField = textField; }

	// Constructor
	public GText(){
		super(EDrawingType.TEXT, new Rectangle());
		swap = new GSwap();
		panel = new JPanel();
		textField = new JTextField();
	}
	
	@Override
	public void setOrigin(int x, int y, Graphics2D g2D) {
		swap.setX1(x);
		swap.setY1(y);
		panel.setLocation(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void addPoint(int x, int y, Graphics2D g2d) { }

	@Override
	public void resize(int x, int y, Graphics2D g2d) {
		
	}
	
	public void draw(Graphics2D g2D){
		
		g2D.drawString("Hellow World!", (int)swap.getX1(), (int)swap.getY1());
	}

	@Override
	public void move(int x, int y) {
		
	}

	@Override
	public void finish(int x, int y, Graphics2D g2D) {
		panel.setSize(x - (int)swap.getX1(), y - (int)swap.getY1());
		panel.add(textField);
		draw(g2D);
	}

	@Override
	public void changeLineColor(Color lineColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFillColor(Color fillColor, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotate(int x, int y, Graphics2D g2D) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void activateAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAnchor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public GShape newClone() {
		// TODO Auto-generated method stub
		return new GText();
	}

}
