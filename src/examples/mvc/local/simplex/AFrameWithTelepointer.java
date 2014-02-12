package examples.mvc.local.simplex;

import java.awt.Frame;
import java.awt.Graphics;

public class AFrameWithTelepointer extends Frame{
	public AFrameWithTelepointer (String title) {
		super(title);
	}
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 0,50, 50);
		g.drawString("hello", 0, 0);
		
	}

}
