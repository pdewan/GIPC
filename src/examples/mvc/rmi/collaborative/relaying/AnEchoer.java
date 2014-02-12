package examples.mvc.rmi.collaborative.relaying;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.models.PropertyListenerRegisterer;


public class AnEchoer implements Echoer {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	String echo = "";
	@Override
	public void echo(String aString) {
		String previousEcho = echo;
		echo = aString;
		propertyChangeSupport.firePropertyChange("echo", previousEcho, echo);
	}
	public String getEcho() {
		return echo;
	}
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);		
	}
}
