package examples.mvc.local.simplex;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ATelePointerGlassPane extends JPanel implements MouseListener, MouseMotionListener, AWTEventListener, KeyListener{
	public static int DIAMETER = 10;
	int x = 50;
	int y = 50;
	int clickX, clickY;
	boolean pointerSelected;
	JFrame frame;
	
	public ATelePointerGlassPane(JFrame aFrame) {
		frame = aFrame;
		this.setFocusable(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
//		this.addKeyListener(this);
////		this.addKeyListener(this);
//		long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK
//			    + AWTEvent.MOUSE_EVENT_MASK
//			    + AWTEvent.KEY_EVENT_MASK;
//		Toolkit.getDefaultToolkit().addAWTEventListener( this, eventMask);
		this.setOpaque(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	 
	boolean inTelePointer (MouseEvent event) {
		return event.getX() >= x && event.getX() <= (event.getX() + DIAMETER) &&
				event.getY() >= y && event.getY() <= (event.getY() + DIAMETER);
	}
	@Override
	 public void eventDispatched(AWTEvent event) { 
		System.out.println("foo");
		  if (event instanceof MouseEvent) { 
	            MouseEvent me = (MouseEvent) event; 
	            if (!SwingUtilities.isDescendingFrom(me.getComponent(), frame)) { 
	                return; 
	            } 
	           
	            if (me.getID() == MouseEvent.MOUSE_EXITED && me.getComponent() == frame) { 
	                x = -1; 
	                y = -1;
	            } else { 
	                MouseEvent converted = SwingUtilities.convertMouseEvent(me.getComponent(), me, frame.getGlassPane()); 
	                Point point = converted.getPoint(); 
	                x = point.x;
	                y = point.y;
	            } 
	            repaint(); 
	        } 
	    } 
	
	@Override
	protected void processEvent (AWTEvent anEvent) {
		super.processEvent(anEvent);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (inTelePointer (arg0)) {
			pointerSelected = true;
			clickX = arg0.getX();
			clickY = arg0.getY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		pointerSelected = false;
		
	}
	@Override
	public void mouseDragged(MouseEvent event) {
		if (!pointerSelected) return;
		int incX = event.getX() - clickX;
		int incY = event.getY() - clickY;
		clickX = event.getX();
		clickY = event.getY();
		x += incX;
		y += incY;
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key pressed in relepointer");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	    

}
