package examples.mvc.local.duplex;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.PropertyListenerRegistrar;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class AnOval implements Oval, PropertyListenerRegistrar {
	int x, y, width, height;
	boolean filled;
	Color color;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public AnOval (int initX, int initY, int initWidth, int initHeight) {
		x = initX; 
		y = initY;
		width = initWidth;
		height = initHeight;	
	}
	public int getX() {return x;}
	public void setX(int newX) {
		x = newX;
		propertyChangeSupport.firePropertyChange("x", null, x);
	}
	public int getY() { return y; }
	public void setY(int newY) {
		y = newY;
		propertyChangeSupport.firePropertyChange("y", null, y);

	}
	public int getWidth() {return width;}
	public void setWidth(int newVal) {
		width = newVal;
		propertyChangeSupport.firePropertyChange("width", null, width);
	}
	public int getHeight() {return height;}
	public void setHeight(int newHeight) {
		height = newHeight;
		propertyChangeSupport.firePropertyChange("height", null, newHeight);
	}	
	public boolean isFilled() {
		return filled;
	}
	public void setFilled(boolean newVal) {
		filled = newVal;
		propertyChangeSupport.firePropertyChange("filled", null, filled);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color newVal) {
		color = newVal;
		propertyChangeSupport.firePropertyChange("color", null, color);

	}
	public static void main(String args[]) {
		Oval oval = new AnOval(10, 10, 20, 20);
		oval.setColor(Color.BLUE);
		oval.setFilled(true);
		bus.uigen.ObjectEditor.edit (oval);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
}
